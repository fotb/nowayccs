<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据字典管理</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript" >
$(document).ready(function(){
	  //$( "#headnav" ).tabs("select",3);
	$("#subDictTable>tbody>tr:even").addClass("table_blue");
});
function btn_search(){
	  var form = document.forms[0];
	  form.submit();
}

</script>

<body>
<form:form method="POST" action="dict.do?action=search" commandName="dict">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><img src="images/bgtd_01.gif" width="850" height="5" alt=""></td>
          </tr>
          <tr>
            <td bgcolor="#F1F1F1">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
                <tr>
                  <td width="8%">类别：</td>
                  <td>
						<form:select path="dictType" items="${dictType}" itemLabel="value" itemValue="dictType" onchange="btn_search()"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td height="10" valign="top"><img src="images/bgtd_02.gif" width="850" height="5"></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="subDictTable">
          <tr class="table_green">
            <td width="50%">代码</td>
            <td width="40%">名称</td>
            <td colspan="2">操作</td>
          </tr>
          <tbody>
          <c:forEach items="${subDictlist}" var="dict">
          	<tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
          		<td>${dict.sortIndex}</td>
            	<td>${dict.value}</td>
                <td width="5%">
                  <a href="dict.do?action=edit&dictID=${dict.dictId}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">
                    <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${dict.dictId}');" style="cursor:hand">
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="4">                </td>
              </tr>
          </c:forEach>
          </tbody>
        </table>
        
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="dictadd.do?action=add&dictType=${dict.dictType}">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td align="right">
            <jsp:include page="../common/pageinfo.jsp" flush="true">
              <jsp:param name="formname" value="forms[0]"/>
              <jsp:param name="pagename" value="pageNO"/>
              <jsp:param name="actionname" value="SysDictAdmin_list.do"/>
            </jsp:include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNO" value="${pageInfo.currentPage}">
  </form:form>
</body>
</html>