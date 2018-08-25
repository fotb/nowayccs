<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/ccs" href="bootstrap/css/bootstrap.min.css">
	

<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap-notify.min.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">

function logout(){
  window.open("login.do?action=logout","_parent");
}




function postLog(action, logId) {
	$.getJSON("index.do?action=icdLog&actionType=" + action + "&logId=" + logId, function(data) {
		//do nothing
	});
}
$(document).ready(function(){
	function sessionHeartBeat(){
		phone_status = "1";
		phone_no = "no";

   		$.post("index.do?action=sessionHeartBeat", {status: phone_status, phoneNo: phone_no}, function(data) {
			            $('audio').attr("autoplay", "autoplay");
		});
	}
	//setInterval(sessionHeartBeat,30000);// 注意函数名没有引号和括弧！ 
	setInterval(sessionHeartBeat,5000);// 注意函数名没有引号和括弧！


	function queryAppInfo() {
		$('audio').remove();
		$.get("userAppInfo.do?action=newInfo",function(data){
			if(typeof(data.meta) != 'undefined')  {
				if(data.meta.success) {
 					var theform = document.forms[0];
					$("<audio src='audio/notify.mp3' autoplay='autoplay'></audio>").appendTo(theform);
				}
			}
		});
	}

	setInterval(queryAppInfo,10000);
});


</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <!-- <tr>
    <td style="height:115px;width:100%;border: 0px;background-image: url(images/head.jpg);background-repeat:repeat-y">
    <img src="images/head.jpg" width="100%" height="115">
    </td>
  </tr> -->
  <tr>
    <td height="24" valign="bottom" background="images/bg_top.gif">
    <table width="95%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="13%" style="vertical-align: middle;">${user.userName}，您好！</td>
        <td width="70%">
        	<div id="notify"></div>
        </td>
        <td align="center" nowrap="nowrap">
          <script language=JavaScript>
<!--
today=new Date();
var week; var date;
if(today.getDay()==0) week="星期日"
if(today.getDay()==1) week="星期一"
if(today.getDay()==2) week="星期二"
if(today.getDay()==3) week="星期三"
if(today.getDay()==4) week="星期四"
if(today.getDay()==5) week="星期五"
if(today.getDay()==6) week="星期六"
date=(today.getFullYear())+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日"+" "
document.write(""+date+week+"");
// -->
          </script></td>
        <!-- <td width="7%" valign="top"><A href="#" border=0><img src="images/topbutton_exit.gif" width="70" height="18" onclick="logout()" border=0></A></td> -->
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
