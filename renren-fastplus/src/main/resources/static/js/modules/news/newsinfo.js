$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/newsinfo/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'newsid', index: 'newsid', width: 50, key: true},
            {label: '标题（后台）', name: 'backtitle', index: 'backtitle', width: 80},
            {
                label: '素材类型',
                name: 'newstype',
                index: 'newstype',
                width: 80,
                formatter: function (value, options, rowData) {
                    if (value == 0) {
                        return '图文';
                    }
                    else if (value == 1) {
                        return '图片';
                    }
                    else {
                        return '视频';
                    }
                }
            },
            {label: '标题（前端）', name: 'fronttitle', index: 'fronttitle', width: 80},
            {label: '生效时间', name: 'publishtime', index: 'publishtime', width: 80},
            // { label: '正文', name: 'content', index: 'content', width: 80 },
            // { label: '置顶', name: 'istop', index: 'istop', width: 80 },
            // { label: '热门', name: 'ishot', index: 'ishot', width: 80 },
            // { label: '文章类型', name: 'newsfrom', index: 'newsfrom', width: 80 },
            // { label: '作者/来源名', name: 'auther', index: 'auther', width: 80 },
            // { label: '链接地址', name: 'linkurl', index: 'linkurl', width: 80 },
            // { label: '创建时间', name: 'createat', index: 'createat', width: 80 },
            // { label: '最后修改时间', name: 'modityat', index: 'modityat', width: 80 },
            // { label: '创建人', name: 'createuser', index: 'createuser', width: 80 },
            // { label: '最后修改人', name: 'modifyuser', index: 'modifyuser', width: 80 },
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value, options, rowData) {
                    if (value == 0) {
                        return '草稿';
                    } else {
                        return '已发布';
                    }
                }
            }
        ],
        viewrecords: true,
        height: 385,
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
        }
    });

    var attaId = 0;
    upImg('#addCoverImg0', 0, 0);
    upImg('#addCoverImg1', 0, 1);
    upImg('#addCoverImg2', 0, 2);

    /**
     * 当素材是图片时   动态添加图片
     */
    new AjaxUpload("addImg", {
        action: baseURL + 'generator/newsinfo/uploadFile',
        name: 'file',
        data: {'type': 1, "token": token, "attaid": vm.newsInfo.attaid, "sort": 1},
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            var setData = {'type': 1, "token": token, "attaid": vm.newsInfo.attaid, "sort": 1}
            this.setData(setData);

            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                var imgt = {
                    imgSrc: "",
                    textarea: "",
                    dataId: "",
                };
                imgt.imgSrc = r.res.urlpath;
                imgt.dataId = r.res.id;
                imgt.textarea = "";
                vm.$set(vm.$data.newsInfo, "attaid", r.res.attaid);
                console.log(r.res.attaid)
                vm.imgMarry(imgt);
                //$(upName).attr('src',r.res.urlpath);//把src属性更改为'6.jpg';
                //vm.reload();
            } else {
                alert(r.msg);
            }
        }
    });


    $('#messageSendTime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 0,
        startDate: new Date(),
        initialDate: new Date(),
        forceParse: 0
    });
    $('#messageSendTime').datetimepicker().on('hide', function (ev) {
        var value = $("#messageSendTime input").val();
        vm.newsInfo.publishtime = value
    });
});

/**
 * 封面图
 * @param upName
 * @param type
 */
function upImg(upName, type, sort) {
    new AjaxUpload(upName, {
        action: baseURL + 'generator/newsinfo/uploadFile',
        name: 'file',
        data: {'type': type, "token": token, "attaid": vm.newsInfo.attaid, "sort": sort},
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            var setData = {'type': type, "token": token, "attaid": vm.newsInfo.attaid, "sort": sort}
            this.setData(setData);
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                console.log(r.res.urlpath);
                $(upName).attr('src', r.res.urlpath);//把src属性更改为'6.jpg';
                // vm.newsInfo.attaid = r.res.attaid ;
                vm.$set(vm.$data.newsInfo, "attaid", r.res.attaid);
                console.log(r.res.attaid)
                //vm.reload();
            } else {
                alert(r.msg);
            }
        }
    });
}


var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        newsInfo: {},
        coverShow: false,
        newsShow: false,
        contentShow: true,
        videoShow: false,
        tableShow: false,
        linkurlShow: false,
        imgTable: [],
    },
    methods: {
        coverType: function () {
            if (vm.newsInfo.covertype == 1) {
                vm.coverShow = true;
            } else {
                vm.coverShow = false;
            }
        },
        newstype: function () {
            if (vm.newsInfo.newstype == 0) {
                vm.contentShow = true;
                vm.newsShow = false;
                vm.videoShow = false;
            }
            else if (vm.newsInfo.newstype == 1) {
                vm.contentShow = false;
                vm.newsShow = true;
                vm.videoShow = false;
            }
            else {
                vm.contentShow = false;
                vm.newsShow = false;
                vm.videoShow = true;
            }
        },
        newsfromFun: function () {
            if (vm.newsInfo.newsfrom == 0) {
                vm.linkurlShow = false;
                vm.newsInfo.linkurl = "";
            }
            else if (vm.newsInfo.newsfrom == 1) {
                vm.linkurlShow = true;
            }
        },
        setTopFun: function () {
            var newsid = vm.newsInfo.newsid;
            if (!newsid)
                newsid = "0.1";

            if (vm.newsInfo.istop == 1) {
                $.get(baseURL + "generator/newsinfo/queryTop/" + newsid, function (r) {
                    console.log(r);
                    if (r.code != 0){
                        alert(r.msg) ;
                        vm.newsInfo.istop = 0 ;
                    }

                });
            }
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.coverShow = false;
            vm.linkurlShow = false;
            vm.videoShow = false;
            vm.contentShow = true;
            vm.newsShow = false;
            vm.title = "新增";
            vm.imgTable = [];
            vm.tableShow = false;
            vm.newsInfo = {
                covertype: 0,
                newstype: 0,
                attaid: 0,
                istop: 0,
                ishot: 0,
                newsfrom: 0,
                publishtime: "",
            };
            $("#messageSendTime input").val("");
            $("#addCoverImg0").attr("src", "../../swagger/images/add_img.png");
            $("#addCoverImg1").attr("src", "../../swagger/images/add_img.png");
            $("#addCoverImg2").attr("src", "../../swagger/images/add_img.png");

            um.ready(function (editor) {
                um.setContent('');
            });


        },
        update: function (event) {
            var newsid = getSelectedRow();
            if (newsid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(newsid)
        },
        saveOrUpdate: function (event) {
            var url = vm.newsInfo.newsid == null ? "generator/newsinfo/save" : "generator/newsinfo/update";
            vm.newsInfo.status = event;
            console.log(this.newsInfo);
            if (vm.newsInfo.newstype == 0) {
                vm.newsInfo.content = um.getContent();
                console.log(vm.newsInfo.content);
            }

            if (vm.newsInfo.newstype == 1) {
                vm.newsInfo.picList = vm.imgTable;
            }


            if (!vm.newsInfo.backtitle) {
                alert("标题（后台）不能为空");
                return;
            }
            if (vm.newsInfo.fronttitle == "") {
                alert("标题（前端）不能为空");
                return;
            }
            if (vm.newsInfo.newstype == "0" && vm.newsInfo.content == "") {
                alert("正文不能为空");
                return;
            }
            if (vm.newsInfo.newstype == "2" && vm.newsInfo.videoUrl == "") {
                alert("视频链接地址不能为空");
                return;
            }
            if (vm.newsInfo.newstype == "2" && vm.newsInfo.videoDesc == "") {
                alert("说明文字不能为空");
                return;
            }
            if (vm.newsInfo.publishtime == "") {
                alert("生效时间不能为空");
                return;
            }
            if (vm.newsInfo.auther == "") {
                alert("作者/来源名不能为空");
                return;
            }
            if (vm.newsInfo.newsfrom == "1" && vm.newsInfo.linkurl == "") {
                alert("文章链接地址不能为空");
                return;
            }

            console.log(vm.newsInfo);
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.newsInfo),
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
            var newsids = getSelectedRows();
            if (newsids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "generator/newsinfo/delete",
                    contentType: "application/json",
                    data: JSON.stringify(newsids),
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
        getInfo: function (newsid) {
            vm.imgTable = [];
            $.get(baseURL + "generator/newsinfo/info/" + newsid, function (r) {
                vm.newsInfo = r.newsInfo;
                console.log(vm.newsInfo);
                $("#addCoverImg0").attr("src", "../../swagger/images/add.jpg");
                $("#addCoverImg1").attr("src", "../../swagger/images/add.jpg");
                $("#addCoverImg2").attr("src", "../../swagger/images/add.jpg");
                $("#messageSendTime input").val(vm.newsInfo.publishtime);
                $('#messageSendTime').datetimepicker({
                    startDate: vm.newsInfo.publishtime,
                });
                um.setContent("");

                if (vm.newsInfo.covertype == 1) {
                    vm.coverShow = true;
                }
                else {
                    vm.coverShow = false;
                }
                for (var index in r.newsInfo.coverList) {
                    $("#addCoverImg" + index).attr("src", JSON.parse(r.newsInfo.coverList[index]).urlpath);
                }

                if (vm.newsInfo.newstype == 0) {
                    vm.contentShow = true;
                    vm.newsShow = false;
                    vm.videoShow = false;

                    um.setContent(vm.newsInfo.content)
                }
                else if (vm.newsInfo.newstype == 1) {
                    vm.contentShow = false;
                    vm.newsShow = true;
                    vm.videoShow = false;

                    vm.tableShow = true;
                    vm.imgTable = vm.newsInfo.picList;
                    console.log(vm.imgTable)
                }
                else {
                    vm.contentShow = false;
                    vm.newsShow = false;
                    vm.videoShow = true;
                }

                if (vm.newsInfo.newsfrom == 0) {
                    vm.linkurlShow = false;
                }
                else if (vm.newsInfo.newsfrom == 1) {
                    vm.linkurlShow = true;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        },
        imgMarry: function (jsonType) {
            vm.tableShow = true;
            vm.imgTable.push(jsonType);
        },
        delImg: function (dataId, index) {
            console.log("id-----" + dataId)
            console.log("index-----" + index);
            console.log("index-----" + vm.newsInfo.attaid);

            vm.imgTable.splice(index, 1);

            // $.ajax({
            //     type: "POST",
            //     url: baseURL + "generator/newsinfo/deleteFile?dataId=" + dataId + "&attaid=" + vm.newsInfo.attaid,
            //     contentType: "application/json",
            //     // data: JSON.stringify(newsids),
            //     success: function (r) {
            //         if (r.code == 0) {
            //
            //         } else {
            //             alert(r.msg);
            //         }
            //     }
            // });
        },

    }
});