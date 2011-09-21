<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<base target="_self">
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.action="bizlife.do?action=receiver";
  form.submit();
}

function btnselect_click(receiverType,receiverID,linkName,linkTel){
  var lifeHandInfo = new LifeHandInfo();
  lifeHandInfo.receiverType=receiverType;
  lifeHandInfo.receiverID=receiverID;
  lifeHandInfo.linkName=linkName;
  lifeHandInfo.linkTel=linkTel;
  window.returnValue=lifeHandInfo;
  window.close();
}

$(document).ready(function(){
	$.getJSON("json.do?action=entcategory", {parentId: -1}, function(data) {
		$("#bigEntCategoryId").append("<option value=' '>请选择</option>");
		$.each(data, function(i, item) {
			if(item.categoryId == "${receiverSearchDomain.bigEntCategoryId}") {
				$("#bigEntCategoryId").append("<option value=" + item.categoryId + " selected='selected'>" + item.value + " </option>");
			} else {
				$("#bigEntCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
			}
		});
	});

	if("" != "${receiverSearchDomain.bigEntCategoryId}") {
		loadSubEntCategory(${receiverSearchDomain.bigEntCategoryId});
	}

	$("#bigEntCategoryId").change(function() {
		$("#subEntCategoryId").empty();
		$("#subEntCategoryId").append("<option value=' '>请选择</option>");
		$("#entCategoryId").empty();
		$("#entCategoryId").append("<option value=' '>请选择</option>");
		$.getJSON("json.do?action=entcategory", {parentId: $("#bigEntCategoryId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#subEntCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
			});
		});
		$("form").submit();
	});

	if("" != "${receiverSearchDomain.subEntCategoryId}") {
		loadEntCategory(${receiverSearchDomain.subEntCategoryId});
	}

	$("#subEntCategoryId").change(function() {
		$("#entCategoryId").empty();
		$("#entCategoryId").append("<option value=' '>请选择</option>");
		$.getJSON("json.do?action=entcategory", {parentId: $("#subEntCategoryId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#entCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
			});
		$("form").submit();
		});
		
	});

	$("#entCategoryId").change(function() {
		$("form").submit();
	});

	$.getJSON("json.do?action=area", function(data) {
		$("#areaId").append("<option value=' '>全部</option>");
		$.each(data, function(i, item) {
			if(item.areaId == "${receiverSearchDomain.areaId}") {
				$("#areaId").append("<option value=" + item.areaId + " selected='selected'>" + item.name + " </option>");
			} else {
				$("#areaId").append("<option value=" + item.areaId + ">" + item.name + " </option>");
			}
		});
	});
	if("" != "${receiverSearchDomain.areaId}") {
		loadAreaSub(${receiverSearchDomain.areaId});
	}

	$("#areaId").change(function() {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("json.do?action=subarea", {areaId: $("#areaId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
			});
		});
		$("form").submit();
	});

	$("#areaSubId").change(function() {
		$("form").submit();
	});
});

function loadAreaSub(areaId) {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("json.do?action=subarea", {areaId: areaId}, function(data) {
			$.each(data, function(i, item) {
				if(item.areaSubId == "${receiverSearchDomain.areaSubId}") {
					$("#areaSubId").append("<option value=" + item.areaSubId + " selected='selected'>" + item.name + " </option>");
				} else {
					$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
				}
			});
		});
}

function loadSubEntCategory(bigEntCategoryId) {
		$("#subEntCategoryId").empty();
		$("#subEntCategoryId").append("<option value=' '>请选择</option>");
		$("#entCategoryId").empty();
		$("#entCategoryId").append("<option value=' '>请选择</option>");
		$.getJSON("json.do?action=entcategory", {parentId: bigEntCategoryId}, function(data) {
			$.each(data, function(i, item) {
				if(item.categoryId == "${receiverSearchDomain.subEntCategoryId}") {
					$("#subEntCategoryId").append("<option value=" + item.categoryId + " selected='selected'>" + item.value + " </option>");
				} else {
					$("#subEntCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
				}
			});
		});
}

function loadEntCategory(subEntCategoryId) {
		$("#entCategoryId").empty();
		$("#entCategoryId").append("<option value=' '>请选择</option>");
		$.getJSON("json.do?action=entcategory", {parentId: subEntCategoryId}, function(data) {
			$.each(data, function(i, item) {
				if(item.categoryId == "${receiverSearchDomain.entCategoryId}") {
					$("#entCategoryId").append("<option value=" + item.categoryId + " selected='selected'>" + item.value + " </option>");
				} else {
					$("#entCategoryId").append("<option value=" + item.categoryId + ">" + item.value + " </option>");
				}
			});
		});
}

</script>
<form:form method="post" action="bizlife.do?action=receiver" commandName="receiverSearchDomain">
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
                  <td>                    请选择服务类别：
                    <form:select path="receiverType" cssClass="form" onchange="btnsearch_click()">
						<form:option value="1">一技之长服务者</form:option>
						<form:option value="2">服务企业</form:option>
                    </form:select>
                  </td>
                </tr>
                <tr>
                  <c:if test="${receiverSearchDomain.receiverType == '1'}">
                    <td>                      服务者所在街道：
                      <form:select path="areaId" cssClass="form" >
                      </form:select>
                      <form:select path="areaSubId" cssClass="form" onchange="btnsearch_click()">
                        <option value=" ">全部</option>
                      </form:select>
                      编号：<form:input path="volunteerNo" cssStyle="form" />
                      服务项目：<form:input path="serviceName" cssStyle="form" />
                      <A href="javascript:btnsearch_click()">
                        <img src="images/button_search.gif" width="60" height="18" alt="" border="0">
                      </A>
                    </td>
                  </c:if>
                  <c:if test="${receiverSearchDomain.receiverType == '2'}">
                    <td>                      所需服务项目&nbsp;&nbsp;&nbsp;&nbsp;：
                      <form:select path="bigEntCategoryId">
                      </form:select>
                      <form:select path="subEntCategoryId">
                        <option value=" ">请选择</option>
                      </form:select>
                      <form:select path="entCategoryId" onchange="btnsearch_click()">
                        <option value=" ">请选择</option>
                      </form:select>
                      <A href="javascript:btnsearch_click()">
                        <img src="images/button_search.gif" width="60" height="18" alt="" border="0">
                      </A>
                    </td>
				</c:if>
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
        <c:if test="${receiverSearchDomain.receiverType == '1'}">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="table_green">
              <td width="15%">&nbsp;</td>
              <td width="5%">编号</td>
              <td width="10%">姓名</td>
              <td width="35%">服务项目</td>
              <td width="15%">联系电话</td>
              <td width="10%">派单总量</td>
              <td width="10%">今日派单量</td>
            </tr>
			<c:forEach items="${vltDTOList}" var="vltDTO">
            <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
              <td>
                <a href="javascript:btnselect_click('1','${vltDTO.volunteerId}','${vltDTO.volunteerName}','${vltDTO.phone}')">
                  <img src="images/button_choose.gif" width="60" height="18" alt="" border=0>
                </a>
              </td>
              <td>
                ${vltDTO.volunteerNo}
              </td>
              <td>
                ${vltDTO.volunteerName}
              </td>
              <td>
                ${vltDTO.serviceName}
              </td>
              <td>
                ${vltDTO.phone}
              </td>
              <td>
              <c:choose>
              <c:when test="${empty vltDTO.totalDispatch}">
              0
              </c:when>
              <c:otherwise>
              ${vltDTO.totalDispatch}
              </c:otherwise>
              </c:choose>
              </td>
              <td>
              <c:choose>
	              <c:when test="${empty vltDTO.todayDispatch}">
	              0
	              </c:when>
	              <c:otherwise>
	              ${vltDTO.todayDispatch}
	              </c:otherwise>
              </c:choose>
              </td>
            </tr>
            <tr class="line">
              <td height="1" colspan="7">                  </td>
            </tr>
			</c:forEach>
          </table>
        </c:if>
        <c:if test="${receiverSearchDomain.receiverType == '2'}">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="table_green">
              <td width="20%">&nbsp;</td>
              <td width="10%">编号</td>
              <td width="15%">企业名称</td>
              <td width="20%">服务电话</td>
              <td width="15%">待办服务数</td>
              <td width="20%">今日待办服务数</td>
            </tr>
			<c:forEach items="${entDTOList}" var="entDTO">
            <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
              <td>
                <a href="javascript:btnselect_click('2','${entDTO.entpriseId}','${entDTO.entpriseName}','${entDTO.serviceTel}')">
                  <img src="images/button_choose.gif" width="60" height="18" alt="" border=0>
                </a>
              </td>
              <td>
                ${entDTO.entpriseNo}
              </td>
              <td nowrap="nowrap">
                ${entDTO.entpriseName}
              </td>
              <td>
                ${entDTO.serviceTel}
              </td>
              <td>
              <c:choose>
              <c:when test="${empty entDTO.totalDispatch}">
              0
              </c:when>
              <c:otherwise>
              ${entDTO.totalDispatch}
              </c:otherwise>
              </c:choose>
              </td>
              <td>
              <c:choose>
	              <c:when test="${empty entDTO.todayDispatch}">
	              0
	              </c:when>
	              <c:otherwise>
	              ${entDTO.todayDispatch}
	              </c:otherwise>
              </c:choose>
              </td>
            </tr>
            <tr class="line">
              <td height="1" colspan="7">                  </td>
            </tr>
            </c:forEach>
          </table>
        </c:if>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="bizlife.do?action=receiver"/>
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
