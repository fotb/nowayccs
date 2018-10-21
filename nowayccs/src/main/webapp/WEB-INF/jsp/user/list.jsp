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
<script type="text/javascript">
  function option_delete(id){
  var form=document.forms[0];
   if(confirm("确定要删除?")){
     form.action="user.do?action=del&userId="+id;
     form.submit();
   }
}

function option_modiOnJob(id, onJob) {
  var form = document.forms[0];
  if(onJob == 1) {
    if(confirm("确定要设置当前工作人员的状态为离职吗?")){
		form.action="user.do?action=onjob&userId="+id+"&onJob=0";
		form.submit();
    }
  } else {
	if(confirm("确定要恢复当前工作人员的状态为在职吗?")){
    	form.action="user.do?action=onjob&userId="+id+"&onJob=1";
    	form.submit();
    }
  }
}

  </script>
</head>
<body>
<form:form method="POST" action="user.do?action=gotopage">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
          <td width="15%">名称</td>
          <td width="15%">登录名</td>
          <td width="25%">地址</td>
          <td width="10%">电话</td>
          <td width="10%">状态</td>
          <td colspan="3">操作</td>
        </tr>
        <c:forEach items="${userList}" var="user">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  <A href="user.do?action=view&userId=${user.userId}&pageNo=${pageInfo.currentPage}">${user.userName}</A>
                </td>
                <td>
                  ${user.loginName}
                </td>
                <td>
                	${user.officeAddr}
                </td>
                <td>
                	${user.officeTel}
                </td>
                <td>
                  <c:if test="${'1' == user.onJob}">
                  	在职
                  </c:if>
                  <c:if test="${'0' == user.onJob}">
                    <font color="red">停职</font>
                  </c:if>
                </td>


                <td width="5%">
                  <a href="user.do?action=edit&userId=${user.userId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">


                <c:if test="${'1' == user.onJob}">
                  <A href="javascript:option_modiOnJob('${user.userId}', '${user.onJob}');">
                    <img src="images/stop.gif" alt="停职" width="14" height="14" border="0" style="cursor:hand">
                  </A>
                  </c:if>
                <c:if test="${'0' == user.onJob}">
                  <A href="javascript:option_modiOnJob('${user.userId}', '${user.onJob}');">
                    <img src="images/ok.gif" alt="在职" width="11" height="14" border="0" style="cursor:hand">
                  </A>
                  </c:if>
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="7">                </td>
              </tr>
          </c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="user.do?action=add&pageNo=${pageInfo.currentPage}">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="user.do?action=gotopage"/>
            </jsp:include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}">
</form:form>
</body>
</html>
