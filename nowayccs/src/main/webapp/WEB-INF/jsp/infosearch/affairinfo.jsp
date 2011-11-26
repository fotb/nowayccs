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
<form:form method="post" action="infosearch.do?action=affairinfo">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">事物服务类业务信息</td>
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
            <td>移送方向：</td>
            <td>${affairInfoVO.moveWay}</td>
            <td>接洽人：</td>
            <td>${affairInfoVO.moveAcceptor}</td>
            <td>移送方式：</td>
            <td>
              ${pdfsMap[infoVO.deliverMode]}
            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>${affairInfoVO.moveAcceptTel}</td>
            <td>移送时间：</td>
            <td><fmt:formatDate value="${affairInfoVO.moveTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
          <td colspan="6">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t2">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">回访记录</td>
          </tr>
        </table>
          </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td>回复方式：</td>
            <td>
            ${pdfsMap[affairInfoVO.answerMode]}
            </td>
            <td>回复时间：</td>
            <td colspan="3">
            <fmt:formatDate value="${affairInfoVO.answerTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>回复情况：</td>
            <td colspan="5">
            ${affairInfoVO.callResult}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td>回访方式：</td>
            <td>
             ${pdfsMap[affairInfoVO.callMode]}
            </td>
            <td>回访时间：</td>
            <td colspan="3">
            <fmt:formatDate value="${affairInfoVO.callTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
           <tr class="table_t2">
            <td>回访记录：</td>
            <td colspan="5">
              ${affairInfoVO.callResult}
            </td>
          </tr>
          <tr class="table_t2">
            <td>结案时间：</td>
            <td>
             <fmt:formatDate value="${infoVO.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>求助者满意度：</td>
            <td colspan="3">
              ${mydMap[affairInfoVO.helpApprove]}
            </td>
          </tr>
          <tr class="table_t2">
            <td>不满意原因：</td>
            <td colspan="5">
              ${affairInfoVO.unApproveCause}
            </td>
          </tr>
          <tr class="table_t2">
            <td>备注：</td>
            <td colspan="5">
            ${affairInfoVO.remark}
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
