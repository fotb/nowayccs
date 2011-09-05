<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/common.js" type=""></script>
<script language="javascript" type="">
function btnsave_click() {
  var form = document.forms[0];
  form.action = "/SysEntpriseAdmin_editviewsave.do";
  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body bgcolor="#ffffff">

<html:form method="post" action="SysEntpriseAdmin_classview.do">
<input type="hidden" name="entpriseID" value="<bean:write name='hjEntprise' property='entpriseId' />"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">企业服务项目：</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="class_1">
            <td width="10%">企业名称：</td>
            <td >
            <bean:write name="hjEntprise" property="entpriseName"/>
            </td>
          </tr>
                    <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          </table>

         <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <logic:iterate id="list" name="coeDTOList">
          <tr class="table_green">
            <td colspan="2" align="left">
              <bean:write name="list" property="hjEntClass.entClassName"/>
            </td>
          </tr>
          <logic:iterate id="list2" name="list" property="coeList">
          <tr class="class_1">
            <td width="10%">
              &nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="list2" property="hjEntClass.entClassName"/>
            </td>
            <td>
            <logic:iterate id="list3" name="list2" property="coeList">
            <input type="checkbox" name="entClassId" value="<bean:write name='list3' property='entClassId'/>"
            <logic:iterate id="coeList" name="hjcoeList">
            <c:out value="${coeList.hjEntclass.entClassId}">
            </c:out>
            <c:out value="${list3.entClassId}">
            </c:out>
            <c:choose>
            <c:when test="${list3.entClassId == coeList.hjEntclass.entClassId}">
            checked
            </c:when>
            </c:choose>
            </logic:iterate>
            /><bean:write name="list3" property="entClassName"/>

            </logic:iterate>
             </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          </logic:iterate>
          </logic:iterate>

          <tr align="center" class="table_t1">
            <td colspan="4">
              <html:img src="images/button_save.gif" alt="保存" width="60" height="18" border="0" onclick="btnsave_click()"/>
              <html:img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</html:form>

</body>
</html>
