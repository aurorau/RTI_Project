<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="script/jquery.min.js"></script>
<script src="script/admin.js"></script>
<html>
<head>
<title>Admin Panel</title>
</head>

<body>

<h2>ADMIN PAGE</h2>

<div>
	<div style="width:15%; float: left;">
		<table>
			<tr>
				<th align='left'><h3>Current users:</h3></th>
				<th><label id="numberOfCurrentUsers"></label></label></th>
			</tr>
		</table>
		<br>
		<div id="userDetailsTableDiv">
			<table id="userDetailsTable">
			</table>
		</div>
	</div>
	
	<div style="width:85%; float: right;" id="rightDiv">
		
		<div style="width:80%; float:left;">
		<table>
			<tr>
				<th><h3>Number of Events :</h3></th>
				<th><label id="numberOfEvents"></label></label></th>
			</tr>
		</table>
		<br>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Event</th>
				<th>Time</th>
				<th>Coordinate(X,Y)</th>
				<th>Screen Size(W,H)</th>
				<th>view</th>
				<th>Device</th>
				<th>Browser</th>
				<th>Browser Version</th>
				<th>User Agent</th>
				<th>Number of Proxies</th>
			</tr>
			<tbody id="eventDetailsTableId">
			</tbody>
		</table>
		<br>
		</div>
		<div style="width:20%; float:right;" id="proxyDiv">
			<table>
				<tr>
					<th><h3>Proxy Details</h3></th>
				</tr>
			</table>
			<br>
			<table border="1">
				<tr>
					<th>Proxy IP</th>
					<th>Country</th>
				</tr>
				<tbody id="proxyDetailsTableId">
				</tbody>
			</table>
			<br>
		</div>
	</div>
</div>






	
</body>
</html>