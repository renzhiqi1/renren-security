$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/zhzgplan/list',
        datatype: "json",
        colModel: [			
//			{ label: 'planId', name: 'planId', index: 'plan_id', width: 50, key: true },
//			{ label: '父计划ID，一级计划为0', name: 'parentId', index: 'parent_id', width: 80 },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '发布人', name: 'publisher', index: 'publisher', width: 80 }, 			
			{ label: '内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '状态（启动、执行、暂定、结束、删除）', name: 'state', index: 'state', width: 80 }, 			
			{ label: '进度', name: 'schedules', index: 'schedules', width: 80 }, 			
			{ label: '发布时间', name: 'time', index: 'time', width: 80 }, 			
			{ label: '审核标识（0 ：待审核 1:审核通过 2：审核不通过）', name: 'auditFlag', index: 'audit_flag', width: 80 }, 			
			{ label: '删除标识（0 禁用 1正常）', name: 'deleFlag', index: 'dele_flag', width: 80 }, 			
			{ label: '备注信息', name: 'comment', index: 'comment', width: 80 }
			//{ label: '管理按钮', name: 'comment', index: 'comment', width: 80 }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		zhzgPlan: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.zhzgPlan = {};
		},
		update: function (event) {
			var planId = getSelectedRow();
			if(planId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(planId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.zhzgPlan.planId == null ? "sys/zhzgplan/save" : "sys/zhzgplan/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.zhzgPlan),
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
			var planIds = getSelectedRows();
			if(planIds == null){
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
                        url: baseURL + "sys/zhzgplan/delete",
                        contentType: "application/json",
                        data: JSON.stringify(planIds),
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
		getInfo: function(planId){
			$.get(baseURL + "sys/zhzgplan/info/"+planId, function(r){
                vm.zhzgPlan = r.zhzgPlan;
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