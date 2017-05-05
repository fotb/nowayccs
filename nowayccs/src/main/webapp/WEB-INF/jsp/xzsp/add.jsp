<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>新增员工</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
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
} 
</style> 
</head>
<script>
		$(document).ready(function(){
			$("#btSubmit").click(function (){
				$("#xzspListDomain").submit();
            });
			
			$("#btReset").click(function (){
				$('#xzspListDomain').form('reset');
            });

		});
	</script>
<body>
<form:form method="post" action="xzsp.do?action=save" commandName="xzspListDomain">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="新增" style="width:800px">
		<div style="padding:10px 10px 20px 10px">
	    
	    	<table style="width:780">
	    		<tr>
	    			<td>清单编号</td>
	    			<td colspan="3">
						<form:input cssClass="easyui-textbox" type="text" path="listCode" data-options="required:true" style="width:100%;"></form:input>
					</td>
	    		</tr>
	    		<tr>
	    			<td>事项名称</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="itemName" data-options="required:true" style="width:100%;"></form:input></td>
	    			<td style="text-align:center;">编码</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="code" data-options="required:true" style="width:100%;"></form:input></td>
	    		</tr>
	    		<tr>
	    			<td>事项类型</td>
	    			<td colspan="3"><form:input cssClass="easyui-textbox" type="text" path="itemType" data-options="required:true" style="width:100%;"></form:input></td>
	    		</tr>
	    		<tr>
	    			<td>办理对象</td>
	    			<td colspan="3"><form:input cssClass="easyui-textbox" type="text" path="target" data-options="required:true" style="width:100%;"></form:input></td>
	    		</tr>
	    		<tr>
	    			<td>设定依据</td>
	    			<td colspan="3"><form:textarea  class="textarea easyui-validatebox"  path="according" data-options="required:true" rows="5" style="width:100%;"></form:textarea></td>
	    		</tr>
	    		<tr>
	    			<td>办理条件</td>
	    			<td colspan="3"><form:textarea  class="textarea easyui-validatebox"  path="requirement" data-options="required:true" rows="5" style="width:100%;"></form:textarea></td>
	    		</tr>
	    		<tr>
	    			<td>申报材料</td>
	    			<td colspan="3"><form:textarea  class="textarea easyui-validatebox"  path="materials" data-options="required:true" rows="5" style="width:100%;"></form:textarea></td>
	    		</tr>
	    		<tr>
	    			<td>办理程序</td>
	    			<td colspan="3"><form:textarea  class="textarea easyui-validatebox"  path="proce" data-options="required:true" rows="5" style="width:100%;"></form:textarea></td>
	    		</tr>
	    		<tr>
	    			<td>办理科室</td>
	    			<td colspan="3"><form:input cssClass="easyui-textbox" type="text" path="dealDept" data-options="required:true" style="width:100%;"></form:input></td>
	    		</tr>
	    		<tr>
	    			<td>法定期限</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="legalTerm" data-options="required:true" style="width:100%;"></form:input></td>
	    			<td>承诺时限</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="promiseDate" data-options="required:true" style="width:100%;"></form:input></td>
	    		
	    		</tr>
	    		<tr>
	    			<td>收费标准</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="chargeStand" data-options="required:true" style="width:100%;"></form:input></td>
	    			<td>收费依据</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="chargeAccording" data-options="" style="width:100%;"></form:input></td>
	    		
	    		</tr>
	    		<tr>
	    			<td>经办电话</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="dealPhone" data-options="required:true" style="width:100%;"></form:input></td>
	    			<td>监督电话</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="servicePhone" data-options="required:true" style="width:100%;"></form:input></td>
	    		
	    		</tr>
	    		<tr>
	    			<td>便民级别</td>
	    			<td colspan="3"><form:input cssClass="easyui-textbox" type="text" path="easyLevel" data-options="required:true" style="width:100%;"></form:input></td>
	    		</tr>
	    	</table>
	    
	    <div style="text-align:center;padding:5px">
			<a href="#" class="easyui-linkbutton" id="btSubmit" data-options="iconCls:'icon-save'">提交</a>
	    	<a href="#" class="easyui-linkbutton" id="btReset" data-options="iconCls:'icon-reload'" onclick="clearForm()">重置</a>
	    </div>
	    </div>
	</div>
	</form:form>
</body>
</html>