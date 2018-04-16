$(function () {

    // 列表加载
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/c2c/productOrder/list',
        datatype: "json",
        toolbar: [true, "top"],
        colModel: [
            {label: 'id', name: 'id', width: 50, key: true},
            {label: '产品', name: 'product.name', width: 160},
            {label: '义工', name: 'volunteer.name', width: 80},
            {label: '总金额', name: 'totalAmount', width: 80},
            {label: '价格', name: 'price', width: 80},
            {label: '数量', name: 'number', width: 80},
            {label: '价格单位', name: 'priceUnit', width: 80},
            {label: '创建时间', name: 'createdAt', width: 120},
            {label: '更新时间', name: 'updatedAt', width: 120}
        ],
        viewrecords: true,
        height: "100%",
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
            $("#toolbar_btn").appendTo("#t_jqGrid");
        }
    });

    /* 时间区间选择 */
    // 创建时间区间选择
    $('#created_time').daterangepicker({
        autoUpdateInput: false,
        locale: {
            format: 'YYYY-MM-DD',
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
        }
    }, function (start, end, label) {
        vm.q.createdStartTime = start.format('YYYY-MM-DD');
        vm.q.createdEndTime = end.format('YYYY-MM-DD');
    });
    $('#created_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#created_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.createdStartTime = null;
        vm.q.createdEndTime = null;
    });

    // 获取产品下拉选项
    vm.getProduct();
    // 获取义工下拉选项
    vm.getVelunteer();
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            vId: null,              // 义工ID
            productId: null,        // 产品ID
            createdStartTime: null, // 创建开始时间
            createdEndTime: null    // 创建结束时间
        },
        showList: true,
        title: null,
        totalAmount : "",
        productOrder: {
            priceUnit: null,
            product: {
                id: null
            },
            volunteer: {}
        },

        // 价格单位options
        priceUnitOptions: [
            {text: '全部', value: null},
            {text: 'CNY', value: 'CNY'}
        ],

        // 产品options
        productOptions: [],

        // 义工options
        volunteerOptions: [],
    },
    watch: {//深度 watcher
        'totalAmount': {
            handler: function (val, oldVal) {
                vm.productOrder.totalAmount = val;
                if (vm.productOrder.number == null) {
                    vm.productOrder.price = 0;
                } else {
                    vm.productOrder.price = val / vm.productOrder.number;
                }
            },
            deep: true
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.totalAmount = null;
            vm.productOrder = {
                state: 1,
                priceUnit: null,
                product: {
                    id: null
                },
                volunteer: {
                    id: null
                }
            };
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.productOrder.id == null ? "manager/c2c/productOrder/save" : "manager/c2c/productOrder/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.productOrder),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "manager/c2c/productOrder/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "manager/c2c/productOrder/info/" + id, function (r) {
                vm.productOrder = r.productOrder;
                vm.totalAmount = r.productOrder.totalAmount;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    "vId": vm.q.vId,
                    "productId": vm.q.productId,
                    "createdStartTime": vm.q.createdStartTime,
                    "createdEndTime": vm.q.createdEndTime
                },
                page: page
            }).trigger("reloadGrid");
        },

        chooseProduct :function (productId) {
            var choosedProduct;
            for (var i = 0; i < vm.productOptions.length; i++) {
                if(vm.productOptions[i].value == productId){
                    choosedProduct = vm.productOptions[i];
                }
            }
            vm.productOrder.number = choosedProduct.price;
        },

        getVelunteer: function() {
            vm.volunteerOptions.push({text: '全部', value: null});
            $.get(baseURL + "manager/c2c/volunteer/list/", function (r) {
                var volunteerList = r.page.list;
                for (var i = 0; i < volunteerList.length; i++) {
                    vm.volunteerOptions.push({text: volunteerList[i].name, value: volunteerList[i].id});
                }
            });
        },

        getProduct: function() {
            vm.productOptions.push({text: '全部', value: null, price: null});
            $.get(baseURL + "manager/product/list/", function (r) {
                var productList = r.page.list;
                for (var i = 0; i < productList.length; i++) {
                    vm.productOptions.push({text: productList[i].name, value: productList[i].id, price: productList[i].price});
                }
            });
        }
    }
});