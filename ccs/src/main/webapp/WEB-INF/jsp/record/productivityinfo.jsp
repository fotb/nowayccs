<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="infosearch.do?action=referinfo">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <c:if test="${empty recordUrlList}">
  	<tr><td><font color="red">没有找到录音文件，或录音文件已删除！</font></td></tr>
  	</c:if>
  	<c:if test="${not empty recordUrlList}">
    <tr>
    <td>
    	<c:forEach var="url" step="1" items="${recordUrlList}" varStatus="status">
    		<a href="javascript:play('${url}')">录音 - ${status.index + 1}</a><br>
    	</c:forEach>
    </td>
    <td>
      <object id="MediaPlayer" height="65" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6" type="application/x-oleobject">
		<param name="ShowPositionControls" value="0" />
		<param name="AutoStart" value="0" />
		<param name="EnableContextMenu" value="0">
		<param name="URL" value="${recordUrlList[0]}" />
		<!--<embed id="MediaPlayer1" height="45" src="http://localhost:8001/test.mp3" type="application/x-mplayer2" autostart="0" EnableContextMenu="0"></embed>-->
	</object>
    </td>
    </tr>
    </c:if>
	<tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
    <tr>
      <td colspan="2">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">生产力服务信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
           <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="12%">求助者姓名：</td>
            <td>
              ${infoVO.helpName}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助方式：</td>
            <td>
			${qzfsMap[infoVO.helpMode]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
              ${infoVO.helpTel}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td>
              ${infoVO.helpAddr}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td>
              <textarea id="helpContent" class="form" cols="120" rows="4" disabled="disabled">${infoVO.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
			${areaMap[infoVO.helpArea]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务者：</td>
            <td>${dealer.userName}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>咨询处理描述：</td>
            <td><textarea id="result" name="result" class="form" cols="120" rows="6" disabled="disabled">${referInfoVO.result}</textarea></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
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
