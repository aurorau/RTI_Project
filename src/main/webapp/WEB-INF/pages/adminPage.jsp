<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="script/jquery.min.js"></script>

<!-- GRAPH -->
<script src="script/graph/jquery.jqplot.js"></script>
<script src="script/graph/jqplot.canvasTextRenderer.js"></script>
<script src="script/graph/jqplot.canvasAxisLabelRenderer.js"></script>
<script src="script/graph/jqplot.highlighter.js"></script>
<script src="script/graph/jqplot.cursor.js"></script>
<script src="script/graph/jqplot.dateAxisRenderer.js"></script>
<script src="script/graph/jqplot.pieRenderer.js"></script>
<script src="script/graph/jqplot.blockRenderer.js"></script>

<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="script/jquery.displaytag-ajax-1.2.js"></script>
<script src="script/admin.js"></script>
<script src="script/graph.js"></script>

<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<link href="style/jquery.jqplot.css" rel="stylesheet">
<title>Admin Panel</title>
</head>

<body>
	<div>
	<div style="width:20%; float: left;">
	<h2 style="font-size: 20px;">ADMIN PAGE</h2>
	<button onclick="getCurrentUserCount()" style="font-size: 12px;">Get Current User Count</button><br>
	<label style="font-size: 15px; color: #009fbc">Current users : <span id=numberOfCurrentUsers style="font-size: 15px;"></span></label>
		<div id="userDetailsTable">
			<label style="font-size: 15px; color: #009fbc">Number of Events : <span id=numberOfEvents style="font-size: 15px;"></span></label>
			<table id="userDetailsTable">
			</table>
		</div>
		<div id="analyseTableId">
			<table>
				<tr>
					<td style="text-align: left;"><b><u>DEVICE</u></b></td>
					<td id="device" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>NUM_OF_SESSION_TIMEOUT</u></b></td>
					<td id="numOfSessionTimeout" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>SESSION_CREATED_TIME</u></b></td>
					<td id="firstAccessTime" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>USER_LAST_ACCESS_TIME</u></b></td>
					<td id="lastAccessTime" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>USER_STAYING_TIME</u></b></td>
					<td id="userStayingTime" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>MAX_IDLE_TIME</u></b></td>
					<td id="userIdleTime" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>TOTAL_REFRESH_COUNT</u></b></td>
					<td id="totalRF" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left;"><b><u>AVG_TIME_TWO_EVENT</u></b></td>
					<td id="avgTime" style="text-align: left;"></td>
				</tr>
				<tr>
					<td style="text-align: left; font-size: 12px;"><b><i>TOTAL_EVENT_COUNT</i></b></td>
					<td id="totalEvents" style="text-align: left; font-size: 12px;"></td>
				</tr>
			</table>
		</div>
		
		<div id="deviceAnlysisTable">
		<label style="font-size: 15px; color: #009fbc">Analysis Device</label>
		<table>
			<thead>
				<tr>
					<th>ATTRIBUTE</th>
					<th>DESKTOP</th>
					<th>MOBILE</th>
				</tr>
			</thead>
			<tr>
				<td style="text-align: left;"><b><u>Device by User Events</u></b></td>
				<td style="text-align: left;" id="deviceUE_D"></td>
				<td style="text-align: left;" id="deviceUE_M"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>Device by Dimension</u></b></td>
				<td style="text-align: left;" id="deviceD_D"></td>
				<td style="text-align: left;" id="deviceD_M"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>Device by Orientation</u></b></td>
				<td style="text-align: left;" id="deviceO_D"></td>
				<td style="text-align: left;" id="deviceO_M"></td>
			</tr>
		</table>
		</div>
		
		<div id="eventPersantageTable">
		<label style="font-size: 15px; color: #009fbc">Event Percentage </label>
		<table>
			<thead>
				<tr>
					<th>ATTRIBUTE</th>
					<th>COUNT</th>
					<th>PERSANTAGE</th>
				</tr>
			</thead>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_LC_COUNT</u></b></td>
				<td style="text-align: left;" id="totalLC"></td>
				<td style="text-align: left;" id="totalLC_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_TS_COUNT</u></b></td>
				<td style="text-align: left;" id="totalTS"></td>
				<td style="text-align: left;" id="totalTS_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_TM_COUNT</u></b></td>
				<td style="text-align: left;" id="totalTM"></td>
				<td style="text-align: left;" id="totalTM_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_TOUCH_ZOOM_COUNT</u></b></td>
				<td style="text-align: left;" id="totalTZ"></td>
				<td style="text-align: left;" id="totalTZ_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_SWAP_ZOOM_COUNT</u></b></td>
				<td style="text-align: left;" id="totalSZ"></td>
				<td style="text-align: left;" id="totalSZ_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_IMG_COUNT</u></b></td>
				<td style="text-align: left;" id="totalIMG"></td>
				<td style="text-align: left;" id="totalIMG_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><i>&nbsp; - LC_IMG_COUNT</i></b></td>
				<td style="text-align: left;" id="totalIMG_LC"></td>
				<td style="text-align: left;" id="totalIMG_LC_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><i>&nbsp; - TS_IMG_COUNT</i></b></td>
				<td style="text-align: left;" id="totalIMG_TS"></td>
				<td style="text-align: left;" id="totalIMG_TS_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><i>&nbsp; - TM_IMG_COUNT</i></b></td>
				<td style="text-align: left;" id="totalIMG_TM"></td>
				<td style="text-align: left;" id="totalIMG_TM_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><i>&nbsp; - TOUCH_ZOOM_IMG_COUNT</i></b></td>
				<td style="text-align: left;" id="totalIMG_TZ"></td>
				<td style="text-align: left;" id="totalIMG_TZ_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><i>&nbsp; - SWP_ZOOM_IMG_COUNT</i></b></td>
				<td style="text-align: left;" id="totalIMG_SZ"></td>
				<td style="text-align: left;" id="totalIMG_SZ_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_PARA_COUNT</u></b></td>
				<td style="text-align: left;" id="totalPARA"></td>
				<td style="text-align: left;" id="totalPARA_P"></td>
			</tr>	
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_INPUT_COUNT</u></b></td>
				<td style="text-align: left;" id="totalINPUT"></td>
				<td style="text-align: left;" id="totalINPUT_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_A_COUNT</u></b></td>
				<td style="text-align: left;" id="totalA"></td>
				<td style="text-align: left;" id="totalA_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_SELECT_COUNT</u></b></td>
				<td style="text-align: left;" id="totalSELECT"></td>
				<td style="text-align: left;" id="totalSELECT_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_OPTION_COUNT</u></b></td>
				<td style="text-align: left;" id="totalOPTION"></td>
				<td style="text-align: left;" id="totalOPTION_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_BTN_COUNT</u></b></td>
				<td style="text-align: left;" id="totalBTN"></td>
				<td style="text-align: left;" id="totalBTN_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_INPUT_KP_COUNT</u></b></td>
				<td style="text-align: left;" id="totalTYPE"></td>
				<td style="text-align: left;" id="totalTYPE_P"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TOTAL_SE_COUNT</u></b></td>
				<td style="text-align: left;" id="totalSE"></td>
				<td style="text-align: left;" id="totalSE_P"></td>
			</tr>
		</table>
	</div>
		<div id="analyseUserTable">
		<label style="font-size: 15px; color: #009fbc">Analysis User </label>
		<table>
			<thead>
				<tr>
					<th>ATTRIBUTE</th>
					<th>POSITIVE</th>
					<th>NEGETIVE</th>
				</tr>
			</thead>
			<tr>
				<td style="text-align: left;"><b><u>BROWSER ID</u></b></td>
				<td id="bidp" style="text-align: left;"></td>
				<td id="bidn" style="text-align: left;"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>TIME ZONE</u></b></td>
				<td id="tzp" style="text-align: left;"></td>
				<td id="tzn" style="text-align: left;"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>OS NAME</u></b></td>
				<td id="osp" style="text-align: left;"></td>
				<td id="osn" style="text-align: left;"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>EVENT SEQUENCE</u></b></td>
				<td id="esp" style="text-align: left;"></td>
				<td id="esn" style="text-align: left;"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>PROXY COUNT</u></b></td>
				<td id="pcp" style="text-align: left;"></td>
				<td id="pcn" style="text-align: left;"></td>
			</tr>
			<tr>
				<td style="text-align: left;"><b><u>USER LOCATION</u></b></td>
				<td id="ulp" style="text-align: left;"></td>
				<td id="uln" style="text-align: left;"></td>
			</tr>
		</table>
	</div>
	</div>
	
	<div style="width:80%; float: right;" id="rightDiv">
		<input type="hidden" id="lstAcTime">
		<div style="width:80%; float:left;">
			<table border='0' id="eventDetailsTableId">
			</table>
		</div>
		<div style="width:20%; float:right;" id="proxyDiv">
			<table border="0">
				<tr>
					<th>Proxy IP</th>
					<th>Country</th>
				</tr>
				<tbody id="proxyDetailsTableId">
				</tbody>
			</table>
		</div>
		<div style="width:100%; float: left;">
			<div style="width:50%; float: left;" >
			<!-- <input type="button" onclick="coordinateGraph()" value="Click"> -->
				<div id="coordinateGraphDiv">
				</div>
			</div>
			<div style="width:50%; float: left;">
				<div id="eventPercentageGraphDiv">
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>