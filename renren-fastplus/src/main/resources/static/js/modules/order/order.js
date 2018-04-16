$(function () {

    var colModel = [], state = GetQueryString('state');

    // 已付款订单管理
    if (state == 2) {
        colModel = [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '订单类型', name: 'orderTypeDesc', width: 80},
            {label: '订单号', name: 'orderNo', width: 120},
            {label: '订单总金额', name: 'accruedMoney', width: 60},
            {label: '支付金额', name: 'actualMoney', width: 60},
            {label: '支付状态', name: 'stateDesc', width: 60},
            {label: '参有项目', name: 'product.name', width: 140},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '被保险人姓名', name: 'lifePolicy.insuredPeopleName', width: 80},
            {label: '支付时间', name: 'paidTime', width: 120},
            {label: '创建时间', name: 'createdAt', width: 120},
        ];
    }
    // 未付款订单管理
    if (state == 0) {
        colModel = [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '订单类型', name: 'orderTypeDesc', width: 80},
            {label: '订单号', name: 'orderNo', width: 120},
            {label: '订单总金额', name: 'accruedMoney', width: 60},
            {label: '支付金额', name: 'actualMoney', width: 60},
            {label: '支付状态', name: 'stateDesc', width: 60},
            {label: '参有项目', name: 'product.name', width: 140},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '被保险人姓名', name: 'lifePolicy.insuredPeopleName', width: 80},
            {label: '创建时间', name: 'createdAt', width: 120},
        ];
    }

    // 列表加载
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/order/list',
        postData: {'state': state}, // 0:待付款,1:支付超时,2:已支付,3:退款完成,4:已完成,10:撤单退款中
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
    // 支付时间区间选择
    $('#query_paid_time').daterangepicker({
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
        vm.q.paidStartTime = start.format('YYYY-MM-DD');
        vm.q.paidEndTime = end.format('YYYY-MM-DD');
    });
    $('#query_paid_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#query_paid_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.paidStartTime = null;
        vm.q.paidEndTime = null;
    });

    // 创建时间区间选择
    $('#query_created_time').daterangepicker({
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
    $('#query_created_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#query_created_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.createdStartTime = null;
        vm.q.createdEndTime = null;
    });
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
            orderType: null,
            orderNo: null,
            mobile: null,
            paidStartTime: null,    // 支付开始时间
            paidEndTime: null,      // 支付结束时间
            createdStartTime: null, // 创建开始时间
            createdEndTime: null    // 创建结束时间
        },
        showList: true,
        title: null,
        order: {
            member: {
                id: null,
                mobile: null
            },
            coinInfo: {
                id: null,
                nameEN: null,
                nameCN: null
            },
            product: {
                id: null,
                name: null
            },
            lifePolicy: {
                id: null,
                insuredPeopleName: null
            }
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.order = {};
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
            vm.title = "查看订单详情";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.order.id == null ? "manager/order/save" : "manager/order/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.order),
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
                    url: baseURL + "manager/order/delete",
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
            $.get(baseURL + "manager/order/info/" + id, function (r) {
                vm.order = r.order;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'mobile': vm.q.mobile,
                    'orderType': vm.q.orderType,
                    'orderNo': vm.q.orderNo,
                    "paidStartTime": vm.q.paidStartTime,
                    "paidEndTime": vm.q.paidEndTime,
                    "createdStartTime": vm.q.createdStartTime,
                    "createdEndTime": vm.q.createdEndTime
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});