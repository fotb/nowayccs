<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.loginName,"登录名",true)){
    return;
  }
  if(!isValidStringObj( form.loginPassword,"登录密码",true)){
    return;
  }
  if(form.loginPassword.value!=form.doublePassword.value){
    alert("两次密码输入不一致，请重新输入");
    form.loginPassword.focus();
    return;
  }
  if(!isValidStringObj( form.userName,"用户名称",true)){
    return;
  }
  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
</head>
<body>
<form:form method="post" action="user.do?action=addsave" commandName="user">
<input type="hidden" name="pageNo" value="${pageNo}" />
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">新增用户</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>          
          <tr class="table_t1">
            <td width="15%">登录名：</td>
            <td>
            	<form:input path="loginName" cssClass="form" size="20" maxLength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><form:errors path="loginName"/></font>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">登录密码：</td>
            <td>
            	<form:input path="loginPassword" cssClass="form" szie="20" maxLength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><form:errors path="loginPassword"/></font>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">再输一遍密码：</td>
            <td>
            <form:input path="doublePassword" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">用户姓名：</td>
            <td>
            <form:input path="userName" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭电话：</td>
            <td>
            <form:input path="homeTel" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭住址：</td>
            <td>
            <form:input path="homeAddr" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭邮编：</td>
            <td>
            <form:input path="homePostCode" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">单位电话：</td>
            <td>
            <form:input path="officeTel" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">单位地址：</td>
            <td>
            <form:input path="officeAddr" cssClass="form" szie="30" maxLength="30"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">单位邮编：</td>
            <td>
            <form:input path="officePostCode" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">联系手机：</td>
            <td>
            <form:input path="linkMobile" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">邮件地址：</td>
            <td>
            <form:input path="emailAddr" cssClass="form" szie="20" maxLength="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%" valign="top">角色：</td>
            <td>
            <form:checkboxes items="${roleVOList}" path="roleIds" itemLabel="roleName" itemValue="roleId" delimiter="<br/>"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
              <img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click()"/>
              <img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
