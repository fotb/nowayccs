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
ҳ
<!-- 
<logic:equal name="pageInfo" property="canFirst" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="firstPage" />")'>��ҳ</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canFirst" value="false">[��ҳ]</logic:equal>
<logic:equal name="pageInfo" property="canPrevious" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="previousPage" />")'>��һҳ</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canPrevious" value="false">[��һҳ]</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="nextPage" />")'>��һҳ</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="false">[��һҳ]</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="true">  [
  <a href='javascript:gotoPage("<bean:write name="pageInfo" property="lastPage" />")'>βҳ</a>
  ]
</logic:equal>
<logic:equal name="pageInfo" property="canNext" value="false">[βҳ]</logic:equal>
ת��:
<input type="text" id="goto" size="2">
</input>ҳ
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
	  <%--��������ҳ���Ƿ���������ֵ���ڵ���1��С�ڵ������ҳ��--%>
	  function validator(){
	    if (!isInteger(document.all.goto.value)) {
			alert("���������ҳ�����Ϊ���֡�");
			return false;
	    }
	    if(!(document.all.goto.value >= 1
	          && document.all.goto.value <= <bean:write name="pageInfo" property="totalPages" /> )) {
			alert("���������ҳ�泬����Χ��");
			return false;
	      }

	    return true;
	  }
</script>
