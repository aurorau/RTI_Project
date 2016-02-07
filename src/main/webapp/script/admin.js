$(document).ready(function() {
	$('#userDetailsTable').hide();
	$("#proxyDiv").hide();
	$("#rightDiv").hide();
	getCurrentUserCount();
	setInterval(function(){ 
		//getCurrentUserCount();
	},30000);
});

function getCurrentUserCount () {
	var allUsers = 0;
	var desktoUserCount = 0;
	var mobileUserCount = 0;
	$.ajax({
        type: "GET",
        url: "getCurrentUserCount",
        //data: formdata,
        success: function(data) {
        	if (data.status == 'success') {
					if(data.result != null){
						allUsers = data.result.length;
						$('#numberOfCurrentUsers').text(allUsers);
						$('#userDetailsTable').html('');
						$('#userDetailsTable').show();
						 $.each(data.result, function(index, value) {
							 index = index+1;
							 $('#userDetailsTable').append('<tr><td><label>User :'+index+'</label></td><td id=_'+index+'><button onclick="getUserDetails('+value.sid+');">Get Details</button></td></tr>');
						 });
					  }
        	}
        }
	});
}

function getUserDetails(sid) {
	
	var eventCount = 0;
	var numOfProxies = 0;
	
	$.ajax({
        type: "GET",
        url: "getUserDetailsBySessionId",
        data:"sid="+sid,
        success: function(data) {
        	if(data.result != null){
        		eventCount = data.result.length;
        		$("#rightDiv").show();
        		$('#eventDetailsTableId').html('');
        		
        		$('#numberOfEvents').text(eventCount);
        		$.each(data.result, function(index, value) {
        			numOfProxies = value.pid.length;
        			index = index+1;
        			
					$('#eventDetailsTableId').append("<tr>" +
							"<td><label>"+index+"</label></td>" +
							"<td><label>"+value.eventName+"</label></td>" +
							"<td><label>"+value.eventTriggeredTime+"</label></td>" +
							"<td><label>("+value.coordinateX+","+value.coordinateY+")</label></td>" +
							"<td><label>("+value.screenWidth+","+value.screenHeight+")</label></td>" +
							"<td><label>"+value.orientation+"</label></td>" +
							"<td><label>"+value.deviceName+"</label></td>" +
							"<td><label>"+value.browserName+"</label></td>" +
							"<td><label>"+value.browserVersion+"</label></td>" +
							"<td><label>"+value.userAgentId+"</label></td>" +
							"<td align='center'><label>"+numOfProxies+"</label> " +
									"<button id="+index+" onclick='getLocationDetails("+value.bid+")'>More</button>" +
							"</td></tr>");
					
					if(numOfProxies == 0) {
						$('#'+index).attr('disabled', 'disabled');
					}
        		});
        		
        	}
        }
    });
}
function resetForm() {
	$('#clickDetails').hide();
	$("#resultNullId").hide();
}

function getLocationDetails(bid) {
	 $.get('getProxyDetails',{
		 bid:bid
	 }, function(data) {
		 if(data.result != null){
     			$("#proxyDiv").show();
     			$('#proxyDetailsTableId').html('');
			 $.each(data.result, function(index, value) {
				 //index = index+1;
				 $('#proxyDetailsTableId').append("<tr>" +
						 	//"<td><label>"+index+"</label></td>" +
							"<td><label>"+value.ip+"</label></td>" +
							"<td><label>"+value.countryName+"</label></td></tr>");
			 });
		 }
	 });
}