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

$(document).ready(function(){
	$("#finishTime" ).dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d",
  button: ".next()" //next sibling to input field
	});
});
</script>
<body>
<form:form method="post" action="bizlifebackvst.do?action=backvstsave" commandName="lifeBackVstDomain">
<input type="hidden" name="infoId" id="infoId" value="${infoVO.infoId}"/>
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo	}"/> 
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
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
              <textarea Class="form" cols="120" rows="4" readonly>${infoVO.helpContent}</textarea>
            </td>
          </tr>
          <tr class="table_t1">
            <td>求助时间：</td>
            <td>
              <fmt:formatDate value="${infoVO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>求助区域：</td>
            <td>
            ${qzqyMap[infoVO.helpArea]}
            </td>
            <td>接洽人：</td>
            <td>
              ${vltVO.volunteerName}
            </td>
          </tr>
          <tr class="table_t1">
            <td nowrap="nowrap">接洽人联系电话：</td>
            <td>
              ${vltVO.linkTel}
            </td>
            <td>派送方式：</td>
            <td>
              ${qzfsMap[infoVO.deliverMode]}
            </td>
            <td>派单时间：</td>
            <td>
              <fmt:formatDate value="${infoVO.deliverTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td>回访方式：</td>
            <td>
              <form:select path="callMode" cssClass="form">
                <!--<option selected>选择</option>-->
                	<form:options items="${qzfsList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
            <td>回访时间：</td>
            <td colspan="3">
				<form:input path="callTime" readonly="true"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>回访记录：</td>
            <td colspan="5">
              <form:textarea path="callResult" cols="140" rows="6" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>结案时间：</td>
            <td>
              <form:input path="finishTime" cssClass="form" size="10" />
            </td>
            <td>求助者满意度：</td>
            <td colspan="3">
              <form:select path="helpApprove" cssClass="form">
                <form:options items="${mydList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td>不满意原因：</td>
            <td colspan="5">
              <form:textarea path="unApproveCause" cols="140" rows="6" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理结果：</td>
            <td colspan="5">
              <form:select path="dealResult" cssClass="form">
                <form:options items="${mydList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理描述：</td>
            <td colspan="5">
               <form:textarea path="dealContent" cols="140" rows="6" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>备注：</td>
            <td colspan="5">
              <form:textarea path="remark" cols="140" rows="2" cssClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>经办人：</td>
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