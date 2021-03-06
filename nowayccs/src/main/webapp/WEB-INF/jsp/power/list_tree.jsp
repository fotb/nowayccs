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
	    <script type="text/javascript">
        (function($){
            function pagerFilter(data){
                if ($.isArray(data)){ 
                    data = {  
                        total: data.length,  
                        rows: data  
                    }
                }
                
                var dg = $(this);  
                var state = dg.data('treegrid');
                var opts = dg.treegrid('options');  
                var pager = dg.treegrid('getPager');  
                pager.pagination({  
                    onSelectPage:function(pageNum, pageSize){  
                        opts.pageNumber = pageNum;  
                        opts.pageSize = pageSize;  
                        pager.pagination('refresh',{  
                            pageNumber:pageNum,  
                            pageSize:pageSize  
                        });  
                        dg.treegrid('loadData',state.allRows);  
                    }  
                });  
                opts.pageNumber = pager.pagination('options').pageNumber || 1;
                if (!state.allRows){
                    state.allRows = data.rows;
                }
                var topRows = [];
                var childRows = [];
                $.map(state.allRows, function(row){
                    row._parentId ? childRows.push(row) : topRows.push(row);
                });
                data.total = topRows.length;
                var start = (opts.pageNumber-1)*parseInt(opts.pageSize);  
                var end = start + parseInt(opts.pageSize);  
                data.rows = $.extend(true,[],topRows.slice(start, end).concat(childRows));
                return data;
            }
 
            var appendMethod = $.fn.treegrid.methods.append;
            var removeMethod = $.fn.treegrid.methods.remove;
            var loadDataMethod = $.fn.treegrid.methods.loadData;
            $.extend($.fn.treegrid.methods, {
                clientPaging: function(jq){
                    return jq.each(function(){
                        var state = $(this).data('treegrid');
                        var opts = state.options;
                        opts.loadFilter = pagerFilter;
                        var onBeforeLoad = opts.onBeforeLoad;
                        opts.onBeforeLoad = function(row,param){
                            state.allRows = null;
                            return onBeforeLoad.call(this, row, param);
                        }
                        $(this).treegrid('loadData', state.data);
                        if (opts.url){
                            $(this).treegrid('reload');
                        }
                    });
                },
                loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('treegrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.treegrid.methods, jq, data);
                },
                append: function(jq, param){
                    return jq.each(function(){
                        var state = $(this).data('treegrid');
                        if (state.options.loadFilter == pagerFilter){
                            $.map(param.data, function(row){
                                row._parentId = row._parentId || param.parent;
                                state.allRows.push(row);
                            });
                            $(this).treegrid('loadData', state.allRows);
                        } else {
                            appendMethod.call($.fn.treegrid.methods, $(this), param);
                        }
                    })
                },
                remove: function(jq, id){
                    return jq.each(function(){
                        if ($(this).treegrid('find', id)){
                            removeMethod.call($.fn.treegrid.methods, $(this), id);
                        }
                        var state = $(this).data('treegrid');
                        if (state.options.loadFilter == pagerFilter){
                            for(var i=0; i<state.allRows.length; i++){
                                if (state.allRows[i][state.options.idField] == id){
                                    state.allRows.splice(i,1);
                                    break;
                                }
                            }
                            $(this).treegrid('loadData', state.allRows);
                        }
                    })
                },
                getAllRows: function(jq){
                    return jq.data('treegrid').allRows;
                }
            });
 
        })(jQuery);
 
       
        $(function(){
            $('#tg').treegrid().treegrid('clientPaging');
            
            $("#btRemove").click(function (){
            	var row = $("#tg").treegrid("getSelected");
            	if(null == row) {
            		$.messager.alert("提示", '请选择需要删除的记录！');
            	} else if($("#tg").treegrid("getChildren", row.id).length > 0) {
            		$.messager.alert("提示", '请删除具体某个员工！');
            	} else {
            		$.messager.confirm("提示", '确认删除员工？',function(r){
            		    if (r){
            		    	$("#tg").treegrid("remove", row.id);
        					
                    		$.post("lps.do?action=del", {id:row.id},
        						function(data,status){
                    				$.getJSON("lps.do?action=buildtree", function(data){
                    					$("#tg").treegrid("loadData", data);
                    				});
        						});
            		    }
            		});
            	}
            });
            
            
            var editingId;
            $("#btEdit").click(function (){
                if (editingId != undefined){
                    $('#tg').treegrid('select', editingId);
                    return;
                }
                var row = $('#tg').treegrid('getSelected');
                if (row){
                    editingId = row.id
                    $('#tg').treegrid('beginEdit', editingId);
                }
            });
            
            $("#btSave").click(function (){
                if (editingId != undefined){
                    var t = $('#tg');
                    t.treegrid('endEdit', editingId);
                    editingId = undefined;
                    var persons = 0;
                    var row = t.treegrid('getSelected');
                    
	                 $.post("lps.do?action=saveLps", {id:row.id, name:row.name, phone:row.phone, remark:row.remark},
	                		 function(){
	                	 	
	                	 $("#tg").treegrid("reload");
	                 });
                }
            });
            
            $("#btCancel").click(function (){
                if (editingId != undefined){
                    $('#tg').treegrid('cancelEdit', editingId);
                    editingId = undefined;
                }
            });
        })
        
		function formatCategory(value){
			var s = "";
	    	if ('1' == value){
		    	s = "负责人";
	    	} else if("2" == value) {
	    		s = "安全负责人";
	    	} else if("3" == value) {
	    		s = "全镇机动";
	    	} else if("4" == value){
	    		s = "电工";
	    	} else {
	    		s = "";
	    	} 
	    	return s;
		}
        
    </script>
</head>
<body>
		<div style="margin-bottom:1px" id="tb">
			<a href="lps.do?action=add" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:'true'" id="btAdd">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove', plain:'true'" id="btRemove">停止服务</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:'true'" id="btEdit">修改</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save', plain:'true'" id="btSave">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">取消修改</a>
			<a href="lps.do?action=associate" class="easyui-linkbutton" data-options="iconCls:'icon-cancel', plain:'true'" id="btCancel">关联</a>
		</div>
	<div style="margin:10px 0;"></div>
	
	<table id="tg" title="光明电力服务员工" style="width:100%;height:450"
            data-options="
                iconCls: 'icon-ok',
                rownumbers: true,
                lines: true,
                animate: true,
                collapsible: true,
                fitColumns: true,
                striped:true,
                url: 'lps.do?action=buildtree',
                method: 'get',
                idField: 'id',
                treeField: 'name',
                pagination: true,
                pageSize: 1,
                pageList: [1,5,10],
                toolbar:'#tb'
            ">
        <thead>
            <tr>
                <th data-options="field:'name',width:100,align:'left',editor:'text'">姓名</th>
                <th data-options="field:'phone',width:80,editor:'text'">电话</th>
                <!-- <th data-options="field:'category',width:80,formatter:formatCategory,editor:{type:'combobox',options:{valueField:'category', textField:'categoryName', data:[{'category':1,'categoryName':1},{'category':2,'categoryName':2}]}}">职务</th> -->
                <th data-options="field:'category',width:80,formatter:formatCategory">职务</th>
                <th data-options="field:'remark',width:40,editor:'text'">备注</th>
            </tr>
        </thead>
    </table>

 
</body>
</html>