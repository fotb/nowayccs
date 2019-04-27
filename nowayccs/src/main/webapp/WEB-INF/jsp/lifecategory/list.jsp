<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>光明电力服务中心员工</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<base target="_self">
	    <script type="text/javascript">
        $(function(){
            $("#btRemove").click(function (){
            	var row = $("#cdg").datagrid("getSelected");
            	if(null == row) {
            		$.messager.alert("提示", '请选择需要停用的分类！');
            	}  else {
            		$.messager.confirm("提示", '确认停用分类？',function(r){
            		    if (r){
            		    	//$("#cdg").datagrid("remove", row.pid);
                    		$.post("lifecategory.do?action=delete", {code:row.id},
        						function(data,status){
        							$("#cdg").datagrid("loadData", data);
        						});
            		    }
            		});
            	}
            });
            
            
            var editingId;
            $("#btEdit").click(function (){
    			if (editingId != undefined){
    				$('#cdg').treegrid('select', editingId);
    				return;
    			}
    			var row = $('#cdg').treegrid('getSelected');
    			if (row){
    				editingId = row.id
    				$('#cdg').treegrid('beginEdit', editingId);
    			}
            });
            
            $("#btSave").click(function (){
                if (editingId != undefined){
                    var t = $('#cdg');
                    t.datagrid('endEdit', editingId);
                    editingId = undefined;
                    var persons = 0;
                    var row = t.datagrid('getSelected');
	                $.post("lifecategory.do?action=update", {code:row.id, name:row.text},
	                		 function(data){
	                	//alert(data.meta.success);
	                });
                }
            });
            
            $("#btCancel").click(function (){
                if (editingId != undefined){
                    $('#cdg').datagrid('cancelEdit', editingId);
                    editingId = undefined;
                }
            });
            
            
            $("#btAdd").click(function (){
            	$('#addWin').window('open');
            });
            
            $('#ff').form({
                url:"lifecategory.do?action=add",
               onSubmit: function(){
	           		var isValid = $(this).form('validate');
	        		if (!isValid){
	        			$.messager.progress('close');	// hide progress bar while the form is invalid
	        		}
	        		return isValid;	// return false will stop the form submission
                },
                success:function(data){
                	//alert(data);
                	//if(data.meta.success) {
                    	$('#addWin').window('close');
                    	$('#ff').form('clear');
                    	$('#cdg').treegrid('reload');	
            		//}
                }
            });
 
            
            
           　$.extend($.fn.validatebox.defaults.rules, {
        	　　　　　　validCode : {// 验证Code是否存在
        	       　　　　       validator : function(value){
        	       　　　　     	  if (value != "") {
        	       　　　                var result = false;
        	       　　　                $.ajax({
        	       　　　                    type: 'POST',
        	       　　　                    url: 'lifecategory.do?action=check',
        	       　　　                    data: {code: $("#code").val()},
        	       　　　                    dataType: 'json',
        	       　　　                    async: false, // 关闭异步
        	       　　　                    success: function(rec) {
        	       　　　                        if (rec.meta.success){
        	       　　　                            result = true;
        	       　　　                        } else {
        	       　　　                            result = false;
        	       　　　                        }
        	       　　　                    }
        	       　　　                });
        	       　　　                return result;
        	       　　　            }
        	         　　　　     },
        	        　　　　      message : '分类已经存在，请重新输入！'
        	        　　　　  }
        	　　});
        })
        
        function check() {
        	$.post("lifecategory.do?action=check", {code: $("#code").val()},
           		 function(data){
       		alert(data.meta.success);
           	if(data.meta.success) {
       			return true;
       		} else {
       			alert("分类编码已存在，请重新选择！");
       			return false;
       		}
           });
        }
        
		function formatCategory(value, row){
			var s = "";
	    	if ('1' == value){
		    	s = "负责人";
	    	} else if("2" == value) {
	    		s = "安全负责人";
	    	} else if("3" == value) {
	    		s = "全镇机动";
	    	} else if("4" == value){
	    		s = "电工";
	    	} else {
	    		s = "";
	    	} 
	    	return s;
		}
        function formatRemark(value, row){
			var s = "";
	    	if(row.deleteFlag == '1') {
	    		s = "已停止服务！";
	    	}
	    	return s;
		}
        
        
		function submitForm(){
			$('#ff').form('submit');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
        
    </script>
</head>
<body>

		<div style="margin-bottom:1px" id="tb">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:'true'" id="btAdd">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:'true'" id="btRemove">停用</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:'true'" id="btEdit">修改</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:'true'" id="btSave">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">取消修改</a>
		</div>
	<div style="margin:10px 0;"></div>

	<table id="cdg" title="生活类服务类别管理" class="easyui-treegrid" style="width:700px;height:750px"
			data-options="
				url: 'lifecategory.do?action=lifecategorytree',
				method: 'get',
				idField: 'id',
				treeField: 'text',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,				
				showFooter: true
			">
		<thead>
			<tr>
				<th data-options="field:'text',editor:'text'" width="50%">类别名称</th>
				<th data-options="field:'id'" width="50%">编码</th>
			</tr>
		</thead>
	</table>



    <div id="addWin" class="easyui-window" title="新增类别" style="width:400px;height:240px"
        data-options="iconCls:'icon-save',modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
    		<div data-options="region:'center'">
    			<div class="easyui-panel" style="width:380px">
	    		<div style="padding:20px 60px 20px 60px">
				    <form id="ff" method="post">
				    	<table cellpadding="3">
				    		<tr>
				    			<td>类别:</td>
				    			<td>
					    			<select class="easyui-combotree" id="pcode" name="pcode" data-options="url: 'lifecategory.do?action=lifecategorytree', prompt:'请选择事件类别', onlyLeafCheck:true, required:true" style="height:100%;width: 180px;">
					            	</select>
				            	</td>
				            	<!-- <td>请选择分类</td> -->
				    		</tr>
				    		<tr>
				    			<td>编码:</td>
				    			<td><input class="easyui-validatebox textbox" type="text" name="code" id="code" data-options="required:true,validType:'validCode',novalidate:true"></input></td>
				    			<!-- <td>编码不能与已有编码重复，如“开锁”类编码为“01”， 建议根据现有递增“01010”</td> -->
				    		</tr>
				    		<tr>
				    			<td>名称:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" id="name" data-options="required:true"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
				    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
				    </div>
				</div>
            	</div>
    		</div>
        </div>
    </div>
</body>
</html>