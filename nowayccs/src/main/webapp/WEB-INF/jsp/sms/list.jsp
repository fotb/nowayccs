<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
    <script>
  function option_delete(id){
  var form=document.forms[0];
   if(confirm("确定要删除?")){
     form.action="role.do?action=delete&roleId="+id;
     form.submit();
   }
}
  </script>
</head>
<body>
<form:form action="sms.do" method="post">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="50%">角色名称</td>
            <td colspan="2">操作</td>
          </tr>
         
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="sms.do"/>
            </jsp:include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}">
  </form:form>
</body>
</html>
