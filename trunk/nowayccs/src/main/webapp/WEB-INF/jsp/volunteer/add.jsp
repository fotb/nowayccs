<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.streetAreaID,"街道",true)){
    return;
  }
  if(!isValidStringObj( form.commAreaID,"社区",true)){
    return;
  }
    if(!isValidStringObj( form.volunteerNo,"服务者编号",true)){
    return;
  }
  if(!isValidStringObj( form.volunteerName,"服务者名称",true)){
    return;
  }
  if(!isValidStringObj( form.sex,"性别",true)){
    return;
  }
  if(!isValidStringObj( form.sevriceName,"服务项目",true)){
    return;
  }
  if(!isValidStringObj( form.serviceTime,"服务时间",true)){
    return;
  }
  if(!isValidStringObj( form.linkTel,"联系电话",true)){
    return;
  }


  form.submit();
}

function btnback_click(){
  history.back();
}
</script>
<body>
<html:form method="post" action="SysVolunteerAdmin_addsave.do">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">新增服务者信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>所在街道和社区：</td>
            <td width="85%">
              <html:select property="streetAreaID" onchange="SetSelectObjValue(document.forms[0].commAreaID,this.value,'');">
                <option selected>请选择</option>
              </html:select>
              <html:select property="commAreaID">
                <option>请选择</option>
              </html:select>

          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>编号：</td>
            <td>
              <html:text property="volunteerNo" styleClass="form" size="20"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>姓名：</td>
            <td>
              <html:text property="volunteerName" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>性别：</td>
            <td>
              <html:select property="sex">
                <option value="">请选择</option>
                <html:optionsCollection name="SYS_SEX_HASHMAP" label="value" value="key"/>
              </html:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务项目：</td>
            <td>
              <html:text property="sevriceName" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务时间：</td>
            <td>
              <html:text property="serviceTime" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>
              <html:text property="linkTel" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系手机：</td>
            <td>
              <html:text property="linkMobile" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>邮件地址：</td>
            <td>
              <html:text property="email" styleClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>其他：</td>
            <td>
              <html:textarea property="remark" styleClass="form" cols="130" rows="5"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
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
</html:form>
</body>
</html>

