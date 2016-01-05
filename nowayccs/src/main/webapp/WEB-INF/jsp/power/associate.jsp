<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/includes.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>光明电力服务中心员工</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<base target="_self">
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
            
            $("#btnSave").click(function (){
            	var areaId = $("#areaId").combobox('getValue');
            	var areaSubId = $("#areaSubId").combobox('getValue');
            	var areaName = $("#areaId").combobox('getText');
            	var areaSubName = $("#areaSubId").combobox('getText');
            	if(areaId == "" || areaId == null) {
            		$.messager.alert("提示","请选择街道（镇）！", "error");
            		return;
            	}
            	if(areaSubId == "") {
            		$.messager.alert("提示", "请选择社区（村）！", "error");
            		return;
            	}
            	
            	var rows = $('#dg').datagrid('getSelections');
            	if(rows == null) {
            		$.messager.alert("提示","请选择电工！", "error");
            		return;
            	}
            	
            	var pids = new Array();
            	var names = "";
    			for(var i=0; i<rows.length; i++){
    				var row = rows[i];
    				pids.push(row.pid);
    				names += row.name;
    			}
            	
                $.post("lps.do?action=associateSave", {areaSubId:areaSubId, pids:pids}, function(json){
                	$.messager.alert("提示","保存成功！", "info");
                	$("#info").html("成功将【" + names + "】加入【" + areaName +"】-【 " + areaSubName + "】中！");
                });
            });
        });
        
        
        
        
        function btnnext_click(){

        	
        	if(row == null) {
        		alert("fasdfa");
        	} else {
        		$("#powerStaffId").val(row.pid);
        	}

        	$("form").submit();
        	//$("#bizAccept").submit(function(){alert("success"); return false;});
        }
    </script>
</head>
<body>
		<div style="margin-bottom:1px" id="tb">
			<input class="easyui-combobox" id="areaId" style="width:200px" 
	    						data-options="url:'json.do?action=arealist',
											method:'get',
											valueField:'value',
											textField:'text',
											required:true,
											onSelect:function(record){
												$('#areaSubId').combobox('setValues', '');
												$('#areaSubId').combobox('reload', 'json.do?action=subarealist&areaId='+record.value);
											}" ></input>
							
							<input class="easyui-combobox" id="areaSubId" style="width:200px" 
	    						data-options="valueField:'value',textField:'text',required:true" ></input>
	    						
	    						
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:'true'" id="btnSave">保存</a>
			<span id="info" style="color: red; font-weight:bold;"></span>
		</div>
	<div style="margin:10px 0;"></div>
	
            <table id="dg" class="easyui-datagrid" title="请选择派送电工：" style="width:90%;height:auto"
            data-options="rownumbers:true,url:'lps.do?action=pslist',method:'get',toolbar:'#tb',pagination:true,
                pageSize:10">
		<thead>
			<tr>
				<th data-options="field:'pid',checkbox:true"></th>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'phone',width:100,align:'right'">电话</th>
			</tr>
		</thead>
	</table>

 
</body>
</html>