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
	<button onclick="getData()">Get Data</button>
	<button onclick="resetForm()">Reset</button>
	<br>


	<!-- 	<div>
		<div style="color: red;">ee</div>
		<div style="color: green;">ff</div>
	</div> -->
	<div id=clickDetails>
		<h4>--User Details--</h4>
		<span>Data Submit Time :</span><label id="dataSubmitTime"></label><br>
		<span>Languages :</span><label id="languages"></label><br> <span>Location
			:</span><label id="location"></label><br> <span>JS Latitude :</span><label
			id="jsLatitude"></label><br> <span>JS Longitude :</span><label
			id="jsLongitude"></label><br> <span>User Agent ID :</span><label
			id="userAgentId"></label><br>

		<h4>--Device Details--</h4>
		<span>Device :</span><label id="device"></label><br> <span>OS
			:</span><label id="os"></label><br> <span>OS Name :</span><label
			id="osName"></label><br> <span>OS Manufacture :</span><label
			id="osManufacture"></label><br>

		<h4>--Browser Details--</h4>
		<span>Browser :</span><label id="browser"></label><br> <span>BrowserName
			:</span><label id="browserName"></label><br> <span>BrowserType :</span><label
			id="browserType"></label><br> <span>BrowserVersion :</span><label
			id="browserVersion"></label><br>

		<h4>--Proxy Details--</h4>
		<span>X-Forwarded-For :</span><label id="xForwardedFor"></label><br>
		<div id="proxyTableDiv">
			<table id="proxyTable" border="0">
			</table>
		</div>
		<h4>--Click Details--</h4>
		<span>RefererURI :</span><label id="refererURI"></label><br>
		<!-- <span>Referer :</span><label id="referer"></label><br> -->
		<span>LeftClick Count :</span><label id="LCCount"></label><br> <span>RightClick
			Count :</span><label id="RCCount"></label><br> <span>DoubleClick
			Count :</span><label id="DCCount"></label><br> <span>KeyPress
			Count:</span><label id="KPCount"></label><br> <span>Scroll Count
			:</span><label id="scrollCount"></label><br> <span>Touch Count :</span><label
			id="touchCount"></label><br> <span>Orientation :</span><label
			id="orientation"></label><br> <span>Event Type :</span><label
			id="eventType"></label><br> <span>Zoom Count :</span><label
			id="zoomCount"></label><br> <span>Zoom Out Count :</span><label
			id="zoomOutCount"></label><br> <span>Zoom In Count :</span><label
			id="zoomInCount"></label><br> <span>Screen Width :</span><label
			id="screenWidth"></label><br> <span>Screen Height :</span><label
			id="screenHeight"></label><br> <span>Touch-X :</span><label
			id="touchX"></label><br> <span>Touch-Y :</span><label
			id="touchY"></label><br> <span>Click-X :</span><label
			id="clickX"></label><br> <span>Click-Y :</span><label
			id="clickY"></label><br>

		<h4>--Session Details--</h4>
		<span>Session Creation Time :</span><label id="SCTime"></label><br>
		<span>Session Id :</span><label id="sessionId"></label><br> <span>Session
			LastAccess Time :</span><label id="SLATime"></label><br>
	</div>
	<div id="resultNullId">
		<h4>No click details to display</h4>
	</div>
</body>
</html>