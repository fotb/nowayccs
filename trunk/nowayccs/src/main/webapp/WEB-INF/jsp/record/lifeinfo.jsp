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
<form:form method="post" action="">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
    <td>
      <object id="MediaPlayer" height="65" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6" type="application/x-oleobject">
		<param name="ShowPositionControls" value="0" />
		<param name="AutoStart" value="0" />
		<param name="EnableContextMenu" value="0">
		<param name="URL" value="D:\DevTools\apache-tomcat-7.0.12\webapps\ROOT\test.mp3" />
		<!--<embed id="MediaPlayer1" height="45" src="http://localhost:8001/test.mp3" type="application/x-mplayer2" autostart="0" EnableContextMenu="0"></embed>-->
	</object>
    </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">生活服务类业务详细信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">序号：</td>
            <td width="15%">
            ${infoVO.infoId}
            </td>
            <td width="15%">状态：</td>
            <td colspan="5">
              ${statusMap[infoVO.status]}
            </td>
          </tr>
          <tr class="table_t1">
            <td width="11%">求助者姓名：</td>
            <td width="15%">
            ${infoVO.helpName}
            </td>
            <td width="12%">联系电话：</td>
            <td width="15%">
			${infoVO.helpTel}
            </td>
            <td width="10%">详细地址：</td>
            <td width="37%">
			${infoVO.helpAddr}
            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td colspan="5">
            ${infoVO.helpContent}
            </td>
          </tr>
          <tr class="table_t1">
            <td>求助时间：</td>
            <td>
            	<fmt:formatDate value="${infoVO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>求助区域：</td>
            <td colspan="5">
            ${areaMap[infoVO.helpArea]}
            </td>
            </tr>
            <tr class="table_t1">
            <td>派送方向：</td>
            <td>
            ${lifeInfoVO.receiverType}
            <c:choose>
            	<c:when test="${lifeInfoVO.receiverType == '1'}">一技之长服务者</c:when>
            	<c:otherwise>服务企业</c:otherwise>
            </c:choose>
            </td>
            <td>派送方式：</td>
            <td>
            ${pdfsMap[infoVO.deliverMode]}
            </td>
			<td>派单时间：</td>
            <td nowrap="nowrap">
            <fmt:formatDate value="${infoVO.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <c:if test="${lifeInfoVO.receiverType == '1' }">
          <tr class="table_t1">
            <td>接洽人联系电话：</td>
            <td>
            ${vltVO.linkTel}&nbsp;${vltVO.linkMobile}
            </td>
 			<td>接洽人：</td>
            <td colspan="3">
            ${vltVO.volunteerName}
            </td>
          </tr>
          </c:if>
          
          <c:if test="${lifeInfoVO.receiverType == '2' }">
          <tr class="table_t1">
            <td>接洽人联系电话：</td>
            <td>
            ${entVO.linkTel}
            </td>
 			<td>接洽人：</td>
            <td>
            ${entVO.linkName}
            </td>
            <td>接洽单位：</td>
            <td>
            ${entVO.entpriseName}
            </td>
          </tr>
          </c:if>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td class="font_no">回访信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td width="15%">回访方式：</td>
            <td width="15%">
              ${pdfsMap[lifeInfoVO.callMode]}
            </td>
            <td width="12%">回访时间：</td>
            <td colspan="3">
              <fmt:formatDate value="${lifeInfoVO.callTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>回访记录：</td>
            <td colspan="5">
              ${lifeInfoVO.callResult}
            </td>
          </tr>
          <tr class="table_t2">
            <td>结案时间：</td>
            <td>
             <fmt:formatDate value="${infoVO.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>求助者满意度：</td>
            <td colspan="3">
              ${mydMap[lifeInfoVO.helpApprove]}
            </td>
          </tr>
          <tr class="table_t2">
            <td>不满意原因：</td>
            <td colspan="5">
              ${lifeInfoVO.unApproveCause}
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理结果：</td>
            <td colspan="5">
              ${mydMap[lifeInfoVO.dealResult]}
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理描述：</td>
            <td colspan="5">
              ${lifeInfoVO.dealContent}
            </td>
          </tr>
          <tr class="table_t2">
            <td>备注：</td>
            <td colspan="5">
             ${lifeInfoVO.remark}
            </td>
          </tr>
          <tr class="table_t2">
            <td>经办人：</td>
            <td colspan="5">
              ${caller.userName}
            </td>
          </tr>
          <tr align="center">
            <td colspan="6" class="table_t2">
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
