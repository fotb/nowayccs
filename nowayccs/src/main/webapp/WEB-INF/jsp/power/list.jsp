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
</head>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="lps.do?action=add" class="easyui-linkbutton" iconCls="icon-add" id="btAdd" plain="true">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<!--
		<div>
			Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px">
			Language: 
			<select class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="java">Java</option>
				<option value="c">C</option>
				<option value="basic">Basic</option>
				<option value="perl">Perl</option>
				<option value="python">Python</option>
			</select>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
		-->
	</div>
	
	<div style="margin:20px 0;"></div>
	
	<table id="tg" title="光明电力服务员工" style="width:100%;height:100%"
            data-options="
                iconCls: 'icon-ok',
                rownumbers: true,
                animate: true,
                collapsible: true,
                fitColumns: true,
                url: 'lps.do?action=buildtree',
                method: 'get',
                idField: 'id',
                treeField: 'name',
                pagination: true,
                pageSize: 2,
                pageList: [2,5,10],
                toolbar:'#tb'
            ">
        <thead>
            <tr>
                <th data-options="field:'name',width:100,align:'left'">姓名</th>
                <th data-options="field:'phone',width:80">电话</th>
                <th data-options="field:'category',width:80,formatter:formatCategory">职务</th>
                <th data-options="field:'remark',width:40">备注</th>
            </tr>
        </thead>
    </table>
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
        })
        
        $("btAdd").click(function (){
        	alert("test");
        	this.location="lps.do?action=add";        	
        });
        
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
 
</body>
</html>