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
	<div style="width:15%; float: left;">
	<label style="font-size: 15px; color: #009fbc">Current users : <span id=numberOfCurrentUsers style="font-size: 15px;"></span></label>
		<div id="userDetailsTableDiv">
			<label style="font-size: 15px; color: #009fbc">Number of Events : <span id=numberOfEvents style="font-size: 15px;"></span></label>
			<table id="userDetailsTable">
			</table>
		</div>
	</div>
	
	<div style="width:85%; float: right;" id="rightDiv">
		
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
</div>
</body>
</html>