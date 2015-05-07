<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/function.js"></script>
  <script type="text/javascript" >
  function option_delete(id){
  var form=document.forms[0];
  if(confirm("确定要删除?")){
    form.action="area.do?action=del&areaId="+id;
    form.submit();
  }
}
  </script>
</head>
<body>
<form:form action="area.do" method="post">
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_green">
            <td width="50%">街道名称</td>
            <td width="40%">所属社区</td>
            <td colspan="2">操作</td>
          </tr>
          <c：forEach items="${AreaList}" var="area">
          	<tr class='table_blue' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
                <td>
                  ${area.name}
                </td>
                <td>
                  <a href="area.do?action=subArea&areaId=${area.areaId}&pageNo=${pageInfo.currentPage}">所属社区</a>
                </td>
                <td width="5%">
                  <a href="area.do?action=editArea&areaId=${area.areaId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">

                    <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${area.areaId}');" style="cursor：hand">

                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="4">                </td>
              </tr>
          </c：forEach>          
        </table>
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="area.do?action=addArea&pageNo=${pageInfo.currentPage}">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
            <td align="right">
            <jsp：include page="../common/pageinfo.jsp" flush="true">
              <jsp：param name="formname" value="forms[0]"/>
              <jsp：param name="pagename" value="pageNo"/>
              <jsp：param name="actionname" value="area.do"/>
            </jsp：include>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="pageNo" value="${pageInfo.currentPage}">
</form:form>
</body>
</html>
