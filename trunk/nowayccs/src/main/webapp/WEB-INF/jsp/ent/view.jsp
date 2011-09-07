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
<script language="javascript" src="js/function.js" type="">
</script>
<script language="javascript" type="">

function btnback_click(){
  history.back();
}
</script>
<body>
<form:form method="post" action="entprise.do?action=save" commandName="entpriseVO">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<form:hidden path="entpriseId"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">修改企业信息</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">企业编号：</td>
            <td>
            	${entpriseVO.entpriseNo}
            </td>
            <td>能否派单：</td>
            <td>
            <c:if test="${entpriseVO.servicesType == 'Y'}">
            	可派单
            </c:if>
            <c:if test="${entpriseVO.servicesType == 'N'}">
            	不可派单
            </c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">企业名称：</td>
            <td colspan="3">
            	${entpriseVO.entpriseName}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>地址：</td>
            <td colspan="3">
            ${entpriseVO.address }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>法定代表人（负责人）：</td>
            <td colspan="3">
            	${entpriseVO.lawDeputy }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="18%">注册资本：</td>
            <td width="32%">
            	${entpriseVO.regMoney })
            </td>
            <td width="18%">服务时间：</td>
            <td width="32%">
            	${entpriseVO.serviceTime }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系电话：</td>
            <td>
            	${entpriseVO.linkTel }
            </td>
            <td>服务电话：</td>
            <td>
            	${entpriseVO.serviceTel }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>联系人姓名：</td>
            <td>
            	${entpriseVO.linkName }
            </td>
            <td>是否愿意加盟：</td>
            <td>
            <c:if test="${entpriseVO.memberSign == 'Y'}">
            	加盟
            </c:if>
            <c:if test="${entpriseVO.memberSign == 'N'}">
            	不加盟
            </c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>工商注册号：</td>
            <td>
            ${entpriseVO.registerCode }
            </td>
            <td>税务登记号：</td>
            <td>
            ${entpriseVO.taxCode }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>

          <tr class="table_t1">
            <td>经营范围：</td>
            <td colspan="3">
            	${entpriseVO.dealRange }
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
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

