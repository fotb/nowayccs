<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.callMode,"回访方式",true)){
    return;
  }
  if(!isValidStringObj( form.finishTime,"结案时间",true)){
    return;
  }
  if(!isValidStringObj( form.helpApprove,"求助者满意度",true)){
    return;
  }
  if(!isValidStringObj( form.dealResult,"处理结果",true)){
    return;
  }
  if(!isValidStringObj( form.principal,"经办人",true)){
    return;
  }
   $("form").submit();
}

function btnfinish_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.callMode,"回访方式",true)){
    return;
  }
  if(!isValidStringObj( form.finishTime,"结案时间",true)){
    return;
  }
  if(!isValidStringObj( form.helpApprove,"求助者满意度",true)){
    return;
  }
  if(!isValidStringObj( form.dealResult,"处理结果",true)){
    return;
  }
  if(!isValidStringObj( form.principal,"经办人",true)){
    return;
  }
  $("form").attr("action", "bizlifebackvst.do?action=bizfinish");
  $("form").submit();
}


function btnback_click(){
  $("form").attr("action", "bizlifebackvst.do");
  $("form").submit();
}

<c:if test="${adminRight == 'Y'}">
$(document).ready(function(){
	$("#finishTime" ).dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d",
  button: ".next()" //next sibling to input field
	});
});
</c:if>

</script>
<body>
<form:form method="post" action="bizlifebackvst.do?action=backvstsave" commandName="lifeBackVstDomain">
<input type="hidden" name="infoId" id="infoId" value="${infoVO.infoId}"/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo	}"/> 
  <table width="830" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">生活服务类业务回访</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <!-- 
          <tr class="table_t1">
            <td width="10%">序号：</td>
            <td colspan="6">
              ${infoVO.infoId}
            </td>
          </tr>
           -->
          <tr class="table_t1">
            <td align="right">求助者姓名：</td>
            <td>
              ${infoVO.helpName}
            </td>
            <td align="right">联系电话：</td>
            <td colspan="3">
              ${infoVO.helpTel}
            </td>
          </tr>
          <tr class="table_t1" style="padding-bottom: 10px;">
            <td align="right">详细地址：</td>
            <td colspan="5">
              <form:input path="helpAddr" cssClass="form" size="100%"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td align="right">求助内容：</td>
            <td colspan="5">
              <form:textarea path="helpContent" cssClass="form" cols="135" rows="3"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td align="right">求助时间：</td>
            <td>
              <fmt:formatDate value="${infoVO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td align="right">求助区域：</td>
            <td>
            ${qzqyMap[infoVO.helpArea]}
            </td>
            <td width="60" align="right">接洽人：</td>
            <td>
            	<c:if test="${lifeInfoVO.receiverType == '1'}">
            		${vltVO.volunteerName}
            	</c:if>
            	<c:if test="${lifeInfoVO.receiverType == '2'}">
            		${entVO.entpriseName}
            	</c:if>
            </td>
          </tr>
          <tr class="table_t1">
            <td nowrap="nowrap" align="right">接洽人联系电话：</td>
            <td>
                <c:if test="${lifeInfoVO.receiverType == '1'}">
            		${vltVO.linkTel}
            	</c:if>
            	<c:if test="${lifeInfoVO.receiverType == '2'}">
            		${entVO.linkTel}
            	</c:if>
              
            </td>
            <td align="right">派送方式：</td>
            <td>
              ${qzfsMap[infoVO.deliverMode]}
            </td>
            <td width="60" align="right">派单时间：</td>
            <td>
              <fmt:formatDate value="${infoVO.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">回访方式：</td>
            <td>
              <form:select path="callMode" cssClass="form">
                <!--<option selected>选择</option>-->
                	<form:options items="${qzfsList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
            <td align="right">回访时间：</td>
            <td colspan="3">
				<form:input path="callTime" readonly="true"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">回访记录：</td>
            <td colspan="5">
              <form:textarea path="callResult" cols="135" rows="3" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">结案时间：</td>
            <td>
              <form:input path="finishTime" cssClass="form" size="10" readonly="true" />
            </td>
            <td width="90" align="right">求助者满意度：</td>
            <td colspan="3">
              <form:select path="helpApprove" cssClass="form">
                <form:options items="${mydList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">不满意原因：</td>
            <td colspan="5">
              <form:textarea path="unApproveCause" cols="135" rows="3" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">处理结果：</td>
            <td colspan="5">
              <form:select path="dealResult" cssClass="form">
                <form:options items="${mydList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">处理描述：</td>
            <td colspan="5">
               <form:textarea path="dealContent" cols="135" rows="3" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">备注：</td>
            <td colspan="5">
              <form:textarea path="remark" cols="135" rows="3" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td align="right">经办人：</td>
            <td colspan="5">
              <form:select path="principal" cssClass="form">
              	<form:options items="${userList}" itemLabel="userName" itemValue="userId"/>
              </form:select>
            </td>
          </tr>
          <tr align="center">
            <td colspan="6" class="table_t2">
              <img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click()" style="cursor: pointer;"/>
              <img src="images/button_end.gif" width="60" height="18" onclick="btnfinish_click()" style="cursor: pointer;"/>
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
