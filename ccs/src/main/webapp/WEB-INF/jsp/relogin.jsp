
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>嘉兴社区服务中心</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">

function relogin() {
	window.open("login.do","_parent");
}
</script>
<body>
<form:form  method="post" action="login.do?action=relogin">
<table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr class="line">
          <td height="1">          </td>
        </tr>
        <tr class="table_t1">
          <td class="table_t1" >
            	请重新登录系统！
          </td>
        </tr>
        <tr class="line">
          <td height="1">          </td>
        </tr>
        <tr align="center" class="table_t1">
          <td>
            <img src="images/login_button.jpg" alt="重新登录" width="92" height="19" border="0" style="cursor: pointer;" onclick="relogin();"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
