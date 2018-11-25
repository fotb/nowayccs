<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/common.js" type=""></script>
<script language="javascript" src="js/function.js" type="">
</script>
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
    if(!isValidStringObj( form.entpriseNo,"企业编号",true)){
    return;
  }
	if(!isInteger($("#entpriseNo").val())) {
		alert("企业编号必须为数字,请修改!");
		return;
	}

  if(!isValidStringObj( form.entpriseName,"企业名称",true)){
    return;
  }
  if(!isValidStringObj( form.address,"地址",true)){
    return;
  }
  /*
  if(!isValidStringObj( form.lawDeputy,"法人代表",true)){
    return;
  }
  if(!isValidStringObj( form.linkTel,"联系电话",true)){
    return;
  }
  if(!isValidStringObj( form.linkName,"联系人姓名",true)){
    return;
  }
  */
  if(!isValidStringObj( form.memberSign,"是否愿意加盟",true)){
    return;
  }

  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="entprise.do?action=save" commandName="entpriseVO">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<form:hidden path="entpriseId"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">修改企业信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">企业编号：</td>
            <td>
            	<form:input path="entpriseNo" size="30" cssClass="form"/>
            </td>
            <td>能否派单：</td>
            <td>
              <form:select path="servicesType">
                <form:option value="Y">可派单</form:option>
                <form:option value="N">不可派单</form:option>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">企业名称：</td>
            <td colspan="3">
            	<form:input path="entpriseName" size="70" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>地址：</td>
            <td colspan="3">
            <form:input path="address" size="70" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>法定代表人（负责人）：</td>
            <td colspan="3">
            	<form:input path="lawDeputy" size="30" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="18%">注册资本：</td>
            <td width="32%">
            	<form:input path="regMoney" size="30" cssClass="form"/>
            </td>
            <td width="18%">服务时间：</td>
            <td width="32%">
            <form:input path="serviceTime" size="30" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>
            <form:input path="linkTel" size="30" cssClass="form"/>
            </td>
            <td>服务电话：</td>
            <td>
            <form:input path="serviceTel" size="30" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系人姓名：</td>
            <td>
            <form:input path="linkName" size="30" cssClass="form"/>
            </td>
            <td>是否愿意加盟：</td>
            <td>
              <form:select path="memberSign">
                <form:option value="">请选择</form:option>
                <form:option value="Y">是</form:option>
                <form:option value="N">否</form:option>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>工商注册号：</td>
            <td>
            <form:input path="registerCode" size="30" cssClass="form"/>
            </td>
            <td>税务登记号：</td>
            <td>
            <form:input path="taxCode" size="30" cssClass="form"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>

          <tr class="table_t1">
            <td>经营范围：</td>
            <td colspan="3">
            	<form:textarea path="dealRange" cssClass="form" cols="130" rows="5"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
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

