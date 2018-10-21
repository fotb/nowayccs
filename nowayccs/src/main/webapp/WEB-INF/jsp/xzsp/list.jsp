<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>南湖区行政审批局事项办理告知清单</title>
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
            		$.messager.confirm("提示", '确认删除？',function(r){
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
                var row = $("#dg").datagrid('getSelected');
                 if (row !=null) {

					/* showWindow({
					    title: 'My Dialog',
						width:840,
						hight:800,
					    closed: false,
					    cache: false,
					   href:'xzsp.do?action=edit&pid=' + row.pid,
					    modal: true
					}); */
					this.href = 'xzsp.do?action=edit&pid=' + row.pid;
                } else {
                	$.messager.alert("提示", '请选择需要修改的记录！');
                }
            }); 
            
            $("#btDel").click(function (){
            	var row = $("#dg").datagrid('getSelected');
            	if (row !=null) {
        			$.messager.confirm('提示', '确实要删除?', function(r){
        				if (r){
        					$.post("xzsp.do?action=del", {pid:row.pid}, function(){
       	                		$("#dg").datagrid("reload");
       	                 	});
        				}
        			});
                } else {
                	$.messager.alert("提示", '请选择需要删除的记录！');
                }
                
            });
            
            
            $('#areaTree').tree({
            	onClick: function(node){
            		$("#dg").datagrid({  
                        url:"xzsp.do?action=load&key=" + node.id,//加载的URL  
                        rownumbers:true,
                        method:'get',
                        toolbar:'#tb',
                       pagination:true,//显示分页  
                        pageSize:10,//分页大小  
                        //pageList:[10,30,50],//每页的个数  
                        fit:true,//自动补全  
                        fitColumns:true,
        				onLoadSuccess: function(data){
        					
        				}
            		});
            	}
            });
        })
        
        function doSearch(value){
        	$("#dg").datagrid({  
                url:"xzsp.do?action=search&key=" + value,//加载的URL  
                rownumbers:true,
                method:'get',
                toolbar:'#tb',
               pagination:true,//显示分页  
                pageSize:10,//分页大小  
                //pageList:[10,30,50],//每页的个数  
                fit:true,//自动补全  
                fitColumns:true,
				onLoadSuccess: function(data){
					
				}
    		});
		}
        
        function formatLink(val,row){
			return "<a href='xzsp.do?action=view&pid=" + row.pid+"' targer='_blank'>"+val+"</a>";
        }

    </script>
</head>
<body>
	
		<div id="cc" class="easyui-layout" style="width:95%;height:480px;">
			<div data-options="region:'west'" style="width:350px;">
				<ul id="areaTree" class="easyui-tree" data-options="url:'xzsp.do?action=codetree',method:'get',animate:true, lines:true"></ul>
			</div>
			<div data-options="region:'center'" style="padding:20px;width:95%">
		
			<div style="margin-bottom:1px" id="tb">
				<input class="easyui-searchbox" data-options="prompt:'请输入编号或名称', searcher:doSearch" style="width:300px"></input>	
											    						
				<a href="xzsp.do?action=add" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:'true'" id="btAdd">新增</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:'true'" id="btEdit">修改</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btDel">删除</a>
			<!-- 			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">取消修改</a> -->
			</div>
				<table id="dg" class="easyui-datagrid" title="事项办理告知清单" style="width: 100%; height: 450"
		data-options="rownumbers:true,singleSelect: true,url:'xzsp.do?action=listAll',method:'get',toolbar:'#tb',pagination:true,
                pageSize:10">
					<thead>
						<tr>
							<th data-options="field:'listCode',width:100,align:'left'">清单编号</th>
							<th data-options="field:'itemName',formatter:formatLink">事项名称</th>
							<th	data-options="field:'code',width:80">编码</th>
							<th data-options="field:'itemType',width:180">事项类型</th>
							<th data-options="field:'target',width:240">办理对象</th>
							<th data-options="field:'dealDept',width:240">办理科室</th>
							<th data-options="field:'dealPhone',width:240">经办电话</th>
							<th data-options="field:'promiseDate',width:240">承诺时限</th>
							<th data-options="field:'chargeStand',width:240">收费标准</th>
							<th data-options="field:'servicePhone',width:240">监督电话</th>
						</tr>
					</thead>
				</table>
			</div>
	</div>
	
</body>
</html>