<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsearch_click(){
  var form=document.forms[0];
  form.action="shs.do";
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
<form:form method="post" action="shs.do" commandName="shsForm">
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
                  <td width="80">求助人姓名：</td>
                  <td width="130"><form:input path="specialName" cssClass="form"/></td>
				  <td width="70">求助电话：</td>
				  <td width="130"><form:input path="telphone" cssClass="form"/></td>
                  <td width="80">党员志愿者：</td>
                  <td width="130"><form:input path="memberName" cssClass="form"/></td>
                  </tr>
                  <tr>
                  <td>求助时间从：</td>
            	  <td colspan="4"><form:input cssClass="form" path="startDt" size="20"/>
      到<form:input cssClass="form" path="endDt" size="20"/></td>
                  <td >
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
	          <td>求助人姓名</td>
	          <td>联系电话</td>
	          <td>求助人类型</td>
	          <td>地址</td>
	          <td>求助内容</td>
	          <td>志愿者</td>
              <td>求助时间</td>
              <td>受理人</td>
	        </tr>
			<c:forEach items="${shsResultDTOList}" var="dto"  varStatus="index">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
				<td>${index.index + 1 + pageInfo.PAGE_COUNT*(pageInfo.currentPage-1)}</td>
                <td>
                  <a href="shs.do?action=view&helpId=${dto.lhVO.helpId}&pageNo=${pageInfo.currentPage }">
                    ${dto.lmiVO.manName}
                  </a>
                </td>
                <td>
                ${dto.lmiVO.telphone}
				</td>
                <td>
                	${sftMap[dto.lmiVO.familyType]}
                </td>
                <td nowrap="nowrap">
					${dto.lmiVO.address}
                </td>
                <td nowrap="nowrap">
					${dto.lhVO.helpContent}
                </td>
                <td>
                	${dto.pmflVO.memberName}
                </td>
                <td>
                <fmt:formatDate value="${dto.lhVO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                	${dto.userName}
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="10">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="shs.do"/>
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
