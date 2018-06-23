<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    System.out.println(basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.0.min.js"></script>
<link href="<%=basePath%>/css/iconfont.css" rel="stylesheet">                  
<link href="<%=basePath%>/css/nav.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>/js/nav.js"></script>
</head>

<div class="nav">
    <div class="nav-top">
        <div id="mini" style="border-bottom:1px solid rgba(255,255,255,.1)"><img src="images/mini.png" ></div>
    </div>
    <ul>  
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>网站配置</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>网站设置</span></a></li>
                <li><a href="javascript:;"><span>友情链接</span></a></li>
                <li><a href="javascript:;"><span>分类管理</span></a></li>
                <li><a href="javascript:;"><span>系统日志</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>文章管理</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>站内新闻</span></a></li>
                <li><a href="javascript:;"><span>站内公告</span></a></li>
                <li><a href="javascript:;"><span>登录日志</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>订单管理</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>订单列表</span></a></li>
                <li><a href="javascript:;"><span>打个酱油</span></a></li>
                <li><a href="javascript:;"><span>也打酱油</span></a></li>
            </ul>
        </li>
    </ul>
</div>
</html>
