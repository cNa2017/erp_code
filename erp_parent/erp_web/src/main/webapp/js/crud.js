	var method ="";
	$(function() {
		$('#editDlg').dialog({
			title : '编辑数据',
			width : 400,
			height : 200,
			closed : true,
			modal : true,
			buttons:[{
				text:'保存',
				iconCls:"icon-save",
				handler:function(){
					var submitData = $('#editForm').serializeJSON();
					$.ajax({
						 url:name+'_' + method,
						 data: submitData,//提交的查询条件,
						 dataType:'json',//JS, 把服务返回的数据转成json对象
						 type:'post',
						 success:function(rtn){//服务端成功执行后回调的方法
						 	//alert(JSON.stringify(rtn));
							 $.messager.alert('提示',rtn.message,'info',function(){
									if(rtn.success){
										//关闭窗口
										$('#editDlg').dialog('close');
										//刷新表格
										$('#grid').datagrid('reload');
									}
								});
						 }
						 });
				}
			},{
				text:'关闭',
				iconCls:"icon-cancel",
				handler:function(){
					$("#editDlg").dialog("close");
				}
			}]
		});
		
		var cfg = {
			url : name+'_listByPage',
			columns : columns,
			pagination : true,
			pageSize : 5,
			pageList : [ 3, 5, 8, 15 ],
			singleSelect:true,//只能选中一行
		    toolbar: [{
				iconCls: 'icon-add',
				text:'新增',
				handler: function(){
					$('#editDlg').dialog('open');
					//这时，提交的方法，应该为add
					method = "add";
					//清空表单内容
					$('#eidtForm').form('clear');
				}
			}]

		};
		$('#grid').datagrid(cfg);

		$("#btnSearch").bind("click", function() {
			var submitData = $('#searchForm').serializeJSON();
			//	alert(submitData);
			//JSON.stringify(submitData);//把json对象转成字符串json对象的操作
			//  alert(JSON.stringify(submitData)); 
			//	return ;
			/* 		$.ajax({
			 url:name+'_list',
			 data: submitData,//提交的查询条件,
			 dataType:'json',//JS, 把服务返回的数据转成json对象
			 type:'post',
			 success:function(abc){//服务端成功执行后回调的方法
			 //	alert(JSON.stringify(abc));
			 $('#grid').datagrid('loadData',abc);
			 //	$('#grid').datagrid("reload");//reload会重新发起一个请求,和ajax一起使用会发送两次,浪费资源
			 }
			 }); */
			$('#grid').datagrid('load', submitData);
		});
	});
	function del(uuid){
		$.messager.confirm('确认', '确认要删除吗？', function(yes){
			if (yes){
			    //提交删除
			//    var submitData = {};
			//    submitData.id = uuid;
				$.ajax({
					url:name+'_delete?id=' + uuid,
					//data: submitData,
					dataType:'json',
					type:'post',
					success:function(rtn){
						$.messager.alert('提示',rtn.message,'info',function(){
							if(rtn.success){
								//刷新表格
								$('#grid').datagrid('reload');
							}
						});
						
					}
				});
			}
		});
	}
	
	function edit(uuid){
		//打开窗口
		$('#editDlg').dialog('open');
		//提交 的方法，应该为更新
		method = "update";
		//清空表单内容
		$('#eidtForm').form('clear');
		//加载显示数据
		$('#editForm').form('load',name+'_get?id=' + uuid);
		//$('#editForm').form('load',{"dep.name":"管理员组","dep.tele":"000000","dep.uuid":1});
	}
	function getData() {
		alert(JSON.stringify($('#grid').datagrid('getData')));
	}