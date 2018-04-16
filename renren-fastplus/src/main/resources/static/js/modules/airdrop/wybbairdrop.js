$(function () {

    $("#jqAddress").jqGrid({
        url: baseURL + 'sys/airdrop/wybbairdrop/queryShe',
        datatype: "json",
        colNames:['SHE地址','SHE金额', 'ETH付款地址', 'ETH金额'],
        colModel: [
            {name: 'sheAddress', width: 80},
            {name: 'sheAmount', width: 50 },
            {name: 'ethAddress', width: 80 },
            {name: 'ethAmount', width: 50 },
        ],
        rowNum: 1,
        viewrecords: true,
        height: 40,
        rownumWidth: 25,
        autowidth:true,
        jsonReader : {
            root: "page.list"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/airdrop/wybbairdrop/list',
        datatype: "json",
        colNames:['id','空投地址', '空投金额', '区块数','交易Hash','空投日期','姓名','空投文件名'],
        colModel: [			
			{ name: 'id', width: 50, key: true },
			{ name: 'address', width: 80 },
			{ name: 'amount', width: 80 },
            { name: 'blocknum', width: 80 },
            { name: 'tranhash', width: 80 },
            { name: 'createdate', width: 80 },
			{ name: 'remark1', width: 80 },
			{ name: 'remark2', width: 80 }
        ],
		viewrecords: true,
        height: 395,
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
        }
        ,
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });

    new AjaxUpload('#uploadFile', {
        action: baseURL + 'sys/airdrop/wybbairdrop/batchImport?token=' + token,
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            $('body').loading({
                loadingWidth:240,
                title:'请稍等',
                name:'test',
                discription:'正在进行空投。。。',
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
                alert('空投成功！', function(){
                    vm.reload();
                });
            }else{
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
        wybbFiles : "",
		wybbAirdrop: {

		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		queryByConditions:function () {
			 var date=$("#date").val();//获取前端送过来的日期
			 var filename=$("#filename").val();//获取前端送过来的文件名
             if(date ==null && filename==null){
                 vm.reload();
             }else {
                 vm.showList = true;
                 var page = $("#jqGrid").jqGrid('getGridParam','page');
                 $("#jqGrid").jqGrid('setGridParam',{
                     url: baseURL + 'sys/airdrop/wybbairdrop/queryByDateAndName?token=' + token,
                     page:page,
                     postData:{'date':date,"filename":filename},
                 }).trigger("reloadGrid");
             }
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.wybbAirdrop = {};
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
			var url = vm.wybbAirdrop.id == null ? "sys/airdrop/wybbairdrop/save" : "sys/airdrop/wybbairdrop/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.wybbAirdrop),
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
				    url: baseURL + "sys/airdrop/wybbairdrop/delete",
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
			$.get(baseURL + "sys/airdrop/wybbairdrop/info/"+id, function(r){
                vm.wybbAirdrop = r.wybbAirdrop;
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