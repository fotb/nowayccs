<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.submit();
}
function option_delete(id){
  var form=document.forms[0];
  if(confirm("确定要删除?")){
    form.action="msg.do?action=del&msgId=" + id;
    form.submit();
  }
}

$(document).ready(function(){
	$("#startDt").dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d"
  //button: ".next()" //next sibling to input field
	});


	$("#endDt").dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d"
  //button: ".next()" //next sibling to input field
	});
});

</script>
<body>
<form:form method="post" action="msg.do" commandName="messageBean">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><img src="images/bgtd_01.gif" width="850" height="5" alt=""></td>
          </tr>
          <tr>
            <td bgcolor="#F1F1F1">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
                <tr>
                  <td width="100">类别：</td>
                  <td>
                    <form:select path="messageType" cssClass="form">
                      <form:option value="">选择</form:option>
                      <form:options items="${msgTypeList}" itemLabel="value" itemValue="sortIndex"/>
                    </form:select>
                  </td>
				 <td>发布人：</td>
                  <td>
					<form:select path="creator" cssClass="form">
                      <form:option value="">选择</form:option>
                      <form:options items="${users}" itemLabel="userName" itemValue="userId"/>
                    </form:select>
                  </td>
                  <td width="20%">
                    <A href="javascript:btnsearch_click()">
                      <img src="images/button_search.gif" width="60" height="18" alt="" border="0">
                    </A>
                  </td>
                </tr>
                <tr>
                <td>创建时间：</td>
                  <td colspan="5">
                   	<form:input path="startDt" cssClass="form"/>
                	&nbsp;&nbsp;到&nbsp;&nbsp;
                   	<form:input path="endDt" cssClass="form"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td height="10" valign="top"><img src="images/bgtd_02.gif" width="850" height="5" alt=""></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="10%">类别</td>
            <td width="20%">标题</td>
            <td width="40%" nowrap="nowrap">内容</td>
            <td width="10%" nowrap="nowrap">发布时间</td>
            <td width="10%">发布人</td>
            <td width="10%" colspan="2">&nbsp;</td>
          </tr>
          <c:forEach items="${msgVOList}" var="msgVO">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  ${msgTypeMap[msgVO.messageType]}
                </td>
                <td>
                  ${msgVO.title}
                </td>
                <td>
                  ${msgVO.content}
                </td>
                <td>
                  <fmt:formatDate value="${msgVO.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                  ${userMap[msgVO.creator].userName}
                </td>

                <c:if test="${msgVO.creator == currentUser.userId}">
                <td width="5%">
                  <a href="msg.do?action=edit&msgId=${msgVO.messageId}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0" style="cursor:pointer;">
                  </a>
                </td>
                <td width="5%">
                <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${msgVO.messageId}');" style="cursor:pointer;">
                </td>
                </c:if>
              </tr>
              <tr class="line">
                <td height="1" colspan="7">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="msg.do?action=add">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="" style="cursor:pointer;">
              </a></td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="msg.do"/>
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
