<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/common.js" type=""></script>
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.action="entprise.do";
  form.submit();
}
function btndelete_click(entpriseId) {
  if(confirm("删除服务企业将无法查找该企业的服务信息。是否真的删除服务企业？")) {
    var form=document.forms[0];
    form.action="entprise.do?action=delete&entpriseId="+entpriseId;
    form.submit();
  }
}

function btnactive_click(entpriseId) {
  if(confirm("是否真的重新开始加盟企业服务？")) {
	changeStatus(entpriseId, "Y");
  }
}

function btnunactive_click(entpriseId) {
  if(confirm("是否真的停止加盟企业服务？")) {
	changeStatus(entpriseId, "N");
  }
}

function changeStatus(entpriseId, newStatus) {
	$("form").attr("action", "entprise.do?action=changestatus&entpriseId=" + entpriseId + "&status=" + newStatus);
	$("form").submit();
}

$(document).ready(function(){
	$.getJSON("entprise.do?action=getCategory", {parentId: -1}, function(data) {
		$.each(data, function(i, item) {
			$("#parentCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
		});
	});

	$("#parentCategoryId").change(function() {
		$("#subParentCategoryId").empty();
		$("#subParentCategoryId").append("<option value='All'>请选择</option>");
		$("#categoryId").empty();
		$("#categoryId").append("<option value='All'>请选择</option>");
		$.getJSON("entprise.do?action=getCategory", {parentId: $("#parentCategoryId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#subParentCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
			});
		});
	});
	$("#subParentCategoryId").change(function() {
		$("#categoryId").empty();
		$("#categoryId").append("<option value='All'>请选择</option>");
		$.getJSON("entprise.do?action=getCategory", {parentId: $("#subParentCategoryId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#categoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
			});
		});
	});
});


</script>

<body>
<form:form method="post" action="/entprise.do" commandName="entSearch">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td>
              <img src="images/bgtd_01.gif" width="850" height="5" alt="">
            </td>
          </tr>
          <tr>
            <td bgcolor="#F1F1F1">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
                <tr>
                	<td width="70">企业编号：</td>
                  <td>
                    <form:input path="entpriseNo" size="15" cssStyle="form"/>
                  </td>
                  <td width="70">企业名称：</td>
                  <td>
                  	<form:input path="entpriseName" size="30" cssStyle="form"/>
                  </td>

					<td width="70">能否派单：</td>
                  <td>
                    <form:select path="servicesType">
                    	<form:option value="All">请选择</form:option>
                    	<form:option value="Y">可派单</form:option>
                    	<form:option value="N">不可派单</form:option>
                    </form:select>
                  </td>
				  <td width="10%" rowspan="2">
                    <A href="javascript:btnsearch_click()">
                      <img src="images/button_search.gif" width="60" height="18" alt="" border="0">
                    </A>
                  </td>
                </tr>
                <tr>
                <td>服务类别：</td>
                  <td colspan="2">
                  <form:select path="parentCategoryId" >
                  	<form:option value="All">请选择</form:option>
                  </form:select>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <form:select path="subParentCategoryId">
                  	<form:option value="All">请选择</form:option>
                  </form:select>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <form:select path="categoryId">
                  	<form:option value="All">请选择</form:option>
                  </form:select>
                  </td>

					<td>地址：<form:input cssClass="form" path="address" size="40"/></td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td height="10" valign="top">
              <img src="images/bgtd_02.gif" width="850" height="5" alt="">
            </td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="10%">编号</td>
            <td width="30%">企业名称</td>
            <td width="10%">联系电话</td>
            <td width="32%">地址</td>
            <td width="8%">服务项目</td>
            <c:if test="${adminRight == 'Y'}">
            <td>修改</td>
            <td>状态变更</td>
            </c:if>
          </tr>
          <c:forEach items="${entpriseList}" var="entprise">
          	<tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  ${entprise.entpriseNo}
                </td>
                <td>
                  <a href="entprise.do?action=view&entpriseId=${entprise.entpriseId}&pageNo=${pageInfo.currentPage }">
                    ${entprise.entpriseName}
                  </a>
                </td>
                <td>
                  ${entprise.linkTel}
                </td>
                <td>
                	${entprise.address }
                </td>
                <td>
                 <a href="entprise.do?action=classview&entpriseId=${entprise.entpriseId}&pageNo=${pageInfo.currentPage }">
                    <img src="images/edit.gif" alt="维护服务项目" width="11" height="14" border="0">
                  </a>
                </td>
                <c:if test="${adminRight == 'Y'}">
                <td width="5%">
                  <a href="entprise.do?action=update&entpriseId=${entprise.entpriseId}&pageNo=${pageInfo.currentPage }">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">
                 	<c:if test="${entprise.status == 'Y'}">
                  <A id="stopLink" href="javascript:btnunactive_click('${entprise.entpriseId}');">
                    <img src="images/del.gif" title="停止服务" width="11" height="14" border="0">
                  </A>
                  </c:if>
                	<c:if test="${entprise.status == 'N'}">
                  <A id="activeLink" href="javascript:btnactive_click('${entprise.entpriseId}');">
                    <img src="images/ok.gif" title="开始服务" width="11" height="14" border="0">
                  </A>
                  </c:if>
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
          	<c:if test="${adminRight == 'Y'}">
            <td height="30">
              <a href="entprise.do?action=add&pageNo=${pageInfo.currentPage}">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            </c:if>
            <td align="right">
              <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="entprise.do"/>
              </jsp:include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage }">
</form:form>
</body>
</html>
