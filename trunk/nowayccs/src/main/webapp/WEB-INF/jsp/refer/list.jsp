<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.action="refer.do";
  form.submit();
}
function btndelete_click(referId) {
  var form=document.forms[0];
  if(confirm("是否真的要删除记录？")) {
    $("form").attr("action", "refer.do?action=del&referId=" + referId);
    $("form").submit();
  }
}
</script>
<body>
<form:form method="post" action="refer.do">
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
                  <td>标题：</td>
                  <td>
                    <input type="text" name="title" id="title" class="form" size="40" value="${title}"/>
                  </td>
                  <td>类型：</td>
                  <td>
                    <select name="referType" class="form">
                      <option value="">全部</option>
                      <c:forEach items="${zxlxDictList}" var="dict">
                      	<c:choose>
                      		<c:when test="${referType == dict.sortIndex }">
                      			<option value="${dict.sortIndex}" selected="selected">${dict.value}</option>
                      		</c:when>
                      		<c:otherwise>
                      			<option value="${dict.sortIndex}">${dict.value}</option>
                      		</c:otherwise>
                      	
                      	</c:choose>
                      </c:forEach>
                    </select>
                  </td>
                  <td width="20%">
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
            <td width="30%">标题</td>
            <td width="20%">联系电话</td>
            <td width="25%">地址</td>
            <td width="10%">类型</td>
            <c:if test="${adminRight == 'Y'}">
              <td colspan="2">操作</td>
            </c:if>
          </tr>

			<c:forEach items="${referList}" var="refer">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  <A href="refer.do?action=view&referId=${refer.referId}&pageNo=${pageInfo.currentPage}">
                    ${refer.title}
                  </A>
                </td>
                 <td>
                  ${refer.phone }
                </td>
				<td>
                  ${refer.address}
                </td>
                <td>
                  ${zxlxDictMap[refer.referType]}
                </td>
                <c:if test="${adminRight == 'Y'}">
                  <td width="5%">
                    <a href="refer.do?action=edit&referId=${refer.referId}&pageNo=${pageInfo.currentPage}">
                      <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                    </a>
                  </td>
                  <td width="5%">
                    <A href="javascript:btndelete_click('${refer.referId}')" >
                      <img src="images/del.gif" alt="删除" width="11" height="14" border="0">
                    </A>
                  </td>
                </c:if>
              </tr>
              <tr class="line">
                <td height="1" colspan="6">                </td>
              </tr>
			</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <c:if test="${adminRight == 'Y'}">
                <a href="refer.do?action=add&pageNo=${pageInfo.currentPage}">
                  <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
                </a>
              </c:if>
            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="refer.do"/>
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
