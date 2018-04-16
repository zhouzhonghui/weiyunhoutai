$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wybbscalp/wybbscalp/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '手机号', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '证件号码', name: 'idno', index: 'idno', width: 80 },
			{ label: '用户姓名', name: 'username', index: 'username', width: 80 },
			{ label: '用户地址', name: 'useraddress', index: 'useraddress', width: 80 },
			{ label: '紧急联系人姓名', name: 'ucname', index: 'ucname', width: 80 },
            { label: '紧急联系人手机号', name: 'ucmobile', index: 'ucmobile', width: 80 },
			{ label: '紧急联系人邮箱', name: 'ucemail', index: 'ucemail', width: 80 },
			{ label: '定时时间', name: 'jobtime', index: 'jobtime', width: 80 },
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter:function(value,options,rowData){
                    if( value==0 ){
                        return '未执行';
                    }else{
                        return '执行成功';
                    }
                }
            }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    new AjaxUpload('#uploadFile', {
        action: baseURL + 'wybbscalp/wybbscalp/batchImport?token=' + token,
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            $('body').loading({
                loadingWidth:240,
                title:'请稍等',
                name:'test',
                discription:'正在进行导入。。。',
                direction:'column',
                type:'origin',
                originDivWidth:40,
                originDivHeight:40,
                originWidth:6,
                originHeight:6,
                smallLoading:false,
                loadingMaskBg:'rgba(0,0,0,0.2)'
            });
            if (!(extension && /^(xlsx|xls)$/.test(extension.toLowerCase()))){
                alert('只支持xlsx、xls格式的文件！');
                return false;
            }
        },
        onComplete : function(file, r){
            removeLoading('test');
            if(r.code == 0){
                alert('客户数据成功！', function(){
                    vm.reload();
                });
            }else{
                alert(r.msg);
            }
        }
    });
    /* 时间区间选择 */
    // 定时时间区间选择
    $('#job_input').daterangepicker({
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
        vm.q.jobstartDate = start.format('YYYY-MM-DD');
        vm.q.jobendDate = end.format('YYYY-MM-DD');
    });
    $('#job_input').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#job_input').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.jobstartDate = null;
        vm.q.jobendDate = null;
    });
    //创建时间选择
    $('#import_input').daterangepicker({
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
        vm.q.importstartDate = start.format('YYYY-MM-DD');
        vm.q.importendDate = end.format('YYYY-MM-DD');
    });
    $('#import_input').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#import_input').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.importstartDate = null;
        vm.q.importendDate = null;
    });

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		wybbScalp: {},
        q: {
            status: null,
            jobstartDate: null, // 定时开始时间
            jobendDate: null,    // 定时结束时间
            importstartDate: null, // 导入开始时间
            importendDate: null    // 导入结束时间
        },
        statusOptions: [
            {text: '全部', value: null },
            {text: '执行成功', value: '1'},
            {text: '未执行', value: '0'}
        ]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.wybbScalp = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.wybbScalp.id == null ? "wybbscalp/wybbscalp/save" : "wybbscalp/wybbscalp/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.wybbScalp),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wybbscalp/wybbscalp/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "wybbscalp/wybbscalp/info/"+id, function(r){
                vm.wybbScalp = r.wybbScalp;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData: {
                    "jobstartDate": vm.q.jobstartDate,
                    "jobendDate": vm.q.jobendDate,
                    "importstartDate": vm.q.importstartDate,
                    "importendDate": vm.q.importendDate,
                    "status": vm.q.status
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});