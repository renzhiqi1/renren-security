$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/zhzgterminl/list',
        datatype: "json",
        colModel: [			
//			{ label: 'terminalId', name: 'terminalId', index: 'terminal_id', width: 50, key: true },
			{ label: '终端编号', name: 'number', index: 'number', width: 80 }, 			
			{ label: '终端IP', name: 'terminalIp', index: 'terminal_ip', width: 80 }, 			
			{ label: '名字', name: 'name', index: 'name', width: 80 }, 			
			{ label: '所属部门', name: 'deptId', index: 'dept_id', width: 80 }, 			
			{ label: '是否删除  -1：已删除  0：正常', name: 'delFlag', index: 'del_flag', width: 80 }, 			
			{ label: '备注', name: 'comment', index: 'comment', width: 80 }			
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
		zhzgTerminl: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.zhzgTerminl = {};
		},
		update: function (event) {
			var terminalId = getSelectedRow();
			if(terminalId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(terminalId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.zhzgTerminl.terminalId == null ? "sys/zhzgterminl/save" : "sys/zhzgterminl/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.zhzgTerminl),
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
			var terminalIds = getSelectedRows();
			if(terminalIds == null){
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
                        url: baseURL + "sys/zhzgterminl/delete",
                        contentType: "application/json",
                        data: JSON.stringify(terminalIds),
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
		getInfo: function(terminalId){
			$.get(baseURL + "sys/zhzgterminl/info/"+terminalId, function(r){
                vm.zhzgTerminl = r.zhzgTerminl;
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