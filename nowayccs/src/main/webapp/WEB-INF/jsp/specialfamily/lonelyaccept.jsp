<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/function.js" type="text/javascript"></script>
<script language="javascript" type="">
function btnnext_click(){
	$("form").submit();
}
function btnprovs_click() {
	$("form").attr("action", "bizaccept.do");
	$("#action").val("back");
	$("form").submit();
}
</script>
<body>
<form：form method="post" action="lonelyFamily.do">
<input type="hidden" id="action" name="action" value="lifesave"/>
  <table width="865" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">党员志愿者结对家庭服务</td>
          </tr>
			<tr class="line">
            	<td height="1" colspan="2">            </td>
          	</tr>
        </table>
        <table width="100%" border="0" align="center" cellpadding="0" id="lonelyManTb">
			<tr class="table_t1" style="height: 20px;">
				<td width="90">姓名：</td>
				<td align="left" id="manName">${lonelyManInfoVO.manName}</td>
				<td width="40">性别：</td>
				<td align="left" id="manSex">${lonelyManInfoVO.manSex}</td>
				<td width="60">出生日期：</td>
				<td align="left" id="manBirthday">${lonelyManInfoVO.birthday}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t2" style="height: 20px;">
				<td>身份证号码：</td>
				<td colspan="5" id="idCardNo">${lonelyManInfoVO.idCardNo}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t1" style="height: 20px;">
				<td>所属区域：</td>
				<td colspan="5" id="manArea">${lonelyManInfoVO.area}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t2" style="height: 20px;">
				<td>家庭地址：</td>
				<td colspan="5" id="manAddr">${lonelyManInfoVO.address}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t1" style="height: 20px;">
				<td>固定联系电话：</td>
				<td colspan="5" id="manTelphone">${lonelyManInfoVO.telphone}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t2" style="height: 20px;">
				<td>家庭类别：</td>
				<td colspan="5" id="familyType">${sftMap[lonelyManInfoVO.familyType]}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t1" style="height: 40px;">
				<td>家庭基本情况：</td>
				<td colspan="5" id="familyInfo">${lonelyManInfoVO.familyInfo}</td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t2">
				<td>求助内容：</td>
				<td colspan="5"><textarea rows="4" cols="60" disabled="disabled">${bizAccept.helpContent}</textarea></td>
			</tr>
			<tr class="line">
            	<td height="1" colspan="6">            </td>
          	</tr>
			<tr class="table_t1">
 				<td>服务者：</td>
 				<td colspan="5">${bizAccept.creator}</td>
			</tr>
			<tr class="line">
 				<td height="1" colspan="6"></td>
			</tr>
			<tr align="center" class="table_t1">
 				<td colspan="6">
  	 				<img src="images/button_pre.gif" width="60" height="18" onclick="btnprovs_click();"/>
   					<img src="images/button_save.gif" width="60" height="18" onclick="btnnext_click();"/>
 				</td>
			</tr>
		</table>
      </td>
    </tr>
    <tr>
    <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">党员志愿者结对家庭服务</td>
          </tr>
			<tr class="line">
            	<td height="1" colspan="2">            </td>
          	</tr>
        </table>
        </td>
        </tr>
        <tr>
        <td>
		<table width="100%" border=0 cellspacing=0 cellpadding=0>
			<tr class="table_t1">
				<td width="100" rowspan=6 align="center"><input type="checkbox" id="vvvv" style="vertical-align: middle; margin-right: 10px;"/>张三</td>
				<td width="1" style="background-color: #E6E6E6;"> </td>
				<td>
				<table width="100%" border=0 cellspacing=0 cellpadding=0 style="padding-left: 2px;">
				<tr class="table_t1">
				<td width="80">性别：</td>
				<td >男</td>
				<td width="80">出生年月：</td>
				<td >11111111</td>
				</tr>
				<tr class="line">
            	<td height="1" colspan="4">            </td>
          		</tr>			
			<tr class="table_t2">
				<td  >工作单位：</td>
				<td colspan=4>1</td>
			</tr>
				<tr class="line">
            	<td height="1" colspan="4">            </td>
          		</tr>
			<tr class="table_t1">
				<td  >联系电话：</td>
				<td colspan=4>1</td>
			</tr>
				<tr class="line">
            	<td height="1" colspan="4">            </td>
          		</tr>
			<tr class="table_t2">
				<td  >家庭地址：</td>
				<td colspan=4>1</td>
			</tr>
				<tr class="line">
            	<td height="1" colspan="4">            </td>
          		</tr>
			<tr class="table_t1">
				<td >特长：</td>
				<td colspan=4>1</td>
			</tr>
				<tr class="line">
            	<td height="1" colspan="4">            </td>
          		</tr>
				</table>
				</td>
			</tr>
			
		</table>
	</td>
    </tr>
  </table>
</form：form>
</body>
</html>
