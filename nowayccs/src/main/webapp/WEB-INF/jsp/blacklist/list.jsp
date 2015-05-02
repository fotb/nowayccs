<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红黑名单管理</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript" >
$(document).ready(function(){
	  //$( "#headnav" ).tabs("select",3);
	//$("#subDictTable>tbody>tr：even").addClass("table_blue");
});
function btn_search(){
	  var form = document.forms[0];
	  form.pageNo.value = "1";
	  form.submit();
}


function option_delete(phoneId) {
	document.forms[0].action = "blacklist.do?action=del&phoneId="+phoneId;
	document.forms[0].submit();
}
</script>

<body>
<form：form method="POST" action="blacklist.do" commandName="dict">
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
                  <td width="8%">电话号码：</td>
                  <td>
						<input type="text" name="phoneNum" id="phoneNum" class="from" value="${phoneNum}">
                  </td>
                  <td width="8%">等级：</td>
                  <td>
						<input type="text" name="levels" id="levels" class="from" value="${levels}">
                  </td>
				  <td><img width="60" height="18" src="images/button_search.gif" onclick="btn_search();" style="cursor：hand"/></td>
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
            <td width="20%">电话号码</td>
            <td width="20%">等级</td>
            <td width="40%">备注</td>
            <td colspan="2"></td>
          </tr>
          <tbody>
          <c：forEach items="${blackListVOList}" var="phone">
          	<tr class='table_white' onmouseover="this.style.backgroundColor='#F0F0F0'" onmouseout="this.style.backgroundColor='#ffffff'">
          		<td>${phone.phoneNum}</td>
            	<td align="left">${phone.levels} (
            	<c：choose>
            		<c：when test="${fn：contains (phone.levels,'-')}">
						<font color=black>
							<c：forEach begin="1" end="${fn：substring(phone.levels, '1', '2')}">
							★
							</c：forEach>
						</font>
					</c：when>
            		<c：otherwise>
            		<font color=red>
							<c：forEach begin="1" end="${phone.levels}">
							★
							</c：forEach>
						</font>
            		</c：otherwise>
            	</c：choose>
            	)
            	</td>
            	<td>${phone.remark}</td>
                <td width="5%">
                  <a href="blacklist.do?action=edit&phoneId=${phone.phoneId}&pageNo=${pageInfo.currentPage}">
                    <img src="images/edit.gif" alt="修改" width="11" height="14" border="0">
                  </a>
                </td>
                <td width="5%">
                    <img src="images/del.gif" alt="删除" width="11" height="14" border="0" onclick="option_delete('${phone.phoneId}');" style="cursor：hand">
                </td>
              </tr>
              <tr class="line">
                <td height="1" colspan="5">                </td>
              </tr>
          </c：forEach>
          </tbody>
        </table>
        
        <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30">
              <a href="blacklist.do?action=add">
                <img src="images/button_add.gif" width="60" height="18" border="0" alt="">
              </a>
            </td>
          </tr>
        <tr>
       <!--  <td><img width="80" height="18" src="images/button_dateexp.gif" onclick="option_exp(document.forms[0]);" style="cursor：pointer;"/></td> -->
          <td height="30" align="right">
            <jsp：include page="../common/pageinfo.jsp" flush="true">
              <jsp：param name="formname" value="forms[0]"/>
              <jsp：param name="pagename" value="pageNo"/>
              <jsp：param name="actionname" value="blacklist.do"/>
            </jsp：include> </td>

        </tr>
        </table>
      </td>
    </tr>
  </table><input type="hidden" name="pageNo" value="${pageInfo.currentPage}" />
  </form：form>
</body>
</html>