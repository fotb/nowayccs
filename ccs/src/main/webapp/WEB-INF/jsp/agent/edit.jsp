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
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.workNo,"工号",true)){
    return;
  }
  /*
  if(!isValidStringObj( form.password,"密码",true)){
   return;
  }
  */
  if(!isValidStringObj( form.agentType,"坐席类型",true)){
    return;
  }

  if(!isValidStringObj( form.cardType,"APC卡类型",true)){
    return;
  }

  if(!isValidStringObj( form.mainCcsIp,"主UIS地址",true)){
    return;
  }

  if(!isValidStringObj( form.backCcsIp,"备UIS地址",true)){
    return;
  }

  if(!isValidStringObj( form.targetDevice,"坐席电话",true)){
    return;
  }

  if(!isValidStringObj( form.serverType,"服务器类型",true)){
    return;
  }
  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
</head>
<body>
<form:form method="post" action="agent.do?action=addsave" commandName="agent">
<form:hidden path="userId"/>
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
              <form:input path="workNo" cssClass="form" size="20"/>
            </td>
            <td width="15%">密码：</td>
            <td>
              <form:password path="password" cssClass="form" size="20" showPassword="true"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">坐席类型：</td>
            <td>
              <form:select path="agentType" cssStyle="HEIGHT: 22px; WIDTH: 132px">
				<form:option value="4">PCPHONE</form:option>
				<form:option value="2">1B1D</form:option>
				<form:option value="5">2B1D</form:option>
				<form:option value="6">6B1D</form:option>
			  </form:select>
            </td>
            <td width="15%">APC卡类型：</td>
            <td>
               <form:select path="cardType" cssStyle="HEIGHT: 22px; WIDTH: 132px" >
					<form:option value="3">CQ04</form:option>
					<form:option value="0">CQ01</form:option>
					<form:option value="1">CQ05</form:option>
					<form:option value="2">CQ06</form:option>
	           </form:select>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">主UIS地址：</td>
            <td>
              <form:input path="mainCcsIp" cssClass="form" size="20"/>
            </td>
            <td width="15%">备UIS地址：</td>
            <td>
              <form:input path="backCcsIp" cssClass="form" size="20"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">坐席电话：</td>
            <td>
              <form:input path="targetDevice" cssClass="form" size="20"/>
            </td>
            <td width="15%">服务器类型：</td>
            <td>
              <form:select path="serverType" cssStyle="HEIGHT: 22px; WIDTH: 132px">
	              <form:option value="22">MCP</form:option>
	              <form:option value="20">UIS</form:option>
              </form:select>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%" colspan="4">
              <form:checkbox path="autoAnswer"/>自动应答
              <form:checkbox path="autoRelease" />自动释放
              <form:checkbox path="autoReconnect" />自动重连
              <form:checkbox path="freeStatus" />空闲/工作状态
 			  <form:checkbox path="haveBell" />铃声提示
 			  <form:checkbox path="mediaPlay" />多媒体铃声
            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">响铃时间</td>
            <td>
              <form:input path="bellTime" cssClass="form" size="20"/>
            </td>
            <td width="15%">多媒体文件路径</td>
            <td>
            	<form:input path="mediaFilename" cssClass="form" size="40" />
            </td>
          </tr>

          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
              <img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click()" style="cursor: pointer;"/>
              <img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()" style="cursor: pointer;"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
