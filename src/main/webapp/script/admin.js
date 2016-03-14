var USER_LAST_ACCESS_TIME = -1;

$(document).ready(function() {
	$('#userDetailsTable').hide();
	$("#proxyDiv").hide();
	$("#rightDiv").hide();
	$("#analyseTableId").hide();
	$("#analyseUserTable").hide();
	getCurrentUserCount();
	setInterval(function(){ 
		getCurrentUserCount();
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
						if(allUsers > 0) {
							$('#numberOfCurrentUsers').text(allUsers);
							$('#userDetailsTable').html('');
							$('#userDetailsTable').show();
				        	//$("#analyseUserTable").show();
							 $.each(data.result, function(index, value) {
								 index = index+1;
								 $('#userDetailsTable').append('<tr><td><label>User :'+index+'</label></td><td id=_'+index+'><button onclick="getUserDetails('+value.sid+');">Get Details</button></td></tr>');
							 });
						} else {
					        $("#rightDiv").hide();
					        $("#analyseTableId").hide();
					        $('#numberOfEvents').text('');
					        $('#userDetailsTable').hide();
					        $('#numberOfCurrentUsers').text('0');
				        	$("#analyseUserTable").hide();
						}

					  }
        	}
        }
	});
}

function analyseUser(sid){
	q = '';
	var formdata = 'ajax=true&q='+q+'&sid='+sid;
	$.ajax({
        type: "GET",
        url: "analyseUserBySessionId",
        data: formdata,
        success: function(data) {
        	
        	$("#analyseUserTable").show();
        	
        	if(data.result.eventCount != null) {
        		$("#analyseTableId").show();
        		$("#device").text(": "+data.result.deviceType);
        		$("#firstAccessTime").text(": "+data.result.eventCount.FIRST_ACCESS_TIME);
        		$("#lastAccessTime").text(": "+data.result.eventCount.LAST_ACCESS_TIME);
        		$("#totalEvents").text(": "+data.result.eventCount.TOTAL_COUNT);
        		$("#totalRF").text(": "+data.result.eventCount.USER_EVENT_COUNT.RF_COUNT);
        		$("#userStayingTime").text(": "+data.result.eventCount.USER_STAYING_TIME+" min");
        		$("#userIdleTime").text(": "+data.result.eventCount.MAX_IDLE_TIME+" min");
        		$("#avgTime").text(": "+data.result.eventCount.AVG_TIME_TWO_EVENT+" sec");
        		$("#totalIMG").text(": "+data.result.eventCount.USER_EVENT_COUNT.TOTAL_IMG_COUNT);
        		$("#imgTS").text(": "+data.result.eventCount.USER_EVENT_COUNT.IMG_COUNT_TS);
        		$("#imgTM").text(": "+data.result.eventCount.USER_EVENT_COUNT.IMG_COUNT_TM);
        		$("#imgTZE").text(": "+data.result.eventCount.USER_EVENT_COUNT.IMG_COUNT_TZE);
        		$("#imgSTZE").text(": "+data.result.eventCount.USER_EVENT_COUNT.IMG_COUNT_STZE);
        		$("#imgLC").text(": "+data.result.eventCount.USER_EVENT_COUNT.IMG_COUNT_LC);
        		$("#totalPARA").text(": "+data.result.eventCount.USER_EVENT_COUNT.TOTAL_P_COUNT);
        		$("#pTS").text(": "+data.result.eventCount.USER_EVENT_COUNT.P_COUNT_TS);
        		$("#pTM").text(": "+data.result.eventCount.USER_EVENT_COUNT.P_COUNT_TM);
        		$("#pTZE").text(": "+data.result.eventCount.USER_EVENT_COUNT.P_COUNT_TZE);
        		$("#pSTZE").text(": "+data.result.eventCount.USER_EVENT_COUNT.P_COUNT_STZE);
        		$("#pLC").text(": "+data.result.eventCount.USER_EVENT_COUNT.P_COUNT_LC);
        		$("#numOfSessionTimeout").text(": "+data.result.eventCount.NUM_OF_SESSION_TIMEOUT);

        		
        		$("#bidp").text(": "+data.result.userStatus.BROWSER_ID.POSITIVE);
        		$("#bidn").text(": "+data.result.userStatus.BROWSER_ID.NEGETIVE);
        		$("#osp").text(": "+data.result.userStatus.OS_NAME.POSITIVE);
        		$("#osn").text(": "+data.result.userStatus.OS_NAME.NEGETIVE);
        		$("#pcp").text(": "+data.result.userStatus.PROXY_COUNT.POSITIVE);
        		$("#pcn").text(": "+data.result.userStatus.PROXY_COUNT.NEGETIVE);
        		$("#tzp").text(": "+data.result.userStatus.TIME_ZONE.POSITIVE);
        		$("#tzn").text(": "+data.result.userStatus.TIME_ZONE.NEGETIVE);
        		$("#esp").text(": "+data.result.userStatus.EVENT_SEQUENCE.POSITIVE);
        		$("#esn").text(": "+data.result.userStatus.EVENT_SEQUENCE.NEGETIVE);
        		$("#ulp").text(": "+data.result.userStatus.USER_LOCATION.POSITIVE);
        		$("#uln").text(": "+data.result.userStatus.USER_LOCATION.NEGETIVE);
        	}
        	
        	
/*          $("#rightDiv").show();
          $("#proxyDiv").hide();
          $("#eventDetailsTableId").html(data);
       	  $("#eventDetailsTableId").displayTagAjax();
       	 // removeTableText('eventDetailsTableId');
*/        }
	});
}

function getUserDetails(sid) {
	
	var q =$('#addDetailsSearchId').val();
	q = '';
	var formdata = 'ajax=true&q='+q+'&sid='+sid;
	$.ajax({
        type: "GET",
        url: "getUserDetailsBySessionId",
        data: formdata,
        success: function(data) {
          $("#rightDiv").show();
          $("#proxyDiv").hide();
          $("#eventDetailsTableId").html(data);
       	  $("#eventDetailsTableId").displayTagAjax();
       	  analyseUser(sid);
       	 // removeTableText('eventDetailsTableId');
        }
	});
	
	
/*	var eventCount = 0;
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
    });*/
}
function resetForm() {
	$('#clickDetails').hide();
	$("#resultNullId").hide();
}

function getLocationDetails(bid) {
	 $.get('getProxyDetails',{
		 bid:bid
	 }, function(data) {
		 if(data.result.length > 0){
     			$("#proxyDiv").show();
     			$('#proxyDetailsTableId').html('');
			 $.each(data.result, function(index, value) {
				 //index = index+1;
				 $('#proxyDetailsTableId').append("<tr>" +
					 	//"<td><label>"+index+"</label></td>" +
						"<td>"+value.ip+"</td>" +
						"<td>"+value.countryName+"</td></tr>"); 
			 });
		 }
	 });
}

var removeTableText = function(id){
	var textTable = $('#'+escapeStr(id)+' .pagebanner').text();
	if(textTable == 'No items found.'){
		$('#'+escapeStr(id)+' .pagebanner').hide();
	}else{
		$('#'+escapeStr(id)+' .pagebanner').show();
	}
}
function escapeStr(str) 
{
  if (str)
      return str.replace(/([ #;?%&,.+*~\':"!^$[\]()=>|\/@])/g,'\\$1');      

  return str;
}