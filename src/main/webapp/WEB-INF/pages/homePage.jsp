<script src="script/jquery.min.js"></script>
<script src="script/homePage.js"></script>
<script language="JavaScript"
	src="http://www.geoplugin.net/javascript.gp" type="text/javascript"></script>
<script language="javascript" src="http://j.maxmind.com/app/geoip.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>RTI Home</title>
</head>
<body bgcolor="red" style="width: 2000px;">
	<div style="height: 200px; width: 700px; background-color: green">
		<h1>RTI</h1>
		<button id="getUserDataBtnId" onclick="getUserData()">Click
			me</button>
	</div>

	<p>
		<button onclick="geoFindMe()">Show my location</button>
	</p>
	<div id="out"></div>

	<div id="result"></div>

	<div>Footer</div>
	<table>
		<tr>
			<td>Table</td>
		</tr>
	</table>
</body>
</html>