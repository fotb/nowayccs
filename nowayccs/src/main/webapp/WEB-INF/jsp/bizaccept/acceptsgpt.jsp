<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<base target="_self">

<script language="javascript" type="">
function btnnext_click(){

	var row = $('#dg').datagrid('getSelected');
	if(row == null) {
		alert("请选择电工！");
	} else {
		$("#powerStaffId").val(row.pid);
	}
	$("#areaSubId1").val($("#areaSubId").combobox('getValue'));
	$("form").submit();
	//$("#bizAccept").submit(function(){alert("success"); return false;});
}

function btnback_click(){
  history.back();
}

function btnprovs_click() {
	$("form").attr("action", "bizaccept.do?action=back");
	//$("#action").val("back");
	$("form").submit();
}

$(document).ready(function(){
$("#detal").html("");
$('#firstCategoryId').combotree({

		onBeforeSelect: function(node) { 
			if (!$(this).tree('isLeaf', node.target)) { 
				return false; 
			} 
		}, 
		onClick: function(node) { 
			if (!$(this).tree('isLeaf', node.target)) { 
				$('#tt').combo('showPanel'); 
			} 
		},
	  onSelect:function(rec){
		$.getJSON("sgpt/getcategory/", {p:rec.id}, function(data) {
			if(!$.isEmptyObject(data)) {
				$("#detal").html(data.detail);
			}
		});
	 }
 });
});
</script>
<body>
<form:form method="post" action="bizaccept.do?action=elevatorsave" commandName="bizAccept">
	<div id="p" class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
		<p class="font_no" style="font-size:14px; font-weight:bold; text-align:center;">“四个平台”事件信息记录单</p>
		<div id="p_info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
           <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件名称</td>
            <td style="width:30%">
              <input class="easyui-textbox" id="eventSubject" data-options="prompt:'请输入事件名称', showSeconds: false" style="height:100%;width:99%">
            </td>
             <td>发生网格</td>
            <td>
              <input class="easyui-textbox" id="reportor" data-options="prompt:'请输入发生网格'" style="height:100%;width: 99%;">
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan=4">            </td>
          </tr>
          <tr class="table_t1">
            <td>发生地点</td>
            <td style=""><input class="easyui-textbox" id="eventLocation" data-options="prompt:'请输入发生地点'"  style="height:100%;width: 99%;"></td>
            <td >发生时间</td>
            <td style=""><input class="easyui-datetimebox" id="eventDate" data-options="prompt:'请输入发生时间'" style="height:100%;width: 120;"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件类别</td>
            <td style="" colspan="3">
            <select class="easyui-combotree" id="firstCategoryId" data-options="url: 'sgpt/category/tree/', prompt:'请选择事件类别'" style="height:100%;width: 180px;">
            </select>
            <span id="detal"></span>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 14pt;"  colspan="4">主要当事人</td>
          </tr>
          <tr class="table_t1">
            <td>姓名</td>
            <td style=""><input class="easyui-textbox" id="position" data-options="prompt:'请输入姓名'" style="height:100%;width: 99%;"></td> 
            <td>联系电话</td>
            <td style=""><input class="easyui-textbox" id="mobile" data-options="prompt:'请输入联系电话'" style="height:100%;width: 99%;"></td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>涉及人数</td>
            <td style=""><input class="easyui-textbox" id="relatePeopleCount" data-options="prompt:'请输入涉及人数'" style="height:100%;width: 99%;"></td>
          	<td style="text-align: center;" colspan="2"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件简述</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="eventContent" data-options="multiline:true, prompt:'请输入事件简述'" style="height:100px;width: 99%;"></td>
          </tr>
            <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>事件分类等级</td>
            <td style="">
            <select class="easyui-combobox" id="eventLevel" data-options="prompt:'请选择事件分类等级',panelHeight:'auto'" style="width:140px;font-size: 12px;">
            	<option value="3" selected="selected">一般</option>
            	<option value="2">紧急</option>
            	<option value="3">特急</option>
            </select>
            </td>
            <td>重点关注事件</td>
            <td style="">
            	<select class="easyui-combobox" id="isImpPlase" data-options="prompt:'请选择是否重点关注事件',panelHeight:'auto'" style="width:140px;font-size: 12px;">
              	<option value="0" selected="selected">否</option>
              	<option value="1">是</option>
              </select>
            </td>
          </tr>      
          </tr>
            <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>   
          
                    <tr align="center" class="table_t1">
            <td colspan="4">
              <img src="images/button_pre.gif" width="60" height="18" onclick="btnprovs_click();"/>
              <img src="images/button_save.gif" width="60" height="18" onclick="btnnext_click();"/>
            </td>
          </tr>         
          </table>
	</div>
</form:form>
</body>
</html>

