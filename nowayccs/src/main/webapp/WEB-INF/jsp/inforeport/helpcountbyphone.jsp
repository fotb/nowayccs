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

function option_exp(form){
  form.action="inforeport.do?action=phonecountexp";
  form.target="_blank";
  form.submit();
  form.target="_self";
}

function option_search(form){
  form.submit();
}

function showInfo(id) {
var form=document.forms[0];
form.action="infosearch.do?action=affairinfo&infoId=" + id;
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
<form:form action="inforeport.do?action=phonecount" method="post" commandName="helpCountByPhoneSearchBean">
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
            <td width="15%"><a href="inforeport.do?action=usertrafficreport">4.话务量统计</a></td>
            <td width="15%">5.电话求助量统计</td>
            <td>&nbsp;</td>
          </tr>
        </table>
          <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>
            <td width="10%">时间从：</td>
            <td width="40%"><form:input cssClass="form" path="startDt" size="20"/>
      到
        <form:input cssClass="form" path="endDt" size="20"/>        </td>
            <td><img width="60" height="18" src="images/button_search.gif" onclick="option_search(document.forms[0]);" style="cursor:hand"/></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="10" valign="top"><img width="850" height="5" src="images/bgtd_02.gif"/></td>
      </tr>
    </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr class="table_green">
        <td>序号</td>
        <td>求助电话</td>
        <td>求助次数</td>
      </tr>

	<c:forEach items="${countList}"  var="dto" varStatus="index">
      <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
        <td width="60">${index.index + 1 + pageInfo.PAGE_COUNT*(pageInfo.currentPage-1)}</td>
        <td width="200" align="right">${dto.helpTel}</td>
        <td>${dto.telCount}</td>
      </tr>
      <tr class="line">
        <td height="1" colspan="10"></td>
        </tr>
    </c:forEach>
    </table>
      <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
      	<td><img width="80" height="18" src="images/button_dateexp.gif" onclick="option_exp(document.forms[0]);" style="cursor:hand"/></td>
          <td height="30" align="right"> <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="inforeport.do?action=phonecount"/>
            </jsp:include> </td>
        </tr>
      </table></td>
  </tr>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}" />
  
</table>
</form:form>
</body>
</html>
