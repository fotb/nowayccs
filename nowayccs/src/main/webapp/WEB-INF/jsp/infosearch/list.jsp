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
  form.action="infosearch.do";
  form.submit();
}

function showLifeInfo(id) {
  var form=document.forms[0];
  form.action="infosearch.do?action=lifeinfo&infoId=" + id;
  form.submit();
}
function showReferInfo(id) {
  var form=document.forms[0];
  form.action="infosearch.do?action=referinfo&infoId=" + id;
  form.submit();
}

function showProductivityInfo(id) {
  var form=document.forms[0];
  form.action="infosearch.do?action=productivityinfo&infoId=" + id;
  form.submit();
}

function showAffairInfo(id) {
  var form=document.forms[0];
form.action="infosearch.do?action=affairinfo&infoId=" + id;
  form.submit();
}

function showPowerInfo(id) {
  var form=document.forms[0];
	form.action="infosearch.do?action=powerinfo&infoId=" + id;
  form.submit();
}

function playRecord(infoId) {
  var form=document.forms[0];
  form.action="record.do?infoId=" + infoId;
  form.submit();
}

$(document).ready(function(){
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
<form:form method="post" action="infosearch.do" commandName="infoSearchBean">
<table width="865"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td><table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img width="850" height="5" src="images/bgtd_01.gif"/></td>
      </tr>
      <tr>
        <td bgcolor="#F1F1F1"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>
            <td nowrap="nowrap">求助时间：</td>
            <td colspan="4"><form:input cssClass="form" path="startDt" size="15" /> 到 <form:input cssClass="form" path="endDt" size="15" /></td>
            </tr>
          <tr class="line">
            <td height="2" colspan="5">            </td>
          </tr>
            <tr>
            <td>求助电话：</td>
            <td><form:input cssClass="form" path="helpTel" size="20"/></td>
            <td>居住地址：</td>
            <td><form:input cssClass="form" path="address" size="30"/></td>
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
        <td>求助者姓名</td>
        <td>求助时间</td>
        <td>联系电话</td>
        <td>详细地址</td>
        <td>求助内容</td>
        <td>求助类别</td>
        <td>输入人</td>
        <td>结案时间</td>
        <td>满意度</td>
        <td>状态</td>
        <!-- 
        <c:if test="${adminRight == 'Y'}">
        <td>播放<br>录音</td>
        </c:if>
         -->
      </tr>

<c:forEach items="${dtoList}" var="dto">
      <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
        <td>
        <c:if test="${dto.helpType == '2'}">
        <a href="javascript:showLifeInfo('${dto.infoId}')">
        ${dto.helpName}
        </a>

        </c:if>
        <!--咨询类-->
        <c:if test="${dto.helpType=='1'}">
        <a href="javascript:showReferInfo('${dto.infoId}')">
        ${dto.helpName}
        </a>
        </c:if>

        <!--事务服务类-->
                <c:if test="${dto.helpType=='3'}">
        <a href="javascript:showAffairInfo('${dto.infoId}')">
        ${dto.helpName}
        </a>
        </c:if>
        <!--生产力服务-->
        <c:if test="${dto.helpType=='4'}">
        <a href="javascript:showProductivityInfo('${dto.infoId}')">
        ${dto.helpName}
        </a>
        </c:if> 
        <!--电力服务类-->
        <c:if test="${dto.helpType=='5'}">
        <a href="javascript:showPowerInfo('${dto.infoId}')">
        ${dto.helpName}
        </a>
        </c:if>

        </td>
        <td><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${dto.helpTel}</td>
        <td>${dto.address}</td>
        <td>${dto.helpContent}</td>
         <td>${dto.helpTypeValue}</td>
        <td>${dto.creator}</td>
        <td><fmt:formatDate value="${dto.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td><c:if test="${dto.callResult == ''}">&nbsp;</c:if><c:if test="${dto.callResult != ''}">${dto.callResult}</c:if></td>
        <td>${dto.status}</td>
       <!-- 
        <c:if test="${adminRight == 'Y'}">
        <td>
       	<a href="javascript:playRecord('${dto.infoId}')">
        <img alt="播放录音" src="images/play.png" width="20px" border="0">
        </a>
       	</td>
       	</c:if>
       	 -->
      </tr>
      <tr class="line">
        <td height="1" colspan="11"></td>
        </tr>
</c:forEach>
    </table>
      <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30" align="right"> 
          	  <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="infosearch.do"/>
            </jsp:include> </td>

        </tr>
      </table></td>
  </tr>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}" />
</table>
</form:form>
</body>
</html>
