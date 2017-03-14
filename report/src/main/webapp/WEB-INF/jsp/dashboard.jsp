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
	
<link rel="stylesheet" href="swiper/css/swiper.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.talking {
	animation-duration: 2s;
	animation-timing-function: linear;
	animation-delay: 1s;
	animation-iteration-count: infinite;
	animation-direction: alternate;
	animation-play-state: running;
}

.swiper-container {
        width: 100%;
        height: 100%;
    }
    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
</style>
</head>

<body>

	<div id="wrapper" >
	<div id="nav">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top  navbar-user"
			role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->
		<div class="col-lg-1 col-md-6">
			<img src="images/96345.png" style="padding-top:5px"/>
		</div>
		<div class="col-lg-8 col-md-6">
			<h2 class="milky">嘉兴市96345社区服务求助中心</h2>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="col-lg-12" style="text-align:left;">
			<h2 class=totalphone>累计受理：<span style="color:red;font-size:30px;" id="totalsum"></span>件次</h2>
			</div>
		</div>
		</nav>
		</div>
		<div class="swiper-container">
        <div class="swiper-wrapper">
        <div class="swiper-slide">
			<div id="page-wrapper" >
				<div class="row">
				<div class="col-lg-8 col-md-6">
					<div class="panel-body" id="agentDiv"></div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div id="area-pie-chart" ></div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div id="date_count_container"></div>
					</div>
					
					<div class="col-lg-4 col-md-6">
						<div id="pie-chart"></div>
					</div>
				</div>
			</div>
			</div>
		<div class="swiper-slide">
			<div id="page-wrapper" >
				<div class="row">
				<div class="col-lg-6 col-md-6">
						<div id="year_count_container"></div>
					</div>
					<div class="col-lg-6 col-md-6">
					<div id="month_count_container" style="margin: 0 auto"></div>
					</div>
				</div>
				<div class="row" style="padding-top:60px;">
					<div class="col-lg-6 col-md-6">
						<div id="volunteer_count_container"></div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div id="entprise_count_container" style="margin: 0 auto"></div>
					</div>
				</div>
				</div>
			</div>
		</div>
		</div>
		<!-- <div class="swiper-pagination"></div> -->
	<!-- /#wrapper -->
</div>

	<!-- jQuery -->
	<script src="jquery/jquery-3.1.1.js"></script>
	<script src="swiper/js/swiper.min.js"></script>

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
	
	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true
    });
    
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
					//shtml += "	<div class='panel panel-default' id='div_"+index+"'>";
					shtml += "				<div class='col-xs-5 text-center agent_site'>";
					shtml += "					<i data-toggle='popover' class='fa fa-circle fa-2x status_lamp status_default' id='i_"+index+"'></i>";
					//shtml += "						<i  data-toggle='popover' id='i_"+index+"' class='fa fa-user-circle fa-3x mouse-hand'></i>";//<i id='i_"+index+"' class='btn  fa fa-user-circle-o fa-3x' rel='popover'></i>";
					shtml += "				</div>";
					//shtml += "		</div>";
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
				   //template:"<div class='panel panel-green' role='tooltip'><div class='panel-heading'><h3 class='popover-info-title'></h3></div><div class='popover-info-content'></div></div>";
				    title: '接线员信息',
				   // content: '  <ul class="list-group" id="ul_'+tId+'"><li class="list-group-item">工号：<span id="id_'+tId+'"></span></li><li class="list-group-item">分机号：<span id="td_'+tId+'"></span></li><li class="list-group-item">日话务量：<span id="d_'+tId+'"></span></li><li class="list-group-item">月话务量：<span id="m_'+tId+'"></span></li><li class="list-group-item">年话务量：<span id="y_'+tId+'"></span></li></ul>'
				   content:"<table class='table'><thead></thead><tbody><tr><td>工&nbsp;&nbsp;&nbsp;&nbsp;号:</td><td style='text-align:left'><span id='id_"+tId+"'></span></td><td>状态:</td><td><span id='st_"+tId+"'></span></td></tr><tr><td>话务量：</td><td colspan='3'><span id='d_"+tId+"'></span>(日) <span id='m_"+tId+"'></span>(月) <span id='y_"+tId+"'></span>(年)</td></tr></tbody></table>"
				  }).click(function(e) {
				    $(this).popover('toggle');
				  });
				
				
			});
			
			$("[data-toggle='popover']").on('shown.bs.popover', function () {
				tId = $(this).attr('id');
				targetDevice = tId.split("_")[1];
				
				$.getJSON("dashboard.do?action=count&targetDevice=" + targetDevice, function(data) {
					astatus = "";
					$.each(data, function(n, value) {
						$("#id_"+ tId).html(value.agentId);
						//$("#td_"+ tId).html(value.targetDevice);
						
						
						switch (value.status) {
						case "2":
							astatus = "空闲中";
							break;
						case "3":
							astatus = "示忙中";
							break;
						case "5":
							astatus = "振铃";
							break;

						case "4":
							astatus = "通话中";
							break;

						default:
							astatus = "未签入";
							break;
						}
						$("#st_"+ tId).html(astatus);
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
							//$('#div_' + index + '').attr("class", "panel panel-primary");
							$("#i_"+ index +'').attr("class", "fa fa-circle fa-2x status_lamp status_free mouse-hand");
							//$('#div_status_' + index + '').html("空闲中");
							break;
						case "3":
							//$('#div_' + index + '').attr("class", "panel panel-yellow");
							$("#i_"+ index +'').attr("class", "fa fa-circle fa-2x status_lamp status_free mouse-hand" );
							//$('#div_status_' + index + '').html("示忙中");
							break;
						case "5":
							//$('#div_' + index + '').attr("class", "panel panel-red");
							$("#i_"+ index +'').attr("class", "fa fa-circle fa-2x status_lamp status_ring mouse-hand animated pulse talking")
							//$('#div_status_' + index + '').html("振铃");
							break;

						case "4":
							//$('#div_' + index + '').attr("class", "panel panel-green");
							$("#i_"+ index +'').attr("class", "fa fa-circle fa-2x status_lamp status_talking mouse-hand animated pulse talking")
							//$('#div_status_' + index + '').html("通话中");
							//$("#i_"+ index +'').popover({placement : 'top', title: 'Bootstrap 弹出框', content: "为我的网站创建一个提示框如此简单！"});
							break;

						default:
							//$('#div_' + index + '').attr("class", "panel panel-default");
							//$("#i_"+ index +'').attr("class", "fa fa-circle fa-2x status_lamp status_default");
							$("#i_"+ index +'').attr("class", "");
							//$('#div_status_' + index + '').html("未签入");
							break;

						}
					});
					for(i = 0; i< array_index.length; i++) {
						if(array_exist.indexOf(array_index[i]) < 0) {
							//$('#div_' + array_index[i] + '').attr("class", "panel panel-default");
							//$("#i_"+ array_index[i] +'').attr("class", "fa fa-circle fa-2x status_lamp status_default");
							$("#i_"+ array_index[i] +'').attr("class", "");
							//$('#div_status_' + array_index[i] + '').html("未签入");
							//$('#div_workno_' + array_index[i] + '').html("-");
						}
					}
				});
			}
			
			
			$.getJSON('http://localhost:9090/report/dashboard.do?action=sumtotal', function (data) {
				sumtotal = "";
				$.each(data, function(n, value) {
					sumtotal = value.total;
				});
				$("#totalsum").html(sumtotal);
			});
			
			
		    Highcharts.setOptions({
		        colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
		    });
		    
		    
			$.getJSON('http://localhost:9090/report/dashboard.do?action=datecount&provsMonth=12', function (data) {
		        $('#date_count_container').highcharts({
				    credits: {  
				        enabled: false  
				      },
		            chart: {
		                zoomType: 'x'
		            },
		            title: {
						align:"left",           
		                text: '话务量走势图',
					        style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                size:'20px'
				            }
		            },
		            subtitle: {
		               // text: document.ontouchstart === undefined ? '鼠标拖动可以进行缩放' : '手势操作进行缩放'
		            },
		            xAxis: {
		                type: 'datetime',
		                dateTimeLabelFormats: {
		                    millisecond: '%H:%M:%S.%L',
		                    second: '%H:%M:%S',
		                    minute: '%H:%M',
		                    hour: '%H:%M',
		                    day: '%m-%d',
		                    week: '%m-%d',
		                    month: '%Y-%m',
		                    year: '%Y'
		                }
		            },
		            tooltip: {
		            	xDateFormat: '%Y-%m-%d',
			            dateTimeLabelFormats: {
		                    millisecond: '%H:%M:%S.%L',
		                    second: '%H:%M:%S',
		                    minute: '%H:%M',
		                    hour: '%H:%M',
		                    day: '%Y-%m-%d',
		                    week: '%m-%d',
		                    month: '%Y-%m',
		                    year: '%Y'
		                }
		            },
		            yAxis: {
		                title: {
		                    text: '话务量'
		                }
		            },
		            legend: {
		                enabled: false
		            },
		            plotOptions: {
		                area: {
		                    fillColor: {
		                        linearGradient: {
		                            x1: 0,
		                            y1: 0,
		                            x2: 0,
		                            y2: 1
		                        },
		                        stops: [
		                            [0, Highcharts.getOptions().colors[0]],
		                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
		                        ]
		                    },
		                    marker: {
		                        radius: 2
		                    },
		                    lineWidth: 1,
		                    states: {
		                        hover: {
		                            lineWidth: 1
		                        }
		                    },
		                    threshold: null
		                }
		            },
		            series: [{
		                type: 'area',
		                name: '话务量',
		                data: data
		            }]
		        });
		    });
			
			$.getJSON("dashboard.do?action=areacount",function(data) {
				seriesStr = "";
				$.each(data, function(n, value) {
					seriesStr += "['" + value.areaName + "', "+ value.count + "],";
				});
				seriesStr = seriesStr.substring(0, seriesStr.length - 1);
				$(function() {
					
				    // Radialize the colors
				    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
				        return {
				            radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
				            stops: [
				                [0, color],
				                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
				            ]
				        };
				    });
					
					Highcharts.chart('area-pie-chart', {
					    credits: {  
					        enabled: false  
					      },  
					    chart: {
					    	//height: 335,
/* 					        type: 'pie',
					        options3d: {
					            enabled: true,
					            alpha: 45,
					            beta: 0
					        } */
					      
				            plotBackgroundColor: null,
				            plotBorderWidth: null,
				            plotShadow: false
					    },
					    title: {
					    	align:"left",
					        text: '区域话务量统计',
					        style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                size:'20px'
				            }
					    },
					    tooltip: {
					        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					    },
					    plotOptions: {
					        pie: {
					            allowPointSelect: true,
					            cursor: 'pointer',
					    		//colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
					            dataLabels: {
					                enabled: true,
							    	style: {
							    		fontSize: '15px',
							    		color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							        },
							        connectorColor: 'silver',
					                format: '{point.name}({point.y}%)'
					            }
					        }
					    },
					    series: [{
					        type: 'pie',
					        name: '比例',
					        //data: eval("["+ seriesStr+ "]")
					        data: [
					        		['其他',   5],
					        		['南湖区',   41.5],
					                {
					                    name: '秀洲区',
					                    y: 31.6,
					                    sliced: true,
					                    selected: true
					                },
					                ['经济开发区',    21.9]
					                
					            ]
					    }]
					});
				});
			});
			
			
			$.getJSON("dashboard.do?action=monthChart", function(data) {
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
						$('#month_count_container').highcharts({
										    credits: {  
										        enabled: false  
										      },  
											chart : {
												type : 'line'
											},
											title : {
												text : '近一年每月话务量',
												align:"left",
										        style: {
									                color: '#000000',
									                fontWeight: 'bold',
									                size:'20px'
									            }
											},
											/* 				        subtitle: {
											 text: 'Source: WorldClimate.com'
											 }, */
											xAxis : {
												categories : eval("[" + xStr + "]"),
												//crosshair : true
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
													borderWidth : 0,
													 value: 0,
											            width: 1,
											            color: '#808080'
												}
											},
											series : [ {
									        	showInLegend: false,
												name : '求助电话',
												//colorByPoint : true,
												data : eval("[" + yStr + "]"),
												color: '#c5533d'
											} ]
										});
					});
			
			$.getJSON('http://localhost:9090/report/dashboard.do?action=yearcount', function (data) {
				   $('#year_count_container').highcharts({
					    credits: {  
					        enabled: false  
					      },  
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: '历年话务量',
				            align:"left",
				            style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                size:'20px'
				            }
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
			
			
			$.getJSON("dashboard.do?action=helpTypeCount", function(data) {

						seriesStr = "";
						$.each(data, function(n, value) {
							seriesStr += "['" + value.helpTypeName + "', " + value.count + "],";
						});
						seriesStr = seriesStr.substring(0,seriesStr.length - 1);
						
						$(function() {
						    
							Highcharts.chart('pie-chart', {
							    credits: {  
							        enabled: false  
							      },  
							    chart: {
							    	//height: 335,
		/* 					        type: 'pie',
							        options3d: {
							            enabled: true,
							            alpha: 45,
							            beta: 0
							        } */
							      
						            plotBackgroundColor: null,
						            plotBorderWidth: null,
						            plotShadow: false
							    },
							    title: {
							    	align:"left",
							        text: '求助类型统计',
							        style: {
						                color: '#000000',
						                fontWeight: 'bold',
						                size:'20px'
						            }
							    },
							    tooltip: {
							        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							    },
							    plotOptions: {
							        pie: {
							            allowPointSelect: true,
							            cursor: 'pointer',
							    		//colors: ['#24CBE5', '#64E572', '#FF9655', '#FFF263', '#058DC7', '#50B432', '#ED561B', '#DDDF00', '#6AF9C4'],
							            dataLabels: {
							                enabled: true,
									    	style: {
									    		fontSize: '15px',
									    		color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
									        },
									        connectorColor: 'silver',
							                format: '{point.name}({point.y}%)'
							            }
							        }
							    },
							    series: [{
							        type: 'pie',
							        name: '比例',
							        //data: eval("["+ seriesStr+ "]")
							        data: [
							        		['生活类',   55],
							        		['事务类',   0.05],
							                {
							                    name: '咨询类',
							                    y: 43.95,
							                    sliced: true,
							                    selected: true
							                },
							                ['电力咨询',    1]
							            ]
							    }]
							});
						});
					});
			
			
			
			
			$.getJSON('http://localhost:9090/report/dashboard.do?action=volunteer&top=15', function (data) {
				total = "";
				topData = "";
				$.each(data, function(n, value) {
					total = value.total;
					topData = value.top;
				});
				   $('#volunteer_count_container').highcharts({
					    credits: {  
					        enabled: false  
					      },  
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: '一技之长服务者派单量TOP15',
				            align:"left",
				            style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                size:'20px'
				            }
				        },
				        subtitle: {
				            text: '共计一技之长服务者' + total + '人',
				            align:"left",
				            style: {
				                color: '#b32b2b',
				                fontWeight: 'bold',
				                size:'18px'
				            }
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
				                text: '派单量'
				            }
				        },
				        legend: {
				            enabled: false
				        },
				        tooltip: {
				            pointFormat: '派单量: <b>{point.y:.0f} </b>'
				        },
				        series: [{
				            name: '派单量',
				            data: eval(topData),
				            color: '#ED561B',
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
			
			
			
			$.getJSON('http://localhost:9090/report/dashboard.do?action=entprise&top=15', function (data) {
				
				total = "";
				topData = "";
				$.each(data, function(n, value) {
					total = value.total;
					topData = value.top;
				});
				   $('#entprise_count_container').highcharts({
					    credits: {  
					        enabled: false  
					      },  
				        chart: {
				            type: 'column'
				        },
				        title: {
				            text: '加盟企业派单量TOP15',
				            align:"left",
				            style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                size:'20px'
				            }
				        },
				        subtitle: {
				            text: '共计加盟企业' + total + '家',
				            align:"left",
				            style: {
				                color: '#b32b2b',
				                fontWeight: 'bold',
				                size:'18px'
				            }
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
				                text: '派单量'
				            }
				        },
				        legend: {
				            enabled: false
				        },
				        tooltip: {
				            pointFormat: '派单量: <b>{point.y:.0f} </b>'
				        },
				        series: [{
				            name: '派单量',
				            data: eval(topData),
				            color: '#6AF9C4',
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
