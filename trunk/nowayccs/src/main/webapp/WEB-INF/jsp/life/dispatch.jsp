<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.handMode,"派单方式",true)){
    return;
  }
  if(!isValidStringObj( form.receiverId,"派送方向",true)){
    return;
  }
  form.submit();
}

function btnchoose_click(){
  var lifeHandInfo = window.showModalDialog("bizlife.do?action=receiver&"+getTimeStr(),window,"dialogWidth=900px;dialogHeight=700px;status=0");
  if(lifeHandInfo!=null){
	$("#receiverType").val(lifeHandInfo.receiverType);
	$("#receiverId").val(lifeHandInfo.receiverID);
	$("#linkName").val(lifeHandInfo.linkName);
	$("#linkTel").val(lifeHandInfo.linkTel);
  }
}

function btnback_click(){
  window.open("bizlife.do?pageNo=${pageNo}","_self");
}
</script>
<form:form method="post" action="bizlife.do?action=dispatchsave" commandName="lifeDispatchBean">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<form:hidden path="receiverType"/>
<form:hidden path="infoId"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">生活服务类业务处理</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <!-- 
          <tr class="table_t1">
            <td width="10%">序号：</td>
            <td>
            <bean:write name="hjLifeInformation" property="lifeInformationId"/>
            </td>
          </tr>
           -->
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">求助名姓名：</td>
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
              <textarea Class="form" cols="120" rows="4" readonly>${infoVO.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助时间：</td>
            <td>
            <fmt:formatDate value="${infoVO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
             ${qzqyMap[infoVO.helpArea]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>派送方向：</td>
            <td>
				<form:input path="receiverId" cssClass="form" size="70" readonly="true"/>
              <A href="javascript:btnchoose_click()"><img src="images/button_choose.gif" width="60" height="18" border="0" alt=""></A>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>接洽人：</td>
            <td>
				<form:input path="linkName" cssClass="form" size="30" readonly="true"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>电话：</td>
            <td>
				<form:input path="linkTel" cssClass="form" size="30" readonly="true"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>派单方式：</td>
            <td>
              <form:select path="handMode">
              	<form:options items="${pdfsList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <!-- 
          <tr class="table_t1">
            <td>派单时间：</td>
            <td>
			  <bean:write name="handTime" />
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2"></td>
          </tr>
           -->
          <tr align="center" class="table_t1">
            <td colspan="4">
              <img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click()"/>
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
