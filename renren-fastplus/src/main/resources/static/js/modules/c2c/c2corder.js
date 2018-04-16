$(function () {

    var colModel = [], toolbarView = false, state = GetQueryString('state');

    // 待确认
    if (state == 11) {
        vm.q.state = 11;
        toolbarView = true;
        colModel = [
            {label: 'ID', name: 'id', index: 'id', width: 30, key: true},
            {label: '义工姓名', name: 'volunteerName', width: 50},
            {label: 'C2C订单编号', name: 'c2cOrderNo', width: 100},
            {label: '支付账户手机号', name: 'member.mobile', width: 80},
            {label: '支付金额', name: 'totalAmount', width: 50, cellattr: function() {return "style='color: blue'"}},
            {label: '币种', name: 'productOrder.priceUnit', width: 50},
            {label: '相互保障订单编号', name: 'orderNo', width: 100},
            {label: '创建时间', name: 'createdAt', width: 100},
            {label: '更新时间', name: 'updatedAt', width: 100},
            {label: '支付时间', name: 'payedTime', width: 100},
            {label: '支付方式', name: 'paymentName', width: 60, cellattr: function() {return "style='color: blue'"}},
            {label: '附加码', name: 'additionalCode', width: 50, cellattr: function() {return "style='color: red; font-size:18px;'"}},
            {label: '订单状态', name: 'stateDesc', width: 80},
        ];
    } else { // 全部列表
        colModel = [
            {label: 'ID', name: 'id', index: 'id', width: 30, key: true},
            {label: '义工姓名', name: 'volunteerName', width: 50},
            {label: 'C2C订单编号', name: 'c2cOrderNo', width: 100},
            {label: '支付账户手机号', name: 'member.mobile', width: 80},
            {label: '支付金额', name: 'totalAmount', width: 50, cellattr: function() {return "style='color: blue'"}},
            {label: '币种', name: 'productOrder.priceUnit', width: 50},
            {label: '相互保障订单编号', name: 'orderNo', width: 100},
            {label: '创建时间', name: 'createdAt', width: 100},
            {label: '更新时间', name: 'updatedAt', width: 100},
            {label: '支付时间', name: 'payedTime', width: 100},
            {label: '支付方式', name: 'paymentName', width: 60, cellattr: function() {return "style='color: blue'"}},
            {label: '附加码', name: 'additionalCode', width: 50, cellattr: function() {return "style='color: red; font-size:18px;'"}},
            {label: '订单状态', name: 'stateDesc', width: 80},
        ];
    }

    // 列表加载
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/c2c/order/list',
        postData: {'state': state}, // 0:待付款, 1:支付超时, 2:已支付, 3:退款完成, 4:已完成, 10:撤单退款中
        datatype: "json",
        toolbar: [toolbarView, "top"],
        colModel: colModel,
        viewrecords: true,
        height: '100%',
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        multiboxonly:true, // 单选
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
            // 隐藏全选复选框
            $("#cb_jqGrid").hide();
        }
    });

    /* 时间区间选择 */
    // 支付时间区间选择
    $('#paid_time').daterangepicker({
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
    $('#paid_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#paid_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.paidStartTime = null;
        vm.q.paidEndTime = null;
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
            state: null,            // 订单状态
            mobile: null,           // 支付账户手机号
            c2cOrderNo: null,       // C2C订单号
            orderNo: null,          // 互相保障订单号
            paymentName: null,      // 支付方式
            // coinInfo: null,      // todo 支付币种
            additionalCode: null,   // 附加码
            paidStartTime: null,    // 支付开始时间
            paidEndTime: null,      // 支付结束时间
            createdStartTime: null, // 创建开始时间
            createdEndTime: null    // 创建结束时间
        },
        showList: true,
        title: null,
        c2cOrder: {
            member: {
                id: null,
                mobile: null
            },
            productOrder: {
                id: null,
                priceUnit: null
            }
        },
        // 支付方式options
        paymentNameOptions: [
            {text: '全部', value: null },
            {text: '银行卡支付', value: 'bankpay'},
            {text: '微信支付', value: 'weixinpay'},
            {text: '支付宝支付', value: 'alipay'}
        ],
        //  订单状态Options prePay(0, "待支付"), overtime(1, "支付超时"), payed(2, "支付完成"), del(3, "删除"), toConfirm(11, "待确认");
        stateOptions: [
            {text: '全部', value: null },
            {text: '待支付', value: '0'},
            {text: '支付超时', value: '1'},
            {text: '支付完成', value: '2'},
            {text: '删除', value: '9'},
            {text: '待确认', value: '11'}
        ],
        // 币种options
        coinOptions: [
            {text: '全部', value: null },
            {text: 'SHE', value: 'SHE'},
            {text: 'CNY', value: 'CNY'},
        ]
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.c2cOrder = {};
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
            var url = vm.c2cOrder.id == null ? "manager/c2c/order/save" : "manager/c2c/order/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.c2cOrder),
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
                    url: baseURL + "manager/c2c/order/delete",
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
            $.get(baseURL + "manager/c2c/order/info/" + id, function (r) {
                vm.c2cOrder = r.c2cOrder;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    "state": vm.q.state,
                    "mobile": vm.q.mobile,
                    "c2cOrderNo": vm.q.c2cOrderNo,
                    "orderNo": vm.q.orderNo,
                    "paymentName": vm.q.paymentName,
                    "additionalCode": vm.q.additionalCode,
                    "paidStartTime": vm.q.paidStartTime,
                    "paidEndTime": vm.q.paidEndTime,
                    "createdStartTime": vm.q.createdStartTime,
                    "createdEndTime": vm.q.createdEndTime
                },
                page: page
            }).trigger("reloadGrid");
        },
        confirmPayment: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('点击确定将此订单操作变更成支付成功。', function () {

                $('body').loading({
                    loadingWidth:240,
                    title:'请稍等',
                    name:'test',
                    discription:'正在确认付款中。。。',
                    direction:'column',
                    type:'origin',
                    originDivWidth:40,
                    originDivHeight:40,
                    originWidth:6,
                    originHeight:6,
                    smallLoading:false,
                    loadingMaskBg:'rgba(0,0,0,0.2)'
                });
                $.ajax({
                    type: "POST",
                    url: baseURL + "manager/c2c/order/confirmPayment",
                    // contentType: "application/json",
                    data: "id=" + id,
                    success: function (r) {
                        removeLoading('test');
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    },
                    error : function(){
                        removeLoading('test');
                    }
                });
            });
        },
        unPaid: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('点击确定将此订单操作变更成待支付状态。', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "manager/c2c/order/unPaid",
                    // contentType: "application/json",
                    data: "id=" + id + "&state=0",
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
        }

    }
});

