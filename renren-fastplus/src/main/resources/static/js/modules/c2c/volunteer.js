$(function () {

    // 加载列表
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/c2c/volunteer/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '姓名', name: 'name', width: 80},
            {label: '服务订单数', name: 'completeNumber', width: 80},
            {label: '服务成功率', name: 'completeRate', width: 80},
            {label: '在线状态', name: 'online', hidden: true},
            {label: '在线状态', name: 'onlineDesc', width: 80},
            {label: '创建时间', name: 'createdAt', width: 80},
            {label: '更新时间', name: 'updatedAt', width: 80}
        ],
        viewrecords: true,
        toolbar: [true, "top"],
        height: "100%",
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        multiboxonly:true,
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

            $("#cb_jqGrid").hide();
        }
    });

    /* 时间区间选择 */
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

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            name: null,
            online: null,
            createdStartTime: null, // 创建开始时间
            createdEndTime: null    // 创建结束时间
        },
        showList: true,
        title: null,
        volunteer: {
            paymentMethodList: null
        },

        // 在线状态options
        onLineOptions: [
            {text: '全部状态', value: null},
            {text: '在线', value: '1'},
            {text: '离线', value: '0'}
        ],

        paymentData: {
            bankPayData: false,
            weixinPayData: false,
            aliPayData: false
        },

        bankPayShow: false,
        weixinPayShow: false,
        aliPayShow: false,

        paymentType: [],

        bankPay: {},
        weixinPay: {},
        aliPay: {},
    },
    watch: {//深度 watcher
        'paymentType': {
            handler: function (val, oldVal) {
                vm.bankPayShow   = false;
                vm.weixinPayShow = false;
                vm.aliPayShow    = false;
                for (var i = 0; i < vm.paymentType.length; i++) {
                    if (vm.paymentType[i] == "bankpay") {
                        vm.bankPayShow = true;
                    }
                    if (vm.paymentType[i] == "weixinpay") {
                        vm.weixinPayShow = true;
                    }
                    if (vm.paymentType[i] == "alipay") {
                        vm.aliPayShow = true;
                    }
                }

                if($('#uploadWeiXinQR')){
                    vm.uploadImg('#uploadWeiXinQR');
                }
                if($('#uploadAliQR')){
                    vm.uploadImg('#uploadAliQR');
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
            vm.title = "添加义工";
            vm.volunteer = {
                state: 1,
                online: 0,
                completeNumber: 0,
                completeRate: 10
            };
            vm.bankPay = {
                state: 1
            };
            vm.weixinPay = {
                state: 1,
                payQrcode : "../../swagger/images/add_img.png"
            };
            vm.aliPay = {
                state: 1,
                payQrcode : "../../swagger/images/add_img.png"
            };
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改义工信息";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.volunteer.id == null ? "manager/c2c/volunteer/save" : "manager/c2c/volunteer/update";

            vm.volunteer.paymentMethodList = [];

            if (vm.bankPayShow) {
                vm.bankPay.paymentName = 'bankpay';
                vm.volunteer.paymentMethodList.push(vm.bankPay);
            } else if (vm.paymentData.bankPayData){
                vm.bankPay.state = 9;
                vm.volunteer.paymentMethodList.push(vm.bankPay);
            }

            if (vm.weixinPayShow) {
                vm.weixinPay.paymentName = 'weixinpay';
                vm.volunteer.paymentMethodList.push(vm.weixinPay);
            } else if (vm.paymentData.weixinPayData){
                vm.weixinPay.state = 9;
                vm.volunteer.paymentMethodList.push(vm.weixinPay);
            }

            if (vm.aliPayShow) {
                vm.aliPay.paymentName = 'alipay';
                vm.volunteer.paymentMethodList.push(vm.aliPay);
            } else if (vm.paymentData.aliPayData){
                vm.aliPay.state = 9;
                vm.volunteer.paymentMethodList.push(vm.aliPay);
            }

            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.volunteer),
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
                    url: baseURL + "manager/c2c/volunteer/delete",
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
            $.get(baseURL + "manager/c2c/volunteer/info/" + id, function (r) {
                vm.volunteer = r.volunteer;
                var paymentMethodList = r.volunteer.paymentMethodList;
                vm.bankPay = {
                    state: 1
                };
                vm.weixinPay = {
                    state: 1,
                    payQrcode : "../../swagger/images/add_img.png"
                };
                vm.aliPay = {
                    state: 1,
                    payQrcode : "../../swagger/images/add_img.png"
                };
                for (var i = 0; i < paymentMethodList.length; i++) {

                    var paymentMethod = paymentMethodList[i];
                    var paymentType   = paymentMethod.paymentName;

                    if (paymentType == "bankpay")   {
                        vm.paymentData.banPayData = true;
                        vm.bankPay = paymentMethod;
                        vm.bankPay.state = paymentMethod.state;
                    } else {
                        vm.paymentData.banPayData = false;
                    }

                    if (paymentType == "weixinpay") {
                        vm.paymentData.weixinPayData = true;
                        vm.weixinPay = paymentMethod;
                        vm.weixinPay.state = paymentMethod.state;
                        vm.weixinPay.payQrcode = paymentMethod.payQrcode;
                    } else {
                        vm.paymentData.weixinPayData = false;
                    }
                    if (paymentType == "alipay")    {
                        vm.paymentData.aliPayData = true;
                        vm.aliPay = paymentMethod;
                        vm.aliPay.state = paymentMethod.state;
                        vm.aliPay.payQrcode = paymentMethod.payQrcode;
                    } else {
                        vm.paymentData.aliPayData = false;
                    }

                    vm.paymentType.push(paymentType);

                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.bankPayShow = false;
                vm.weixinPayShow = false;
                vm.aliPayShow = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');

            $("#jqGrid").jqGrid('setGridParam', {

                postData: {
                    "name": vm.q.name,
                    "online": vm.q.online,
                    "createdStartTime": vm.q.createdStartTime,
                    "createdEndTime": vm.q.createdEndTime
                },
                page: page
            }).trigger("reloadGrid");
        },

        // 修改义工在线状态
        modifyOnLine: function (event) {
            var data = getSelectedData(), tip, online;
            if (data.online == 1) {
                tip = '点击确定将义工【' + data.name + '】下线。';
                online = 0;
            } else {
                tip = '点击确定将义工【' + data.name + '】上线。';
                online = 1;
            }
            confirm(tip, function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "manager/c2c/volunteer/modifyOnLine",
                    // contentType: "application/json",
                    data: "id=" + data.id + "&onLine=" + online,
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

        // 图片上传
        uploadImg :function (upName) {
            new AjaxUpload(upName, {
                action: baseURL + 'manager/c2c/volunteer/uploadFile',
                name: 'file',
                data: {"token": token},
                autoSubmit: true,
                responseType: "json",
                onSubmit: function (file, extension) {
                    if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                        alert('只支持jpg、png、gif格式的图片！');
                        return false;
                    }
                },
                onComplete: function (file, r) {
                    if (r.code == 0) {
                        if(upName == "#uploadWeiXinQR"){
                            vm.weixinPay.payQrcode = r.imgPath;
                        }
                        if(upName == "#uploadAliQR"){
                            vm.aliPay.payQrcode = r.imgPath;
                        }
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }
    }
});