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
<button onclick="getCurrentUserCount()">Get Current User Count</button>
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
<!-- 		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">IMG_COUNT_TS</label></td>
			<td  style="text-align: left;" id="imgTS"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">IMG_COUNT_TM</label></td>
			<td style="text-align: left;" id="imgTM"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">IMG_COUNT_TZE</label></td>
			<td style="text-align: left;" id="imgTZE"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">IMG_COUNT_STZE</label></td>
			<td style="text-align: left;" id="imgSTZE"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">IMG_COUNT_LC</label></td>
			<td style="text-align: left;" id="imgLC"></td>
		</tr> -->
		<tr>
			<td style="text-align: left;"><b><u>TOTAL_PARA_COUNT</u></b></td>
			<td style="text-align: left;" id="totalPARA"></td>
		</tr>
<!-- 		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">P_COUNT_TS</label></td>
			<td  style="text-align: left;" id="pTS"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">P_COUNT_TM</label></td>
			<td style="text-align: left;" id="pTM"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">P_COUNT_TZE</label></td>
			<td style="text-align: left;" id="pTZE"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">P_COUNT_STZE</label></td>
			<td style="text-align: left;" id="pSTZE"></td>
		</tr>
		<tr>
			<td style="text-align: left;"><label style="margin-left: 10px; margin-top: 5px;">P_COUNT_LC</label></td>
			<td style="text-align: left;" id="pLC"></td>
		</tr> -->
	</table>
</div>
</div>`

</body>
</html>