<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务受理</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	loadUserAppInfo();
});

function loadUserAppInfo() {
    $('#elevHelpInfo').datagrid({
        url:"elevbackvst.do?action=get",
        columns:[[
            {field:'helpName',title:'求助者姓名', width:90},
            {field:'createTime',title:'求助时间', width:140},
            {field:'helpTel',title:'求助电话',width:100,align:'center'},
            {field:'helpAddr',title:'详细地址',width:200,align:'center'},
            {field:'helpContent',title:'求助内容',width:300,align:'left'},
            {field:'helpType',title:'求助类别',width:80,align:'center'},
            {field:'helpType',title:'求助类别',width:80,align:'center'},
            {field:'helpType',title:'求助类别',width:80,align:'center'},
            {field:'helpType',title:'求助类别',width:80,align:'center'},
            
            {field:'pid',title:'操作',width:80,align:'right', formatter:formatOper}
        ]],
		method:'get',
		fit: true,   //自适应大小
		border:false,
		nowrap: true,//数据长度超出列宽时将会自动截取。0
		rownumbers:true,//行号
		fitColumns:true,//自动使列适应表格宽度以防止出现水平滚动。
	//pagination:true,
		//pageSize:10,
		singleSelect: true
    });
}

function formatOper(val,row,index) {
	  return '<a href="bizaccept.do?action=app&appInfoId='+row["pid"]+'" target="_blank">回访</a>';  
}

function myformatter(value){
	 var year = value.year+1900;
     var month = value.month+1;
     var day = value.date;
     month=(month>9)?(""+month):("0"+month);  //如果得到的数字小于9要在前面加'0'
     day=(day>9)?(""+day):("0"+day);
     
     var hour=value.hours;
     var minute=value.minutes;
     var seconds=value.seconds;
     hour=(hour>9)?(""+hour):("0"+hour);
     minute=(minute>9)?(""+minute):("0"+minute);
     seconds=(seconds>9)?(""+seconds):("0"+seconds);

     return year + "-" + month + "-" + day + " " + hour+":"+minute + ":" + seconds;
}

</script>
<body>
	<table id="elevHelpInfo"></table>
</body>
</html>
