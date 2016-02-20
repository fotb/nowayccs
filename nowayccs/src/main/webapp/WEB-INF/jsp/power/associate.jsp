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
<script>
        $(function(){
            $("#btnSave").click(function (){
            	var node = $('#areaTree').tree('getSelected');
    			if(node) { 	
	            	var rows = $('#dg').datagrid('getSelections');
	            	if(rows == null) {
	            		$.messager.alert("提示","请选择电工！", "error");
	            		return;
	            	}
	            	
	            	var pids = new Array();
	            	var names = "";
	    			for(var i=0; i<rows.length; i++){
	    				var row = rows[i];
	    				pids.push(row.pid);
	    				names += row.name;
	    			}
	                $.post("lps.do?action=associateSave", {areaSubId:node.id, pids:pids}, function(json){
	                	$.messager.alert("提示","保存成功！", "info");
	                	$("#dg").datagrid("reload");
	                });
            	} else {
            		$.messager.alert('提示', '请选择社区（存）！');
            	}
            });
            
            $('#areaTree').tree({
            	onClick: function(node){
            		if(node.children == "" || node.children == null) {
            			$("#dg").datagrid({  
                            url:"lps.do?action=subareapslist&areaSubId=" + node.id,//加载的URL  
                            rownumbers:true,
                            method:'get',
                            toolbar:'#tb',
                           //pagination:true,//显示分页  
                           // pageSize:10,//分页大小  
                            //pageList:[10,30,50],//每页的个数  
                            fit:true,//自动补全  
                            fitColumns:true,
            				onLoadSuccess: function(data){
            					for(i = 0; i < data.selectedRows; i++){
            						$(this).datagrid('selectRow', i);
            						$(this).datagrid('freezeRow',i);
            					}
            				}
                		});
                    	
                        //$('#dg').datagrid().datagrid('clientPaging');
            		}
            	}
            });

            
            $("#btnSearch").click(function (){
            	var node = $('#areaTree').tree('getSelected');
    			if (node){
    				$("#dg").datagrid({  
                        url:"lps.do?action=subareapslist&areaSubId=" + node.id + "&psname=" + $("#psname").val() + "&psphone=" + $("#psphone").val(),//加载的URL  
                        rownumbers:true,
                        method:'get',
                        toolbar:'#tb',
                       //pagination:true,//显示分页  
                       // pageSize:10,//分页大小  
                        //pageList:[10,30,50],//每页的个数  
                        fit:true,//自动补全  
                        fitColumns:true,
        				onLoadSuccess: function(data){
        					for(i = 0; i < data.selectedRows; i++){
        						$(this).datagrid('selectRow', i);
        						$(this).datagrid('freezeRow',i);
        					}
        				}
            		});
    			} else {
    				$.messager.alert('提示', '请选择社区（存）！');
    			}
            	
            });
        });
                
        
        function btnnext_click(){

        	
        	if(row == null) {
        		alert("fasdfa");
        	} else {
        		$("#powerStaffId").val(row.pid);
        	}

        	$("form").submit();
        	//$("#bizAccept").submit(function(){alert("success"); return false;});
        }
    </script>
</head>
<body>
		
	
	    <div class="easyui-layout" style="width:90%;height:450px;">
            <div data-options="region:'west'" title="街道社区" style="width:20%;padding:10px">
        		<ul id="areaTree" class="easyui-tree" data-options="url:'lps.do?action=areatree',method:'get',animate:true"></ul>
    		</div>
        <div data-options="region:'center'" title="电工">
        <div style="margin-bottom:1px,padding:10px" id="tb">
        	姓名：<input class="eeasyui-textbox" id="psname">
        	电话：<input class="eeasyui-textbox" id="psphone">
        	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch">搜索</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="btnSave">保存</a>
		</div>
        <table id="dg" class="easyui-datagrid" title="请选择电工：" style="width:100%;height:auto">
		<thead>
			<tr>
				<th data-options="field:'pid',checkbox:true"></th>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'phone',width:100,align:'right'">电话</th>
			</tr>
		</thead>
	</table>
        </div>
    </div>
	
	
	
            

     
</body>
</html>