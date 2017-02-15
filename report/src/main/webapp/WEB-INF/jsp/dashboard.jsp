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
.talking {
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
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">

			<a class="navbar-brand" href="index.html"><h2 class="text-center" contenteditable="true">嘉兴96345社区求助服务中心--服务状态展示中心</h2></a>
		</div>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="container-fluid col-lg-6" >
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-user fa-fw"></i> 坐席状态
							</h3>
						</div>
						<div class="panel-body" id="agentDiv">

							<!-- <div class="col-lg-3 col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<i class="fa fa-user-circle fa-5x"></i>
											</div>
											<div class="col-xs-9 text-right">
												<div class="huge">801</div>
												<div>未签入</div>
											</div>
										</div>
									</div>
								</div>
							</div> -->
							
						</div>
					</div>
				</div>


				<div class="container-fluid col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> 按月统计求助电话
							</h3>
						</div>
						<div class="panel-body">
							<h3>h3. 这是一套可视化布局系统.</h3>
							<table class="table table-condensed">
								<tbody>
									<tr>
										<td>总计</td>
										<td>323232</td>
									</tr>
									<tr class="success">
										<td>事务类</td>
										<td>1211</td>
									</tr>
									<tr class="error">
										<td>资讯类</td>
										<td>3334</td>
									</tr>
									<tr class="warning">
										<td>求组累</td>
										<td>33232</td>
									</tr>
									<tr class="info">
										<td>满意度</td>
										<td>100%</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- 
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8016" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8016" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8016">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8016">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8016">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8016">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> -->





				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> 按月统计求助电话
							</h3>
						</div>
						<div class="panel-body">
								<div id="area-chart"></div>
							</div>
						</div>
					</div>

					<div class="col-lg-6">
						<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> 求助类型比例图
							</h3>
						</div>
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
	<!-- <script src="highcharts/themes/grid-light.js" type="text/javascript"></script> -->

	<!-- Morris Charts JavaScript -->
	<!-- 	<script src="bootstrap/js/plugins/morris/raphael.min.js"></script>
	<script src="bootstrap/js/plugins/morris/morris.min.js"></script>
	<script src="bootstrapjs/plugins/morris/morris-data.js"></script> -->
	<script>
		$(function() {

			var shtml = "";
			for (i = 1; i <= 3; i++) {

				for (j = 1; j <= 4; j++) {
					if (((i-1)*4 +j) >= 10) {
						index = "80" + ((i-1)*4 +j > 4 ? (i-1)*4 +j +1 : (i-1)*4 +j);   //没有分配8005分机号，故如果分机号大于8004，则下一分机号未8006
					} else {
						index = "800" + ((i-1)*4 +j > 4 ? (i-1)*4 +j +1 : (i-1)*4 +j);
					}
					

					shtml += "<div class='col-lg-3 col-md-6'>";
					shtml += "	<div class='panel panel-default' id='div_"+index+"'>";
					shtml += "		<div class='panel-heading'>";
					shtml += "			<div class='row'>";
					shtml += "				<div class='col-xs-3'>";
					shtml += "					<i class='fa fa-user-circle-o fa-4x' id='i_"+index+"'></i>";
					shtml += "					<i>116</i>";
					shtml += "				</div>";
					shtml += "				<div class='col-xs-9 text-center'>";
					shtml += "					<div class='huge-1' id='div_status_"+index+"'>未签入</div>";
					shtml += "					<div class='huge' id='div_workno_"+index+"'></div>";
					shtml += "				</div>";
					shtml += "			</div>";
					shtml += "		</div>";
					shtml += "	</div>";
					shtml += "</div>";
					
				}
			}

			$("#agentDiv").prepend(shtml);

			queryAgentStatus();

			setInterval(queryAgentStatus,5000);

			function queryAgentStatus() {
				$.getJSON("dashboard.do?action=agentStatus", function(data) {
					$.each(data, function(n, value) {
						index = value.targetDevice;
						$('#div_workno_' + index + '').html(value.agentId);
						//2:空闲 3:示忙中 4:振铃 5:通话 
						switch (value.status) {
						case "2":
							$('#div_' + index + '').attr("class", "panel panel-primary");
							$('#div_status_' + index + '').html("空闲中");
							break;
						case "3":
							$('#div_' + index + '').attr("class", "panel panel-yellow");
							$('#div_status_' + index + '').html("示忙中");
							break;
						case "4":
							$('#div_' + index + '').attr("class", "panel panel-red");
							$('#div_status_' + index + '').html("振铃");
							break;

						case "5":
							$('#div_' + index + '').attr("class", "panel panel-green animated pulse talking");
							$('#div_status_' + index + '').html("通话中");
							break;

						default:
							$('#div_' + index + '').attr("class", "panel panel-default");
							$('#div_status_' + index + '').html("未签入");
							break;

						}
					});
				});
			}

			//getCount();

			//setInterval(getCount,10000);

			function getCount() {
				$.getJSON("dashboard.do?action=count", function(data) {
					$.each(data, function(n, value) {
						$('#td_d_' + value.targetDevice + '').html(
								value.dayCount);
						$('#td_m_' + value.targetDevice + '').html(
								value.monthCount);
						$('#td_y_' + value.targetDevice + '').html(
								value.yearCount);
					});
				});
			}

			$
					.getJSON(
							"dashboard.do?action=monthChart",
							function(data) {
								xStr = "";
								yStr = "";
								i = 1;
								$.each(data, function(n, value) {
									if (i >= data.length) {
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
								$('#area-chart')
										.highcharts(
												{
													chart : {
														type : 'column'
													},
													title : {
														text : ''
													},
													/* 				        subtitle: {
													 text: 'Source: WorldClimate.com'
													 }, */
													xAxis : {
														categories : eval("["
																+ xStr + "]"),
														crosshair : true
													},
													yAxis : {
														min : 0,
														title : {
															text : '求助电话 (个)'
														}
													},
													tooltip : {
														headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
														pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
																+ '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
														footerFormat : '</table>',
														shared : true,
														useHTML : true
													},
													plotOptions : {
														column : {
															pointPadding : 0,
															borderWidth : 0
														}
													},
													series : [ {
														name : '每月话务量',
														colorByPoint : true,
														data : eval("[" + yStr
																+ "]"),
														dataLabels : {
															enabled : true,
															rotation : -90,
															color : '#FFFFFF',
															align : 'right',
															format : '{point.y}', // one decimal
															y : 10, // 10 pixels down from the top
															style : {
																fontSize : '13px',
																fontFamily : 'Verdana, sans-serif'
															}
														}
													} ]
												});
							});

			$
					.getJSON(
							"dashboard.do?action=helpTypeCount",
							function(data) {

								seriesStr = "";
								$.each(data, function(n, value) {
									seriesStr += "['" + value.helpType + "', "
											+ value.count + "],";
								});
								seriesStr = seriesStr.substring(0,
										seriesStr.length - 1);
								$(function() {
									$('#pie-chart')
											.highcharts(
													{
														chart : {
															plotBackgroundColor : null,
															plotBorderWidth : null,
															plotShadow : false
														},
														title : {
															text : ''
														},
														tooltip : {
															pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
														},
														plotOptions : {
															pie : {
																allowPointSelect : true,
																cursor : 'pointer',
																dataLabels : {
																	enabled : true,
																	format : '<b>{point.name}</b>: {point.percentage:.1f} %',
																	style : {
																		color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																				|| 'black'
																	}
																}
															}
														},
														series : [ {
															type : 'pie',
															name : '比例',
															data : eval("["
																	+ seriesStr
																	+ "]")
														} ]
													});
								});
							});
		});
	</script>

</body>

</html>
