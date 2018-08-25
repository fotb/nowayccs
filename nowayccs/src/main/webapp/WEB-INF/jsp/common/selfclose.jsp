<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="">
function closeit() {
    //window.setTimeout("self.close()",3000) //毫秒
window.setTimeout("closeSelf();",3000) //毫秒
}

function closeSelf() {
  window.open('','_parent',''); 
  window.close(); 
}
</script>
<body onload="closeit()">
<table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr class="line">
          <td height="1">          </td>
        </tr>
        <tr class="table_t1">
          <td class="table_t1" >
            	操作成功，请关闭页面！(页面3秒后自动关闭!)
          </td>
        </tr>
        <tr class="line">
          <td height="1">          </td>
        </tr>
        <tr align="center" class="table_t1">
          <td>
          <button name="关闭" onclick="closeSelf();" value="关闭">关闭</button>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>

