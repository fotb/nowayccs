<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" type="">
function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="refer.do?action=view">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">查看业务咨询</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">标题：</td>
            <td>
              ${referVO.title}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>类型：</td>
            <td>
              <c:out value="${zxlxDictMap[referVO.referType]}">
              </c:out>
            </td>
          </tr>
                    <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">联系电话：</td>
            <td>
              ${referVO.phone }
            </td>
          </tr>
                     <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">地址：</td>
            <td>
              ${referVO.address }
            </td>
          </tr>
                               <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">内容：</td>
            <td>
              <textarea cols="120" rows="10" class="form" readonly>${referVO.content}</textarea>
            </td>
          </tr>
           <tr class="table_t1">
            <td width="10%">最后修改人：</td>
            <td>
              ${lastUpdator}
            </td>
          </tr>
			<tr class="table_t1">
            <td width="10%">最后修改时间：</td>
            <td>
            	<c:if test="${not empty referVO.lastUpdateDt}"> 
              <fmt:formatDate value="${referVO.lastUpdateDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
              </c:if>
            </td>
            
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="2">
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
