<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">

var win;
function openNewWin(url){
if(win)
win.close();
win=window.open(url,null,"width=500,height=325");
}

function option_search(form){
  form.submit();
}

$(document).ready(function(){

$('tbody > tr:odd', $('#infoList')).toggleClass('table_blue'); 

	$("#startDt").dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d"
  //button: ".next()" //next sibling to input field
	});


	$("#endDt").dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d"
  //button: ".next()" //next sibling to input field
	});
});
</script>
</head>

<body>
  <form:form action="inforeport.do?action=usertrafficreport" method="post" commandName="trafficSearchBean">
<table width="865"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">

  <tr>
    <td><table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img width="850" height="5" src="images/bgtd_01.gif"/></td>
      </tr>
      <tr>
        <td bgcolor="#F1F1F1"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr valign="top" class="font_no">
            <td width="15%" height="22"><a href="inforeport.do">1.求助受理记录表</a></td>
            <td width="18%"><a href="inforeport.do?action=lifeinforeport">2.生活类服务派单表</a></td>
            <td width="15%"><a href="inforeport.do?action=affairinforeport">3.事务移送记录表</a></td>
            <td width="15%">4.话务量统计</td>
            <td width="15%"><a href="inforeport.do?action=phonecount">5.电话求助量统计</a></td>
            <td>&nbsp;</td>
          </tr>
        </table>
          <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>
             <td width="10%">工号：</td>
             <td width="20%"><form:input path="loginName" cssClass="form"/></td>
            <td width="10%">统计时间从：</td>
            <td width="40%"><form:input cssClass="form" path="startDt" size="20"/>
      到
        <form:input cssClass="form" path="endDt" size="20" />        </td>

            <td><img width="60" height="18" src="images/button_search.gif" onclick="option_search(document.forms[0])" style="cursor:hand"/></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="10" valign="top"><img width="850" height="5" src="images/bgtd_02.gif"/></td>
      </tr>
    </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr class="table_green">
        <td>工号</td>
        <td>姓名</td>
        <td>咨询服务类</td>
        <td>生活服务类</td>
        <td>事务服务类</td>
        <td>生产力服务</td>
        <td>电力服务</td>
        <td>结对家庭服务</td>
        <td>总计</td>
        </tr>
		<c:forEach items="${dtoList}" var="dto">
        <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
        <td>${dto.loginName}</td>
        <td>${dto.userName}</td>
        <td>${dto.referTraffic}</td>
        <td>${dto.lifeTraffic}</td>
        <td>${dto.affairTraffic}</td>
        <td>${dto.sclTraffic}</td>
        <td>${dto.powerTraffic}</td>
        <td>${dto.jdjtTraffic}</td>
        <td>${dto.totalTraffic}</td>
        </tr>
      <tr class="line">
        <td height="1" colspan="8"></td>
        </tr>
	  </c:forEach>
    </table>
      </td>
  </tr>

</table>
 </form:form>
</body>
</html>
