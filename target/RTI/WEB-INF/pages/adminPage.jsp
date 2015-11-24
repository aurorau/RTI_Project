<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="script/jquery.min.js"></script>
<script src="script/admin.js"></script>
<html>
	<head>
		<title>Admin Panel</title>
	</head>
	
	<body>
		<h2>ADMIN PAGE</h2>
		<button onclick="getData()">Get Data</button><br>
		
		<h4>--User Details--</h4>
		<span>Languages :</span><label id="languages"></label><br>
		<span>Location :</span><label id="location"></label><br>
		
		<h4>--Device Details--</h4>
		<span>IP Address :</span><label id="ipAddress"></label><br>
		<span>Device :</span><label id="device"></label><br>
		<span>OS :</span><label id="os"></label><br>
		<span>Browser :</span><label id="browser"></label><br>
		
		<h4>--Click Details--</h4>
		<span>RefererURI :</span><label id="refererURI"></label><br>
		<span>Referer :</span><label id="referer"></label><br>
		<span>LeftClick Count :</span><label id="LCCount"></label><br>
		<span>RightClick Count :</span><label id="RCCount"></label><br>
		<span>DoubleClick Count :</span><label id="DCCount"></label><br>
		<span>KeyPress Count:</span><label id="KPCount"></label><br>
		<span>Scroll Event :</span><label id="scrollEvent"></label><br>
		<span>Touch Count :</span><label id="touchCount"></label><br>
		<span>Orientation :</span><label id="orientation"></label><br>
		<span>Event Type :</span><label id="eventType"></label><br>
		<span>Zoom Count :</span><label id="zoomCount"></label><br>
		<span>Zoom Event :</span><label id="zoomEvent"></label><br>
		<span>Screen Width :</span><label id="screenWidth"></label><br>
		<span>Screen Height :</span><label id="screenHeight"></label><br>
		<span>Touch-X :</span><label id="touchX"></label><br>
		<span>Touch-Y :</span><label id="touchY"></label><br>
		<span>Click-X :</span><label id="clickX"></label><br>
		<span>Click-Y :</span><label id="clickY"></label><br>
		
		<h4>--Session Details--</h4>
		<span>Session Creation Time :</span><label id="SCTime"></label><br>
		<span>Session Id :</span><label id="sessionId"></label><br>
		<span>Session LastAccess Time :</span><label id="SLATime"></label><br>
	</body>
</html>