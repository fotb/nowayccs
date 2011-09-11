<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
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
form.action="OprInforAccept_icdinitsubmit.do";
  form.target = "_self";
  form.submit();
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

</script>
<body>
<form:form method="post" action="bizaccept.do?action=submit" commandName="bizAccept">
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
              <form:select path="helpMode" cssClass="form" items="${qzfsList}" itemLabel="value" itemValue="dictId" />
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
              <form:select path="helpArea" cssClass="form" items="${qzqyList}" itemLabel="value" itemValue="dictId" />              
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
                <form:options items="${slrqList}" itemLabel="value" itemValue="dictId"/>
              </form:select>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务者：</td>
            <td>${userName}</td>
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

  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">近期求助记录</td>
 </tr>
    <tr>
    <td colspan="2">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr class="table_green">
        <td>求助者姓名</td>
        <td>求助时间</td>
        <!--<td>联系电话</td>-->
        <td>详细地址</td>
        <td>求助内容</td>
        <td>求助类别</td>
        <td>输入人</td>
        <td>结案时间</td>
        <td>壮态</td>
        <td>&nbsp;</td>
      </tr>

    <logic:notEmpty name="list">
            <logic:iterate name="list" id="ltt" indexId="indexID">
              <c:if test="${indexID %2 == 0}">
                <c:set var="class_1" value="table_white"/>
              </c:if>
              <c:if test="${indexID %2 == 1}">
                <c:set var="class_1" value="table_blue"/>
              </c:if>
              <tr class='<c:out value="${class_1}"/>' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
        <td>
        <c:if test="${ltt.helpType==HELP_TYPE_LIFE}">
        <a href="javascript:showLifeInfo('<bean:write name="ltt" property="informationId"/>')">
        <bean:write name="ltt" property="helpName"/>
        </a>
        </c:if>
        <!--咨询类-->
        <c:if test="${ltt.helpType==HELP_TYPE_QUERY}">
        <a href="javascript:showReferInfo('<bean:write name="ltt" property="informationId"/>')">
        <bean:write name="ltt" property="helpName"/>
        </a>
        </c:if>

        <!--事务服务类-->
                <c:if test="${ltt.helpType==HELP_TYPE_TRANSACTION}">
        <a href="javascript:showAffairInfo('<bean:write name="ltt" property="informationId"/>')">
        <bean:write name="ltt" property="helpName"/>
        </a>
        </c:if>
        <!--生产力服务-->
        <c:if test="${ltt.helpType==INFOMATION_HELPTYPE_FERTILITY}">
        <a href="javascript:showReferInfo('<bean:write name="ltt" property="informationId"/>')">
        <bean:write name="ltt" property="helpName"/>
        </a>
        </c:if>
        </td>
        <td><bean:write name="ltt" property="createTime"/></td>
        <!--<td> <bean:write name="ltt" property="helpTel"/></td>-->
        <td><bean:write name="ltt" property="helpAddr"/></td>
        <td><bean:write name="ltt" property="helpContent"/></td>
         <td><c:out value="${HELP_TYPE_HASHMAP[ltt.helpType]}"/></td>
        <td><bean:write name="ltt" property="creatorName"/></td>
        <c:if test="${ltt.helpType == HELP_TYPE_QUERY}">
        <td><bean:write name="ltt" property="hjReferInformation.dealTime"/></td>
        <td><c:out value="${SYS_INFOMATION_STATES_HASHMAP[ltt.hjReferInformation.states]}"/></td>
        </c:if>
        <c:if test="${ltt.helpType == HELP_TYPE_LIFE}">
        <td><bean:write name="ltt" property="hjLifeInformation.finishTime"/></td>
        <td><c:out value="${SYS_INFOMATION_STATES_HASHMAP[ltt.hjLifeInformation.states]}"/></td>
        </c:if>
         <c:if test="${ltt.helpType == HELP_TYPE_TRANSACTION}">
        <td><bean:write name="ltt" property="hjAffairInformation.finishTime"/></td>
        <td><c:out value="${SYS_INFOMATION_STATES_HASHMAP[ltt.hjAffairInformation.states]}"/></td>
        </c:if>
        <td></td>
      </tr>
      <tr class="line">
        <td height="1" colspan="10"></td>
        </tr>
      </logic:iterate>
          </logic:notEmpty>
    </table>
      <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30" align="right"> <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNo"/>
              <jsp:param name="actionname" value="OprInforAccept_icdInit.do"/>
            </jsp:include> </td>

        </tr>
      </table></td>
  </tr>
  <input type="hidden" name="pageNo" value=""/>
</table>


</form:form>
</body>
</html>
