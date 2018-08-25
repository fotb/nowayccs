<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台任务管理</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	loadAll();
});

function loadAll() {
    $('#datagrid').datagrid({
        url:"quartz.do?action=all",
        columns:[[
            {field:'jobName',title:'任务名', width:90},
            {field:'jobGroupName',title:'任务组名', width:140},
            {field:'triggerName',title:'触发器名',width:100,align:'center'},
            {field:'triggerGroupName',title:'触发器组名',width:200,align:'center'},
            {field:'jobClass',title:'任务',width:300,align:'left'},
            {field:'cron',title:'时间设置',width:80,align:'center'},
            {field:'status',title:'状态',width:80,align:'center'},
            {field:'pid',title:'操作',width:80,align:'right', formatter:formatOper}
        ]],
		method:'post',
		//fit: true,   //自适应大小
		cache:false,
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
	  return '<a href="quartz.do?action=start&pid='+row["pid"]+'" target="_blank">开启</a>';  
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
	<table id="datagrid"></table>
</body>
</html>
