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
          <div id="subTitle" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
		<p class="font_no" style="font-size:14px; font-weight:bold; text-align:center;">“四个平台”事件信息记录单</p>
		</div>
		<div id="p_info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
           <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件名称</td>
            <td colspan="3">
				${eventVO.eventSubject}
            </td>
<!--              <td>发生网格</td>
            <td>
              <input class="easyui-textbox" id="reportor" name="reportor" data-options="prompt:'请输入发生网格'" style="height:100%;width: 99%;">
            </td> -->
          </tr>
          <tr class="line">
            <td height="1" colspan="4"></td>
          </tr>
          <tr class="table_t1">
            <td>发生地点</td>
            <td style="">
            	${eventVO.eventLocation}
			</td>
            <td >发生时间</td>
            <td style="">
            	${eventVO.eventDate}
			</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件类别</td>
            <td style="" colspan="3">
            ${eventVO.secondCategoryId}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 14pt;"  colspan="4">主要当事人</td>
          </tr>
          <tr class="table_t1">
            <td>姓名</td>
            <td style="">
            ${eventVO.objName}
            </td> 
            <td>联系电话</td>
            <td style="">
            ${eventVO.mobile}
            </td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>涉及人数</td>
            <td style="">
            ${eventVO.relatePeopleCount}
            </td>
          	<td style="text-align: center;" colspan="2"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件简述</td>
            <td style="" colspan="3">
            ${eventVO.eventContent}
            </td>
          </tr>
            <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>事件分类等级</td>
            <td style="">
            <c:choose>
            	<c:when test="${eventVO.eventLevel == '3'}">一般</c:when>
            	<c:when test="${eventVO.eventLevel == '2'}">紧急</c:when>
            	<c:when test="${eventVO.eventLevel == '1'}">特急</c:when>
            	<c:otherwise>一般</c:otherwise>
            </c:choose>
            </td>
            <td>重点关注事件</td>
            <td style="">
            <c:choose>
            	<c:when test="${eventVO.isImpPlase == '0'}">否</c:when>
            	<c:when test="${eventVO.isImpPlase == '1'}">是</c:when>
            	<c:otherwise>否</c:otherwise>
            </c:choose>
            </td>
          </tr>      
                      <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
           <tr class="table_t1">
            <td>处理状态</td>
            <td style="" colspan="3">
              <c:choose>
            	<c:when test="${eventVO.status == '10'}">上报</c:when>
            	<c:when test="${eventVO.status == '20'}">受理</c:when>
            	<c:when test="${eventVO.status == '30'}">办理</c:when>
            	<c:when test="${eventVO.status == '32'}">分流</c:when>
            	<c:when test="${eventVO.status == '36'}">回退</c:when>
            	<c:when test="${eventVO.status == '37'}">协同审核</c:when>
            	<c:when test="${eventVO.status == '38'}">协同反馈</c:when>
            	<c:when test="${eventVO.status == '39'}">督办</c:when>
            	<c:when test="${eventVO.status == '40'}">办结</c:when>
            	<c:when test="${eventVO.status == '50'}">反馈</c:when>
            	<c:otherwise>受理</c:otherwise>
            </c:choose>
            </td>
          </tr>   
          <tr class="table_t1">
            <td>处理结果</td>
            <td style="" colspan="3">
            ${eventVO.suggestion}
            </td>
          </tr>   
           <tr align="center">
            <td colspan="6" class="table_t2">
              <img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
          </table>
	</div>
	</div>
</form:form>
</body>
</html>
