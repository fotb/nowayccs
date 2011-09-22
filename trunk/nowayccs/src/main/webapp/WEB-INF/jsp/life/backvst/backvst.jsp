<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsave_click(endsign){
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
  form.endsign.value=endsign;
  form.submit();
}


function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="OprLifeInforBack_backsave.do">
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
          <tr class="table_t1">
            <td width="10%">序号：</td>
            <td colspan="6">
              <bean:write name="hjLifeInformation" property="lifeInformationId"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td width="11%">求助者姓名：</td>
            <td width="15%">
              <bean:write name="hjLifeInformation" property="hjInformation.helpName"/>
            </td>
            <td width="12%">联系电话：</td>
            <td width="15%">
              <bean:write name="hjLifeInformation" property="hjInformation.helpTel"/>
            </td>
            <td width="10%">详细地址：</td>
            <td width="37%">
              <bean:write name="hjLifeInformation" property="hjInformation.helpAddr"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td colspan="5">
              <textarea Class="form" cols="120" rows="4" readonly><bean:write name="hjLifeInformation" property="hjInformation.helpContent"/></textarea>
            </td>
          </tr>
          <tr class="table_t1">
            <td>求助时间：</td>
            <td>
              <bean:write name="helpTime"/>
            </td>
            <td>求助区域：</td>
            <td>
            <bean:write name="qzqy"/>
            </td>
            <td>接洽人：</td>
            <td>
              <bean:write name="jqr"/>
            </td>
          </tr>
          <tr class="table_t1">
            <td nowrap="nowrap">接洽人联系电话：</td>
            <td>
              <bean:write name="jqrtel"/>
            </td>
            <td>派送方式：</td>
            <td>
              <bean:write name="llfs"/>
            </td>
            <td>派单时间：</td>
            <td>
              <bean:write name="handTime"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t2">
            <td>回访方式：</td>
            <td>
              <html:select property="callMode" styleClass="form">
                <!--<option selected>选择</option>-->
                <html:optionsCollection name="llfsDictList" label="content" value="code"/>
              </html:select>
            </td>
            <td>回访时间：</td>
            <td colspan="3">
<bean:write name="callTime" />
            </td>
          </tr>
          <tr class="table_t2">
            <td>回访记录：</td>
            <td colspan="5">
              <html:textarea property="callResult" cols="140" rows="2" styleClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>结案时间：</td>
            <td>
              <html:text property="finishTime" styleClass="form" size="10" onfocus="CreateMonthView(this)" onblur="DeleteMonthView(this)"/>
            </td>
            <td>求助者满意度：</td>
            <td colspan="3">
              <html:select property="helpApprove" styleClass="form">
                <option selected>选择</option>
                <html:optionsCollection name="mydDictList" label="content" value="code"/>
              </html:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td>不满意原因：</td>
            <td colspan="5">
              <html:textarea property="unapproveCause" cols="140" rows="2" styleClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理结果：</td>
            <td colspan="5">
              <html:select property="dealResult" styleClass="form">
                <option selected>选择</option>
                <html:optionsCollection name="mydDictList" label="content" value="code"/>
              </html:select>
            </td>
          </tr>
          <tr class="table_t2">
            <td>处理描述：</td>
            <td colspan="5">
               <html:textarea property="dealContent" cols="140" rows="2" styleClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>备注：</td>
            <td colspan="5">
              <html:textarea property="remark" cols="140" rows="2" styleClass="form"/>
            </td>
          </tr>
          <tr class="table_t2">
            <td>经办人：</td>
            <td colspan="5">
              <html:select property="principal" styleClass="form">
                <html:optionsCollection name="hjUserList" label="userName" value="userId"/>
              </html:select>
            </td>
          </tr>
          <tr align="center">
            <td colspan="6" class="table_t2">
              <html:img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click(0)"/>
              <html:img src="images/button_end.gif" width="60" height="18" onclick="btnsave_click(1)"/>
              <html:img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <html:hidden property="lifeInformationID"/>
  <input type="hidden" name="endsign">
</form:form>
</body>
</html>
