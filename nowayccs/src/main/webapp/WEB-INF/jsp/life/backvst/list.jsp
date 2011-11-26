<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsearch_click(){
	$("form").submit();	
}
function option_delete(id){
  var form=document.forms[0];
  if(confirm("确定要删除?")){
    $("form").attr("action","bizlifebackvst.do?action=del&infoId="+id);
    $("form").submit();	
  }
}
</script>
<body>
<form:form method="post" action="bizlifebackvst.do">
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
                  <td>派单人：</td>
                  <td>
                    <select id="deliverer" name="deliverer" >
                      <c:forEach items="${userList}" var="user">
                      	<c:choose>
                      	<c:when test="${user.userId == deliverer}">
                      		<option value="${user.userId}" selected="selected">${user.userName}</option>
                      	</c:when>
                      	<c:otherwise>
                      		<option value="${user.userId}">${user.userName}</option>
                      	</c:otherwise>
                      </c:choose>
                      </c:forEach>
                    </select>
                  </td>
                  <td width="55%">
                    <A href="javascript:btnsearch_click()">
                      <img src="images/button_search.gif" width="60" height="18" alt="" border="0">
                    </A>
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
            <td width="10%">序号</td>
            <td width="20%">求助者姓名</td>
            <td width="15%" nowrap="nowrap">求助者电话</td>
            <td width="15%" nowrap="nowrap">求助时间</td>
            <td width="15%">派单时间</td>
            <td width="15%">&nbsp;</td>
            <td width="10%">&nbsp;</td>
          </tr>
          <c:forEach items="${infoList}" var="info" varStatus="status">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  ${status.index}
                </td>
                <td>
                  ${info.helpName}
                </td>
                <td nowrap="nowrap">
                  ${info.helpTel}
                </td>
                <td>
                  <fmt:formatDate value="${info.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                  <fmt:formatDate value="${info.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                    <a href="bizlifebackvst.do?action=backvst&infoId=${info.infoId}&pageNo=${pageInfo.currentPage}">进行回访</a>
                </td>
                <td>
					<img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${info.infoId}');" style="cursor: pointer;">
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="7">                </td>
              </tr>
            </c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">            </td>
            <td align="right">
            <jsp:include page="../../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="bizlifebackvst.do"/>
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
