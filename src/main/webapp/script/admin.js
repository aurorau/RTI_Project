$(document).ready(function() {

});

function getData() {
	
	 $.get('getDataForAdminPage', function(data) {
			if (data.status == 'success') {
				$('#ipAddress').text(data.result.IP);
				$('#device').text(data.result.Device);
				$('#os').text(data.result.OS);
				$('#browser').text(data.result.Browser);
				$('#LCCount').text(data.result.LeftClickCount);
				$('#RCCount').text(data.result.RightClickCount);
				$('#DCCount').text(data.result.DoubleClickCount);
				$('#KPCount').text(data.result.KeyPressCount);
				$('#scrollEvent').text(data.result.ScrollEvent);
				$('#touchCount').text(data.result.TouchCount);
				$('#orientation').text(data.result.Orientation);
				$('#eventType').text(data.result.EventType);
				$('#zoomCount').text(data.result.ZoomCount);
				$('#zoomEvent').text(data.result.ZoomEvent);
				$('#screenWidth').text(data.result.ScreenWidth);
				$('#screenHeight').text(data.result.ScreenHeight);
				$('#touchX').text(data.result.TouchX);
				$('#touchY').text(data.result.TouchY);
				$('#clickX').text(data.result.ClickX);
				$('#clickY').text(data.result.ClickY);
				$('#referer').text(data.result.Referer);
				$('#languages').text(data.result.Languages);
				$('#location').text(data.result.Location);
				$('#SCTime').text(data.result.SessionCreationTime);
				$('#sessionId').text(data.result.SessionId);
				$('#SLATime').text(data.result.LastAccessTime);
				$('#refererURI').text(data.result.RefererURI);
			} else {
				console.log(data.status);
			}
	    });
	
	
	//$('#ipAddress').text($('#hiddenIpAddress').val());
}