<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>嘉兴社区服务中心</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function login() {
  $("form").submit();
}

function handlekeydown(){
/*
  if (window.event.keyCode ==13){
     $("form").submit();
  }
*/
}

$(document).keypress(function(e) {
	if(e.which == 13) {
		$("form").submit();
	}
});
</script>
<body topmargin="0" leftmargin="0">
<form:form action="login.do?action=login" method="post" commandName="loginBean">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0" background="images/bg_login.gif">
  <tr>
    <td><table width="683" height="408"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="bottom" background="images/login.jpg">
          <table width="70%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="20%" height="30" class="leftmenu_01">用户帐号：</td>
              <td width="30%"><form:input path="loginName" cssClass="form" size="25" onkeydown="handlekeydown()"/></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="30" class="leftmenu_01">登录密码：</td>
              <td><form:password path="loginPassword" cssClass="form" size="25" onkeydown="handlekeydown()"/></td>
              <td>&nbsp;</td>
            </tr>
			<tr>
              <td height="15" colspan="3"><font color="red">
              <form:errors path="loginName"/>
              <form:errors path="loginPassword"/>
              </font>
              </td>
              </tr>
            <tr align="center">
              <td height="30" colspan="2" valign="bottom"><a href="javascript:login()"><img src="images/login_button.jpg" width="92" height="19" border="0"></a></td>
              <td height="20" valign="bottom">&nbsp;</td>
            </tr>
            <tr>
              <td height="65" colspan="3">&nbsp;</td>
              </tr>
            <tr>
              <td height="30" colspan="3" class="font_inscribed">Copyright&copy;版权所有：嘉兴市南湖区服务中心</td>
              </tr>
          </table>
                  </td>
      </tr>
    </table></td>
  </tr>
</table>
</form:form>
</body>
</html>
