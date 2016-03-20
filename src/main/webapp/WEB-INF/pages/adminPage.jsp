<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="script/jquery.min.js"></script>
<script src="script/admin.js"></script>
<script src="script/jquery.displaytag-ajax-1.2.js"></script>
<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/style.css" rel="stylesheet">
<title>Admin Panel</title>
</head>

<body>

<h2 style="font-size: 20px;">ADMIN PAGE</h2>
<button onclick="getCurrentUserCount()" style="font-size: 12px;">Get Current User Count</button>
<div>
	<div style="width:20%; float: left;">
	<label style="font-size: 15px; color: #009fbc">Current users : <span id=numberOfCurrentUsers style="font-size: 15px;"></span></label>
		<div id="userDetailsTableDiv">
			<label style="font-size: 15px; color: #009fbc">Number of Events : <span id=numberOfEvents style="font-size: 15px;"></span></label>
			<table id="userDetailsTable">
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
			<td style="text-align: left;"><b><u>FIRST_ACCESS_TIME</u></b></td>
			<td id="firstAccessTime" style="text-align: left;"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>LAST_ACCESS_TIME</u></b></td>
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
			<td style="text-align: left;"><b><u>TOTAL_EVENT_COUNT</u></b></td>
			<td id="totalEvents" style="text-align: left;"></td>
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
			<td style="text-align: left;"><b><u>TOTAL_IMG_COUNT</u></b></td>
			<td style="text-align: left;" id="totalIMG"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_PARA_COUNT</u></b></td>
			<td style="text-align: left;" id="totalPARA"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_INPUT_COUNT</u></b></td>
			<td style="text-align: left;" id="totalINPUT"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_A_COUNT</u></b></td>
			<td style="text-align: left;" id="totalA"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_SELECT_COUNT</u></b></td>
			<td style="text-align: left;" id="totalSELECT"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_OPTION_COUNT</u></b></td>
			<td style="text-align: left;" id="totalOPTION"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_BTN_COUNT</u></b></td>
			<td style="text-align: left;" id="totalBTN"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_TYPE_COUNT</u></b></td>
			<td style="text-align: left;" id="totalTYPE"></td>
		</tr>
	</table>
</div>

	<div id="analyseUserTable">
		<label style="font-size: 15px; color: #009fbc">Analyse User </label>
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

</div>`

</body>
</html>