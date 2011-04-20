<%@page contentType="text/html;charset=GBK"%>
<%@ include file="includes.jsp" %>  
<%@page import="com.ccs.util.PageInfo"%>
<%
  PageInfo pageInfo = new PageInfo();
  pageInfo = (PageInfo) request.getAttribute("pageInfo");
  request.setAttribute("pageInfo", pageInfo);
  request.setAttribute("formname", request.getParameter("formname"));
  request.setAttribute("pagename", request.getParameter("pagename"));
  request.setAttribute("actionname", request.getParameter("actionname"));
%>
<input type="hidden" name="page" value="${pageInfo.currentPage}">
${pageInfo.currentPage}
/
${pageInfo.totalPages}
页
<!-- 
<logic:equal name="pageInfo" property="canFirst" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="firstPage" />")'>首页</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canFirst" value="false">[首页]</logic:equal>
<logic:equal name="pageInfo" property="canPrevious" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="previousPage" />")'>上一页</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canPrevious" value="false">[上一页]</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="nextPage" />")'>下一页</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="false">[下一页]</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="lastPage" />")'>尾页</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="false">[尾页]</logic:equal>
转到:
<input type="text" id="goto" size="2">
</input>页
<img src="images/button_ok.gif" width="60" height="18" onclick="goPage()" alt="">
 -->
<script language="Javascript" src="cssandjs/function.js" type=""></script>
<script language="Javascript" type="">
	function gotoPage(page){
		  obj = document.<bean:write name="formname"/>;
		  obj.action = "<bean:write name="actionname" />";
		  obj.<bean:write name="pagename"/>.value = page;
                  //obj.target="_self";
		  obj.submit();
	}
	  function goPage() {
	    if (validator()) {
	      obj = document.<bean:write name="formname"/>;
		  obj.action = "<bean:write name="actionname" />";
	      obj.<bean:write name="pagename"/>.value = document.all.goto.value;
              //obj.target="_self";
	      obj.submit();
	     }
	  }
	  <%--检查输入的页面是否整数，且值大于等于1，小于等于最大页数--%>
	  function validator(){
	    if (!isInteger(document.all.goto.value)) {
			alert("输入的请求页面必须为数字。");
			return false;
	    }
	    if(!(document.all.goto.value >= 1
	          && document.all.goto.value <= <bean:write name="pageInfo" property="totalPages" /> )) {
			alert("输入的请求页面超出范围。");
			return false;
	      }

	    return true;
	  }
</script>
