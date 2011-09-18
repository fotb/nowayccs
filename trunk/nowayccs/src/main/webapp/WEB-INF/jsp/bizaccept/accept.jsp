<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/smoothness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script src="js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.jqGrid.src.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/function.js" type="text/javascript"></script>
<script type="text/javascript">
function btnnext_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.helpName,"求助者姓名",true)){
    return;
  }
  if(!isValidStringObj( form.helpMode,"求助方式",true)){
    return;
  }
  if(!isValidStringObj( form.helpTel,"求助者电话",true)){
    return;
  }
  /*
  if(!isValidStringObj( form.helpAddr,"求助者地址",true)){
    return;
  }
  */
  if(!isValidStringObj( form.helpContent,"求助内容",true)){
    return;
  }
  if(!isValidStringObj( form.helpType,"求助类型",true)){
    return;
  }
  if(!isValidStringObj( form.helpArea,"求助区域",true)){
    return;
  }
  
  if("2" == $("#helpType").val()) {
	$("#action").val("life");
	$("form").submit();
  } else if("3" == $("#helpType").val()) {
		$("#action").val("affair");
		$("form").submit();
  } else if("1" == $("#helpType").val() || "4" == $("#helpType").val()) {
		$("#action").val("refer");
		$("form").submit();
  }
}

function showInfo(id) {
  var form=document.forms[0];
  form.action="opr_lifeinfo_show.do";
  form.lifeInformationID.value=id;
  form.target = "_blank";
  form.submit();
}

function showLifeInfo(id) {
  var form=document.forms[0];
  form.action="opr_lifeinfo_show.do";
  form.lifeInformationID.value=id;
  form.target = "_blank";
  form.submit();
}
function showReferInfo(id) {
  var form=document.forms[0];
  form.action="opr_referinfo_show.do";
  form.referInformationID.value=id;
  form.target = "_blank";
  form.submit();
}
function showAffairInfo(id) {
  var form=document.forms[0];
  form.action="opr_affairinfo_show.do";
  form.affairInformationID.value=id;
  form.target = "_blank";
  form.submit();
}


$(document).ready(function(){
	loadHist();
	$("#helpTel").change(function(){
		jQuery("#histList").setGridParam({url:"bizaccept.do?action=helphist&callNo="+$("#helpTel").val()+""});
		jQuery("#histList").trigger("reloadGrid");
	});
});

function loadHist() {
	jQuery("#histList").jqGrid({ 
		url:"bizaccept.do?action=helphist&callNo="+$("#helpTel").val()+"", 
		datatype: "json", 
		colNames:['求助者姓名','求助时间','详细地址','求助内容','求助类别','输入人','结案时间','壮态 '], 
		colModel:[ 
		           {name:'helpName',index:'helpName'}, 
		           {name:'createTime',index:'createTime'}, 
		           {name:'helpAddr',index:'helpAddr'}, 
		           {name:'helpContent',index:'helpContent', align:"left"}, 
		           {name:'helpType',index:'helpType', align:"right"}, 
		           {name:'creator',index:'creator', align:"right"},
		           {name:'finishTime',index:'finishTime'}, 
		           {name:'status',index:'status', sortable:false} 
		], 
		rowNum:10, 
		rowList:[10,20,30], 
		pager: '#histPagerNav', 
		sortname: 'createTime', 
		viewrecords: true, 
		sortorder: "desc", 
		caption:"近期求助记录" 
	}); 
	jQuery("#histList").jqGrid('navGrid','#histPagerNav',{edit:false,add:false,del:false});	
}
</script>
<body>
<form:form method="post" action="bizaccept.do" commandName="bizAccept">
<input type="hidden" name="action" id="action"/>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">业务受理</td>
            <td class="font_no" width="3%" align="right">
              <!--<INPUT id =btnTransInner name=btnTransInner  type=button value=内部转移 LANGUAGE=javascript ondblclick="return btnTransInner_ondblclick()">-->
            </td>
            <!--<td class="font_no" width="3%" align="right"><INPUT id =btnCallOut name=btnCallOut  type=button value=呼出 LANGUAGE=javascript ondblclick="return btnCallOut_ondblclick()"></td>-->
            <td class="font_no" width="3%" align="right">
              <!--<INPUT id =btnTransOut name=btnTransOut  type=button value=转出 LANGUAGE=javascript ondblclick="return btnTransOut_ondblclick()">-->
            </td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
           <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="15%">求助者姓名：</td>
            <td>
              <form:input path="helpName" cssClass="form" size="40"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助方式：</td>
            <td>
              <form:select path="helpMode" cssClass="form" items="${qzfsList}" itemLabel="value" itemValue="sortIndex" />
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
              <form:input path="helpTel" cssClass="form" size="40"/><font color="red">(*长度不能超过50位)</font>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td>
              <form:input path="helpAddr" cssClass="form" size="70"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td>
              <form:textarea path="helpContent" cssClass="form" cols="120" rows="4"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助类型：</td>
            <td>
              <form:select path="helpType" cssClass="form" >
                <form:option value=" ">选择</form:option>
                <form:options items="${helpTypeMap}"/>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
              <form:select path="helpArea" cssClass="form" items="${qzqyList}" itemLabel="value" itemValue="sortIndex" />              
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
              <tr class="table_t1">
            <td>受理人群：</td>
            <td>
              <form:select path="helpGroup" cssClass="form">
                <option value=" ">选择</option>
                <form:options items="${slrqList}" itemLabel="value" itemValue="sortIndex"/>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务者：</td>
            <td>${user.userName}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助时间：</td>
            <td>${bizAccept.createTime}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
              <img src="images/button_next.gif" width="60" height="18" onclick="btnnext_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <form:hidden path="creator"/>
  <form:hidden path="createTime"/>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="table_gray">
			<!-- 
			<tr class="table_t1">
				<td width="3%" align="center"><img src="images/icon_01.gif"
					width="5" height="17" alt=""></td>
				<td class="font_no">近期求助记录</td>
			</tr>
			 -->
			<tr>
				<td colspan="1">
					<table id="histList"></table>
					<div id="histPagerNav"></div>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
