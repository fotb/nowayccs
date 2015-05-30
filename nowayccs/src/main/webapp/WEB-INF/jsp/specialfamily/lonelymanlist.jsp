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
  form.action="lfmgr.do";
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

</script>
<body>
<form:form method="post" action="lfmgr.do" commandName="lfMgrForm">
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
                  <tr>
                  <td width="80">
                  	姓名:
                  </td>
                   <td width="200">
                   	<form:input path="manName" cssClass="form"/>
                  </td>
                  
                   <td width="80">
                  	电话:
                  </td>
                   <td width="200">
                   	<form:input path="telphone" cssClass="form" size="40"/>
                  </td>
                  <td>
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
	          <td nowrap="nowrap">姓名</td>
	          <td>性别</td>
	          <td nowrap="nowrap">出身年月</td>
	          <td>联系电话</td>
	          <td nowrap="nowrap">身份证号码</td>
	          <td nowrap="nowrap">所属区域</td>
              <td>地址</td>
              <td>家庭类别</td>
              <td>基本信息</td>   
              <td>结对志愿者</td>   
              <td>操作</td>              
	        </tr>
			<c:forEach items="${lmiVOList}" var="lmiVO"  varStatus="index">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
              <td>${index.index + 1 + pageInfo.PAGE_COUNT*(pageInfo.currentPage-1)}</td>
               <td nowrap="nowrap">
                  ${lmiVO.manName}
                </td>
                <td nowrap="nowrap">
                    ${lmiVO.manSex}
                </td>
                <td nowrap="nowrap">
 					${lmiVO.birthday}
                </td>
                <td>
                	${lmiVO.telphone}
                </td>
                <td nowrap="nowrap">
					${lmiVO.idCardNo}
                </td>
                <td nowrap="nowrap">
                	${lmiVO.area}
                </td>
                <td nowrap="nowrap">
                	${lmiVO.address}
                </td>
                <td nowrap="nowrap">
                	${sftMap[lmiVO.familyType]}
                </td>
                <td>
                	${lmiVO.familyInfo}
                </td>
                <td width="8%">
					<a href="lfmgr.do?action=pmlist&manId=${lmiVO.manId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="8%">
                  <a href="lfmgr.do?action=update&manId=${lmiVO.manId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="12">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="lfmgr.do?action=add&pageNo=${pageInfo.currentPage }">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="lfmgr.do"/>
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
