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
		<nav class="navbar navbar-default navbar-fixed-top  navbar-user" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><h2 class="milky text-center " contenteditable="true">嘉兴市96345社区服务求助中心</h2></a>
		</div>
		</nav>

<div id="myCarousel" class="carousel slide">
   <!-- 轮播（Carousel）指标 -->
<!--    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
   </ol>   -->
   <!-- 轮播（Carousel）项目 -->
   <div class="carousel-inner">
      <div class="item active">
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


				<div class="container-fluid col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart fa-fw"></i> 求助类型统计
							</h3>
						</div>
						<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
							<table class="table table-condensed huge-helptype">
								<thead>
                                    <tr>
                                        <th>类型</th>
                                        <th id="thisYear">2017</th>
                                        <th id="provYear">2016</th>
                                        <th>总计</th>
                                    </tr>
                                </thead>
								<tbody id="tcBody">
								</tbody>
							</table>
							</div>
							<div class="col-lg-6">
								<div id="pie-chart" style="height: 100%" ></div>
							</div>
					</div>
							</div>
						</div>
					</div>
				</div>


				<div class="row">


					<div class="container-fluid col-lg-6">
						<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-line-chart fa-fw"></i> 实时电话量
							</h3>
						</div>
						<div class="panel-body">
								<div id="container" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
							</div>
						</div>
						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> 按月统计求助电话
								</h3>
							</div>
							<div class="panel-body">
									<div id="area-chart" style="height: 220px; margin: 0 auto"></div>
								</div>
							</div>
					</div>
					
					<div class="container-fluid col-lg-6">
						<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart fa-fw"></i> 市区求助统计
							</h3>
						</div>
						<div class="panel-body">
								<div id="area-pie-chart" style="height: 513px"></div>
							</div>
						</div>
				   
				</div>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->
      </div>
      <div class="item">
         <div id="page-wrapper">
			<div class="row">
				<div class="container-fluid col-lg-6" >
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-user fa-fw"></i> 社区志愿者
							</h3>
						</div>
						<div class="panel-body" id="11">

						</div>
					</div>
				</div>


				<div class="container-fluid col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart fa-fw"></i> 服务企业
							</h3>
						</div>
						<div class="panel-body">
						
							</div>
						</div>
					</div>
				</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
      </div>
   </div>
   <!-- 轮播（Carousel）导航 -->

			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
	</div> 

		

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="jquery/jquery-3.1.1.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="bootstrap/js/bootstrap.min.js"></script>


	<script src="highcharts/highcharts.js" type="text/javascript"></script>
	<script src="highcharts/highcharts-3d.js"></script>
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
					index = (i-1)*4 +j;
					if(index > 4 & index < 9) {
						index = "800" + (index +1);
					} else if(index <= 4){
						index = "800" + (index);
					} else {
						index = "80" + (index +1);
					}
					/* if (((i-1)*4 +j) > 9) {
						index = "80" + ((i-1)*4 +j > 4 ? ((i-1)*4 +j +1) : ((i-1)*4 +j));   //没有分配8005分机号，故如果分机号大于8004，则下一分机号未8006
					} else {
						index = "800" + ((i-1)*4 +j > 4 ? ((i-1)*4 +j +1) : ((i-1)*4 +j));
					} */
					

					shtml += "<div class='col-lg-3 col-md-6'>";
					shtml += "	<div class='panel panel-default' id='div_"+index+"'>";
					shtml += "		<div class='panel-heading'>";
					shtml += "			<div class='row'>";
					shtml += "				<div class='col-xs-5 text-center'>";
					//shtml += "					<i class='fa fa-user-circle-o fa-3x' id='i_"+index+"'></i>";
					shtml += "						<i  data-toggle='popover' id='i_"+index+"' class='fa fa-user-circle fa-3x mouse-hand'></i>";//<i id='i_"+index+"' class='btn  fa fa-user-circle-o fa-3x' rel='popover'></i>";
					shtml += "					<div class='work-no' id='div_workno_"+index+"'>-</div>";
					shtml += "				</div>";
					shtml += "				<div class='col-xs-7 text-center'>";
					shtml += "					<div class='huge-1' id='div_status_"+index+"'>未签入</div>";
					//shtml += "					<div class='huge-2' id='div_workno_"+index+"'></div>";
					shtml += "				</div>";
					shtml += "			</div>";
					shtml += "		</div>";
					shtml += "	</div>";
					shtml += "</div>";
					
				}
			}

			$("#agentDiv").prepend(shtml);

			queryAgentStatus();
			
			$("[data-toggle='popover']").each(function(){
				tId = $(this).attr('id');
				//alert(tId);
				$("#"+tId).popover({
					html: true,
				    trigger: 'hover',
				    placement: 'buttom',
				    template: '<div class="popover-info" role="tooltip"><div class="arrow"></div><h3 class="popover-info-title"></h3><div class="popover-info-content"></div></div>',
				    title: '接线员信息',
				    content: '  <ul class="list-group" id="ul_'+tId+'"><li class="list-group-item">工号：<span id="id_'+tId+'"></span></li><li class="list-group-item">分机号：<span id="td_'+tId+'"></span></li><li class="list-group-item">日话务量：<span id="d_'+tId+'"></span></li><li class="list-group-item">月话务量：<span id="m_'+tId+'"></span></li><li class="list-group-item">年话务量：<span id="y_'+tId+'"></span></li></ul>'
				  }).click(function(e) {
				    $(this).popover('toggle');
				  });
				
				
			});
			
			$("[data-toggle='popover']").on('shown.bs.popover', function () {
				tId = $(this).attr('id');
				targetDevice = tId.split("_")[1];
				
				$.getJSON("dashboard.do?action=count&targetDevice=" + targetDevice, function(data) {
		
					$.each(data, function(n, value) {
						$("#id_"+ tId).html(value.agentId);
						$("#td_"+ tId).html(value.targetDevice);
						$("#d_"+ tId).html(value.dayCount);
						$("#m_"+ tId).html(value.monthCount);
						$("#y_"+ tId).html(value.yearCount);
					});
				});
			});
			/* $("[data-toggle='popover']").popover({
					html: true,
				   // trigger: 'hover',
				    placement: 'top',
				    template: '<div class="popover-info" role="tooltip"><div class="arrow"></div><h3 class="popover-info-title"></h3><div class="popover-info-content"></div></div>',
				    title: '接线员信息',
				    content: '  <ul class="list-group"> <li class="list-group-item">Cras justo odio</li><li class="list-group-item">Dapibus ac facilisis in</li></ul>'
				  }).click(function(e) {
				    $(this).popover('toggle');
				  });
 */
			setInterval(queryAgentStatus,5000);

			function queryAgentStatus() {
				var array_index = new Array('8001','8002','8003','8004','8005', '8006', '8007', '8008', '8009', '8010', '8011', '8012', '8013');
				var array_exist = new Array();
				$.getJSON("dashboard.do?action=agentStatus", function(data) {
					$.each(data, function(n, value) {
						index = value.targetDevice;
						array_exist.push(index);
						$('#div_workno_' + index + '').html(value.agentId);
						//2:空闲 3:示忙中 4:振铃 5:通话 
						switch (value.status) {
						case "2":
							$('#div_' + index + '').attr("class", "panel panel-primary");
							$("#i_"+ index +'').attr("class", "fa fa-user-circle fa-3x mouse-hand");
							$('#div_status_' + index + '').html("空闲中");
							break;
						case "3":
							$('#div_' + index + '').attr("class", "panel panel-yellow");
							$("#i_"+ index +'').attr("class", "fa fa-user-circle fa-3x mouse-hand");
							$('#div_status_' + index + '').html("示忙中");
							break;
						case "5":
							$('#div_' + index + '').attr("class", "panel panel-red");
							$("#i_"+ index +'').attr("class", "fa fa-phone fa-3x mouse-hand animated pulse talking")
							$('#div_status_' + index + '').html("振铃");
							break;

						case "4":
							$('#div_' + index + '').attr("class", "panel panel-green");
							$("#i_"+ index +'').attr("class", "fa fa-volume-control-phone fa-3x mouse-hand animated pulse talking")
							$('#div_status_' + index + '').html("通话中");
							//$("#i_"+ index +'').popover({placement : 'top', title: 'Bootstrap 弹出框', content: "为我的网站创建一个提示框如此简单！"});
							break;

						default:
							$('#div_' + index + '').attr("class", "panel panel-default");
							$("#i_"+ index +'').attr("class", "fa fa-user-circle fa-3x mouse-hand");
							$('#div_status_' + index + '').html("未签入");
							break;

						}
					});
					for(i = 0; i< array_index.length; i++) {
						if(array_exist.indexOf(array_index[i]) < 0) {
							$('#div_' + array_index[i] + '').attr("class", "panel panel-default");
							$("#i_"+ array_index[i] +'').attr("class", "fa fa-user-circle fa-3x mouse-hand");
							$('#div_status_' + array_index[i] + '').html("未签入");
							$('#div_workno_' + array_index[i] + '').html("-");
						}
					}
				});
				
			}
			
			queryHelpTypeCount();
			
			function queryHelpTypeCount() {

				$.getJSON("dashboard.do?action=htCount", function(data) {
					var td1 = '';
					var td2 = '';
					var td3 = '';
					var td4 = '';
					var td5 = '';
					$.each(data, function(name, value){
						sonvalue = eval(value.thisYear);
						$.each(sonvalue, function(n, value1){
							switch(value1.helpType) {
							case '1':
								td1 += '<td>' + value1.count +'</td>';
								break;
							case '2':
								td2 += '<td>' + value1.count +'</td>';
								break;
							case '3':
								td3 += '<td>' + value1.count +'</td>';
								break;
							case '4':
								td4 += '<td>' + value1.count +'</td>';
								break;
							case '5':
								td5 += '<td>' + value1.count +'</td>';
								break;
							}
						});
						
						sonvalue = eval(value.provYear);
						$.each(sonvalue, function(n, value1){
							switch(value1.helpType) {
							case '1':
								td1 += '<td>' + value1.count +'</td>';
								break;
							case '2':
								td2 += '<td>' + value1.count +'</td>';
								break;
							case '3':
								td3 += '<td>' + value1.count +'</td>';
								break;
							case '4':
								td4 += '<td>' + value1.count +'</td>';
								break;
							case '5':
								td5 += '<td>' + value1.count +'</td>';
								break;
							}
						});
						
						sonvalue = eval(value.allYear);
						$.each(sonvalue, function(n, value1){
							switch(value1.helpType) {
							case '1':
								td1 += '<td>' + value1.count +'</td>';
								break;
							case '2':
								td2 += '<td>' + value1.count +'</td>';
								break;
							case '3':
								td3 += '<td>' + value1.count +'</td>';
								break;
							case '4':
								td4 += '<td>' + value1.count +'</td>';
								break;
							case '5':
								td5 += '<td>' + value1.count +'</td>';
								break;
							}
						});
					});
					td1 = '<tr class="success"><td>生活服务类</td>'+td1+'</tr>';
					td2 = '<tr class="info"><td>咨询服务类</td>'+td2+'</tr>';
					td3 = '<tr class="warning"><td>事务服务类</td>'+td3+'</tr>';
					td4 = '<tr class="danger"><td>生产力服务类</td>'+td4+'</tr>';
					td5 = '<tr class="active"><td>电力服务类</td>'+td5+'</tr>';
					
					$("#tcBody").html(td1 + td2 + td3 +td4 + td5);
				});
			}
			
			
			$('.carousel').carousel({
				  interval: 0
				})
			
			
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
								$('#area-chart').highcharts({
												    credits: {  
												        enabled: false  
												      },  
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
														data : eval("[" + yStr + "]"),
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
									seriesStr += "['" + value.helpTypeName + "', "
											+ value.count + "],";
								});
								seriesStr = seriesStr.substring(0,
										seriesStr.length - 1);
								$(function() {
									Highcharts.chart('pie-chart', {
									    credits: {  
									        enabled: false  
									      },  
									    chart: {
									    	height: 335,
									        type: 'pie',
									        options3d: {
									            enabled: true,
									            alpha: 45,
									            beta: 0
									        }
									    },
									    title: {
									        text: ''
									    },
									    tooltip: {
									        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
									    },
									    plotOptions: {
									        pie: {
									            allowPointSelect: true,
									            cursor: 'pointer',
									    		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
									            innerSize: 50,
									            depth: 45,
									            dataLabels: {
									                enabled: true,
											    	style: {
											    		fontSize: '15px'
											        },
									                format: '{point.name}'
									            }
									        }
									    },
									    series: [{
									        type: 'pie',
									        name: '比例',
									        data: eval("["+ seriesStr+ "]")
									    }]
									});
								});
							});
			
			
			$.getJSON("dashboard.do?action=areacount",function(data) {
						seriesStr = "";
						$.each(data, function(n, value) {
							seriesStr += "['" + value.areaName + "', "+ value.count + "],";
						});
						seriesStr = seriesStr.substring(0,
								seriesStr.length - 1);
						$(function() {
							Highcharts.chart('area-pie-chart', {
							    credits: {  
							        enabled: false  
							      },  
							    chart: {
							    	//height: 335,
							        type: 'pie',
							        options3d: {
							            enabled: true,
							            alpha: 45,
							            beta: 0
							        }
							    },
							    title: {
							        text: ''
							    },
							    tooltip: {
							        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							    },
							    plotOptions: {
							        pie: {
							            allowPointSelect: true,
							            cursor: 'pointer',
							    		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
							            innerSize: 60,
							            depth: 65,
							            dataLabels: {
							                enabled: true,
									    	style: {
									    		fontSize: '15px'
									        },
							                format: '{point.name}({point.y})'
							            }
							        }
							    },
							    series: [{
							        type: 'pie',
							        name: '比例',
							        data: eval("["+ seriesStr+ "]")
							    }]
							});
						});
					});
		
		
		      Highcharts.setOptions({  
		          global: {  
		            useUTC: false  
		          }  
		        });  
			
			splineChart = new Highcharts.chart('container', {
			    credits: {  
			        enabled: false  
			      },  
		        chart: {
		            type: 'spline',
		            animation: Highcharts.svg, // don't animate in old IE
		            marginRight: 10,
		            events: {
		                load: function () {

		                    // set up the updating of the chart each second
		                    var series = this.series[0];
		                    setInterval(function () {
		                    	$.getJSON("dashboard.do?action=callcount",function(data) {
		        					result = 0;
		        					$.each(data, function(n, value) {
		        						result = value.count;
		        					});
		        			        var x = (new Date()).getTime(); // current time  
		        			        var y = parseInt(result);  
		        			        series.addPoint([x, y], true, true);
		        				});
		                    }, 2000);
		                }
		            }
		        },
		        title: {
		            text: '实时电话量'
		        },
		        xAxis: {
		            type: 'datetime',
		            tickPixelInterval: 150
		        },
		        yAxis: {
		            title: {
		                text: '电话量'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.series.name + '</b><br/>' +
		                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
		                    Highcharts.numberFormat(this.y, 2);
		            }
		        },
		        legend: {
		            enabled: false
		        },
		        exporting: {
		            enabled: false
		        },
		        series: [{
		            name: 'Random data',
		            data: (function () {
		                // generate an array of random data
		                var data = [],
		                    time = (new Date()).getTime(),
		                    i;

		                for (i = -19; i <= 0; i += 1) {
		                    data.push({
		                        x: time + i * 1000,
		                        y: Math.random()
		                    });
		                }
		                return data;
		            }())
		        }]
		    });
			
			function getCallCount() {
				$.getJSON("dashboard.do?action=callcount",function(data) {
					result = 0;
					$.each(data, function(n, value) {
						result = value.count;
					});
			        var series = splineChart.series[0];  
			        var x = (new Date()).getTime(); // current time  
			        var y = result;  
			        series.addPoint([x, y], true, true);
				});
			}
		});
	</script>
</body>

</html>
