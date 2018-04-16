$(function () {

    var colModel = [], type = GetQueryString('type');

    // observation: 观察期
    if (type == 'observation') {
        colModel = [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '所属产品', name: 'product.name', width: 120},
            {label: '保单余额', name: 'policyAccount.availableAmount', width: 120},
            {label: '被保险人姓名', name: 'insuredPeopleName', width: 80},
            {label: '被保险人证件号', name: 'insuredPeopleCard', width: 80},
            {label: '保单生效日期', name: 'insuranceStartDate', width: 80},
            {label: '创建时间', name: 'createdAt', width: 80},
        ];
    }
    // effective: 有效
    if (type == 'effective') {
        colModel = [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '所属产品', name: 'product.name', width: 120},
            {label: '保单余额', name: 'policyAccount.availableAmount', width: 120},
            {label: '被保险人姓名', name: 'insuredPeopleName', width: 80},
            {label: '被保险人证件号', name: 'insuredPeopleCard', width: 80},
            {label: '保单生效日期', name: 'insuranceStartDate', width: 80},
            {label: '创建时间', name: 'createdAt', width: 80},
        ]
    }
    // invalid: 失效保单
    if (type == 'invalid') {
        colModel = [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '所属产品', name: 'product.name', width: 120},
            {label: '保单余额', name: 'policyAccount.availableAmount', width: 120},
            {label: '被保险人姓名', name: 'insuredPeopleName', width: 80},
            {label: '被保险人证件号', name: 'insuredPeopleCard', width: 80},
            {label: '保单生效日期', name: 'insuranceStartDate', width: 80},
            {label: '创建时间', name: 'createdAt', width: 80},
        ]
    }

    // 列表加载
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/policy/lifePolicy/list',
        postData: {'type': type},
        datatype: "json",
        toolbar: [true, "top"],
        colModel: colModel,
        viewrecords: true,
        height: '100%',
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
            // 加载toolbar
            $("#toolbar_btn").appendTo("#t_jqGrid");
        }
    });

    /* 时间区间选择 */
    // 保单生效时间区间选择
    $('#insurance_start_time').daterangepicker({
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
        vm.q.insuranceStartTime = start.format('YYYY-MM-DD');
        vm.q.insuranceEndTime   = end.format('YYYY-MM-DD');
    });
    $('#insurance_start_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#insurance_start_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.insuranceStartTime = null;
        vm.q.insuranceEndTime   = null;
    });

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

    vm.getProductOptions();
});

// 获取请求参数
function GetQueryString(param) {
    var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            mobile: null,             // 会员手机号
            productId: null,          // 所属产品
            insuredPeopleName: null,  // 被保险人姓名
            insuredPeopleCard: null,  // 被保险人证件号码
            insuranceStartTime: null, // 保单生效开始时间
            insuranceEndTime: null,   // 保单生效结束时间
            createdStartTime: null,   // 创建开始时间
            createdEndTime: null      // 创建结束时间
        },
        showList: true,
        title: null,
        productOptions: null,
        lifePolicy: {
            member: {
                id: null,
                mobile: null
            },
            product: {
                id: null,
                name: null
            },
            policyAccount: {
                id: null,
                availableAmount: null,
                availableAmountDesc: null
            },
            coinInfo: {
                id: null,
                nameEN: null,
                nameCN: null
            },
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.lifePolicy = {};
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
        view: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "查看保单详情";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.lifePolicy.id == null ? "manager/policy/lifePolicy/save" : "manager/policy/lifePolicy/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.lifePolicy),
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
                    url: baseURL + "manager/policy/lifePolicy/delete",
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
            $.get(baseURL + "manager/policy/lifePolicy/info/" + id, function (r) {
                vm.lifePolicy = r.lifePolicy;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'mobile': vm.q.mobile,
                    'productId': vm.q.productId,
                    'insuredPeopleName': vm.q.insuredPeopleName,
                    'insuredPeopleCard': vm.q.insuredPeopleCard,
                    "insuranceStartTime": vm.q.insuranceStartTime,
                    "insuranceEndTime": vm.q.insuranceEndTime,
                    "createdStartTime": vm.q.createdStartTime,
                    "createdEndTime": vm.q.createdEndTime
                },
                page: page
            }).trigger("reloadGrid");
        },
        // 获取下拉列表下拉列表
        getProductOptions: function () {
            vm.productOptions = [];
            vm.productOptions.push({text: '全部', value: null});
            $.get(baseURL + "manager/product/list/", function (r) {
                var productList = r.page.list;
                for (var i = 0; i < productList.length; i++) {
                    vm.productOptions.push({text: productList[i].name, value: productList[i].id});
                }
            });
            //
            // $.get(baseURL + "manager/product/list", function (r) {
            //     vm.productSelect = r.page.list;
            // });
        }
    }
});