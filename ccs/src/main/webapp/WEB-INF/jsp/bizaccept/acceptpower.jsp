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
</script>
<body>
<form:form method="post" action="bizaccept.do?action=powersave" commandName="bizAccept">
	<input id="powerStaffId" type="hidden" name="powerStaffId">
	<input id="areaSubId1" type="hidden" name="areaSubId1">
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
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td width="10%">求助名姓名：</td>
            <td>
              ${bizAccept.helpName}
            </td>
             <td>求助方式：</td>
            <td>
              ${qzfsMap[bizAccept.helpMode]}
            </td>
          </tr>

          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助电话：</td>
            <td  colspan="3">
              ${bizAccept.helpTel}<c:if test="${not empty bizAccept.otherTel}">, ${bizAccept.otherTel}</c:if>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>详细地址：</td>
            <td  colspan="3"> 
              ${bizAccept.helpAddr}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助内容：</td>
            <td colspan="3">
            <textarea rows="4" cols="60" disabled="disabled">${bizAccept.helpContent}</textarea>
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          <tr class="table_t1">
            <td>求助类型：</td>
            <td>
			 ${helpTypeMap[bizAccept.helpType]}
            </td>
            <td style="width: 100px">求助区域：</td>
            <td>
               ${qzqyMap[bizAccept.helpArea]}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>

          <tr class="table_t1">
            <td>受理人群：</td>
            <td>
            <c:if test="${not empty bizAccept.helpGroup}">${slrqMap[bizAccept.helpGroup]}</c:if>
            </td>
                        <td>服务者：</td>
            <td>
            ${bizAccept.creator}
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
          </tr>
          
           <tr class="table_t1">
            <td colspan="4">
            <table id="dg" class="easyui-datagrid" title="请选择派送电工：" style="width:90%;height:250px"
            data-options="rownumbers:true,singleSelect:true,url:'bizaccept.do?action=pslist',method:'get',toolbar:'#tb',pagination:true,
                pageSize:10">
		        <thead>
		            <tr>
		                 <th data-options="field:'pid',checkbox:true"></th>
		                <th data-options="field:'name',width:100">姓名</th>
		                <th data-options="field:'phone',width:100,align:'right'">电话</th>
		                <th data-options="field:'remark',width:100,align:'right'">备注</th>
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
											onSelect:function(record){
												$('#areaSubId').combobox('setValues', '');
												$('#areaSubId').combobox('reload', 'json.do?action=subarealist&areaId='+record.value);
											}" />
							
							<input class="easyui-combobox" id="areaSubId" style="width:200px" 
	    						data-options="valueField:'value',textField:'text',panelHeight:'auto'" />
        	<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="btnSearch">搜索</a>
    		</div>      
            </td>
          </tr>
          <tr class="line">
            <td height="1" colspan="4">            </td>
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

    <script>
        (function($){
            function pagerFilter(data){
                if ($.isArray(data)){    // is array
                    data = {
                        total: data.length,
                        rows: data
                    }
                }
                var target = this;
                var dg = $(target);
                var state = dg.data('datagrid');
                var opts = dg.datagrid('options');
                if (!state.allRows){
                    state.allRows = (data.rows);
                }
                if (!opts.remoteSort && opts.sortName){
                    var names = opts.sortName.split(',');
                    var orders = opts.sortOrder.split(',');
                    state.allRows.sort(function(r1,r2){
                        var r = 0;
                        for(var i=0; i<names.length; i++){
                            var sn = names[i];
                            var so = orders[i];
                            var col = $(target).datagrid('getColumnOption', sn);
                            var sortFunc = col.sorter || function(a,b){
                                return a==b ? 0 : (a>b?1:-1);
                            };
                            r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
                            if (r != 0){
                                return r;
                            }
                        }
                        return r;
                    });
                }
                var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
                var end = start + parseInt(opts.pageSize);
                data.rows = state.allRows.slice(start, end);
                return data;
            }
 
            var loadDataMethod = $.fn.datagrid.methods.loadData;
            var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
            $.extend($.fn.datagrid.methods, {
                clientPaging: function(jq){
                    return jq.each(function(){
                        var dg = $(this);
                        var state = dg.data('datagrid');
                        var opts = state.options;
                        opts.loadFilter = pagerFilter;
                        var onBeforeLoad = opts.onBeforeLoad;
                        opts.onBeforeLoad = function(param){
                            state.allRows = null;
                            return onBeforeLoad.call(this, param);
                        }
                        var pager = dg.datagrid('getPager');
                        pager.pagination({
                            onSelectPage:function(pageNum, pageSize){
                                opts.pageNumber = pageNum;
                                opts.pageSize = pageSize;
                                pager.pagination('refresh',{
                                    pageNumber:pageNum,
                                    pageSize:pageSize
                                });
                                dg.datagrid('loadData',state.allRows);
                            }
                        });
                        $(this).datagrid('loadData', state.data);
                        if (opts.url){
                            $(this).datagrid('reload');
                        }
                    });
                },
                loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('datagrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.datagrid.methods, jq, data);
                },
                deleteRow: function(jq, index){
                    return jq.each(function(){
                        var row = $(this).datagrid('getRows')[index];
                        deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                        var state = $(this).data('datagrid');
                        if (state.options.loadFilter == pagerFilter){
                            for(var i=0; i<state.allRows.length; i++){
                                if (state.allRows[i] == row){
                                    state.allRows.splice(i,1);
                                    break;
                                }
                            }
                            $(this).datagrid('loadData', state.allRows);
                        }
                    });
                },
                getAllRows: function(jq){
                    return jq.data('datagrid').allRows;
                }
            })
        })(jQuery);
 
        $(function(){
            $('#dg').datagrid().datagrid('clientPaging');
            
            $("#btnSearch").click(function (){
            	var areaId = $("#areaId").combobox('getValue');
            	var areaSubId = $("#areaSubId").combobox('getValue');
            	if(areaId == "" || areaId == null) {
            		$.messager.alert("提示","请选择街道（镇）！", "error");
            		return;
            	}
            	if(areaSubId == "") {
            		$.messager.alert("提示", "请选择社区（村）！", "error");
            		return;
            	}
                $.getJSON("bizaccept.do?action=pslist&areaId=" + areaId + "&areaSubId=" + areaSubId, function(data){
                	$("#dg").datagrid("loadData", data);
                });
            });
        });
    </script>
</body>
</html>
