<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


$("#bt_prov").on("click", function(){
	history.back();
});

    $('#bt_save').on('click',function(){
	console.log($("#fm_elev").serializeObject());
		if($("#fm_elev").form('validate')){
       		$.ajax({
            	url:"elev/bizaccept/save",
            	type:"POST",
            	data:JSON.stringify($('#fm_elev').serializeObject()),
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
    });


$("#reportingTime").textbox('textbox').css("font-size", "12pt");


//$('#otherPhone').next().hide();
$('#reportPhone').combobox({
	onSelect: function(data){
		value = $('#reportPhone').combobox('getValue');
		if("other" == value) {
			$('#otherPhone').next().show();
		} else {
			$('#otherPhone').next().hide();
		}	
	}
});

$('#casualtySpan').hide();
$('#casualty').combobox({
	onSelect: function(data){
		value = $('#casualty').combobox('getValue');
		if("1" == value) {
			$('#casualtySpan').show();
		} else {
			$('#casualtySpan').hide();
		}
	}
});

$('#deviceId').combobox({
      mode: 'remote',  //模式： 远程获取数据
      url: 'elev/getelev/',  //远程数据请求地址
      valueField: 'pid', 　　//value对应的属性字段
      textField: 'deviceId',　　　 //text对应的属性字段
	  onBeforeLoad:function(param){
		param.q = $(this).combobox('getValue')
	  },
	  onSelect:function(rec){
		console.log(rec);
		$("#useDept").textbox("setValue", rec.useDept);
		$("#position").textbox("setValue", rec.position);
		$("#serviceName").textbox("setValue", rec.serviceName);
		$("#manufacturer").textbox("setValue", rec.manufacturer);
		$("#referNo").textbox("setValue", rec.referNo);
		$("#serialNumber").textbox("setValue", rec.serialNumber);
		$("#type").combobox("setValue", rec.type);
		$("#surveyDate").datebox("setValue", rec.surveyDate);
		$("#nextSurveyDate").datebox("setValue", rec.nextSurveyDate);
	 }
 });
});

    /**
     * 自动将form表单封装成json对象
     */
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
		console.log(a);
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
<form method="post" id="fm_elev" name="fm_elev">
	<div id="p" class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
		<p style="font-size:14px; font-weight:bold; text-align:center;">嘉兴市电梯应急公共救援平台电话记录</p>
		<div style="margin-bottom:3px">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
          <tr class="table_t1">
          <td>值班人员:${bizAccept.creator}</td>
          <td>值班日期:${bizAccept.createTime}</td>
          </tr>
			</table>
		</div>
		<div id="p_info" class="easyui-panel"  style="width:98%;height:100%;padding:0px; border-width: 0px; display:inline;float:left;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
           <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td style="width: 80px;">接报时间</td>
            <td style="width:180px;" colspan="1">
              <input class="easyui-datetimebox" id="reportingTime" name="reportingTime" data-options="prompt:'请输入接报时间', showSeconds: false" style="height:100%" value="${bizAccept.createTime}">
            </td>
             <td style="width: 80px;text-align: right;">报告人</td>
            <td  style="width:200px;">
              <input class="easyui-textbox" id="reporter" name="reporter" data-options="prompt:'请输入报告人'" style="height:100%" value="${bizAccept.helpName}">
            </td>
             <td style="width: 100px;text-align: center;">报告人电话</td>
            <td>
              <select class="easyui-combobox" id="reportPhone" name="reportPhone" data-options="panelHeight:'auto'" style="width:100px;font-size: 12px;">
              	<option value="110">110</option>
              	<option value="119">119</option>
              	<option value="other" selected="selected">其他</option>
              </select>
              <input class="easyui-textbox" id="otherPhone" name="otherPhone" data-options="prompt:'请输入报告人电话'" style="height:100%;display: none;width: 120px;" value="${bizAccept.helpTel}">
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 16pt;"  colspan="6">电梯基本信息</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>设备识别码</td>
            <td style="" colspan="5"><input class="easyui-combox" id="deviceId" name="deviceId" data-options="prompt:'请输入电梯设备识别码'"  style="height:100%;width: 90%;"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯使用<br>管理单位</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="useDept"  name="useDept" data-options="prompt:'请输入电梯使用管理单位'" style="height:100%;width: 90%;"></td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯安装<br>具体位置</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="position" name="position" data-options="prompt:'请输入电梯安装具体位置，精确到几栋几单元几号电梯'" style="height:100%;width: 90%;"></td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>维保单位</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="serviceName" name="serviceName" data-options="prompt:'请输入电梯维保单位'" style="height:100%;width: 90%;"></td>
          	<td style="text-align: center;">制造单位</td>
            <td style="" colspan="1"><input class="easyui-textbox" id="manufacturer"  name="manufacturer" data-options="prompt:'请输入电梯制造单位'" style="height:100%;width: 90%;"></td>          
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td>档案号</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="referNo" name="referNo" data-options="prompt:'请输入电梯档案号'" style="height:100%;width: 90%;"></td>
            <td colspan="2"></td>
          </tr>
            <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>出厂编号</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="serialNumber" name="serialNumber" data-options="prompt:'请输入电梯出厂编号'" style="height:100%;width: 90%;"></td>
          	<td style="text-align: center;">电梯类别</td>
            <td style="" colspan="1">
 			<select class="easyui-combobox" id="type" name="type" data-options="panelHeight:'auto'" style="width:100px;font-size: 12px;">
              	<option value="1">电梯</option>
              	<option value="2">货梯</option>
              	<option value="3">医梯</option>
              	<option value="4">扶梯</option>
              </select>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>检验日期</td>
            <td style="" colspan="3"><input class="easyui-datebox" id="surveyDate" name="surveyDate" data-options="prompt:'请输入电梯检验日期'" style="height:100%;width: 45%;"></td>
          	<td style="text-align: center;">下检日期</td>
            <td style="" colspan="1"><input class="easyui-datebox" id="nextSurveyDate" name="nextSurveyDate" data-options="prompt:'请输入电梯下次检验日期'" style="height:100%;width: 45%;"></td>          
          </tr>   
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td class="font_no"  style="text-align: left; font-size: 16pt;"  colspan="6">救援情况</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>救援单位</td>
            <td style="" colspan="5">
            <select class="easyui-combobox" id="helpDept" name="helpDept" data-options="prompt:'请选择救援单位',panelHeight:'auto'" style="width:140px;font-size: 12px;">
              	<option value="1">签约维保单位救援</option>
              	<option value="2">网格救援单位救援</option>
              </select>
          </tr>
			<tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>值班人发出<br>指令时间</td>
            <td style="" colspan="3"><input class="easyui-datetimebox" id="dispatchTime" name="dispatchTime" data-options="prompt:'请选择值班人发出指令时间',showSeconds: false" style="height:100%;width: 90%;"></td>
           <td style="text-align: center;">救援人员<br>出动时间</td>
            <td style="" colspan="1"><input class="easyui-datetimebox" id="startTime" name="startTime" data-options="prompt:'请选择救援人员出动时间',showSeconds: false" style="height:100%;width: 90%;"></td>
          </tr>  
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>救援人员到<br>达现场时间</td>
            <td style="" colspan="3"><input class="easyui-datetimebox" id="arriveTime" name="arriveTime" data-options="prompt:'请选择救援人员到达现场时间',showSeconds: false" style="height:100%;width: 90%;"></td>
           <td style="text-align: center;">救援结束<br>时间</td>
            <td style="" colspan="1"><input class="easyui-datetimebox" id="finishTime" name="finishTime" data-options="prompt:'请选择救援人员出动时间',showSeconds: false" style="height:100%;width: 90%;"></td>
          </tr> 
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯困人<br>数量</td>
            <td style="" colspan="3"><input class="easyui-textbox" id="trapppedPerson" name="trapppedPerson" data-options="prompt:'请输入电梯困人数量'" style="height:100%;width: 90%;"></td>
           <td style="text-align: center;">人员伤亡<br>情况</td>
            <td style="" colspan="1">
             <select class="easyui-combobox" id="casualty" name="casualty" data-options="prompt:'请选择人员伤亡情况',panelHeight:'auto'" style="width:140px;font-size: 12px;">
              	<option value="0">无</option>
              	<option value="1">有</option>
              </select>
              <span id="casualtySpan">
              受伤<input class="easyui-numberspinner" id="injuries" name="injuries" value="0" data-options="increment:1,min:0" style="width:60px;"></input>人
              &nbsp;&nbsp;死亡<input class="easyui-numberspinner" id="deathToll"  name="deathToll" value="0" data-options="increment:1,min:0" style="width:60px;"></input>人
              </span>
            </td>
          </tr>   
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯困人<br>故障原因</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="reason"  name="reason" data-options="multiline:true, prompt:'请输入电梯困人故障原因'" style="height:100px;width: 90%;"></td>
          </tr>          

          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>后续处理<br>情况</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="dealResult" name="dealResult" data-options="prompt:'请输入电梯困人故障原因'" style="height:100px;width: 90%;"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>值班状况</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="dutyResult" name="dutyResult" data-options="prompt:'请输入电梯困人故障原因'" style="height:100%;width: 90%;"></td>
          </tr> 
          
			<tr align="center" class="table_t1">
            <td colspan="6">
              <a href="#" class="easyui-linkbutton" id="bt_save" data-options="iconCls:'icon-save'">保存</a>
              <a href="#" class="easyui-linkbutton" id="bt_prov" data-options="iconCls:'icon-back'">上一步</a>
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

