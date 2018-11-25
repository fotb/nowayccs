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
function btnback_click(){
 	$("form").attr("action", "lfmgr.do");
	$("form").submit();
}

 function option_delete(id){
  var form=document.forms[0];
   if(confirm("确定要删除?")){
     form.action="lfmgr.do?action=pmdel&memberId="+id;
     form.submit();
   }
}
</script>
<body>
<form:form method="post" action="lfmgr.do?action=pmlist">
<input type="hidden" name="manId" value="${manId}" />
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><img src="images/bgtd_01.gif" width="850" height="5" alt=""></td>
          </tr>
          <tr>
            <td bgcolor="#F1F1F1">
              &nbsp;
            </td>
          </tr>
          <tr>
            <td height="10" valign="top"><img src="images/bgtd_02.gif" width="850" height="5" alt=""></td>
          </tr>
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
              <td>编号</td>
	          <td>姓名</td>
	          <td>性别</td>
	          <td>出身年月</td>
	          <td>工作单位</td>
	          <td>联系电话</td>
              <td>家庭地址</td>
              <td>特长</td>   
              <td colspan="2">操作</td>              
	        </tr>
			<c:forEach items="${pmVOList}" var="pmVO"  varStatus="index">
              <tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
              <td>${index.index + 1 + pageInfo.PAGE_COUNT*(pageInfo.currentPage-1)}</td>
               <td>
                  ${pmVO.memberName}
                </td>
                <td>
                    <c:choose><c:when test="${pmVO.memberSex =='M'}">男</c:when>	<c:otherwise>女</c:otherwise></c:choose>
                </td>
                <td>
 					${pmVO.birthday}
                </td>
                <td>
                	${pmVO.workDept}
                </td>
                <td nowrap="nowrap">
					${pmVO.linkPhone}
                </td>
                <td>
                	${pmVO.address}
                </td>
                <td>
                	${pmVO.specialty}
                </td>
                <td width="8%">
					<a href="lfmgr.do?action=pmupdate&memberId=${pmVO.memberId}&manId=${manId}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">
                    <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${pmVO.memberId}');" style="cursor:pointer">
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="10">                </td>
              </tr>
		</c:forEach>
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="lfmgr.do?action=pmadd&manId=${manId}">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td height="30">
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
