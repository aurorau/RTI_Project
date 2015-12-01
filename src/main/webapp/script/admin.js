$(document).ready(function() {
	$('#clickDetails').hide();
	$("#resultNullId").hide();
	$("#proxyTableDiv").hide();
});

function getData() {
	 $.get('getDataForAdminPage', function(data) {
			if (data.status == 'success') {
				if(data.result != null){
					$("#resultNullId").hide();
					$('#clickDetails').show();
					
					$('#xForwardedFor').text(data.result.xForwardedFor);
					//$('#remoteIPAddrs').text(data.result.RemoteIPAddrs);
					$('#device').text(data.result.deviceName);
					$('#os').text(data.result.os);
					$('#browser').text(data.result.browser);
					$('#LCCount').text(data.result.leftClickCount);
					$('#RCCount').text(data.result.rightClickCount);
					$('#DCCount').text(data.result.dbClickCount);
					$('#KPCount').text(data.result.keyPressCount);
					$('#scrollCount').text(data.result.scrollCount);
					$('#touchCount').text(data.result.touchCount);
					$('#orientation').text(data.result.orientation);
					$('#eventType').text(data.result.eventType);
					$('#zoomOutCount').text(data.result.zoomOutCount);
					$('#zoomInCount').text(data.result.zoomInCount);
					$('#screenWidth').text(data.result.screenWidth);
					$('#screenHeight').text(data.result.screenHeight);
					$('#touchX').text(data.result.touchX);
					$('#touchY').text(data.result.touchY);
					$('#clickX').text(data.result.clickX);
					$('#clickY').text(data.result.clickY);
					//$('#referer').text(data.result.referer);
					$('#languages').text(data.result.languages);
					$('#location').text(data.result.location);
					$('#SCTime').text(data.result.creationTime);
					$('#sessionId').text(data.result.sessionId);
					$('#SLATime').text(data.result.lastAccessTime);
					$('#refererURI').text(data.result.refererURI);
/*					$('#proxyHostName').text(data.result.proxyHostName);
					$('#proxyAddrs').text(data.result.proxyAddrs);
					$('#proxyPort').text(data.result.proxyPort);*/
					$('#zoomCount').text(data.result.zoomCount);
					$('#dataSubmitTime').text(data.result.dataSubmitTime);
					
					if(data.result.proxyList != null){
						 $("#proxyTableDiv").show();
						 $('#proxyTable').html('');
						 //$('#proxyTable').append('<tr><td>IP ADDRESS</td><td colspan="20">LOCATION DETAILS</td></tr>');
						  $.each(data.result.proxyList, function(index, value) {
							  $('#proxyTable').append('<tr><td><label id='+index+'>'+value+'</label></td><td id=_'+index+'><button onclick="getLocationDetails('+index+');">Get Location Details</button></td></tr>');
						  });
					  }
					
					/*$('#proxyTable').append('<tr><td><input type="checkbox" id="'+value.id+'"></td><td>'+value.description+'</td><td><input type="text" id="evaluationCriteriaRemark'+value.id+'" value="'+value.remark+'"></td></tr>');*/
				} else {
					$("#resultNullId").show();
				}

			} else {
				alert(data.status);
			}
	    });
}
function resetForm() {
	$('#clickDetails').hide();
	$("#resultNullId").hide();
}

function getLocationDetails(ip) {
	 var ipAddress = $("#"+ip).text().trim();
	 console.log(ipAddress);
	 $.get('getLocationDetails',{
		 ipAddress:ipAddress
	 }, function(data) {
		 if(data.result != null){
			 $("#_"+ip).html('');
			 $("#_"+ip).append(data.result);
		 }
	 });
}