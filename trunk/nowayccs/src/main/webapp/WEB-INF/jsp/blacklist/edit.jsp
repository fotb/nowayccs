<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/function.js"></script>
</head>

<script language="javascript">
function btnsave_click(){
	if("" == $("#phoneNum").val()) {
		alert("电话号码不能为空！");
		return;
	}
	$("form").submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="blacklist.do?action=editsave" commandName="blackListVO">
<input type="hidden" name="pageNo" value="${pageNo}" />
<form:hidden path="phoneId"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">新增电话号码</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">电话号码：</td>
            <td>
            	<form:input path="phoneNum" cssClass="form" size="20" readonly="true"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">等级：</td>
            <td>
            <form:select path="levels">
	            <form:option value="-5" >-5</form:option>
	            <form:option value="-4" >-4</form:option>
	            <form:option value="-3" >-3</form:option>
	            <form:option value="-2" >-2</form:option>
	            <form:option value="-1" >-1</form:option>
            	<form:option value="0" >0</form:option>
            	<form:option value="1" >1</form:option>
            	<form:option value="2" >2</form:option>
            	<form:option value="3" >3</form:option>
            	<form:option value="4" >4</form:option>
            	<form:option value="5" >5</form:option>
            </form:select>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">备注：</td>
            <td>
            <form:textarea path="remark" cssClass="form"/>
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
