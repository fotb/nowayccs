<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function logout(){
  window.open("login.do?action=logout","_parent");
}

function btnSignIn_onclick() {
  $("#TextResult").html("");
  var i,j,sResult;
  Phone.TimeOut = 10000;
i = Phone.Initial();
sResult = Phone.GetPromptByErrorCode(i);
  //sResult = Phone.GetPromptByErrorCode(i);
  //TextResult.innerHTML = sResult;
$("#TextResult").html(sResult);
  if (i==0)
  {
    TextResult.innerHTML = "";

    sResult = Phone.SynchronizeCcsTime()
    sResult = Phone.GetPromptByErrorCode(j);
    $("#TextResult").append('\n'+sResult);

    $("#TextResult").html("");
    i = Phone.AgentType;
    //j = Phone.SignIn(3);
    j = Phone.SignInEx('TTT',i,'${agentVO.targetDevice}');
    sResult = Phone.GetPromptByErrorCode(j);
postLog("signId", sResult);
    $("#TextResult").append('\n'+sResult);
    window.status = $("#TextResult").html();
  }
}

function btnSignOut_onclick() {
    TextResult.innerHTML = "";
var i,sResult;
i = Phone.SignOutEx();
sResult = Phone.GetPromptByErrorCode(i);
postLog("signOut", sResult);
TextResult.innerHTML = sResult;
window.status = $("#TextResult").html();
}

function BtAns_onclick() {
    TextResult.innerHTML = "";
    var MediaType,lRetVal,sResult;

    MediaType = 5; //语音呼叫
    lRetVal = Phone.AnswerEx(MediaType);
    //lRetVal = Phone.Answer();
    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
postLog("answer", sResult);
    window.status = sResult;
    if(lRetVal == 0) {
      //window.open("/OprInforAccept_icdInit.do?callerNo="+Phone.GetCallerNo(), "", 'height=500, width=750, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }
}

function BtRelease_onclick() {
    TextResult.innerHTML = "";
var MediaType,lRetVal,sResult;



    MediaType = 5;
    lRetVal = Phone.ReleaseCallEx(MediaType);
    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
postLog("Release", sResult);
    window.status = sResult;
}

function Phone_OnRequestReleaseEx(MediaType) {
    //CbMediaType.selectedIndex = MediaType-1;
}

var recordFileName;
function Phone_OnAnswerExSuccess() {
/*
  $("#BtAns").attr("disabled", true); 
  $("#BtRelease").attr("disabled", false); 
  window.open("bizaccept.do?flag=Y&callNo="+Phone.GetCallerNo(), "", 'height=700, width=750, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
*/
//TODO -----
	//getRecordFile();
//alert(Phone.QueryAgentSelfRecordFilename());
}

function getRecordFile() {
	var file = "";
	var recordFileName = Phone.QueryAgentSelfRecordFilename(file);
	var id;
	if(recordFileName=="") {
		id = window.setTimeout("getRecordFile()",5000);
	} else {
	alert(recordFileName);
	alert(file);
		window.clearTimeout(id);
		//post recordFileName to Spring MVC
		$.post("${pageContext.request.contextPath}/bizaccept.do?action=recordfile",
				{callId: callId},
				function(data){							  													
					//nothing					   
				});
	}
}

function Phone_OnSignOutSuccess() {
    btnSignIn.disabled = false;
    btnSignOut.disabled = true;
    BtAns.disabled = true;
    BtRelease.disabled = true;
    btnCallOut.disabled = true;
    btnTrans.disabled = true;
	//btnTransOut.disabled = true;
    btnSayBusy.disabled = true;
    btnSayFree.disabled = true;
}

function Phone_OnSignInExSuccess() {
    btnSignIn.disabled = true;
    btnSignOut.disabled = false;
    BtAns.disabled = false;
    BtRelease.disabled = false;
    btnCallOut.disabled = false;
	//btnTransOut.disabled = false;
    btnTrans.disabled = false;
    btnSayBusy.disabled = false;
    btnSayFree.disabled = true;
}

function Phone_OnSignInExFailure() {

}
function Phone_OnAnswerSuccess() {
/*
  BtAns.disabled = true;
  BtRelease.disabled = false;
  window.open("bizaccept.do?flag=Y&callNo="+Phone.GetCallerNo(), "", 'height=500, width=700, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');
*/
var callId = Phone.GetCallIDByMediaType(5);

$.post("${pageContext.request.contextPath}/bizaccept.do?action=recordfile",
				{callId: callId},
				function(data){							  													
					//nothing					   
				});
}

function Phone_OnReleaseSuccess() {
  BtAns.disabled = false;
  BtRelease.disabled =true;
}

function Phone_OnBackPlayFailure() {

}

function Phone_OnBackPlaySuccess() {

}

function Phone_OnBeforeHold() {

}

function Phone_OnBeforeRelease() {

}

function Phone_OnAgentForceIdleFailure() {

}

function Phone_OnAnswerFailure() {

}

function Phone_OnAnswerRequest() {

}

function Phone_OnAnswerRequestEx() {
    //BtAns.disabled    = false;
  //alert("success");
//alert(Phone.GetCallerNo());

$("#BtAns").attr("disabled", true); 
  $("#BtRelease").attr("disabled", false); 
  window.open("bizaccept.do?flag=Y&callNo="+Phone.GetCallerNo(), "", 'height=700, width=750, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
}

function Phone_test() {


	alert("isLogon:" + Phone.IsInitial);
	alert("IsSignIn:" + Phone.IsSignIn);
alert("isFree:" + Phone.IsFree);
alert("FreeStatus:" + Phone.FreeStatus);
alert("IsTalking:" + Phone.IsTalking);

    //BtAns.disabled    = false;
  //alert("success");
//alert(Phone.GetCallerNo());

$("#BtAns").attr("disabled", true); 
  $("#BtRelease").attr("disabled", false); 
//window.parent.frames("main").location = "bizaccept.do?flag=Y&callNo=13958186722&qzfs=4";
  window.open("bizaccept.do?flag=Y&callNo=82626090&qzfs=4", "", 'height=700, width=750, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
}

var w;//open 窗口对象  
var wTimer;//计时器变量, 监听窗口关闭 
function wisclosed(){  
  if(w.closed){  
    //alert(w.returnValue);//子窗体返回值  
    //alert(transOutPhoneNo.value);//子窗体返回值  
    window.clearInterval(wTimer);
    //外呼转
	if(transOutPhoneNo.value != "" && confirm("确定要把当前电话转接到【" + transOutRegion.value + "】？")) {
		btnTransOut(transOutPhoneNo.value);
	}
	transOutPhoneNo.value = "";
	transOutRegion.value = "";
  }  
} 

function btnTransOut_onclick() {
	w = window.open("agent.do?action=transout", "", 'height=400, width=450, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	if(w) {
		window.w.focus();
	}
	wTimer=window.setInterval("wisclosed()",500);  
}

function btnTransOut(transOutNo) {
	TextResult.innerHTML = "";
	var MediaType,lRetVal,sResult;
	MediaType = 5;
  	//alert("Phone.GetCallerNo():" + Phone.GetCallerNo());
 	//lRetVal = Phone.TransOutEx2(MediaType,"", "1183120534582611263","3",0, "");
	//alert(transOutNo + Phone.GetCallerNo());
	lRetVal = Phone.TransOutEx2(MediaType,"", transOutNo + Phone.GetCallerNo(),"3",0, "");
	
	//alert("stesdfasdf");

    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
	postLog("TransOutEx2", sResult);
    window.status = sResult;
}

function calloutEx(callNo) {
	TextResult.innerHTML = "";
	var MediaType,lRetVal,sResult;
	MediaType = 5;
  	//alert("Phone.GetCallerNo():" + Phone.GetCallerNo());
 	lRetVal = Phone.TransOutEx2(MediaType,"", callNo + Phone.GetCallerNo(),"3",0, "");

    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
	postLog("TransOutEx2", sResult);
    window.status = sResult;
}

//呼出
function btnCallOut_onclick() {
      TextResult.innerHTML = "";
	var MediaType,lRetVal,sResult;
	MediaType = 5;
  var test=window.prompt("请输入被叫号码:", "");
 lRetVal = Phone.CallOutEx2(MediaType,test,"",0,65535,test);

    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
	postLog("callOut", sResult);
    window.status = sResult;

}
//呼出成功
function Phone_OnCallOutSuccess() {
}


//呼叫转移
function btnTrans_onclick() {
      TextResult.innerHTML = "";
      var MediaType,lRetVal,sResult;
      var deskNo=window.prompt("请输入转移工号:", "");
 	var i;
    i = Phone.TransToAgent(5,2,deskNo);
    if (i==0 ){
    }
    else {
      sResult = Phone.GetPromptByErrorCode(i);
      TextResult.value =  TextResult.innerHTML+'\n'+sResult;
		postLog("trans", sResult);
      window.status = sResult;
    }

}

function btnHold_onclick() {
  TextResult.innerHTML = "";
  var MediaType,lRetVal,sResult;
lRetVal = Phone.Hold(1,1);

    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  TextResult.innerHTML+'\n'+sResult;
postLog("hold", sResult);
    window.status = sResult;
}

function btnGetHold_onclick() {
    TextResult.innerHTML = "";
  var MediaType,lRetVal,sResult;
lRetVal = Phone.GetHold(1,1);

    sResult = Phone.GetPromptByErrorCode(lRetVal);
    TextResult.innerHTML =  sResult;
postLog("getHold", sResult);
    window.status = sResult;
}

function btnSayBusy_onclick() {
var i;
  i = Phone.SayBusy();
  sResult = Phone.GetPromptByErrorCode(i);
postLog("sayBusy", sResult);
   window.status = sResult;
}

function btnSayFree_onclick() {
var i;
  i = Phone.SayFree();
  sResult = Phone.GetPromptByErrorCode(i);
	postLog("sayFree", sResult);
   window.status = sResult;
}


function Phone_OnSayBusySuccess() {
    btnSayBusy.disabled = true;
    btnSayFree.disabled = false;

}

function Phone_OnSayFreeSuccess() {
    btnSayBusy.disabled = false;
    btnSayFree.disabled = true;
}

function Phone_CheckStatus() {
	//alert(Phone.IsSignIn());
}


function postLog(action, logId) {
	$.getJSON("index.do?action=icdLog&actionType=" + action + "&logId=" + logId, function(data) {
		//do nothing
	});
}
$(document).ready(function(){
	function sessionHeartBeat(){
   		$.getJSON("index.do?action=sessionHeartBeat", function(data) {
			//do nothing
		});
	}
	//setInterval(sessionHeartBeat,300000);// 注意函数名没有引号和括弧！ 
	setInterval(sessionHeartBeat,300000);// 注意函数名没有引号和括弧！
});

</script>


<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnSayBusySuccess>
<!--
 Phone_OnSayBusySuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnSayFreeSuccess>
<!--
 Phone_OnSayFreeSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnReleaseSuccess>
<!--
 Phone_OnReleaseSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnCallOutSuccess>
<!--
 Phone_OnCallOutSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnSignOutSuccess>
<!--
 Phone_OnSignOutSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnSignInExSuccess>
<!--
 Phone_OnSignInExSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnSignInExFailure>
<!--
Phone_OnSignInExFailure()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAgentForceOutSuccess>
<!--
 Phone_OnAgentForceOutSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAnswerExSuccess>
<!--
 Phone_OnAnswerExSuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnReleaseRequest>
<!--
 Phone_OnReleaseRequest()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnRequestReleaseEx>
<!--
 Phone_OnRequestReleaseEx()

//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAnswerSuccess>
<!--
 Phone_OnAnswerSuccess();
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnBackPlayFailure>
<!--
 Phone_OnBackPlayFailure()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnBackPlaySuccess>
<!--
 Phone_OnBackPlaySuccess()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnBeforeHold>
<!--
 Phone_OnBeforeHold()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnBeforeRelease>
<!--
 Phone_OnBeforeRelease()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAgentForceIdleFailure>
<!--
 Phone_OnAgentForceIdleFailure()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAnswerFailure>
<!--
 Phone_OnAnswerFailure()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAnswerRequest>
<!--
 Phone_OnAnswerRequest()
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnAnswerRequestEx(MediaType)>
<!--
 Phone_OnAnswerRequestEx(1)
//-->
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=Phone EVENT=OnTimer>
<!--
 Phone_CheckStatus()
//-->
</SCRIPT>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="height:115px;width:100%;border: 0px;background-image: url(images/head.jpg);background-repeat:repeat-y">
    <!-- <img src="images/head.jpg" width="100%" height="115"> -->
    </td>
  </tr>
  <tr>
    <td height="24" valign="bottom" background="images/bg_top.gif">
    <table width="95%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="13%" style="vertical-align: middle;">${user.userName}，您好！</td>
        <td width="70%">
        <logic:present name="hjAgent">
          <INPUT id=btnSignIn type=button value=签入 name=button1 LANGUAGE=javascript onclick="return btnSignIn_onclick()">
          <INPUT id=btnSignOut type=button value=签出 name=button2 disabled LANGUAGE=javascript onclick="return btnSignOut_onclick()">
          <INPUT id=btnSayBusy type=button value=示忙 name=button5 disabled LANGUAGE=javascript onclick="return btnSayBusy_onclick()">
          <INPUT id=btnSayFree type=button value=示闲 name=button6 disabled LANGUAGE=javascript onclick="return btnSayFree_onclick()">
          &nbsp;&nbsp;
            <INPUT id=BtAns name=button3 type=button value=应答 disabled LANGUAGE=javascript onclick="return BtAns_onclick()">
          <INPUT id=BtRelease name=button4 type=button value=释放 disabled LANGUAGE =javascript onclick="return BtRelease_onclick()">
         &nbsp;&nbsp;
          <INPUT id=btnHold name=btnHold type=button value=保持 LANGUAGE=javascript onclick="return btnHold_onclick()" style="VISIBILITY: visible">
            <INPUT id=btnGetHold name=btnGetHold type=button value=取保持 LANGUAGE=javascript onclick="return btnGetHold_onclick()" style="VISIBILITY: visible">
          &nbsp;&nbsp;
            <INPUT id =btnCallOut  name=button12  type=button value=呼出 LANGUAGE=javascript onclick="return btnCallOut_onclick()">
          <INPUT id =btnTrans name=button11  type=button value=内呼转 disabled LANGUAGE=javascript onclick="return btnTrans_onclick()">
          <INPUT id =btnTransOut name=btnTransOut  type=button value=外呼转 LANGUAGE=javascript onclick="return btnTransOut_onclick()">
          <input id=transOutPhoneNo name=transOutPhoneNo type=hidden value="">
          <input id=transOutRegion name=transOutRegion type=hidden value="">
		  	<!-- input id="test" name="testbt" type="button" onclick="Phone_test();" value="test"> -->
        </logic:present>
          <div style="position:absolute;top:120;left:595; VISIBILITY:hidden;" id="TextResult"></div>
        </td>
        <td align="center" nowrap="nowrap">
          <script language=JavaScript>
<!--
today=new Date();
var week; var date;
if(today.getDay()==0) week="星期日"
if(today.getDay()==1) week="星期一"
if(today.getDay()==2) week="星期二"
if(today.getDay()==3) week="星期三"
if(today.getDay()==4) week="星期四"
if(today.getDay()==5) week="星期五"
if(today.getDay()==6) week="星期六"
date=(today.getFullYear())+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日"+" "
document.write(""+date+week+"");
// -->
          </script></td>
        <td width="7%" valign="top"><A href="#" border=0><img src="images/topbutton_exit.gif" width="70" height="18" onclick="logout()" border=0></A></td>
      </tr>
    </table></td>
  </tr>
</table>
<c:if test="${not empty agentVO}">
<OBJECT classid=clsid:014D83A5-7E35-11D3-8AF9-00C0DF245E51 name=Phone style="LEFT: 0px; TOP: 0px; VISIBILITY: hidden" VIEWASTEXT>
<PARAM NAME="CcsID" VALUE='${agentVO.serverType}'>
<PARAM NAME="MyID" VALUE="40">
<PARAM NAME="MainCcsIP" VALUE='${agentVO.mainCcsIp}'>
<PARAM NAME="BackCcsIP" VALUE='${agentVO.backCcsIp}'>
<PARAM NAME="WorkNo" VALUE='${agentVO.workNo}'>
<PARAM NAME="DesktopNo" VALUE="1000">
<PARAM NAME="Password" VALUE='${agentVO.password}'>
<PARAM NAME="TimeOut" VALUE="10000">
<PARAM NAME="RecordFileDir" VALUE="c:\temp">
<PARAM NAME="CardType" VALUE='${agentVO.cardType}'>
<PARAM NAME="AutoAnswer" VALUE='${agentVO.autoAnswer}'>
<PARAM NAME="AutoRelease" VALUE='${agentVO.autoRelease}'>
<PARAM NAME="AutoReconnect" VALUE='${agentVO.autoReconnect}'>
<PARAM NAME="HaveBell" VALUE='${agentVO.haveBell}'>
<PARAM NAME="BellTime" VALUE='${agentVO.bellTime}'>
<PARAM NAME="PlayStep" VALUE="2">
<PARAM NAME="FreeStatus" VALUE='${agentVO.freeStatus}'>
<PARAM NAME="TimerEnabled" VALUE="true">
<PARAM NAME="TimerInterval" VALUE="5000">
<PARAM NAME="MediaPlay" VALUE='${agentVO.mediaPlay}'>
<PARAM NAME="MediaFileName" VALUE='${agentVO.mediaFilename}'>
<PARAM NAME="Version" VALUE="3">
<PARAM NAME="AgentType" VALUE='${agentVO.agentType}'>
<PARAM NAME="ConvoyDirection" VALUE="1">
<PARAM NAME="ConvoyMode" VALUE="1">
<PARAM NAME="WFSMode" VALUE="2">
<PARAM NAME="PMSMode" VALUE="1">
</OBJECT>
</c:if>
</body>
</html>
