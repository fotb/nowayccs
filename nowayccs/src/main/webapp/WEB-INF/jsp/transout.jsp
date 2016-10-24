<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/includes.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<base target="_self">
<script type="text/javascript">
	function winBack() {
		if (window.opener) {
			transOutPhoneNo = window.opener.document
					.getElementById("transOutPhoneNo");
			transOutRegion = window.opener.document
			.getElementById("transOutRegion");
			//alert(transOutPhoneNo.value);
			
			var checkedItems = $('#dg').datagrid('getChecked');
			$.each(checkedItems, function(index, item){
				transOutPhoneNo.value = item.Id;
				transOutRegion.value = item.Region;
			});  
			window.close();
		}
	}

	function load(mode) {
		$('#dg').datagrid({
			url : 'data/ts-'+mode+'.json'
		});
	}
</script>
</head>
<body>

	<div style="margin-bottom: 10px">
		<select onchange="load(this.value)" style="width:200px">
			<option value="mzt">省民政厅</option>
			<option value="hz">杭州</option>
			<option value="nb">宁波</option>
			<option value="wz">温州</option>
			<option value="sx">绍兴</option>
			<option value="huz">湖州</option>
			<option value="jx" selected="selected">嘉兴</option>
			<option value="jh">金华</option>
			<option value="qz">衢州</option>
			<option value="tz">台州</option>
			<option value="ls">丽水</option>
			<option value="zs">舟山</option>
		</select>
		<a href="#" class="easyui-linkbutton" onclick="winBack()">确定</a>
	</div>

	<table id="dg" class="easyui-datagrid" title="智能网模块站点内码" 
		style="width: 420px; height: 300px;font-weight: bold;"
		data-options="rownumbers:true,singleSelect:true,
                url:'data/ts-jx.json',
                autoRowHeight:false,pageSize:50">
		<thead>
			<tr>
			 	<th data-options="field:'Id',checkbox:true"></th>
				<th field="Region" width="180">平台区域</th>
				<th field="Code" width="100">智能网码号</th>
			</tr>
		</thead>
	</table>
</body>
</html>
