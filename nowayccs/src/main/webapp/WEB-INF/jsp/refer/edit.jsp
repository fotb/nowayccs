<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.title,"标题",true)){
    return;
  }
  if(!isValidStringObj( form.referType,"类型",true)){
    return;
  }

  form.submit();
}

function btnback_click(){
	$("form").attr("action", "refer.do");
	$("form").submit();
}
</script>
<body>
<form:form method="post" action="refer.do?action=editsave" commandName="referVO">
<form:hidden path="referId"/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">修改业务咨询</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">标题：</td>
            <td>
              <form:input path="title" cssClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>类型：</td>
            <td>
              <form:select path="referType" cssClass="form">
				<form:options items="${zxlxDictList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
                    <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">联系电话：</td>
            <td>
              <form:input path="phone" cssClass="form" size="70"/>
            </td>
          </tr>
           <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">地址：</td>
            <td>
              <form:input path="address" cssClass="form" size="70"/>
            </td>
          </tr>
                     <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">内容：</td>
            <td>
              <form:textarea path="content" cssClass="form" cols="120" rows="10"/>
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
