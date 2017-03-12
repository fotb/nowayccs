<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>嘉兴96345社区服务求助中心</title>

<link rel="stylesheet" href="css/animate.min.css">

<!-- Bootstrap Core CSS -->
<!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="bootstrap/css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<!-- <link href="bootstrap/css/plugins/morris.css" rel="stylesheet"> -->

<!-- Custom Fonts -->
<link href="bootstrap/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div id="wrapper">

		<div id="container" style="min-width:400px;height:800px"></div>


	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="jquery/jquery-3.1.1.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/js/bootstrap.min.js"></script>


	<script src="highcharts/highcharts.src.js" type="text/javascript"></script>
	<script src="highcharts/highcharts-3d.src.js"></script>
	<!-- <script src="highcharts/themes/grid-light.js" type="text/javascript"></script> -->

	<!-- Morris Charts JavaScript -->
	<!-- 	<script src="bootstrap/js/plugins/morris/raphael.min.js"></script>
	<script src="bootstrap/js/plugins/morris/morris.min.js"></script>
	<script src="bootstrapjs/plugins/morris/morris-data.js"></script> -->
	<script>
	$(function () {

	   $.getJSON('http://localhost:9090/report/dashboard.do?action=yearcount', function (data) {
		   $('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '历年话务量'
		        },
		        subtitle: {
		            text: ''
		        },
		        xAxis: {
		            type: 'category',
		            labels: {
		                rotation: -45,
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                }
		            }
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: '话务量'
		            }
		        },
		        legend: {
		            enabled: false
		        },
		        tooltip: {
		            pointFormat: '话务量: <b>{point.y:.0f} </b>'
		        },
		        series: [{
		            name: '话务量',
		            data: data,
		            dataLabels: {
		                enabled: true,
		                rotation: -90,
		                color: '#FFFFFF',
		                align: 'right',
		                format: '{point.y:.0f}', // one decimal
		                y: 10, // 10 pixels down from the top
		                style: {
		                    fontSize: '13px',
		                    fontFamily: 'Verdana, sans-serif'
		                }
		            }
		        }]
		    });
	    });
	});

	</script>
</body>

</html>
