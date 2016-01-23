<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Document</title>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<base target="_self">
<script type="text/javascript">
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

        $.getJSON("preport.do?action=list&areaId=" + areaId + "&areaSubId=" + areaSubId + "&startdt=" + $("#startDt").datebox("getValue") + "&enddt=" + $("#endDt").datebox("getValue"), function(data){
        	$("#dg").datagrid("loadData", data);
        });
    });
    
    
    var today = new Date();
    $("#startDt").datebox("setValue", today.getFullYear()+"-"+today.getMonth()+"-"+today.getDate());
    $("#endDt").datebox("setValue", today.getFullYear()+"-"+(today.getMonth()+1 )+"-"+today.getDate());
    
    
    
    
    var pager = $('#dg').datagrid().datagrid('getPager');    // get the pager of datagrid
    pager.pagination({
        buttons:[{
            iconCls:'icon-save',
            handler:function(){
            	var areaId = $("#areaId").combobox('getValue');
            	var areaSubId = $("#areaSubId").combobox('getValue');
                window.location.href = "preport.do?action=export&areaId=" + areaId + "&areaSubId=" + areaSubId + "&startdt=" + $("#startDt").datebox("getValue") + "&enddt=" + $("#endDt").datebox("getValue");
            }
        }]
    });            
});


</script>
<body>
	<table id="dg" class="easyui-datagrid" title="电力服务查询："
		style="width: 90%; height:auto;"
		data-options="rownumbers:true,singleSelect:true,url:'preport.do?action=list',method:'get',toolbar:'#tb',pagination:true,
                pageSize:10">
		<thead>
			<tr>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'phone',width:100,align:'right'">电话</th>
				<th data-options="field:'area',width:100,align:'right'">区域</th>
				<th data-options="field:'areaSub',width:100,align:'right'">社区（村）</th>
				<th data-options="field:'count',width:100,align:'right'">派单总量</th>
				<th data-options="field:'todayCount',width:100,align:'right'">今日派单量</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
	    <table>
        <tr>
            <td>开始时间:</td>
            <td>
                <input class="easyui-datebox" data-options="sharedCalendar:'#cc'" id=startDt>
            </td>
            <td>结束时间:</td>
            <td>
                <input class="easyui-datebox" data-options="sharedCalendar:'#cc'" id=endDt>
            </td>
            <td rowspan="2" style="text-align: center;"><a href="#" class="easyui-linkbutton" iconCls="icon-search"  id="btnSearch">搜索</a></td>
        </tr>
        
        <tr>
        <td>街道(镇):</td>
        <td>
        <input class="easyui-combobox" id="areaId" style="width: auto"
			data-options="url:'json.do?action=arealist',
											method:'get',
											valueField:'value',
											textField:'text',
											onSelect:function(record){
												$('#areaSubId').combobox('setValues', '');
												$('#areaSubId').combobox('reload', 'json.do?action=subarealist&areaId='+record.value);
											}" />
		</td>												
		<td>社区(村):</td>
		<td>
		<input class="easyui-combobox" id="areaSubId" style="width: auto"
			data-options="valueField:'value',textField:'text',panelHeight:'auto'" />
        </td>
        </tr>
    </table>
	</div>
	<div id="cc" class="easyui-calendar"></div>
</body>
</html>