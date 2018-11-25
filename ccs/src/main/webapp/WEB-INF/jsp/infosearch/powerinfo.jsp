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
function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="infosearch.do?action=powerinfo">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">电力服务信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
           <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td nowrap="nowrap" >求助者姓名：</td>
            <td>
              ${infoVO.helpName}
            </td>            
            <td width=100>求助方式：</td>
            <td>
			${qzfsMap[infoVO.helpMode]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
              ${infoVO.helpTel}
            </td>
            <td>详细地址：</td>
            <td>
              ${infoVO.helpAddr}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td colspan="3">
              <textarea id="helpContent" class="form" cols="120" rows="4" disabled="disabled">${infoVO.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
			${areaMap[infoVO.helpArea]}
            </td>
            <td>服务者：</td>
            <td>${dealer.userName}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>电工：</td>
            <td>${psVO.name }</td>
            <td>联系电话：</td>
            <td>${psVO.phone }</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>          
          <tr class="table_t1">
            <td>区域：</td>
            <td colspan="3">${areaVO.name } - ${asVO.name }</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
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
