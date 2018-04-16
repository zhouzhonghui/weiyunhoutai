$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wybbbatchtransfer/wybbbatchtransfer/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '付款地址', name: 'payaddress', index: 'payaddress', width: 80 }, 			
			{ label: '收款地址', name: 'gatheraddress', index: 'gatheraddress', width: 80 }, 			
			{ label: '付款金额', name: 'amount', index: 'amount', width: 80 },
			{ label: '区块hash', name: 'tranhash', index: 'tranhash', width: 80 }, 			
			{ label: '转账类型', name: 'flag', index: 'flag', width: 80 ,formatter:function(value,options,rowData){
                    if( value==1 ){
                        return 'eth转账';
                    }else{
                        return 'she转账';
                    }
                }},
			{ label: '转账状态', name: 'status', index: 'status', width: 80 ,formatter:function(value,options,rowData){
                    if( value==0 ){
                        return '未发送转账';
                    }else if(value==1){
                        return '转账已发送';
                    }else {
                        return '转账失败';
                    }
                }},
            { label: '创建日期', name: 'createdate', index: 'createdate', width: 80 }
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
        action: baseURL + 'wybbbatchtransfer/wybbbatchtransfer/batchImport?token=' + token,
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            $('body').loading({
                loadingWidth:240,
                title:'请稍等',
                name:'test',
                discription:'正在进行钱包转账。。。',
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
                alert('转账成功！', function(){
                    vm.reload();
                });
            }else{
                alert(r.msg);
            }
        }
    });
    // 定时时间区间选择
    $('#transfer_input').daterangepicker({
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
        vm.q.transferstartDate = start.format('YYYY-MM-DD');
        vm.q.transferendDate = end.format('YYYY-MM-DD');
    });
    $('#transfer_input').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $('#transfer_input').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
        vm.q.transferstartDate = null;
        vm.q.transferendDate = null;
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		wybbBatchtransfer: {},
        q: {
            status: null,
            transferstartDate: null, // 定时开始时间
            transferendDate: null,    // 定时结束时间
			flag:null
        },
        statusOptions: [
            {text: '全部', value: null },
            {text: '转账成功', value: '1'},
            {text: '未转账', value: '0'},
            {text: '转账失败', value: '2'},
        ],
        flagOptions: [
            {text: '全部', value: null },
            {text: 'eth类型', value: '1'},
            {text: 'she类型', value: '2'}
        ]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.wybbBatchtransfer = {};
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
			var url = vm.wybbBatchtransfer.id == null ? "wybbbatchtransfer/wybbbatchtransfer/save" : "wybbbatchtransfer/wybbbatchtransfer/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.wybbBatchtransfer),
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
				    url: baseURL + "wybbbatchtransfer/wybbbatchtransfer/delete",
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
			$.get(baseURL + "wybbbatchtransfer/wybbbatchtransfer/info/"+id, function(r){
                vm.wybbBatchtransfer = r.wybbBatchtransfer;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});