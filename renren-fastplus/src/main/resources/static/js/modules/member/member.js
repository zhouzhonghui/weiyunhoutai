$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/member/list',
        datatype: "json",
        toolbar: [true, "top"],
        colModel: [
            {label: 'ID', name: 'id', width: 50, key: true},
            {label: '用户名', name: 'username', width: 80},
            {label: '手机号', name: 'mobile', width: 80},
            {label: '姓名', name: 'realname', width: 80},
            {label: '注册时间', name: 'createdAt', width: 80}
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

    // 时间区间选择
    $('#created_time').daterangepicker({
        autoUpdateInput: false,
        locale: {
            format: 'YYYY-MM-DD',
            cancelLabel: 'Clear',
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
        },
    }, function (start, end, label) {
        vm.q.startDate = start.format('YYYY-MM-DD');
        vm.q.endDate = end.format('YYYY-MM-DD');
    });
    $('#created_time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#created_time').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.startDate = null;
        vm.q.endDate = null;
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            mobile: null,
            startDate: null, // 开始时间
            endDate: null    // 结束时间
        },
        showList: true,
        title: null,
        member: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.member = {};
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
            vm.title = "查看会员信息";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.member.id == null ? "manager/member/save" : "manager/member/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.member),
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
                    url: baseURL + "manager/member/delete",
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
            $.get(baseURL + "manager/member/info/" + id, function (r) {
                vm.member = r.member;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'mobile': vm.q.mobile, "startDate": vm.q.startDate, "endDate": vm.q.endDate},
                page: page
            }).trigger("reloadGrid");
        }
    }
});