<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/function.js" type="text/javascript"></script>
<script language="javascript" type="">
function btnnext_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.result,"咨询处理描述",true)){
    return;
  }

  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="bizaccept.do?action=refersave" commandName="bizAccept">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">业务咨询类</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
           <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">求助名姓名：</td>
            <td>
              ${bizAccept.helpName}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助方式：</td>
            <td>
              ${qzfsMap[bizAccept.helpMode]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
              ${bizAccept.helpTel}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td>
              ${bizAccept.helpAddr}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td>
            <textarea rows="4" cols="60" disabled="disabled">${bizAccept.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助类型：</td>
            <td>
			 ${helpTypeMap[bizAccept.helpType]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
               ${qzqyMap[bizAccept.helpArea]}
            </td>
          </tr> <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>受理人群：</td>
            <td>
            <c:if test="${not empty bizAccept.helpGroup}">${slrqMap[bizAccept.helpGroup]}</c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务者：</td>
            <td>
            ${bizAccept.creator}
            </td>
          </tr>
          <tr class="table_t1">
            <td>咨询处理描述</td>
            <td><form:textarea path="result" styleClass="form" cols="60" rows="6"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
            	<img src="images/button_pre.gif" width="60" height="18" onclick="history.back();"/>
              <img src="images/button_save.gif" width="60" height="18" onclick="btnnext_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
