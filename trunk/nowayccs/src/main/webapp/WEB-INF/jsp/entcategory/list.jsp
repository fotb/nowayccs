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
</head>
<script type="text/javascript">
function btnadd_click(){
  var form=document.forms[0];
  form.action="entcategory.do?action=add";
  form.submit();
}

function btndel_click(entClassID){
  var form=document.forms[0];
  if(confirm("确定要删除?")){
    form.action="entcategory.do?action=del&delId="+entClassID;
    form.submit();
  }
}

function btnedit_click(entClassID){
  var form=document.forms[0];
  form.action="entcategory.do?action=edit&categoryId=" + entClassID;
  form.submit();
}

function btnTopSearch_click(){
	  var form=document.forms[0];
	  form.subParentId.value = "-1";
	  form.submit();
}
	
	
function btnSearch_click(){
  var form=document.forms[0];
  form.submit();
}
</script>
<body>
<form:form action="entcategory.do?action=search" method="post" commandName="entCategory">
<table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
  <tr>
    <td>
      <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="images/bgtd_01.gif" width="850" height="5"></td>
        </tr>
        <tr>
          <td bgcolor="#F1F1F1">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
              <tr>
                <td>请选择企业类别：</td>
                <td align="left">
                	大类：
                <form:select path="parentId"  onchange="btnTopSearch_click()">
                	<form:option value="-1">--全部--</form:option>
                	<form:options items="${topCategoryList}" itemLabel="value" itemValue="categoryId"/>
                </form:select>
				小类：
                <form:select path="subParentId"  onchange="btnSearch_click()">
                	<form:option value="-1">--全部--</form:option>
                	<form:options items="${subCategoryList}" itemLabel="value" itemValue="categoryId"/>
                </form:select>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td height="10" valign="top"><img src="images/bgtd_02.gif" width="850" height="5"></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="table_green">
          <td width="50%">企业类别名称</td>
          <td colspan="2" width="50%">操作</td>
        </tr>
        <c:forEach items="${categoryList}" var="categoryVO">
            <tr class='table_blue' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
              <td>
                ${categoryVO.value}
              </td>
              <td width="5%">
                <a href="javascript:btnedit_click('${categoryVO.categoryId}')">
                  <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                </a>
              </td>
              <td width="5%">
                <A href="javascript:btndel_click('${categoryVO.categoryId}')">
                  <img src="images/del.gif" alt="删除" width="11" height="14" border="0">
                </A>
              </td>
            </tr>
            <tr class="line">
              <td height="1" colspan="4">              </td>
            </tr>
		</c:forEach>
      </table>
      <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30">
            <a href="javascript:btnadd_click()">
              <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
            </a>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<input type="hidden" name="pageNO" value='<bean:write name="pageInfo" property="currentPage"/>' />
</form:form>
</body>
</html>
