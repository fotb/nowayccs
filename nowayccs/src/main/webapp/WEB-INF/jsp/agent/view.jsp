<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function btnback_click(){
  history.back();
}
</script>
</head>
<body>
<form:form method="post" action="agent.do?action=view" commandName="agent">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">呼叫中心连接设置</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">用户：</td>
            <td colspan="3">
              ${user.userName}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">工号：</td>
            <td>
              ${agent.workNo}
            </td>
            <td width="15%">密码：</td>
            <td>
              ${agent.password}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">坐席类型：</td>
            <td>
              ${agent.agentType }
            </td>
            <td width="15%">APC卡类型：</td>
            <td>
              ${agent.cardType}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">主UIS地址：</td>
            <td>
              ${agent.mainCcsIp}
            </td>
            <td width="15%">备UIS地址：</td>
            <td>
              ${agent.backCcsIp}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">坐席电话：</td>
            <td>
				${agent.targetDevice}
            </td>
            <td width="15%">服务器类型：</td>
            <td>
              ${agent.serverType}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%" colspan="4">
            <form:checkbox path="autoAnswer" disabled="true"/>自动应答
            &nbsp;
             <form:checkbox path="autoRelease" disabled="true"/>自动释放
            &nbsp;
             <form:checkbox path="autoReconnect" disabled="true"/>自动重连
            &nbsp;
              <form:checkbox path="freeStatus" disabled="true"/>空闲/工作状态
            &nbsp;
             <form:checkbox path="haveBell" disabled="true"/>铃声提示
            &nbsp;
             <form:checkbox path="mediaPlay" disabled="true"/>多媒体铃声
            &nbsp;
            </td>
          </tr>
                    <tr class="table_t1">
            <td width="15%">响铃时间</td>
            <td>
            ${agent.bellTime }
            </td>
            <td width="15%">多媒体文件路径</td>
            <td>
            ${ageng.mediaFilename}
            </td>
          </tr>

          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
              <img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
