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

        <!-- Fav and touch icons -->
        <link rel="shortcut icon" href="ico/favicon.png">

    </head>

    <body>

        <div class="topbar">
            <div class="ims-logo-container pull-left">
                <h4 class="header-title">Real Time Investigator</h4>
            </div>
            <div class="col-md-3 pull-right">
                <p class="text-right margin20">
                    <label>User Status -</label>
                    <span class="label label-success font-13">Positive - 10%</span>
                    <span class="label label-danger font-13">Negative - 1%</span>
                </p>
            </div>
            <div class="col-md-2 pull-right">
                <p class="text-right margin20">
                    <label>Total Events</label>- <span class="badge">10</span>
                </p>
            </div>
            <div class="col-md-5">
                <p class="margin20 padding-left8">
                    <label>User 1</label>
                    <label>Desktop</label>
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
                                <label class="count">50</label>
                                <label class="text">Mobile
                                    <br>Users</label>
                            </a>
                        </div>
                        <div class="desktop">
                            <a href="#">
                                <label class="count">10</label>
                                <label class="text">Desktop
                                    <br>Users</label>
                            </a>
                        </div>
                    </div>
                    <div class="fraud-count margin-top5">

                        <div class="desktop">
                            <a href="#">
                                <label class="count white-text">5</label>
                                <label class="text white-text">Fraud Users</label>
                            </a>
                        </div>
                    </div>
                    <label class="user-type">All Users</label>

                   <!--  <input type="text" class="form-control search-user" placeholder="User No." /> -->

                    <!-- <span class="fix-search-icon" role="button"><span class="fa fa-search"></span></span> -->
					
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
                        <div class="col-sm-12">
                            <div class="summery-detail-div">
                                <div class="box">
                                    <label class="">Session Time out</label>
                                    <label class="description full-width">1</label>
                                    <br>
                                </div>
                                <div class="box">
                                    <label class="">Session Created Time</label>
                                    <label class="description">2016-04-08 09:44:456</label>
                                    <br>
                                </div>
                                <div class="box">
                                    <label class="">Last Access Time</label>
                                    <label class="description">2016-04-08 09:55:456</label>
                                    <br>
                                </div>
                                <div class="box">
                                    <label class="full-width">Staying Time</label>
                                    <label class="description">0 min</label>
                                    <br>
                                </div>
                                <div class="box">
                                    <label class="full-width">Max. Idle Time</label>
                                    <label class="description">0 min</label>
                                    <br>
                                </div>
                                <div class="box">
                                    <label class="full-width">Two Event's avg. time</label>
                                    <label class="description">0 sec</label>
                                    <br>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 margin-top9" id="eventDetailsTableId">
                          <%--   <table class="table headeronly">
                                <thead>
                                    <tr>
                                        <th class="th1">Event</th>
                                        <th class="th2">Tag</th>
                                        <th class="th3">Image Path</th>
                                        <th class="th4">Triggered Time</th>
                                        <th class="th5">Taps</th>
                                        <th class="th6">Scroll Top</th>
                                        <th class="th7">Event(x,y)</th>
                                        <th class="th8">Time Zone</th>
                                        <th class="th9">Zone Date/Time</th>
                                        <th class="th10">Device</th>
                                        <th class="th11">Device(w,h)</th>
                                        <th class="th12">View(w,h)</th>
                                        <th class="th13">View</th>
                                        <th class="th14">Browser</th>
                                        <th class="th15">ID</th>
                                        <th class="th16">Proxies</th>
                                    </tr>
                                </thead>
                            </table>
                            <div class="div-table-content" >
                                <table class="bodyonly">
                                    <tbody>
                                        <tr>
                                            <td class="td1">SE</td>
                                            <td class="td2">Div</td>
                                            <td class="td3">no</td>
                                            <td class="td4">2016-4-18 10:15:18.822</td>
                                            <td class="td5">0</td>
                                            <td class="td6">690</td>
                                            <td class="td7">0,0</td>
                                            <td class="td8">+5.30</td>
                                            <td class="td9">2016-4-18 10:15:19.146</td>
                                            <td class="td10">Com.</td>
                                            <td class="td11">1366,768</td>
                                            <td class="td12">1366,643</td>
                                            <td class="td13">-1</td>
                                            <td class="td14">Chrome</td>
                                            <td class="td15">33623809</td>
                                            <td class="td16">2<a href="#" data-toggle="popover" data-html="true" data-content="<div><b>Popover Example</b> 1 - Content</div>" title="Popover Example <b>1</b> - Title"><span class="fa fa-eye proxy"></span></a>

                                                <!--                                    <a href="#" tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="<p class='book-title'>Steve Jobs</p>"><span class="fa fa-eye proxy"></span></a>-->
                                            </td>
                                        </tr><tr>
                                            <td class="td1">SE</td>
                                            <td class="td2">Div</td>
                                            <td class="td3">no</td>
                                            <td class="td4">2016-4-18 10:15:18.822</td>
                                            <td class="td5">0</td>
                                            <td class="td6">690</td>
                                            <td class="td7">0,0</td>
                                            <td class="td8">+5.30</td>
                                            <td class="td9">2016-4-18 10:15:19.146</td>
                                            <td class="td10">Com.</td>
                                            <td class="td11">1366,768</td>
                                            <td class="td12">1366,643</td>
                                            <td class="td13">-1</td>
                                            <td class="td14">Chrome</td>
                                            <td class="td15">33623809</td>
                                            <td class="td16">2<a href="#" data-toggle="popover" data-html="true" data-content="<div><b>Popover Example</b> 1 - Content</div>" title="Popover Example <b>1</b> - Title"><span class="fa fa-eye proxy"></span></a>

                                                <!--                                    <a href="#" tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="<p class='book-title'>Steve Jobs</p>"><span class="fa fa-eye proxy"></span></a>-->
                                            </td>
                                        </tr><tr>
                                            <td class="td1">SE</td>
                                            <td class="td2">Div</td>
                                            <td class="td3">no</td>
                                            <td class="td4">2016-4-18 10:15:18.822</td>
                                            <td class="td5">0</td>
                                            <td class="td6">690</td>
                                            <td class="td7">0,0</td>
                                            <td class="td8">+5.30</td>
                                            <td class="td9">2016-4-18 10:15:19.146</td>
                                            <td class="td10">Com.</td>
                                            <td class="td11">1366,768</td>
                                            <td class="td12">1366,643</td>
                                            <td class="td13">-1</td>
                                            <td class="td14">Chrome</td>
                                            <td class="td15">33623809</td>
                                            <td class="td16">2<a href="#" data-toggle="popover" data-html="true" data-content="<div><b>Popover Example</b> 1 - Content</div>" title="Popover Example <b>1</b> - Title"><span class="fa fa-eye proxy"></span></a>

                                                <!--                                    <a href="#" tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="<p class='book-title'>Steve Jobs</p>"><span class="fa fa-eye proxy"></span></a>-->
                                            </td>
                                        </tr><tr>
                                            <td class="td1">SE</td>
                                            <td class="td2">Div</td>
                                            <td class="td3">no</td>
                                            <td class="td4">2016-4-18 10:15:18.822</td>
                                            <td class="td5">0</td>
                                            <td class="td6">690</td>
                                            <td class="td7">0,0</td>
                                            <td class="td8">+5.30</td>
                                            <td class="td9">2016-4-18 10:15:19.146</td>
                                            <td class="td10">Com.</td>
                                            <td class="td11">1366,768</td>
                                            <td class="td12">1366,643</td>
                                            <td class="td13">-1</td>
                                            <td class="td14">Chrome</td>
                                            <td class="td15">33623809</td>
                                            <td class="td16">2<a href="#" data-toggle="popover" data-html="true" data-content="<div><b>Popover Example</b> 1 - Content</div>" title="Popover Example <b>1</b> - Title"><span class="fa fa-eye proxy"></span></a>

                                                <!--                                    <a href="#" tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="<p class='book-title'>Steve Jobs</p>"><span class="fa fa-eye proxy"></span></a>-->
                                            </td>
                                        </tr><tr>
                                            <td class="td1">SE</td>
                                            <td class="td2">Div</td>
                                            <td class="td3">no</td>
                                            <td class="td4">2016-4-18 10:15:18.822</td>
                                            <td class="td5">0</td>
                                            <td class="td6">690</td>
                                            <td class="td7">0,0</td>
                                            <td class="td8">+5.30</td>
                                            <td class="td9">2016-4-18 10:15:19.146</td>
                                            <td class="td10">Com.</td>
                                            <td class="td11">1366,768</td>
                                            <td class="td12">1366,643</td>
                                            <td class="td13">-1</td>
                                            <td class="td14">Chrome</td>
                                            <td class="td15">33623809</td>
                                            <td class="td16">2<a href="#" data-toggle="popover" data-html="true" data-content="<div><b>Popover Example</b> 1 - Content</div>" title="Popover Example <b>1</b> - Title"><span class="fa fa-eye proxy"></span></a>

                                                <!--                                    <a href="#" tabindex="0"  role="button" data-toggle="popover" data-trigger="focus" title="<p class='book-title'>Steve Jobs</p>"><span class="fa fa-eye proxy"></span></a>-->
                                            </td>
                                        </tr>
                                        
                                    </tbody>
                                </table> 
                            </div>--%>

                        </div>
                    </div>
                </div>
                <!-- end of row-->
            </div>
        </div>


        <script src="script/jquery.min.js"></script>
        <script src="script/bootstrap.min.js"></script>
        <script src="script/jquery.displaytag-ajax-1.2.js"></script>
        <script src="script/common.js"></script>
        <script src="script/dashboard.js"></script>
        <!-- <script src="script/jquery.dataTables.min.js"></script> -->
        <script>
            $(document).ready(function() {
                $('[data-toggle="popover"]').popover();
            });
        </script>
    </body>

    </html>