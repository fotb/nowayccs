<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form：form action="agent.do" method="post">
		<table width="865" border="0" align="center" cellpadding="0"
			cellspacing="0" class="table_gray">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr class="table_green">
							<td width="10%">用户</td>
							<td width="10%">工号</td>
							<td width="30%">坐席类型</td>
							<td width="20%">APC卡类型</td>
							<td width="20%">主UIS地址</td>
							<td width="10%">操作</td>
						</tr>
						<tr class='table_white'
							onmouseover="this.style.backgroundColor='#F0F0F0'"
							onmouseout="this.style.backgroundColor='#ffffff'">
							<td><c：if test="${not empty agent}"><a href="agent.do?action=view&userId=${agent.userId}">${user.userName}</a></c：if></td>
							<td>${agent.workNo}</td>
							<td>
								<c：if test="${agent.agentType == '4'}">PCPHONE</c：if>
								<c：if test="${agent.agentType != '4'}">${agent.agentType}</c：if>
							</td>
							<td>
								<c：if test="${agent.cardType == '3'}">CQ04</c：if>
								<c：if test="${agent.cardType != '3'}">${agent.cardType}</c：if>
							</td>
							<td>${agent.mainCcsIp}</td>
							<td width="5%">
								<c：if test="${not empty agent}">
									<a href="agent.do?action=edit&userId=${agent.userId}">
										<img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
									</a>
								</c：if>
							</td>
						</tr>
						<tr class="line">
							<td height="1" colspan="6"></td>
						</tr>
					</table> 
					
					<c：if test="${empty agent}">
						<table width="97%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="30">
								<a href="agent.do?action=add"> 
									<img src="images/button_add.gif" width="60" height="18" border="0" alt="">
								</a>
								</td>	
							</tr>
						</table>
					</c：if>
					</td>
			</tr>
		</table>
	</form：form>
</body>
</html>
