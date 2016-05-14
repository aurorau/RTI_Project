<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <title>RTI</title>
        <meta name="viewport" content="width=device-width">
        <meta name="description" content="">
        <meta name="author" content="Developed by Piyumika Dissanayake">

        <!-- The Styles -->
        <link href="style/bootstrap.min.css" rel="stylesheet">
        <link href="style/common-margins-paddings.css" rel="stylesheet">
        <link href="style/custom.css" rel="stylesheet">
        <link href="style/font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<link href="style/morris.css" rel="stylesheet">
        <!-- Fav and touch icons -->
        <link rel="shortcut icon" href="ico/favicon.png">

        <!--        scripts-->



    </head>

    <body>

        <div class="topbar">
            <div class="ims-logo-container pull-left">
                <h4 class="header-title">Real Time Investigator</h4>
            </div>
            <div class="col-md-4 col-lg-3 pull-right">
                <p class="text-right margin20">
                    <label>User Status</label>
                    <span class="label label-success font-13">Positive - 10%</span>
                    <span class="label label-danger font-13">Negative - 1%</span>
                </p>
            </div>
            <div class="col-md-3 col-lg-2 pull-right">
                <p class="text-right margin20">
                    <label>Total Events Count</label>- <span class="badge" id="totalEvents"></span>
                </p>
            </div>
            <div class="ol-md-2 col-lg-4 pull-right">
                <p class="margin20 text-right">
                    <label id="userId"></label>-
                    <label id="deviceName"></label>
                </p>
            </div>

        </div>

        <!-- End of topbar -->

        <div id="wrapper">
            <!--MAIN BODY-->
            <div id="sidebar-wrapper" class="style-3">
                <!--SIDEBAR CONTENT-->
                <div class="sidebar-nav">
                    <div class="user-count-btn">
                        <button class="btn btn-primary full-width" onclick="getCurrentUserCount()">Current Users</button>
                    </div>
                    <div class="users-count">

                        <div class="mobile">
                            <a href="#">
                                <label class="count" id="mobileCount">0</label>
                                <label class="text">Mobile
                                    <br>Users</label>
                            </a>
                        </div>
                        <div class="desktop">
                            <a href="#">
                                <label class="count" id="desktopCount">0</label>
                                <label class="text">Desktop
                                    <br>Users</label>
                            </a>
                        </div>
                    </div>
                    <div class="fraud-count margin-top5">

                        <div class="desktop">
                            <a href="#">
                                <label class="count white-text" id="fraudCount" >0</label>
                                <label class="text white-text">Fraud Users</label>
                            </a>
                        </div>
                    </div>
                    <label class="user-type">All Users</label>

<!--                     <input type="text" class="form-control search-user" placeholder="User No." />

                    <span class="fix-search-icon" role="button"><span class="fa fa-search"></span></span> -->
                    
					<div id="userCountDynamicTable">
					</div>

                    <div class="col-sm-12 padding0">
                        <hr>
                        <a href="#"><span class="fa fa-sign-out margin-right5" aria-hidden="true"></span>Log out</a>
                        <hr>
                    </div>
                </div>
            </div>

            <div id="page-content-wrapper" class="padding-bottom20">
                <!--MAIN CONTENT-->
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items" id="numOfSessionTimeout"></div>
                                    <div class="dashboard_text_bottom margin-top18">Session Time out</div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items">
                                        <p class="margin0" id="createdDate"></p>
                                        <p class="margin0" id="createdTime"></p>
                                    </div>
                                    <div class="dashboard_text_bottom">Session Created Time</div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items">
                                        <p class="margin0" id="lastAccessDate"></p>
                                        <p class="margin0" id="lastAccessTime"></p>
                                    </div>
                                    <div class="dashboard_text_bottom">Last Access Time</div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items" id="userStayingTime"></div>
                                    <div class="dashboard_text_bottom margin-top18">Staying Time</div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items" id="userIdleTime"></div>
                                    <div class="dashboard_text_bottom margin-top18">Max. Idle Time</div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-4 padding5">
                                <div class="inner-box">
                                    <div class="dashboard_items" id="avgTime"></div>
                                    <div class="dashboard_text_bottom margin-top18">Two Event's avg. time</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-sm-12 margin-top9" id="eventDetailsTableId">
                        </div>
                        
                        <div class="col-md-6 margin-bottom20">
                            <div class="col-lg-12 graph">
                                <h4>Overall Result</h4>
                                <div id="overall-graph"></div>
                                <div class="col-md-6 col-lg-12 margin-top20 margin-left10">
                                    <div class="pull-left">
                                        <div class="color_1 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>text</p>
                                        </div>
                                    </div>
                                    <div class="pull-left margin-left10">
                                        <div class="color_2 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>text</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="col-md-6 margin-bottom20">
                            <div class="col-lg-12 graph">
                                <h4>Device Analysis</h4>
                                <div id="device-analysis-graph"></div>
                                <div class="col-md-6 col-lg-12 margin-top20 margin-left10">
                                    <div class="pull-left">
                                        <div class="color_3 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>Desktop</p>
                                        </div>
                                    </div>
                                    <div class="pull-left margin-left10">
                                        <div class="color_4 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>Mobile</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 margin-bottom20">
                            <div class="col-lg-12 graph">
                                <h4>User Analysis</h4>
                                <div id="user-analysis-graph"></div>
                                <div class="col-md-6 col-lg-12 margin-top20 margin-left10">
                                    <div class="pull-left">
                                        <div class="color_5 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>Positive</p>
                                        </div>
                                    </div>
                                    <div class="pull-left margin-left10">
                                        <div class="color_6 pull-left"></div>
                                        <div class="text_headings pull-right">
                                            <p>Negative</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- end of row-->
            </div>
        </div>

        <script src="script/jquery.min.js"></script>
        <script src="script/morris-chart/raphael-min.js"></script>
        <script src="script/morris-chart/morris.min.js"></script>
        <script src="script/custom-graphs/overall-graph.js"></script>
       <!--  <script src="script/custom-graphs/device-analysis-graph.js"></script> -->
       <!--  <script src="script/custom-graphs/user-analysis-graph.js"></script> -->
        <script src="script/bootstrap.min.js"></script>
        <script src="script/jquery.displaytag-ajax-1.2.js"></script>
        <script src="script/common.js"></script>
        <script src="script/dashboard.js"></script>

        <script>
        </script>

    </body>

    </html>