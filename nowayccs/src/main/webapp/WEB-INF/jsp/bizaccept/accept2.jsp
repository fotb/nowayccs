<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务受理</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/smoothness/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css"/> 
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="js/lang/calendar-zh.js"></script>
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
  /*
  if(!isValidStringObj( form.helpTel,"求助者电话",true)){
    return;
  }
  */
  /*
  if(!isValidStringObj( form.helpAddr,"求助者地址",true)){
    return;
  }
  */
  if(!isValidStringObj( form.helpContent,"求助内容",true)){
    return;
  }
  
  if($("#lonelyFamilyDeal").attr("checked") == "checked") {
	  //结对家庭求助处理
	  $("#bizAccept").attr("action", "lonelyFamily.do");
	  $("#action").val("accept");
	  $("form").submit();
  } else {
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
	getLonelyFamily($("#helpTel").val());
	loadHist();
	getPhoneLevels($("#helpTel").val());
	$("#helpTel").change(function(){
		jQuery("#histList").setGridParam({url:"bizaccept.do?action=helphist&callNo="+$("#helpTel").val()+""});
		jQuery("#histList").trigger("reloadGrid");
		
		getPhoneLevels($("#helpTel").val());
		getLonelyFamily($("#helpTel").val());
	});
	
	$("#addContent").click(function(){
		if($("#helpContent2").css("display") != "block") {
			$("#helpContent2").css("display", "block");
		} else if($("#helpContent3").css("display") != "block"){
			$("#helpContent3").css("display", "block");
		} else {
		alert("dafa");
			$("#helpContent4").css("display", "block");
		}
	});
	
	$("#reduceContent").click(function(){
		if($("#helpContent4").css("display") == "block") {
			$("#helpContent4").css("display", "none");
		} else if($("#helpContent3").css("display") == "block"){
			$("#helpContent3").css("display", "none");
		} else {
			$("#helpContent2").css("display", "none");
		}
	});
	
	$("#createTime" ).dynDateTime({
		  showsTime: true,
		  ifFormat: "%Y-%m-%d %H:%M:%S",
		  button: ".next()" //next sibling to input field
			});
});

function getPhoneLevels(phone) {
	if(phone == "") {
		phone = "${bizAccept.helpTel}";
	}
	$.getJSON("blacklist.do?action=phonelevels&phoneNum=" + phone, function(data) {
		if(data != "") {
			$("#phonelevels").html("");
			if(data[0].levels < 0) {
				$("#phonelevels").css("color", "black");
			} else {
				$("#phonelevels").css("color", "red");
			}
			
			for(i = 0; i < Math.abs(data[0].levels); i++) {
				$("#phonelevels").append("★");
			}
			
			$("#phonelevels").append("(" + data[0].remark + ")");
		}
	});
}

function loadHist() {
	var lastsel;
	jQuery("#histList").jqGrid({ 
		url:"bizaccept.do?action=helphist&callNo="+$("#helpTel").val()+"", 
		datatype: "json", 
		colNames:['求助者姓名','求助时间','详细地址','求助内容','求助类别','输入人','结案时间','壮态 ', ''], 
		colModel:[ 
		           {name:'helpName',index:'helpName', formatter:bizShowLinkFormatter, width:'90'}, 
		           {name:'createTime',index:'createTime',width:'120'}, 
		           {name:'helpAddr',index:'helpAddr',width:'200'}, 
		           {name:'helpContent',index:'helpContent', align:"left",width:'200'}, 
		           {name:'helpType',index:'helpType', align:"right",width:'80'}, 
		           {name:'creator',index:'creator', align:"right",width:'60'},
		           {name:'finishTime',index:'finishTime',width:'120'}, 
		           {name:'status',index:'status', sortable:false,width:'50'} ,
		           {name:'infoId', index:'infoId', hidden:true}
		], 
		rowNum:10, 
		rowList:[10,20,30], 
		pager: '#histPagerNav', 
		sortname: 'createTime', 
		viewrecords: true, 
		sortorder: "desc", 
		caption:"近期求助记录"
		/*
		onSelectRow: function(id){ 
			//alert(id);
			//if(id && id!==lastsel){ 
					window.open("infosearch.do?action=showinfo&infoId=" + id, "", 'height=700, width=750, top=0, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'); 
			//		lastsel=id; 
			//	} 
			}
		*/
	}); 
	jQuery("#histList").jqGrid('navGrid','#histPagerNav',{edit:false,add:false,del:false});	
}

function bizShowLinkFormatter(cellValue, options, rowObj) {
	return "<a href=\"infosearch.do?action=showinfo&infoId=" + options.rowId + "\" target=\"_blank\">"+cellValue+"</a>";
}

function getLonelyFamily(phone) {
	if(phone == "") {
		phone = "${bizAccept.helpTel}";
	}
	if(phone != "") {
		$.getJSON("lonelyFamily.do?action=lonelyManInfo&callNo=" + phone, function(data) {
		if(null != data) {
			$("#manName").text(data.manName);
			$("#manSex").text(data.manSex);
			$("#manBirthday").text(data.birthday);
			$("#idCardNo").text(data.idCardNo);
			$("#manArea").text(data.area);
			$("#manAddr").text(data.address);
			$("#manTelphone").text(data.telphone);
			if("L" == data.familyType) {
				$("#familyType").text("孤寡、空巢老人");
			} else if("D" == data.familyType) {
				$("#familyType").text("困难残疾家庭");
			}
			
			$("#familyInfo").text(data.familyInfo);
			
			$("#phonelevels").append("<div style='color:red;font-weight:bold;'>（结对家庭求助电话）</div>");
			
			$("#helpName").val(data.manName);
			$("#helpAddr").val(data.address);
			
			$("#lonelyManTb").show();
			$("#lonelyFamilyDiv").show();
		} else {
			$("#lonelyManTb").hide();
			$("#lonelyFamilyDiv").hide();
		}
	});
	} else {
		$("#lonelyManTb").hide();
		$("#lonelyFamilyDiv").hide();
	}
}

</script>
<body>
<form:form method="post" action="bizaccept.do" commandName="bizAccept">
<input type="hidden" name="action" id="action"/>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td colspan="2">
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
              <form:input path="helpName" cssClass="form_accept" size="30" tabindex="1"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助方式：</td>
            <td>
              <form:select path="helpMode" cssClass="form_accept" items="${qzfsList}" itemLabel="value" itemValue="sortIndex" />
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
            <c:choose>
				<c:when test="${not empty bizAccept.popupFlag}">
				<div style="margin-top:4px;font-family: sans-serif; font-size: 14px; font-weight: bold; color: #333333; height:25px;float: left;vertical-align: middle;">${bizAccept.helpTel}</div>
				<form:hidden path="helpTel" />
				</c:when>
				<c:otherwise>
				<form:input path="helpTel" cssClass="form_accept" size="30" />
				</c:otherwise>
			</c:choose>
			<div id="phonelevels" style="margin-top:4px;color: black;vertical-align: middle;float:left;"></div>
            <c:if test="${not empty bizAccept.popupFlag}">
               <div style="vertical-align: middle; float:left;padding-left: 20px;">其他联系电话：<form:input path="otherTel" cssClass="form_accept" size="30"/></div>
            </c:if>
            </td>
            </tr>

          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td>
              <form:input path="helpAddr" cssClass="form_accept" size="50" tabindex="2"/>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：<br><br><input type="button" value="+" id="addContent"/><input type="button" value="-" id="reduceContent"/></td>
            <td>
              <form:textarea path="helpContent" cssClass="form_accept" cols="1" rows="4" tabindex="3" cssStyle="width:440px;height:60px;"/>
              <form:textarea path="helpContent2" cssClass="form_accept" cols="1" rows="4" cssStyle="display:none;width:440px;height:60px;"/>
              <form:textarea path="helpContent3" cssClass="form_accept" cols="1" rows="4" cssStyle="display:none;width:440px;height:60px;"/> 
              <form:textarea path="helpContent4" cssClass="form_accept" cols="1" rows="4" cssStyle="display:none;width:440px;height:60px;"/>              
            </td>
          </tr>
          
	          
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助类型：</td>
            <td>
              <form:select path="helpType" cssClass="form_accept" >
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
              <form:select path="helpArea" cssClass="form_accept" items="${qzqyList}" itemLabel="value" itemValue="sortIndex" />              
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
              <tr class="table_t1">
            <td>受理人群：</td>
            <td>
              <form:select path="helpGroup" cssClass="form_accept">
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
            <td><form:input path="createTime" size="20" readonly="true"/></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
         
        </table>
      </td>
    </tr>
 <tr >
            <td  align="center">
              <div style="position: absolute;float:left;left:100px;" id="lonelyFamilyDiv"><input type="checkbox" id="lonelyFamilyDeal" style="vertical-align: middle;"/>结对家庭业务</div><img style="vertical-align: middle;"   src="images/button_next.gif" width="60" height="18" onclick="btnnext_click()"/>
            </td>
          </tr>         
              <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
    <tr style="padding-top: 20px;" >
    <td colspan="2">

					<table width="100%" border="0" align="center" cellpadding="0" id="lonelyManTb">
						<tr class="table_t1">
							<td colspan="5" class="font_no">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr class="table_t1">
										<td width="3%" align="center"><img
											src="images/icon_01.gif" width="5" height="17" alt="">
										</td>
										<td class="font_no">结对家庭信息</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr class="table_t1" style="height: 20px;">
							<td width="90">姓名：</td>
							<td align="left" id="manName"></td>
							<td width="40">性别：</td>
							<td align="left" id="manSex"></td>
							<td width="60">出生日期：</td>
							<td align="left" id="manBirthday"></td>
						</tr>
						<tr class="table_t2" style="height: 20px;">
							<td>身份证号码：</td>
							<td colspan="5" id="idCardNo"></td>
						</tr>
						<tr class="table_t1" style="height: 20px;">
							<td>所属区域：</td>
							<td colspan="5" id="manArea"></td>
						</tr>
						<tr class="table_t2" style="height: 20px;">
							<td>家庭地址：</td>
							<td colspan="5" id="manAddr"></td>
						</tr>
						<tr class="table_t1" style="height: 20px;">
							<td>固定联系电话：</td>
							<td colspan="5" id="manTelphone"></td>
						</tr>
						<tr class="table_t2" style="height: 20px;">
							<td>家庭类别：</td>
							<td colspan="5" id="familyType"></td>
						</tr>
						<tr class="table_t1" style="height: 40px;">
							<td>家庭基本情况：</td>
							<td colspan="5" id="familyInfo"></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="table_gray">
						<tr>
							<td colspan="1">
								<table id="histList"></table>
								<div id="histPagerNav"></div>
							</td>
						</tr>
					</table>
				</td>
    </tr>
  </table>
  <form:hidden path="creator"/>
  <form:hidden path="createTime"/>
		<form:hidden path="popupFlag"/>
	</form:form>
</body>
</html>
