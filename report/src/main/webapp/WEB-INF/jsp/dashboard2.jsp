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
<style>
#img_status {
	position: relative;
	animation-duration: 2s;
	animation-timing-function: linear;
	animation-delay: 1s;
	animation-iteration-count: infinite;
	animation-direction: alternate;
	animation-play-state: running;
}
</style>
</head>

<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header-user">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">嘉兴96345社区求助服务中心--服务状态展示中心</a>
		</div>
		<!-- Top Menu Items --> <!-- 
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
						class="caret"></b></a>
					<ul class="dropdown-menu message-dropdown">
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-preview"><a href="#">
								<div class="media">
									<span class="pull-left"> <img class="media-object"
										src="http://placehold.it/50x50" alt="">
									</span>
									<div class="media-body">
										<h5 class="media-heading">
											<strong>John Smith</strong>
										</h5>
										<p class="small text-muted">
											<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
										</p>
										<p>Lorem ipsum dolor sit amet, consectetur...</p>
									</div>
								</div>
						</a></li>
						<li class="message-footer"><a href="#">Read All New
								Messages</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-bell"></i> <b
						class="caret"></b></a>
					<ul class="dropdown-menu alert-dropdown">
						<li><a href="#">Alert Name <span
								class="label label-default">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-primary">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-success">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span class="label label-info">Alert
									Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-warning">Alert Badge</span></a></li>
						<li><a href="#">Alert Name <span
								class="label label-danger">Alert Badge</span></a></li>
						<li class="divider"></li>
						<li><a href="#">View All</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
						</li>
						<li><a href="#"><i class="fa fa-fw fa-envelope"></i>
								Inbox</a></li>
						<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="#"><i class="fa fa-fw fa-power-off"></i> Log
								Out</a></li>
					</ul></li>
			</ul>
			 --> <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href="dashboard.do"><i
						class="fa fa-fw fa-dashboard fa-2x"></i></a></li>
				<!-- <li><a href="dashboard.do"><i
						class="fa fa-fw fa-bar-chart-o"></i></a></li> -->
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>

		<div id="page-wrapper">

			<div class="container-fluid" id="agentDiv">


				<!-- Page Heading -->

<!-- 				<div class="row">
					<div class="col-lg-12">
						                        <h1 class="page-header">
                            Dashboard <small>Statistics Overview</small>
                        </h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i>坐席状态
							</li>
						</ol>
					</div>
				</div> -->
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-4">
										<div class="animated pulse" align="center" id="div_img_8001">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div align="center" id="name_8001">陈银洁(100)</div>
									</div>
									<div class="col-xs-8 text-center" id="div_status_8001">
									<h3>通话中</h3>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<div class="animated pulse" align="center" id="img_status">
											<img alt="" src="images/worker-blue.png">
										</div>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<div>离线</div>

											<div>今日话务量：100</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-gray.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-red.png"></i> <i>陈银洁</i>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				</div>
								<div class="row">
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-4">
										<div class="animated pulse" align="center" id="div_img_8001">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div align="center" id="name_8001">陈银洁(100)</div>
									</div>
									<div class="col-xs-8 text-center" id="div_status_8001">
									<h3>通话中</h3>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<div class="animated pulse" align="center" id="img_status">
											<img alt="" src="images/worker-blue.png">
										</div>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<div>离线</div>

											<div>今日话务量：100</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-gray.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-red.png"></i> <i>陈银洁</i>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				</div>
								<div class="row">
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-4">
										<div class="animated pulse" align="center" id="div_img_8001">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div align="center" id="name_8001">陈银洁(100)</div>
									</div>
									<div class="col-xs-8 text-center" id="div_status_8001">
									<h3>通话中</h3>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<div class="animated pulse" align="center" id="img_status">
											<img alt="" src="images/worker-blue.png">
										</div>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<div>离线</div>

											<div>今日话务量：100</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-blue.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-gray.png"></i>
										<div align="center">陈银洁</div>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-5">
										<i><img alt="" src="images/worker-red.png"></i> <i>陈银洁</i>
									</div>
									<div class="col-xs-7 text-right">
										<div>
											<table>
												<tr>
													<td>状态</td>
													<td>离线</td>
												</tr>
												<tr>
													<td>今日话务量</td>
													<td>100</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-black">
							<div class="panel-body">
								<div id="area-chart"></div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-6">
						<div class="panel panel-black">
							<div class="panel-body">
								<div id="pie-chart"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="jquery/jquery-3.1.1.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/js/bootstrap.min.js"></script>


	<script src="highcharts/highcharts.js" type="text/javascript"></script>
	<script src="highcharts/themes/dark-unica.js" type="text/javascript"></script>

	<!-- Morris Charts JavaScript -->
	<!-- 	<script src="bootstrap/js/plugins/morris/raphael.min.js"></script>
	<script src="bootstrap/js/plugins/morris/morris.min.js"></script>
	<script src="bootstrapjs/plugins/morris/morris-data.js"></script> -->
	<script>


	$(function () {
		alert("1");
		for(i = 0; i < 3; i++) {
			alert("2");
				$("agentDiv").append("<div class='row'>1111111111111111111111111111111111111111");
				alert("2");
		}

		 $.getJSON("dashboard.do?action=monthChart",function(data) { 
				xStr = "";
				yStr = "";
			 i = 1;
			 $.each(data, function(n, value) {
				 if(i >= data.length) {
					 xStr += "'" + value.date + "'";
					 yStr += value.count;
				 } else {
					 xStr += "'" + value.date + "',";
					 yStr += value.count + ",";
				 }
				 i++;
			 });
			 //xStr = "[" + xStr+ "]";
			 //yStr = "[" + yStr+ "]";
				    $('#area-chart').highcharts({
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: '按月统计求助电话'
				        },
/* 				        subtitle: {
				            text: 'Source: WorldClimate.com'
				        }, */
				        xAxis: {
				            categories: eval("[" + xStr + "]"),
				            crosshair: true
				        },
				        yAxis: {
				            min: 0,
				            title: {
				                text: '求助电话 (个)'
				            }
				        },
				        tooltip: {
				            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				            '<td style="padding:0"><b>{point.y} mm</b></td></tr>',
				            footerFormat: '</table>',
				            shared: true,
				            useHTML: true
				        },
				        plotOptions: {
				            column: {
				                pointPadding: 0.2,
				                borderWidth: 0
				            }
				        },
				        series: [{
				            name: '每月话务量',
				            colorByPoint: true,
				            data: eval("[" + yStr + "]"),
				            dataLabels: {
				                enabled: true,
				                rotation: -90,
				                color: '#FFFFFF',
				                align: 'right',
				                format: '{point.y}', // one decimal
				                y: 10, // 10 pixels down from the top
				                style: {
				                    fontSize: '13px',
				                    fontFamily: 'Verdana, sans-serif'
				                }
				            }
				        }]			            
				    });
		 });
		 
		 
		 $.getJSON("dashboard.do?action=helpTypeCount",function(data) {
			 
			 seriesStr = "";
			 $.each(data, function(n, value) {
				 seriesStr += "['" + value.helpType + "', " + value.count + "],";
			 });
			 seriesStr = seriesStr.substring(0, seriesStr.length - 1);
			 $(function () {
				    $('#pie-chart').highcharts({
				        chart: {
				            plotBackgroundColor: null,
				            plotBorderWidth: null,
				            plotShadow: false
				        },
				        title: {
				            text: '求组类型比例图'
				        },
				        tooltip: {
				            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				        },
				        plotOptions: {
				            pie: {
				                allowPointSelect: true,
				                cursor: 'pointer',
				                dataLabels: {
				                    enabled: true,
				                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
				                    style: {
				                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				                    }
				                }
				            }
				        },
				        series: [{
				            type: 'pie',
				            name: '比例',
				            data: eval("[" + seriesStr + "]")
				        }]
				    });
				});
		 });
	});
	</script>

</body>

</html>
