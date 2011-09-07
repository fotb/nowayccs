<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/common.js" type=""></script>
<script language="javascript" type="">

$(document).ready(function(){
	$("input[name=categoryId]").each(function() { 
		$(this).attr("disabled", true);
    }); 

	$("#editImg").click(function(){
		$("input[name=categoryId]").each(function() { 
			$(this).attr("disabled", false);
    	}); 
		$("#editImg").hide();
		$("#saveImg").show();
	});

	$("#saveImg").click(function(){
		$("form").submit();
	});

	$("#backImg").click(function(){
		$("form").attr("action", "entprise.do");
		$("form").submit();
	});
});
</script>
<body bgcolor="#ffffff">

<form:form method="post" action="entprise.do?action=updateclass">
<input type="hidden" name="entpriseId" value="${entpriseVO.entpriseId }"/>
<input type="hidden" name="pageNo" value="${pageNo}" />
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">企业服务项目：</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="class_1">
            <td width="10%">企业名称：</td>
            <td >
            	${entpriseVO.entpriseName}
            </td>
          </tr>
                    <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          </table>

         <table width="100%" border="0" cellpadding="0" cellspacing="1">
         <c:forEach items="${dtoList}" var="dto">
	          <tr class="table_green">
	            <td colspan="2" align="left">
					${dto.entCategoryVO.value}
	            </td>
	          </tr>
         	<c:forEach items="${dto.sonCategoryList}" var="sonDTO">
		      <tr class="class_1">
				<td width="10%">
				  &nbsp;&nbsp;&nbsp;&nbsp;${sonDTO.entCategoryVO.value}
				</td>
				<td>
	            <c:forEach items="${sonDTO.sonCategoryList}" var="thirdDTO">
	            	<c:choose>
	            		<c:when test="${not empty coeMap[thirdDTO.entCategoryVO.categoryId]}">
	            			<input type="checkbox" name="categoryId" id="categoryId" value="${thirdDTO.entCategoryVO.categoryId}" disabled="disabled" checked="checked"/>
	            		</c:when>
	            		<c:otherwise>
	            			<input type="checkbox" name="categoryId" id="categoryId" value="${thirdDTO.entCategoryVO.categoryId}" disabled="disabled" />
	            		</c:otherwise>
	            	</c:choose>
					${thirdDTO.entCategoryVO.value}	            
	            </c:forEach>
	            </td>
	           </tr>
          	  <tr class="line">
				<td height="1" colspan="2">            </td>
              </tr>
         	</c:forEach>
         </c:forEach>
          <tr align="center" class="table_t1">
            <td colspan="2">
              <img src="images/button_edit.gif" id="editImg" alt="修改" width="60" height="18" border="0" style="cursor: pointer;"/>
              <img src="images/button_save.gif" id="saveImg" alt="保存" width="60" height="18" border="0" style="display: none; cursor: pointer;"/>
              <img src="images/button_back.gif" id="backImg" alt="返回前一页面" width="60" height="18" border="0" style="cursor: pointer;"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>
