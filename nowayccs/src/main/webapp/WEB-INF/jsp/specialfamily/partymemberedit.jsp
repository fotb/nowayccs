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
  if(!isValidStringObj( form.memberName,"姓名",true)){
    return;
  }
  if(!isValidStringObj( form.memberSex,"性别",true)){
	    return;
	  }
  if(!isValidStringObj( form.birthday,"出生年月",true)){
	    return;
	  }
  if(!isValidStringObj( form.workDept,"工作单位",true)){
	    return;
	  }
  if(!isValidStringObj( form.linkPhone,"联系电话",true)){
    return;
  }
  if(!isValidStringObj( form.address,"所属区域",true)){
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
<form:form method="post" action="lfmgr.do?action=pmsaveorupdate" commandName="pmVO">
<input type="hidden" name="manId" value="${manId}" />
<input type="hidden" name="memberId" value="${pmVO.memberId}" />
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">添加党员志愿者</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>          
          <tr class="table_t1" >
            <td width="15%">姓名：</td>
            <td>
            	<form:input path="memberName" cssClass="form" size="20" maxLength="20"/>
            </td>
            <td>性别：</td>
            <td>
            	<form:select path="memberSex">
            		<form:option value="M">男</form:option>
            		<form:option value="W">女</form:option>
            	</form:select>
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
            <td width="15%" >工作单位：</td>
            <td colspan="5"><form:input path="workDept"  cssClass="form" szie="18" maxLength="18" cssStyle="width:400px;"/></td>
          </tr>         
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">联系电话：</td>
            <td colspan="5">
            <form:input path="linkPhone" cssClass="form" szie="70" maxLength="20" cssStyle="width:400px;"/>
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
            <td width="15%">特长：</td>
            <td colspan="5">
            <form:input path="specialty" cssClass="form" szie="20" maxLength="20" cssStyle="width:400px;"/>
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
