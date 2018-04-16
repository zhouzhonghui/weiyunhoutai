$(function () {

    // 列表加载
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/policy/policyAccount/list',
        datatype: "json",
        toolbar: [true, "top"],
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '会员手机号', name: 'member.mobile', width: 80},
            {label: '被保险人姓名', name: 'lifePolicy.insuredPeopleName', width: 80},
            {label: '可用金额', name: 'availableAmount', width: 80},
            {label: '冻结金额', name: 'freezeAmount', width: 80},
            {label: '创建时间', name: 'createdAt', width: 80},
        ],
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
            root    : "page.list",
            page    : "page.currPage",
            total   : "page.totalPage",
            records : "page.totalCount"
        },
        prmNames: {
            page  : "page",
            rows  : "limit",
            order : "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
            // 加载toolbar
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
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            mobile: null,
            createdStartTime: null, // 创建开始时间
            createdEndTime: null    // 创建结束时间
        },
        showList: true,
        title: null,
        policyAccount: {
            member: {
                id: null,
                mobile: null
            },
            lifePolicy: {
                id: null,
                policyNo: null,
                insuredPeopleName: null
            },
            coinInfo: {
                id: null,
                nameEN: null,
                nameCN: null
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
            vm.policyAccount = {};
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
            vm.title = "查看保单账户信息";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.policyAccount.id == null ? "manager/policy/policyAccount/save" : "manager/policy/policyAccount/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.policyAccount),
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
                    url: baseURL + "manager/policy/policyaccount/delete",
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
            $.get(baseURL + "manager/policy/policyAccount/info/" + id, function (r) {
                vm.policyAccount = r.policyAccount;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'mobile': vm.q.mobile, "createdStartTime": vm.q.createdStartTime, "createdEndTime": vm.q.createdEndTime},
                page: page
            }).trigger("reloadGrid");
        }
    }
});