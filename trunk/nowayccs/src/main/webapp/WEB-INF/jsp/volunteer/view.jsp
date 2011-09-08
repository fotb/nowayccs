<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="cssandjs/function.js" type="text/javascript"></script>
<script language="javascript" type="">
function btnback_click(){
  history.back();
}
</script>
<body>
<html:form method="post" action="SysVolunteerAdmin_editsave.do">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">修改服务者信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>所在街道和社区：</td>
            <td width="85%">
              <bean:write name="streetAreaName"/>
              <bean:write name="commAreaName"/>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>编号：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="volunteerNo"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>姓名：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="volunteerName"/></td>
          </tr>
		  <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>性别：</td>
            <td>
              <c:out value="${SYS_SEX_HASHMAP[sysVolunteerAdminForm.sex]}">
              </c:out>
            </td>
          </tr>
		  <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>服务项目：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="sevriceName"/></td>
          </tr>
		  <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>服务时间：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="serviceTime"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="linkTel"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>联系手机：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="linkMobile"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>邮件地址：</td>
            <td><bean:write name="sysVolunteerAdminForm" property="email"/></td>
          </tr>
		  <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
          <tr class="table_t1">
            <td>其他：</td>
            <td><textarea  cols="130" rows="5" class="form" readonly><bean:write name="sysVolunteerAdminForm" property="remark"/></textarea></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>

          <tr align="center" class="table_t1">
            <td colspan="4">
              <html:img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <html:hidden property="volunteerID"/>
</html:form>
</body>
</html>
