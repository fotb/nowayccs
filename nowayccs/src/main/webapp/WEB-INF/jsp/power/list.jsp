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
            	var row = $("#dg").datagrid("getSelected");
            	if(null == row) {
            		$.messager.alert("提示", '请选择需要删除的记录！');
            	}  else {
            		$.messager.confirm("提示", '确认删除员工？',function(r){
            		    if (r){
            		    	//$("#dg").datagrid("remove", row.pid);
                    		$.post("lps.do?action=del", {id:row.pid},
        						function(data,status){
                        			$.getJSON("lps.do?action=buildlist&areaId=" + $("#areaId").combobox("getValue") + "&page=1&rows=10", function(data){
                        				$("#dg").datagrid({
                                       		url:'lps.do?action=buildlist&areaId=' + $("#areaId").combobox("getValue")
                                   	 	});
                						$("#dg").datagrid("loadData", data);
                					});
        						});
            		    }
            		});
            	}
            });
            
            
            var editingId;
            $("#btEdit").click(function (){
                if (editingId != undefined){
                    $('#dg').datagrid('select', editingId);
                    return;
                }
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    editingId = row.id
                    $('#dg').datagrid('beginEdit', editingId);
                }
            });
            
            $("#btSave").click(function (){
                if (editingId != undefined){
                    var t = $('#dg');
                    t.datagrid('endEdit', editingId);
                    editingId = undefined;
                    var persons = 0;
                    var row = t.datagrid('getSelected');
                    
	                 $.post("lps.do?action=saveLps", {id:row.id, name:row.name, phone:row.phone, remark:row.remark},
	                		 function(){
	                	 $("#dg").datagrid("loadData", data);
	                 });
                }
            });
            
            $("#btCancel").click(function (){
                if (editingId != undefined){
                    $('#dg').datagrid('cancelEdit', editingId);
                    editingId = undefined;
                }
            });
            
            
            $('#areaId').combobox({
            	onSelect: function(param){
            		$.getJSON("lps.do?action=buildlist&areaId=" + param.value + "&page=1&rows=10", function(data){
            			$("#dg").datagrid({
                            url:'lps.do?action=buildlist&areaId=' +param.value
                        });
    					$("#dg").datagrid("loadData", data);
    				});
            	}
            });
        })
        
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
        
        
    </script>
</head>
<body>

		<div style="margin-bottom:1px" id="tb">
			<input class="easyui-combobox" id="areaId" style="width:200px" 
	    						data-options="url:'json.do?action=arealist',
											method:'get',
											valueField:'value',
											textField:'text'" ></input>
	    						
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:'true'" id="btnSave">保存</a>
			<span id="info" style="color: red; font-weight:bold;"></span>
			<a href="lps.do?action=add" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:'true'" id="btAdd">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:'true'" id="btRemove">停止服务</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:'true'" id="btEdit">修改</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:'true'" id="btSave">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">取消修改</a>
			<a href="lps.do?action=associate" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">关联</a>
		</div>
	<div style="margin:10px 0;"></div>

	<table id="dg" class="easyui-datagrid" title="电力服务电工" style="width: 100%; height: 450"
		data-options="rownumbers:true,url:'lps.do?action=buildlist',method:'get',toolbar:'#tb',pagination:true,
                pageSize:10,
                rowStyler: function(index,row){
                    if (row.deleteFlag == '1'){
                        return 'text-decoration: line-through; color: #ff0000;';
                    }
                }">
		<thead>
			<tr>
				<th data-options="field:'name',width:100,align:'left',editor:'text'">姓名</th>
				<th data-options="field:'phone',width:120,editor:'text'">电话</th>
				<!-- <th data-options="field:'category',width:80,formatter:formatCategory,editor:{type:'combobox',options:{valueField:'category', textField:'categoryName', data:[{'category':1,'categoryName':1},{'category':2,'categoryName':2}]}}">职务</th> -->
				<th	data-options="field:'category',width:80,formatter:formatCategory">职务</th>
				<th data-options="field:'areaSubName',width:180,editor:'text'">社区（村）</th>
				<th data-options="field:'remark',width:240,editor:'text',formatter:formatRemark"">备注</th>
			</tr>
		</thead>
	</table>


</body>
</html>