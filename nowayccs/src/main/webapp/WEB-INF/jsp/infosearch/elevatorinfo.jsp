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
<form:form method="post" action="infosearch.do?action=sgptinfo">
  <div id="p" class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
            <div id="title" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
		<p class="font_no" style="font-size:14px; font-weight:bold; text-align:center;">事件信息</p>
		</div>
  <div id="info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
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
            </table>
          </div>  
		
		<div style="margin-bottom:3px">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
		<tr><td colspan="6" class="font_no"><p style="font-size:16px; font-weight:bold; text-align:center;">嘉兴市电梯应急公共救援平台电话记录</p></td></tr>
          <tr class="table_t1">
          <td>值班人员:${userVO.userName}</td>
          <td>值班日期:${infoVO.createTime}</td>
          </tr>
			</table>
		</div>
		<div id="p_info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
           <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td style="width: 80px;">接报时间</td>
            <td style="width:180px;" colspan="1">
            ${elevHelpInfoVO.reportingTime}
            </td>
             <td style="width: 80px;text-align: right;">报告人</td>
            <td  style="width:200px;">
            ${elevHelpInfoVO.reporter}
            </td>
             <td style="width: 100px;text-align: center;">报告人电话</td>
            <td>
            ${elevHelpInfoVO.reportPhone}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 16pt;"  colspan="6">电梯基本信息</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>设备识别码</td>
            <td colspan="5">${elevatorVO.deviceId}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯使用<br>管理单位</td>
            <td style="" colspan="5">
            ${elevatorVO.useDept}
            </td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯安装<br>具体位置</td>
            <td style="" colspan="5">
            ${elevatorVO.position}
            </td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>维保单位</td>
            <td style="" colspan="3">
            ${elevatorVO.serviceName}
            </td>
          	<td style="text-align: center;">制造单位</td>
            <td style="" colspan="1">
            ${elevatorVO.manufacturer}
            </td>          
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td>档案号</td>
            <td style="" colspan="3">
            ${elevatorVO.referNo}
            </td>
            <td colspan="2"></td>
          </tr>
            <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>出厂编号</td>
            <td style="" colspan="3">
            ${elevatorVO.serialNumber}
            </td>
          	<td style="text-align: center;">电梯类别</td>
            <td style="" colspan="1">
                       <c:choose>
            	<c:when test="${elevatorVO.type == '1'}">电梯</c:when>
            	<c:when test="${elevatorVO.type == '2'}">货梯</c:when>
            	<c:when test="${elevatorVO.type == '3'}">医梯</c:when>
            	<c:when test="${elevatorVO.type == '4'}">扶梯</c:when>
            	<c:otherwise>电梯</c:otherwise>
            </c:choose>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>检验日期</td>
            <td style="" colspan="3">
            ${elevatorVO.surveyDate}
            </td>
          	<td style="text-align: center;">下检日期</td>
            <td style="" colspan="1">${elevatorVO.nextSurveyDate}</td>          
          </tr>   
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 16pt;"  colspan="6">救援情况</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>救援单位</td>
            <td style="" colspan="5">
             <c:choose>
            	<c:when test="${elevHelpInfoVO.helpDept == '1'}">签约维保单位救援</c:when>
            	<c:when test="${elevHelpInfoVO.helpDept == '2'}">网格救援单位救援</c:when>
            	<c:otherwise>签约维保单位救援</c:otherwise>
            </c:choose>
          </tr>
			<tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>值班人发出<br>指令时间</td>
            <td style="" colspan="3">${elevHelpInfoVO.dispatchTime}</td>
           <td style="text-align: center;">救援人员<br>出动时间</td>
            <td style="" colspan="1">${elevHelpInfoVO.startTime}</td>
          </tr>  
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>救援人员到<br>达现场时间</td>
            <td style="" colspan="3">${elevHelpInfoVO.arriveTime}</td>
           <td style="text-align: center;">救援结束<br>时间</td>
            <td style="" colspan="1">${elevHelpInfoVO.finishTime}</td>
          </tr> 
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯困人<br>数量</td>
            <td style="" colspan="3">${elevHelpInfoVO.trapppedPerson}</td>
           <td style="text-align: center;">人员伤亡<br>情况</td>
            <td style="" colspan="1">
             <c:choose>
            	<c:when test="${elevHelpInfoVO.casualty == '0'}">无</c:when>
            	<c:when test="${elevHelpInfoVO.casualty == '1'}">有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="casualtySpan">其中： 受伤${elevHelpInfoVO.injuries}人&nbsp;&nbsp;死亡${elevHelpInfoVO.deathToll}人</span></c:when>
            	<c:otherwise>无</c:otherwise>
            </c:choose>
            </td>
          </tr>   
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯困人<br>故障原因</td>
            <td style="" colspan="5">${elevHelpInfoVO.reason}</td>
          </tr>          

          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>后续处理<br>情况</td>
            <td style="" colspan="5">${elevHelpInfoVO.dealResult}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>值班状况</td>
            <td style="" colspan="5">${elevHelpInfoVO.dutyResult}</td>
          </tr> 
          
			<tr align="center" class="table_t1">
            <td colspan="6">
              <img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>                   
          </table>
	</div>
	</div>
</form:form>
</body>
</html>
