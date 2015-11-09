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

function option_exp(form){
  form.action="QryReport_exp.do";
  form.target="_blank";
  form.submit();
  form.target="_self";
}

function option_search(form){
  form.action="inforeport.do";
  //form.target="_self";
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


	$.getJSON("inforeport.do?action=infocount", {creator: "${infoSearchBean.creator}", helpType: "${infoSearchBean.helpType}", helpArea: "${infoSearchBean.helpArea}", helpGroup: "${infoSearchBean.helpGroup}", startDt: "${infoSearchBean.startDt}", endDt: "${infoSearchBean.endDt}", helpContent: "${infoSearchBean.helpContent}"}, function(data) {
$("#count").html(data.count);
$("#lifecount").html(data.lifeCount);
$("#affaircount").html(data.affairCount);
$("#refercount").html(data.referCount);
$("#fertilitycount").html(data.fertilityCount);
$("#total").html(data.total);
$("#life").html(data.life);
$("#refer").html(data.refer);
$("#affair").html(data.affair);
$("#fertility").html(data.fertility);

$("#jdjt").html(data.jdjt);
$("#jdjtcount").html(data.jdjtcount);

	});
});
</script>
</head>
<body>
<form:form action="inforeport.do" method="post" commandName="infoSearchBean">
<table width="865"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td><table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img width="850" height="5" src="images/bgtd_01.gif"/></td>
      </tr>
      <tr>
        <td bgcolor="#F1F1F1"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr valign="top" class="font_no">
            <td width="15%" height="22">1.求助受理记录表</td>
            <td width="18%"><a href="inforeport.do?action=lifeinforeport">2.生活类服务派单表</a></td>
            <td width="15%"><a href="inforeport.do?action=affairinforeport">3.事务移送记录表</a></td>
            <td width="15%"><a href="inforeport.do?action=usertrafficreport">4.话务量统计</a></td>
			<td width="15%"><a href="inforeport.do?action=phonecount">5.电话求助量统计</a></td>
            <td>&nbsp;</td>
          </tr>
        </table>
          <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>
            <td>受理人：</td>
            <td>
            <form:select path="creator" cssClass="form">
				<form:option value="">全部</form:option>
				<form:options items="${users}" itemLabel="userName" itemValue="userId"/>
            </form:select>
            </td>
            <td>求助类型：</td>
            <td><form:select cssClass="form" path="helpType">
                <form:option value="">全部</form:option>
				<form:options items="${helpTypeMap}"/>
            </form:select></td>
            <td>求助区域：</td>
            <td width="20%"><form:select cssClass="form" path="helpArea">
                <form:option value="">全部</form:option>
                <form:options items="${helpAreaList}" itemLabel="value" itemValue="sortIndex"/>
            </form:select></td>
            <td>求助方式：</td>
              <form:select path="helpMode" cssClass="form">
              <form:option value="">全部</form:option>
              <form:options items="${qzfsList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            <td>受理人群：</td>
            <td width="20%"><form:select cssClass="form" path="helpGroup">
                <form:option value="">全部</form:option>
                <form:options items="${slrqList}"  itemLabel="value" itemValue="sortIndex"/>
            </form:select></td>
          </tr>
          <tr>
            <td>求助时间从：</td>
            <td colspan="3"><form:input cssClass="form" path="startDt" size="20"/>
      到
        <form:input cssClass="form" path="endDt" size="20"/></td>
            
          <td>求助内容</td>
          <td><form:input cssClass="form" path="helpContent" size="30"/></td>
          <td><img width="60" height="18" src="images/button_search.gif" onclick="option_search(document.forms[0]);" style="cursor:pointer;"/></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="10" valign="top"><img width="850" height="5" src="images/bgtd_02.gif"/></td>
      </tr>
    </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0" id="infoList">
      <tr class="table_green">
        <td>序号</td>
        <td>求助者姓名</td>
        <td>求助时间</td>
        <td>求助内容</td>
        <td>联系电话</td>
        <td>求助类型</td>
        <td>接线员</td>
        <td>满意度</td>

        <td>状态</td>
      </tr>
	 <tbody>
      <c:forEach items="${dtoList}"  var="dto" varStatus="index">
      <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
        <td>${index.index + 1 + pageInfo.PAGE_COUNT*(pageInfo.currentPage-1)}</td>
        <td><a href="infosearch.do?action=showinfo&infoId=${dto.infoId}" target="_blank">${dto.helpName}</a></td>
        <td><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${dto.helpContent}</td>
        <td>${dto.helpTel}</td>
        <td>${dto.helpTypeValue}</td>
        <td>${dto.creator}</td>
        <td><c:if test="${dto.callResult == ''}">-</c:if><c:if test="${dto.callResult != ''}">${dto.callResult}</c:if></td>
        <td>${dto.status}</td>
      </tr>
	  <!-- <tr class="line">
        <td height="1" colspan="9"></td>
        </tr> -->
        </c:forEach>
        </tbody>
    </table>
      <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
       <!--  <td><img width="80" height="18" src="images/button_dateexp.gif" onclick="option_exp(document.forms[0]);" style="cursor:pointer;"/></td> -->
          <td height="30" align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="inforeport.do"/>
            </jsp:include> </td>

        </tr>
        <tr>
          <td height="20" colspan="2">合计：<span id="count"></span>件 生活服务类：<span id="lifecount"></span>件  咨询类：<span id="refercount"></span>件  事务类：<span id="affaircount"></span>件 生产力服务类：<span id="fertilitycount"></span>件 结对家庭服务类：<span id="jdjtcount"></span>件<!--取消：<bean:write name="hjdelete"/>件--></td>
        </tr>
        <tr>
          <td height="20" colspan="2">总计：<span id="total"></span>件 生活服务类：<span id="life"></span>件  咨询类：<span id="refer"></span>件  事务类：<span id="affair"></span>件 生产力服务类：<span id="fertility"></span>件 结对家庭服务类：<span id="jdjt"></span>件<!--取消：<bean:write name="hjdelete"/>件--></td>
        </tr>
      </table></td>
  </tr>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}" />
</table>
</form:form>
</body>
</html>
