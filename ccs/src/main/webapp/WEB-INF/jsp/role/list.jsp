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
    <script>
  function option_delete(id){
  var form=document.forms[0];
   if(confirm("确定要删除?")){
     form.action="role.do?action=delete&roleId="+id;
     form.submit();
   }
}
  </script>
</head>
<body>
<form action="" method="post">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="50%">角色名称</td>
            <td colspan="2">操作</td>
          </tr>
          <c:forEach items="${roleList}" var="role">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  <A href="role.do?action=view&roleId=${role.roleId}&pageNo=${pageInfo.currentPage}">
                    ${role.roleName}
                  </A>
                </td>
                <td width="5%">
                  <a href="role.do?action=edit&roleId=${role.roleId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">

                    <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${role.roleId}');" style="cursor:pointer">

                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="4">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="role.do?action=add">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="role.do"/>
            </jsp:include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}">
  </form>
</body>
</html>
