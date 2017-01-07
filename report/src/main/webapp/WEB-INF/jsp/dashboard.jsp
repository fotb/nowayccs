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
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"><h2 class="text-center" contenteditable="true">嘉兴96345社区求助服务中心--服务状态展示中心</h2></a>
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
				<!-- <div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8001" align="center">
											<img alt="" src="images/worker-uncheckin.png">
										</div>
										<div id="name_8001" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8001">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8001">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8001">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8001">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8002" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8002" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8002">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8002">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8002">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8002">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8003" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8003" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8003">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8003">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8003">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8003">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8004" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8004" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8004">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8004">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8004">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8004">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8005" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8005" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8005">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8005">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8005">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8005">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8006" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8006" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8006">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8006">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8006">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8006">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8007" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8007" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8007">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8007">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8007">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8007">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8008" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8008" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8008">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8008">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8008">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8008">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8009" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8009" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8009">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8009">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8009">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8009">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8010" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8010" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8010">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8010">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8010">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8010">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8011" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8011" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8011">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8011">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8011">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8011">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8012" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8012" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8012">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8012">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8012">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8012">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8013" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8013" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8013">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8013">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8013">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8013">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8014" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8014" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_80142">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8014">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8014">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8014">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-black">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<div class="animated pulse" id="div_img_8015" align="center">
											<img alt="" src="images/call-center-worker.png">
										</div>
										<div id="name_8015" align="center">陈银洁</div>
									</div>
									<div class="col-xs-6 text-center" id="div_status_8015">
										<h3>通话中</h3>
									</div>
									<div class="col-xs-3 text-left">
										<table class="table-count table-count-bordered">
											<tbody>
												<tr>
													<td>日：</td>
													<td id="td_d_8015">12</td>
												</tr>
												<tr>
													<td>月：</td>
													<td id="td_m_8015">126</td>
												</tr>
												<tr>
													<td>年：</td>
													<td id="td_y_8015">1265</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
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
		$(function() {

			var shtml = "";
			for (i = 1; i <= 4; i++) {

				shtml += "<div class='row'>";
				for (j = 1; j <= 4; j++) {
					if (((i-1)*4 +j) >= 10) {
						index = "80" + ((i-1)*4 +j);
					} else {
						index = "800" + ((i-1)*4 +j);
					}
					shtml += "<div class='col-lg-3 col-md-6'>";
					shtml += "<div class='panel panel-black'>";
					shtml += "<div class='panel-heading'>";
					shtml += "<div class='row'>";
					shtml += "<div class='col-xs-3'>";
					shtml += "<div class='animated pulse' align='center' id='div_img_"+ index +"'>";
					shtml += "<img alt='' id='img_" + index + "' src='images/worker-gray.png'>";
					shtml += "</div>";
					shtml += "<div align='center' id='name_"+ index +"'></div>";
					shtml += "</div>";
					shtml += "<div class='col-xs-6 text-center' id='div_status_"+ index+"'>";
					shtml += "<h3></h3>";
					shtml += "</div>";
					shtml += "<div class='col-xs-3 text-left'>";
					shtml += "<table class='table-count table-count-bordered'>";
					shtml += "<tbody>";
					shtml += "<tr><td>日：</td><td id='td_d_"+ index+"'></td></tr>";
					shtml += "<tr><td>月：</td><td id='td_m_"+ index+"'></td></tr>";
					shtml += "<tr><td>年：</td><td id='td_y_"+ index+"'></td></tr>";
					shtml += "</tbody>";
					shtml += "</table>";
					shtml += "</div>";
					shtml += "</div>";
					shtml += "</div>";
					shtml += "</div>";
					shtml += "</div>";
				}
				shtml += "</div>";
			}

			$("#agentDiv").prepend(shtml);

			queryAgentStatus();
			
			setInterval(queryAgentStatus,5000);

			function queryAgentStatus() {
				$.getJSON("dashboard.do?action=agentStatus", function(data) {
					$.each(data, function(n, value) {
						index = value.targetDevice;
						$('#name_'+ index + '').html(value.userName);
						//所有session状态都为1，（标示在线）
						//1:置闲 2:振铃 3:接话中 4:事后处理 5:置忙 6:退出
						switch(value.agentStatus) {
						case "1":
							$('#img_'+ index + '').attr("src", "images/worker-stanby.png");
							$('#div_status_' + index+'').html("<h3>空闲</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;
						case "2":
							$('#img_'+ index + '').attr("src", "images/worker-stanby.png");
							$('#div_status_' + index+'').html("<h3>空闲</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;
						case "3":
							$('#img_'+ index + '').attr("src", "images/worker-talking.png");
							$('#div_status_' + index+'').html("<h3>通话中</br>"+value.phoneNo+"</h3>");
/* 							$('#div_img_' + index+'').css("position", "relative");
							$('#div_img_' + index+'').css("animation-duration", "2s");
							$('#div_img_' + index+'').css("animation-timing-function", "linear");
							$('#div_img_' + index+'').css("animation-delay", "1s");
							$('#div_img_' + index+'').css("animation-iteration-count", "infinite");
							$('#div_img_' + index+'').css("animation-direction", "alternate");
							$('#div_img_' + index+'').css("animation-play-state", "running"); */

							$('#div_img_' + index+'').attr("class", "animated pulse talking");
							break;

						case "4":
							$('#img_'+ index + '').attr("src", "images/worker-stanby.png");
							$('#div_status_' + index+'').html("<h3>空闲</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;

						case "5":
							$('#img_'+ index + '').attr("src", "images/worker-busy.png");
							$('#div_status_' + index+'').html("<h3>置忙</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;

						case "6":
							$('#img_'+ index + '').attr("src", "images/worker-uncheckin.png");
							$('#div_status_' + index+'').html("<h3>签出</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;
						
						default:
							$('#img_'+ index + '').attr("src", "images/worker-uncheckin.png");
							$('#div_status_' + index+'').html("<h3>签出</h3>");
							$('#div_img_' + index+'').attr("class", "animated pulse");
							break;
							
						}
					});
				});
			}
			
			getCount();
			
			setInterval(getCount,10000);
			
			function getCount() {
				$.getJSON("dashboard.do?action=count", function(data) {
					$.each(data, function(n, value) {
						$('#td_d_'+ value.targetDevice + '').html(value.dayCount);
						$('#td_m_'+ value.targetDevice + '').html(value.monthCount);
						$('#td_y_'+ value.targetDevice + '').html(value.yearCount);
					});
				});
			}

			$.getJSON("dashboard.do?action=monthChart",
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
														text : '按月统计求助电话'
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

			$.getJSON("dashboard.do?action=helpTypeCount",
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
															text : '求助类型比例图'
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
