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
<script>
	function option_delete(id) {
		var form = document.forms[0];
		if (confirm("确定要删除?")) {
			form.action = "role.do?action=delete&roleId=" + id;
			form.submit();
		}
	}
	function delSms(smsId) {
		var form = document.forms[0];
		if (confirm("系统将删除该短信，但短信还保留在后台数据库中。请确认！")) {
			form.action = "sms.do?action=delete&smsId=" + smsId;
			form.submit();
		}
	}

	function bizAccept(telNum) {
		window
				.open(
						"bizaccept.do?flag=Y&callNo=" + telNum + "&qzfs=4",
						"",
						'height=700, width=750, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
function send() {
	var form = document.forms[0];
	form.action = "sms.do?action=new";
	form.target = "_blank";
	form.submit();
}
</script>
</head>
<body>
<form:form action="sms.do" method="post">

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
  <td>
  <c:if test="${not empty remoteError}">
	<span id="getsms.errors" class="error">远程读取短信失败，请联系管理员！</span>
</c:if>
  </td>
    <td align="left" width="120">
    <c:if test="${adminRight == 'Y'}">
    <button name="sendSms" onclick="send();">发送短信</button>
    </c:if>
    </td>
  </tr>
    <tr>
      <td colspan="2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="25%">接收时间</td>
            <td width="20%">电话号码</td>
            <td width="50%">内容</td>
            <td width="5%">操作</td>
          </tr>
         <c:forEach items="${smsList}" var="smsRecvVO">
		<tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
            <td width="25%"><fmt:formatDate value="${smsRecvVO.recvDt}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
            <td width="20%">${smsRecvVO.telNum }</td>
            <td width="50%" align="left"><c:if test="${not empty smsRecvVO.content}">${smsRecvVO.content}</c:if><c:if test="${empty smsRecvVO.content}">-</c:if></td>
            <td width="5%"><a href="javascript:bizAccept('${smsRecvVO.telNum }')">受理</a> | <a href="javascript:delSms('${smsRecvVO.smsId}')">删除</a></td>
          </tr>
			<tr class="line">
              <td height="1" colspan="7">              </td>
            </tr>
         </c:forEach>
        </table>
      </td>
    </tr>
    			<tr>
              <td height="40">              </td>
            </tr>

  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}">
  </form:form>
</body>
</html>
