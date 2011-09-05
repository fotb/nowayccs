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
  form.action ="SysEntpriseAdmin_editsave.do";

  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<html:form method="post" action="SysEntpriseAdmin_editsave.do">
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
            <td colspan="3">
              <html:text property="entpriseNo" styleClass="form" size="30"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">企业名称：</td>
            <td colspan="3">
              <html:text property="entpriseName" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>地址：</td>
            <td colspan="3">
              <html:text property="address" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>法定代表人（负责人）：</td>
            <td colspan="3">
              <html:text property="lawDeputy" styleClass="form" size="30"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="18%">注册资本：</td>
            <td width="32%">
              <html:text property="regMoney" styleClass="form" size="30"/>
            </td>
            <td width="18%">服务时间：</td>
            <td width="32%">
              <html:text property="serviceTime" styleClass="form" size="30"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>
              <html:text property="linkTel" styleClass="form" size="30"/>
            </td>
            <td>服务电话：</td>
            <td>
              <html:text property="serviceTel" styleClass="form" size="30"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系人姓名：</td>
            <td>
              <html:text property="linkName" styleClass="form" size="30"/>
            </td>
            <td>是否愿意加盟：</td>
            <td>
              <html:select property="memberSign">
                <option value="">请选择</option>
                <html:optionsCollection name="SYS_YESNO_NO" label="value" value="key"/>
              </html:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>工商注册号：</td>
            <td>
              <html:text property="registerCode" styleClass="form" size="30"/>
            </td>
            <td>税务登记号：</td>
            <td>
              <html:text property="taxCode" styleClass="form" size="30"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>

          <tr class="table_t1">
            <td>经营范围：</td>
            <td colspan="3">
              <html:textarea property="dealRange" styleClass="form" cols="130" rows="5"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
              <html:img src="images/button_save.gif" width="60" height="18" onclick="btnsave_click()"/>
              <html:img src="images/button_back.gif" alt="返回前一页面" width="60" height="18" border="0" onclick="btnback_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <html:hidden property="entpriseID"/>
</html:form>
</body>
</html>
