<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送短信</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/function.js"></script>
</head>

<script language="javascript">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.sortIndex,"代码",true)){
	    return;
	  }
	  if(!isValidStringObj(form.value,"值",true)){
    return;
  }
  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="sms.do?action=send" commandName="smsBean">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
  <td>
  <c:if test="${not empty errorMsg}">
	<span id="getsms.errors" class="error">发送短信失败，请联系管理员！</span>
</c:if>
  </td>
  </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">发送短信</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">接收手机：</td>
            <td>
            	<form:input path="phoneNum" cssClass="form" size="80"/>&nbsp;&nbsp;&nbsp;&nbsp;<span>多个号码请用<span style="font-weight:bold; color: red;">;</span>号分割</span>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">短信内容：</td>
            <td>
            <form:textarea path="smsContent" cssClass="form" rows="10" size="520" cols="100"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
            <button name="send">发送</button>
            <button name="close" onclick="window.close();">关闭</button>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
