<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电梯紧急救援回访</title>
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

	$("#bt_prov").on("click", function(){
		history.back();
	});

    $('#bt_save').on('click',function(){
		if($("#fm_elev_back").form('validate')){
       		$.ajax({
            	url:"elevbackvst.do?action=backsave",
            	type:"POST",
            	data:JSON.stringify($('#fm_elev_back').serializeObject()),
            	contentType:"application/json;charset=utf-8",  //缺失会出现URL编码，无法转成json对象
            	success:function(data){
					if(data.meta.success){
							$.messager.confirm('提示', '保存成功！', function(r){
								if (r){
									//parent.main.location = "bizaccept.do?action=selfclose";
								}
							});
					}
            	}
        	});
		}else{
			$.messager.alert('操作提示','存在校验项未通过！',"warning");
		}
    });

$('#bt_ok').on('click',function(){
		if($("#fm_elev_back").form('validate')){
       		$.ajax({
            	url:"elevbackvst.do?action=backsave&end=1",
            	type:"POST",
            	data:JSON.stringify($('#fm_elev_back').serializeObject()),
            	contentType:"application/json;charset=utf-8",  //缺失会出现URL编码，无法转成json对象
            	success:function(data){
					if(data.meta.success){
							$.messager.confirm('提示', '结案成功，窗口将自动关闭！', function(r){
								if (r){
									window.opener = null; 
　　　　　							window.open(' ', '_self', ' '); 
　　　　　							window.close(); 
								}
							});
					}
            	}
        	});
		}else{
			$.messager.alert('操作提示','存在校验项未通过！',"warning");
		}
    });

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

	$('#helpDept').combobox("setValue", "${domain.helpDept}");
	$('#dispatchTime').datetimebox('setValue', "${domain.dispatchTime}");
	$('#startTime').datetimebox('setValue', "${domain.startTime}");
	$('#arriveTime').datetimebox('setValue', "${domain.arriveTime}");
	$('#finishTime').datetimebox('setValue', "${domain.finishTime}");
	$('#trapppedPerson').textbox('setValue', "${domain.trapppedPerson}");
	$('#casualty').combobox('setValue', "${domain.casualty}");
	$('#injuries').numberspinner('setValue', "${domain.injuries}");
	$('#deathToll').numberspinner('setValue', "${domain.deathToll}");
	$('#reason').textbox('setValue', "${domain.reason}");
	$('#dealResult').textbox('setValue', "${domain.dealResult}");
	$('#dutyResult').textbox('setValue', "${domain.dutyResult}");
	val1 = $('#casualty').combobox('getValue');
		if("1" == val1) {
			$('#casualtySpan').show();
		} else {
			$('#casualtySpan').hide();
		}
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
<form method="post" id="fm_elev_back" name="fm_elev_back">
	<input type="hidden" name="pid" id="pid" value="${domain.pid}"/>
	<div id="p" class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
		<p class="font_no" style="font-size:16px; font-weight:bold; text-align:center;">嘉兴市电梯应急公共救援平台电话记录</p>
		<div style="margin-bottom:3px">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" style="font-size: 10pt">
          <tr class="table_t1">
          <td>值班人员:${domain.creatorName}</td>
          <td>值班日期:${domain.createTime}</td>
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
              ${domain.createTime}
            </td>
             <td style="width: 80px;text-align: right;">报告人</td>
            <td  style="width:200px;">
              ${domain.helpName}
            </td>
             <td style="width: 100px;text-align: center;">报告人电话</td>
            <td>
             <c:choose>
            	<c:when test="${domain.reportPhone == '110'}">110</c:when>
            	<c:when test="${domain.reportPhone == '119'}">119</c:when>
            	<c:when test="${domain.reportPhone == 'other'}">其他</c:when>
            	<c:otherwise>其他</c:otherwise>
            </c:choose>
              ${domain.helpTel}
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
            <td style="" colspan="5">${domain.deviceId}</td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯使用<br>管理单位</td>
            <td style="" colspan="5">${domain.useDept}</td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>电梯安装<br>具体位置</td>
            <td style="" colspan="5">${domain.position}</td>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>维保单位</td>
            <td style="" colspan="3">${domain.serviceName}</td>
          	<td style="text-align: center;">制造单位</td>
            <td style="" colspan="1">${domain.manufacturer}</td>          
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          <tr class="table_t1">
            <td>档案号</td>
            <td style="" colspan="3">${domain.referNo}</td>
            <td colspan="2"></td>
          </tr>
            <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>出厂编号</td>
            <td style="" colspan="3">${domain.serialNumber}</td>
          	<td style="text-align: center;">电梯类别</td>
            <td style="" colspan="1">
              <c:choose>
            	<c:when test="${domain.type == '1'}">电梯</c:when>
            	<c:when test="${domain.type == '2'}">货梯</c:when>
            	<c:when test="${domain.type == '3'}">医梯</c:when>
            	<c:when test="${domain.type == '4'}">扶梯</c:when>
            	<c:otherwise>电梯</c:otherwise>
            </c:choose>
          </tr>
          
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>检验日期</td>
            <td style="" colspan="3">${domain.surveyDate}</td>
          	<td style="text-align: center;">下检日期</td>
            <td style="" colspan="1">${domain.nextSurveyDate}</td>          
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
            <td style="" colspan="5"><input class="easyui-textbox" id="dealResult" name="dealResult" data-options="multiline:true,prompt:'请输入电梯困人故障原因'" style="height:100px;width: 90%;"></td>
          </tr>
          <tr class="line">
            <td height="1" colspan="6">            </td>
          </tr>
          
          <tr class="table_t1">
            <td>值班状况</td>
            <td style="" colspan="5"><input class="easyui-textbox" id="dutyResult" name="dutyResult" data-options="prompt:'请输入值班状况'" style="height:100%;width: 90%;"></td>
          </tr> 
          
			<tr align="center" class="table_t1">
            <td colspan="6">
              <a href="#" class="easyui-linkbutton" id="bt_save" data-options="iconCls:'icon-save'">保存</a>
              <a href="#" class="easyui-linkbutton" id="bt_ok" data-options="iconCls:'icon-ok'">结案</a>
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

