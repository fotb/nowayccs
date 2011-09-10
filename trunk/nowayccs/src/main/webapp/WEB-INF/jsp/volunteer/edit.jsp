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
<script language="javascript" src="js/common.js" type=""></script>
<script language="javascript" src="js/function.js" type=""></script>
<script language="javascript" type="">
function btnsave_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.areaId,"街道",true)){
    return;
  }
  if(!isValidStringObj( form.areaSubId,"社区",true)){
    return;
  }
    if(!isValidStringObj( form.volunteerNo,"服务者编号",true)){
    return;
  }

	if(!isInteger($("#volunteerNo").val())) {
		alert("服务者编号必须为数字,请修改!");
		return;
	}

  if(!isValidStringObj( form.serviceType,"是否可派单",true)){
    return;
  }

  if(!isValidStringObj( form.volunteerName,"服务者名称",true)){
    return;
  }
  if(!isValidStringObj( form.sex,"性别",true)){
    return;
  }
  if(!isValidStringObj( form.serviceName,"服务项目",true)){
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

$(document).ready(function(){
	$.getJSON("volunteer.do?action=area", function(data) {
		$("#areaId").append("<option value=' '>全部</option>");
		$.each(data, function(i, item) {
			if("${volunteerVO.areaId}" == item.areaId) {
				$("#areaId").append("<option value=" + item.areaId + " selected='selected'>" + item.name + " </option>");
			} else {
				$("#areaId").append("<option value=" + item.areaId + ">" + item.name + " </option>");
			}
		});
	});


	if("${volunteerVO.areaSubId}" != "") {
		fillAreaSub(${volunteerVO.areaId});
	}

	$("#areaId").change(function() {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("volunteer.do?action=subarea", {areaId: $("#areaId").val()}, function(data) {
			$.each(data, function(i, item) {
				$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
			});
		});
	});
});

function fillAreaSub(areaId) {
		$("#areaSubId").empty();
		$("#areaSubId").append("<option value=' '>全部</option>");
		$.getJSON("volunteer.do?action=subarea", {areaId: areaId}, function(data) {
			$.each(data, function(i, item) {
				if("${volunteerVO.areaSubId}" == item.areaSubId) {
					$("#areaSubId").append("<option value=" + item.areaSubId + " selected='selected'>" + item.name + " </option>");
				} else {
					$("#areaSubId").append("<option value=" + item.areaSubId + ">" + item.name + " </option>");
				}
			});
		});
}

</script>
<body>
<form:form method="post" action="volunteer.do?action=saveorupdate" commandName="volunteerVO">
<input type="hidden" value="${pageNo}" name="pageNo"/>
<form:hidden path="volunteerId"/>
<form:hidden path="status"/>
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
	        	<form:select path="areaId" cssClass="form">
	        		
	        	</form:select>
	        	<form:select path="areaSubId" cssClass="form">
	        		<option value=" ">全部</option>
	        	</form:select>
	        	<font color="red">*</font>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>编号：</td>
            <td>
              <form:input path="volunteerNo" cssClass="form" size="20"/><font color="red">*</font>
            </td>
          </tr>
          <tr class="table_t1">
          <td>是否可以派单：</td>
          <td>
			<form:select path="serviceType" cssClass="form" >
			<form:option value="Y">可派单</form:option>
			<form:option value="N">不可派单</form:option>
			</form:select>
			<font color="red">*</font>
          </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>姓名：</td>
            <td>
              <form:input path="volunteerName" cssClass="form" size="70"/><font color="red">*</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>性别：</td>
            <td>
              <form:select path="sex">
                <form:option value="1">男</form:option>
                <form:option value="2">女</form:option>
              </form:select>
              <font color="red">*</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务项目：</td>
            <td>
              <form:input path="serviceName" cssClass="form" size="70"/><font color="red">*</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务时间：</td>
            <td>
              <form:input path="serviceTime" cssClass="form" size="70"/><font color="red">*</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>
              <form:input path="linkTel" cssClass="form" size="70"/><font color="red">*</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系手机：</td>
            <td>
              <form:input path="linkMobile" cssClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>邮件地址：</td>
            <td>
              <form:input path="email" cssClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>其他：</td>
            <td>
              <form:textarea path="remark" cssClass="form" cols="130" rows="5"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
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