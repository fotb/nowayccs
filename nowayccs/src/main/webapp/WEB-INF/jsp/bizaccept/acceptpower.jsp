<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<base target="_self">
<script language="javascript" type="">
function btnnext_click(){
  var form = document.forms[0];
  if(!isValidStringObj( form.result,"咨询处理描述",true)){
    return;
  }

  form.submit();
}

function btnback_click(){
  history.back();
}

function btnprovs_click() {
	$("form").attr("action", "bizaccept.do?action=back");
	//$("#action").val("back");
	$("form").submit();
}
</script>
<body>
<form:form method="post" action="bizaccept.do?action=refersave" commandName="bizAccept">
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_gray">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr class="table_t1">
            <td width="3%" align="center">
              <img src="images/icon_01.gif" width="5" height="17" alt="">
            </td>
            <td class="font_no">业务咨询类</td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
           <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">求助名姓名：</td>
            <td>
              ${bizAccept.helpName}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助方式：</td>
            <td>
              ${qzfsMap[bizAccept.helpMode]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td>
              ${bizAccept.helpTel}<c:if test="${not empty bizAccept.otherTel}">, ${bizAccept.otherTel}</c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td>
              ${bizAccept.helpAddr}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td>
            <textarea rows="4" cols="60" disabled="disabled">${bizAccept.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助类型：</td>
            <td>
			 ${helpTypeMap[bizAccept.helpType]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助区域：</td>
            <td>
               ${qzqyMap[bizAccept.helpArea]}
            </td>
          </tr> <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>受理人群：</td>
            <td>
            <c:if test="${not empty bizAccept.helpGroup}">${slrqMap[bizAccept.helpGroup]}</c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr class="table_t1">
            <td>服务者：</td>
            <td>
            ${bizAccept.creator}
            </td>
          </tr>
          
           <tr class="table_t1">
            <td colspan="2">
            <table class="easyui-datagrid" title="请选择派送电工：" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:true,url:'datagrid_data1.json',method:'get',toolbar:'#tb',footer:'#ft'">
		        <thead>
		            <tr>
		                 <th data-options="field:'ck',checkbox:true"></th>
		                <th data-options="field:'name',width:100">姓名</th>
		                <th data-options="field:'phone',width:80,align:'right'">电话</th>
		                <th data-options="field:'remark',width:80,align:'right'">备注</th>
		            </tr>
		        </thead>
   			 </table>
    		  <div id="tb" style="padding:2px 5px;">
        区域: 
        <input class="easyui-combobox" id="areaId" style="width:20%" 
	    						data-options="url:'json.do?action=arealist',
											method:'get',
											valueField:'value',
											textField:'text',
											panelHeight:'auto',
											onSelect:function(record){
												$('#areaSubId').combobox('setValues', '');
												$('#areaSubId').combobox('reload', 'json.do?action=subarealist&areaId='+record.value);
											}" />
							
							<input class="easyui-combobox" id="areaSubId" style="width:200px" 
	    						data-options="valueField:'value',textField:'text',panelHeight:'auto'" />
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    </div>      
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="2">            </td>
          </tr>
          <tr align="center" class="table_t1">
            <td colspan="4">
            	<img src="images/button_pre.gif" width="60" height="18" onclick="btnprovs_click()"/>
              <img src="images/button_save.gif" width="60" height="18" onclick="btnnext_click()"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
