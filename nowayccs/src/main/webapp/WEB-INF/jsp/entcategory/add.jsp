<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>嘉兴社区服务中心</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/function.js"></script>
</head>
<script type="text/javascript">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.value,"企业类别名称",true)){
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
<form:form action="entcategory.do?action=addsave" method="post" commandName="entCategory">
<form:hidden path="parentId"/>
<form:hidden path="subParentId"/>
<table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="table_t1">
          <td width="3%" align="center">
            <img src="images/icon_01.gif" width="5" height="17" alt="">
          </td>
          <td class="font_no">新增企业类别信息</td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr class="line">
          <td height="1" colspan="2">          </td>
        </tr>
        <tr class="table_t1">
          <td width="20%">企业类别名称：</td>
          <td>
          	<form:input path="value" cssClass="form" maxlength="45"/>
          </td>
        </tr>
        <tr class="line">
          <td height="1" colspan="2">          </td>
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
