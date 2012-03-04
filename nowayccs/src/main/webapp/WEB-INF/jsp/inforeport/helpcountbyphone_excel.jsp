<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("content-disposition","attachment;filename=求助电话量统计.xls");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
</head>

<body>
<table width="865"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
       <td>
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
      </td>
  </tr>
  
</table>
</body>
</html>
