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
<script type="text/javascript" src="js/function.js"></script>
<script language="javascript">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.roleName,"角色名称",true)){
    return;
  }
  form.submit();
}

function btnback_click(){
  history.back();
}
</script>

<body>
<form:form method="post" action="role.do?action=doSave">
<input type="hidden" name="pageNo" value="${pageNo}"/>
<input type="hidden" name="roleId" value="${roleVO.roleId}"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">角色信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">角色名称：</td>
            <td>
              <input name="roleName" size="70" class="form" value="${roleVO.roleName}" disabled="disabled"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%" valign="top">权限：</td>
            <td>
            	<c:forEach items="${operationList}" var="operation">
            		<c:choose>
            			<c:when test="${not empty operationIdMap[operation.operationId]}">
            				<input type="checkbox" id="operationIds" name="operationIds" disabled="disabled" checked="checked" value="${operation.operationId}" />${operation.operationName}</br>
            			</c:when>
            			<c:otherwise>
            				<input type="checkbox" id="operationIds" name="operationIds" disabled="disabled" value="${operation.operationId}" />${operation.operationName}</br>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
        	</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
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
