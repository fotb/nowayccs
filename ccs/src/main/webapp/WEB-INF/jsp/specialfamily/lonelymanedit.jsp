<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.manName,"姓名",true)){
    return;
  }
  if(!isValidStringObj( form.manSex,"性别",true)){
	    return;
	  }
  if(!isValidStringObj( form.birthday,"出生年月",true)){
	    return;
	  }
  if(!isValidStringObj( form.idCardNo,"身份证号码",true)){
	    return;
	  }
  if(!isValidStringObj( form.telphone,"固定联系电话",true)){
    return;
  }
  if(!isValidStringObj( form.area,"所属区域",true)){
	    return;
	  }
  if(!isValidStringObj( form.familyType,"家庭类别",true)){
	    return;
	  }
  form.submit();
}

function btnback_click(){
  history.back();
}

$(document).ready(function(){
	$("#birthday").dynDateTime({
  showsTime: true,
  ifFormat: "%Y-%m-%d"
  //button: ".next()" //next sibling to input field
	});
});
</script>
</head>
<body>
<form:form method="post" action="lfmgr.do?action=saveorupdate" commandName="lmiVO">
<input type="hidden" name="manId" value="${lmiVO.manId }" />
<input type="hidden" name="pageNo" value="${pageNo}" />
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">新增结对家庭</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>          
          <tr class="table_t1" >
            <td width="15%">姓名：</td>
            <td>
            	<form:input path="manName" cssClass="form" size="20" maxLength="20"/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><form:errors path="manName"/></font>
            </td>
            <td>性别：</td>
            <td>
            	<form:input path="manSex" cssClass="form" szie="20" maxLength="20" cssStyle="width:100px;"/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><form:errors path="manSex"/></font>
            </td>
            <td>出生年月：</td>
            <td>
            <form:input path="birthday" cssClass="form" szie="20" maxLength="10" cssStyle="width:200px;"/>
            </td>
          </tr>          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%" >身份证号码：</td>
            <td colspan="5"><form:input path="idCardNo"  cssClass="form" szie="18" maxLength="18" cssStyle="width:400px;"/></td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">所属区域：</td>
            <td colspan="5">
            <form:input path="area" cssClass="form" szie="70" maxLength="20" cssStyle="width:400px;"/>
            </td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭住址：</td>
            <td colspan="5">
            <form:input path="address" cssClass="form" szie="20" maxLength="20" cssStyle="width:400px;"/>
            </td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">固定联系电话：</td>
            <td colspan="5">
            <form:input path="telphone" cssClass="form" szie="20" maxLength="20" cssStyle="width:400px;" />
            </td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭类别：</td>
            <td colspan="5">
            <form:select path="familyType">
            	<form:option value="L">孤寡、空巢老人</form:option>
            	<form:option value="D">困难残疾家庭</form:option>
            </form:select>
            </td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">家庭基本情况：</td>
            <td colspan="5">
            <form:textarea path="familyInfo" cssClass="form" szie="200" cssStyle="width:400px;height:60px;"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
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
