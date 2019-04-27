<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<base target="_self">

<script language="javascript" type="">

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
		$.getJSON("sgpt.do?action=getcategory", {p:rec.id}, function(data) {
			if(!$.isEmptyObject(data)) {
				$("#detal").html(data.detail);
			}
		});
	 }
	 });


	$("#bt_prov").on("click", function(){
		history.back();
	});

    $('#bt_save').on('click',function(){
	if($("#fm_sgpt").form('validate')){
		$("form").submit();
	}
	/*

	console.log($("#fm_sgpt").serializeObject());
		if($("#fm_sgpt").form('validate')){
			jQuery.support.cors = true;
       		$.ajax({
            	url:"sgpt/bizaccept/save",
            	type:"POST",
            	data:JSON.stringify($('#fm_sgpt').serializeObject()),
            	contentType:"application/json;charset=utf-8",  //缺失会出现URL编码，无法转成json对象
            	success:function(data){
					if(data.meta.success){
							$.messager.confirm('提示', '保存成功！', function(r){
								if (r){
									parent.main.location = "bizaccept.do?action=selfclose";
								}
							});
					}
            	}
        	});
		}else{
			$.messager.alert('操作提示','存在校验项未通过！',"warning");
		}
	*/
    });

		$.extend($.fn.validatebox.defaults.rules, {
        	　　　　　　datetime : {//验证时间格式是否类似2019-04-27 22:44
        	       　　　　       validator : function(value){
        	       　　　　     	 	var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/);
           							if (r == null) return false;
            						var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6]);
            						return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6]);
        	         　　　　     },
        	        　　　　      message : '时间格式不正确，请重新选择。'
        	        　　　　  }
        	　　});
});



    /**
     * 自动将form表单封装成json对象
     */
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [ o[this.name] ];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
</script>
<body>
<form method="post" action="sgpt.do?action=save" id="fm_sgpt" name="fm_sgpt">
	<div id="p" class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
		<p class="font_no" style="font-size:14px; font-weight:bold; text-align:center;">“四个平台”事件信息记录单</p>
		<div id="p_info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
           <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件名称</td>
            <td colspan="3">
              <input class="easyui-textbox" id="eventSubject" name="eventSubject"  data-options="prompt:'请输入事件名称', required:true" style="height:100%;width:99%">
            </td>
<!--              <td>发生网格</td>
            <td>
              <input class="easyui-textbox" id="reportor" name="reportor" data-options="prompt:'请输入发生网格'" style="height:100%;width: 99%;">
            </td> -->
          </tr>
          <tr class="line">
            <td height="1" colspan="4"></td>
          </tr>
          <tr class="table_t1">
            <td>发生地点</td>
            <td style=""><input class="easyui-textbox" id="eventLocation" name="eventLocation" data-options="prompt:'请输入发生地点', required:true"  style="height:100%;width: 99%;"></td>
            <td >发生时间</td>
            <td style=""><input class="easyui-datetimebox" id="eventDate"  name="eventDate" data-options="prompt:'请输入发生时间', validType:'datetime', required:true,showSeconds:false" style="height:100%;width: 220;"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件类别</td>
            <td style="" colspan="3">
            <select class="easyui-combotree" id="firstCategoryId" name="firstCategoryId" data-options="url: 'sgpt.do?action=categorytree', prompt:'请选择事件类别', required:true" style="height:100%;width: 280px;">
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
            <td style=""><input class="easyui-textbox" id="objName" name="objName" data-options="prompt:'请输入姓名', required:true" style="height:100%;width: 99%;"></td> 
            <td>联系电话</td>
            <td style=""><input class="easyui-textbox" id="mobile" name="mobile" data-options="prompt:'请输入联系电话', required:true" style="height:100%;width: 99%;"></td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>涉及人数</td>
            <td style=""><input class="easyui-numberbox" id="relatePeopleCount" name="relatePeopleCount" data-options="prompt:'请输入涉及人数', required:true, min:0,precision:0" style="height:100%;width: 99%;"></td>
          	<td style="text-align: center;" colspan="2"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>事件简述</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="eventContent" name="eventContent" data-options="multiline:true, prompt:'请输入事件简述', required:true" style="height:100px;width: 99%;"></td>
          </tr>
            <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>事件分类等级</td>
            <td style="">
            <select class="easyui-combobox" id="eventLevel" name="eventLevel" data-options="prompt:'请选择事件分类等级',panelHeight:'auto', required:true" style="width:140px;font-size: 12px;">
            	<option value="3" selected="selected">一般</option>
            	<option value="2">紧急</option>
            	<option value="1">特急</option>
            </select>
            </td>
            <td>重点关注事件</td>
            <td style="">
            	<select class="easyui-combobox" id="isImpPlase" name="isImpPlase" data-options="prompt:'请选择是否重点关注事件',panelHeight:'auto', required:true" style="width:140px;font-size: 12px;">
              	<option value="0" selected="selected">否</option>
              	<option value="1">是</option>
              </select>
            </td>
          </tr>      
            <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>   
          
                    <tr align="center" class="table_t1">
            <td colspan="4">
              <a href="#" class="easyui-linkbutton" id="bt_save" name="bt_save" data-options="iconCls:'icon-save'">保存</a>
              <a href="#" class="easyui-linkbutton" id="bt_prov" name="bt_prov"  data-options="iconCls:'icon-back'">上一步</a>
<!--               <img src="images/button_pre.gif" width="60" height="18" onclick="btnprovs_click();"/>
              <img src="images/button_save.gif" width="60" height="18" onclick="btnnext_click();"/> -->
            </td>
          </tr>         
          </table>
	</div>
	</div>
</form>
</body>
</html>

