<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>事项详细信息</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
		<base target="_self">
		
		<style type="text/css"> 
table 
{ 
	border-collapse: collapse; 
	border: none; 
} 
td 
{ 
	border: solid #d3d3d3 1px; 
	padding:4px;
} 
td-title
{ 
	color: #333333;
} 
</style> 
</head>
<script>
		
		$(document).ready(function(){
			
			$("#btSubmit").click(function (){
				$("#xzspListDomain").submit();
/* 				$("#xzspListDomain").submit( function(){
					$.messager.alert("提示", '请选择需要修改的记去问问无录！');
					this.location="xzsp.do";
				} );  */
            });
			
			$("#btReturn").click(function (){
				history.back();
            });
		});
		
	</script>
<body>
<form:form method="post" action="xzsp.do?action=editSave" commandName="xzspListDomain">
<form:hidden path="pid"/>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="事项详细信息" style="width:98%">
		<div style="padding:10px 10px 20px 10px">
	    
	    	<table style="width:98%" class="table_gray">
	    		<tr>
	    			<td nowrap="nowrap">清单编号</td>
	    			<td colspan="3">${xzspListDomain.listCode}</td>
	    		</tr>
	    		<tr>
	    			<td>事项名称</td>
	    			<td>${xzspListDomain.itemName}</td>
	    			<td nowrap="nowrap" style="text-align:center;">编码</td>
	    			<td>${xzspListDomain.code}</td>
	    		</tr>
	    		<tr>
	    			<td>事项类型</td>
	    			<td colspan="3">${xzspListDomain.itemType}</td>
	    		</tr>
	    		<tr>
	    			<td>办理对象</td>
	    			<td colspan="3">${xzspListDomain.target}</td>
	    		</tr>
	    		<tr>
	    			<td>设定依据</td>
	    			<td colspan="3">${xzspListDomain.according}</td>
	    		</tr>
	    		<tr>
	    			<td>办理条件</td>
	    			<td colspan="3">${xzspListDomain.requirement}</td>
	    		</tr>
	    		<tr>
	    			<td>申报材料</td>
	    			<td colspan="3">${xzspListDomain.materials}</td>
	    		</tr>
	    		<tr>
	    			<td>办理程序</td>
	    			<td colspan="3">${xzspListDomain.proce}</td>
	    		</tr>
	    		<tr>
	    			<td>办理科室</td>
	    			<td colspan="3">${xzspListDomain.dealDept}</td>
	    		</tr>
	    		<tr>
	    			<td>法定期限</td>
	    			<td>${xzspListDomain.legalTerm}</td>
	    			<td>承诺时限</td>
	    			<td>${xzspListDomain.promiseDate}</td>
	    		
	    		</tr>
	    		<tr>
	    			<td>收费标准</td>
	    			<td>${xzspListDomain.chargeStand}</td>
	    			<td>收费依据</td>
	    			<td>${xzspListDomain.chargeAccording}</td>
	    		
	    		</tr>
	    		<tr>
	    			<td>经办电话</td>
	    			<td>${xzspListDomain.dealPhone}</td>
	    			<td>监督电话</td>
	    			<td>${xzspListDomain.servicePhone}</td>
	    		
	    		</tr>
	    		<tr>
	    			<td>便民级别</td>
	    			<td colspan="3">${xzspListDomain.easyLevel}</td>
	    		</tr>
	    	</table>
	    
	    <div style="text-align:center;padding:5px">
	    	<a href="#" class="easyui-linkbutton" id="btReturn" data-options="iconCls:'icon-back'">返回</a>
	    </div>
	    </div>
	</div>
	</form:form>
</body>
</html>