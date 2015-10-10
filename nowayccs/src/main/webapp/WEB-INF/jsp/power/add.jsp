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
</head>
<script>
		function submitForm(){
			$('#powerStaffDomain').form('submit');
		}
		function clearForm(){
			$('#powerStaffDomain').form('clear');
		}

		
		$(document).ready(function(){
			/*
			$.getJSON("json.do?action=area", function(data) {
				alert(data);
				$("areaId").append("<option value=' '>全部</option>");
				alert($("#areaId").val());
				alert("2");
				$.each(data, function(i, item) {
					alert("3");
					if(item.areaId == "${powerStaffDomain.areaId}") {
						$("#areaId").append("<option value=" + item.areaId + " selected='selected'>" + item.name + " </option>");
					} else {
						$("#areaId").append("<option value=" + item.areaId + ">" + item.name + " </option>");
					}
				});
			});
			if("" != "${powerStaffDomain.areaId}") {
				loadAreaSub("${powerStaffDomain.areaId}");
			}
			*/
			
		});
		
		function hideCombo() {
			$("#areaSubId + .combo").hide();
		}
		function showCombo() {
			$("#areaSubId + .combo").show();
		}

		function setComboValue(value) {
			if('' == $("input[name='areaSubId']").val()) {
				$('#areaSubId').combobox('setValues', value);
			}
		}
	</script>
<body>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="新增" style="width:800px">
		<div style="padding:10px 60px 20px 60px">
	    <form:form method="post" action="lps.do?action=save" commandName="powerStaffDomain">
	    	<table>
	    		<tr>
	    			<td>区域:</td>
	    			<td>
	    					<form:input class="easyui-combobox" path="areaId" style="width:40%" 
	    						data-options="url:'json.do?action=arealist',
											method:'get',
											valueField:'value',
											textField:'text',
											panelHeight:'auto',
											onSelect:function(record){
												$('#areaSubId').combobox('setValues', '');
												$('#areaSubId').combobox('reload', 'json.do?action=subarealist&areaId='+record.value);
											}" ></form:input>
							
							<form:input class="easyui-combobox" path="areaSubId" style="width:200px" 
	    						data-options="multiple:true,valueField:'value',textField:'text',panelHeight:'auto',
	    										onSelect:function(record){
													setComboValue(record.value);
											}" ></form:input>
					</td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="name" data-options="required:true" style="width:220px;"></form:input>
	    		</tr>
	    		<tr>
	    			<td>联系方式:</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="phone" data-options="required:true" style="width:220px;"></form:input></td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td>
	    				<form:select cssClass="easyui-combobox" path="category" data-options="required:true,onSelect:function(record){
												if('4' != record.value) {
													hideCombo();
												} else {
													showCombo();
												}
											}">
	    					<option value="1">负责人</option>
	    					<option value="2">安全负责人</option>
	    					<option value="3">全镇机动</option>
	    					<option value="4" selected="selected">电工</option>
	    				</form:select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td><form:input cssClass="easyui-textbox" type="text" path="remark" style="width:220px;"></form:input></td>
	    		</tr>
	    	</table>
	    </form:form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	    </div>
	</div>
	
</body>
</html>