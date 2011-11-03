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
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.action="volunteer.do";
  form.submit();
}
function btndelete_click(volunteerId) {
  if(confirm("是否真的停止服务者服务？")) {
    $("form").attr("action", "volunteer.do?action=changestatus&volunteerId="+ volunteerId + "&tostatus=N");
    $("form").submit();
  }
}
function btnactive_click(volunteerId) {
  if(confirm("是否真的重新开始服务者服务？")) {
    $("form").attr("action", "volunteer.do?action=changestatus&volunteerId="+ volunteerId + "&tostatus=Y");
    $("form").submit();
  }
}

$(document).ready(function(){
	$.getJSON("volunteer.do?action=area", function(data) {
		$("#areaId").append("<option value=' '>全部</option>");
		$.each(data, function(i, item) {
			if("${volunteerSearch.areaId}" == item.areaId) {
				$("#areaId").append("<option value=" + item.areaId + " selected='selected'>" + item.name + " </option>");
			} else {
				$("#areaId").append("<option value=" + item.areaId + ">" + item.name + " </option>");
			}
		});
	});

	if("${volunteerSearch.areaId}" != "") {
		fillAreaSub(${volunteerSearch.areaId});
	}

	$("#areaId").change(function() {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("volunteer.do?action=subarea", {areaId: $("#areaId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
			});
		});
	});

});

function fillAreaSub(areaId) {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("volunteer.do?action=subarea", {areaId: areaId}, function(data) {
			$.each(data, function(i, item) {
				if("${volunteerSearch.areaSubId}" == item.areaSubId) {
					$("#areaSubId").append("<option value=" + item.areaSubId + " selected='selected'>" + item.name + " </option>");
				} else {
					$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
				}
			});
		});
}

</script>
<body>
<form:form method="post" action="volunteer.do" commandName="volunteerSearch">
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
                  <td width="80">服务者状态：</td>
                  <td width="130">
                  <form:select path="status">
                  <form:option value=" ">全部</form:option>
                  <form:option value="Y">服务中</form:option>
                  <form:option value="N">停止服务</form:option>
                  </form:select>
                  </td>
				  <td width="40">派单：</td>
                  <td width="130">
                  <form:select path="serviceType">
                  <form:option value=" ">全部</form:option>
                  <form:option value="Y">可派单</form:option>
                  <form:option value="N">不可派单</form:option>
                  </form:select>
                  </td>
                  <td width="140">服务者所在街道和社区：</td>
                  <td align="left">
                  	<form:select path="areaId">
                  		
                  	</form:select>
                  	<form:select path="areaSubId">
                  		<form:option value=" ">全部</form:option>
                  	</form:select>
                  </td>
                  </tr>
                  
                  <tr>
                  <td width="80">
                  	服务者编号：
                  </td>
                   <td width="200">
                   	<form:input path="volunteerNo" cssClass="form"/>
                  </td>
                  
                   <td width="80">
                  	服务项目：
                  </td>
                   <td width="200" colspan="2">
                   	<form:input path="serviceName" cssClass="form" size="40"/>
                  </td>
                  <td colspan="4">
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
                   <td>编号</td>
	          <td>服务者姓名</td>
	          <td>性别</td>
	          <td>联系电话</td>
	          <td>派单</td>
	          <td>服务项目</td>
	          <td>服务时间</td>
              <td>状态</td>
              <c:if test="${adminRight == 'Y'}">
	          <td>修改</td>
       	      <td>状态变更</td>
       	      </c:if>
	        </tr>
			<c:forEach items="${volunteerVOList}" var="vtVO">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
               <td>
                  ${vtVO.volunteerNo}
                </td>
                <td>
                  <a href="volunteer.do?action=view&volunteerId=${vtVO.volunteerId}&pageNo=${pageInfo.currentPage}">
                    ${vtVO.volunteerName}
                  </a>
                </td>
                <td>
                	<c:if test="${vtVO.sex == '1'}">
                		男
                	</c:if>
                	<c:if test="${vtVO.sex == '2'}">
                		女
                	</c:if>
                </td>
                <td>
                	${vtVO.linkTel}<br/>${vtVO.linkMobile}
                </td>
                <td>
					<c:if test="${vtVO.status == 'Y'}">
                		可派单
                	</c:if>
                 	<c:if test="${vtVO.status == 'N' }">
                 		不可派单
                 	</c:if>
                </td>
                <td>
                	${vtVO.serviceName}
                </td>
                <td>
                	${vtVO.serviceTime}
                </td>
                <td>
                	<c:if test="${vtVO.status == 'Y'}">
                		服务中
                	</c:if>
                 	<c:if test="${vtVO.status == 'N' }">
                 		停止服务
                 	</c:if>
                </td>
                <c:if test="${adminRight == 'Y'}">
                <td width="5%">
                  <a href="volunteer.do?action=update&volunteerId=${vtVO.volunteerId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="8%">
                <c:if test="${vtVO.status == 'Y'}">
                  <A href="javascript:btndelete_click('${vtVO.volunteerId}');">
                    <img src="images/del.gif" alt="停止服务" width="11" height="14" border="0">
                  </A>
                  </c:if>
                <c:if test="${vtVO.status == 'N'}">
                  <A href="javascript:btnactive_click('${vtVO.volunteerId}');">
                    <img src="images/ok.gif" alt="开始服务" width="11" height="14" border="0">
                  </A>
                  </c:if>

                </td>
                </c:if>
              </tr>
              <tr class="line">
                <td height="1" colspan="10">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
          <c:if test="${adminRight == 'Y'}">
            <td height="30">
              <a href="volunteer.do?action=add&pageNo=${pageInfo.currentPage }">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            </c:if>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="SysVolunteerAdmin_list.do"/>
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
