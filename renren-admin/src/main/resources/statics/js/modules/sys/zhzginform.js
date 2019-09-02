$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/zhzginform/list',
        datatype: "json",
        colModel: [			
//			{ label: 'informId', name: 'informId', index: 'inform_id', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '发布者', name: 'publisher', index: 'publisher', width: 80 }, 			
			{ label: '内容', name: 'context', index: 'context', width: 80 }, 			
			{ label: '发布时间', name: 'pubTime', index: 'pub_time', width: 80 }, 			
			{ label: '发布地点', name: 'pubSite', index: 'pub_site', width: 80 }, 			
			{ label: '状态：0 禁用 1正常', name: 'status', index: 'status', width: 80 }			
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
});

var x = document.createElement("INPUT");
x.setAttribute("type", "date");

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		zhzgInform: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.zhzgInform = {};
		},
		update: function (event) {
			var informId = getSelectedRow();
			if(informId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(informId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.zhzgInform.informId == null ? "sys/zhzginform/save" : "sys/zhzginform/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.zhzgInform),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var informIds = getSelectedRows();
			if(informIds == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sys/zhzginform/delete",
                        contentType: "application/json",
                        data: JSON.stringify(informIds),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(informId){
			$.get(baseURL + "sys/zhzginform/info/"+informId, function(r){
                vm.zhzgInform = r.zhzgInform;
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