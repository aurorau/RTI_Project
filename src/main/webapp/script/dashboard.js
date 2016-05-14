$(document).ready(function() {

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
        success: function(data) {
        	$("#userCountDynamicTable").html(data);
	  		$("#userCountDynamicTable").displayTagAjax();
	  		removeTableText('userCountDynamicTable');
	  		getDeviceCount();
        }
	});
}

function getDeviceCount(){
	$.ajax({
        type: "GET",
        url: "getDeviceCount",
        success: function(data) {
        	$('#mobileCount').text(data.result.mobileCount);
        	$('#desktopCount').text(data.result.desktopCount);
        	$('#fraudCount').text(data.result.fraudCount);
        }
	});
}

function getUserDetailsBySessionId(sessionId, userId){
	var q =$('#addDetailsSearchId').val();
	q = '';
	var formdata = 'ajax=true&q='+q+'&sid='+sessionId;
	$.ajax({
        type: "GET",
        url: "getUserDetailsBySessionId",
        data: formdata,
        success: function(data) {
          $("#eventDetailsTableId").html(data);
       	  $("#eventDetailsTableId").displayTagAjax();
       	  getHeaderDetailsData(sessionId, userId);
        }
	});
}

function getHeaderDetailsData(sessionId, userId){
	q = '';
	var formdata = 'ajax=true&q='+q+'&sid='+sessionId;
	$.ajax({
        type: "GET",
        url: "analyseUserBySessionId",
        data: formdata,
        success: function(data) {
        	if(data.result.eventCount != null) {
        		var createdDate = '';
        		var created
        		$("#totalEvents").text(data.result.eventCount.TOTAL_COUNT);
        		$("#numOfSessionTimeout").text(data.result.eventCount.NUM_OF_SESSION_TIMEOUT);
        		$("#createdDate").text(getDate(data.result.eventCount.FIRST_ACCESS_TIME));
        		$("#createdTime").text(getTime(data.result.eventCount.FIRST_ACCESS_TIME));
        		$("#lastAccessDate").text(getDate(data.result.eventCount.LAST_ACCESS_TIME));
        		$("#lastAccessTime").text(getTime(data.result.eventCount.LAST_ACCESS_TIME));
        		$("#userStayingTime").text(data.result.eventCount.USER_STAYING_TIME+" min");
        		$("#userIdleTime").text(data.result.eventCount.MAX_IDLE_TIME+" min");
        		$("#avgTime").text(data.result.eventCount.AVG_TIME_TWO_EVENT+" sec");
        		
        		$("#deviceName").text(data.result.deviceType.deviceType);
        		$("#userId").text("User :"+userId);
        		
        		var userEventDesktop = data.result.deviceType.deviceTypeByEvents.desktopDevice*100;
        		var userEventMobile = data.result.deviceType.deviceTypeByEvents.mobileDevice*100;
        		var diamensionDesktop = data.result.deviceType.deviceTypeByDimention.desktopDevice*100;
        		var diamensionMobile = data.result.deviceType.deviceTypeByDimention.mobileDevice*100;
        		var viewDesktop = data.result.deviceType.deviceTypeByOrientation.desktopDevice*100;
        		var viewMobile = data.result.deviceType.deviceTypeByOrientation.mobileDevice*100;
        		
        		deviceAnalysisBarChart.setData([{ "y": "by User Events", "a": userEventDesktop, "b": userEventMobile },
        		                                { "y": "by Dimention", "a": diamensionDesktop, "b": diamensionMobile },
        		                                { "y": "by Oriantation", "a": viewDesktop, "b": viewMobile}]);
        		
        		
        		var bowserIdPositive = data.result.userStatus.BROWSER_ID.POSITIVE;
        		var bowserIdNegeitive = data.result.userStatus.BROWSER_ID.NEGETIVE;
        		var OSNamePositive = data.result.userStatus.OS_NAME.POSITIVE;
        		var OSNameNegetive = data.result.userStatus.OS_NAME.NEGETIVE;
        		var proxyPositive = data.result.userStatus.PROXY_COUNT.POSITIVE;
        		var proxyNegetive = data.result.userStatus.PROXY_COUNT.NEGETIVE;
        		var timeZonePositive = data.result.userStatus.TIME_ZONE.POSITIVE;
        		var timeZoneNegetive = data.result.userStatus.TIME_ZONE.NEGETIVE;
        		var eventSequencePositive = data.result.userStatus.EVENT_SEQUENCE.POSITIVE;
        		var eventSequenceNegetive = data.result.userStatus.EVENT_SEQUENCE.NEGETIVE;
        		var userLocationPositive = data.result.userStatus.USER_LOCATION.POSITIVE;
        		var userLocationNegetive = data.result.userStatus.USER_LOCATION.NEGETIVE;
        		
        		userAnalysisBarChart.setData(  [{ "y": "Browser", "a": bowserIdPositive, "b": bowserIdNegeitive },
        		                                { "y": "Time Zone", "a": timeZonePositive, "b": timeZoneNegetive },
        		                                { "y": "OS", "a": OSNamePositive, "b": proxyNegetive},
        		                                { "y": "Event", "a": eventSequencePositive, "b": eventSequenceNegetive},
        		                                { "y": "Proxy", "a": proxyPositive, "b": proxyNegetive},
        		                                { "y": "Location", "a": userLocationPositive, "b": userLocationNegetive}]);
        	}
        }
	});
}

var deviceAnalysisBarChart =  Morris.Bar({
	  element: 'device-analysis-graph',
	  data: [
	        // {y:null}
	    { y: 'by User Events', a: 100, b: 90 },
	    { y: 'by Dimention', a: 75,  b: 65 },
	    { y: 'by Oriantation', a: 50,  b: 40 }
	  ],
	  xkey: 'y',
	  ykeys: ['a', 'b'],
	  labels: ['Desktop', 'Mobile'],
	  barColors:['#9b59b6','#3498db']
});

var userAnalysisBarChart = Morris.Bar({
	  element: 'user-analysis-graph',
	  data: [
	    { y: 'Browser', a: 100, b: 90 },
	    { y: 'Time Zone', a: 75,  b: 65 },
	    { y: 'OS', a: 50,  b: 40 },
	    { y: 'Event', a: 50,  b: 40 },
	    { y: 'Proxy', a: 50,  b: 40 },
	    { y: 'Location', a: 50,  b: 40 }
	  ],
	  xkey: 'y',
	  ykeys: ['a', 'b'],
	  labels: ['Positive', 'Negative'],
	  barColors:['#5CB85C','#D9534F']
	});

function getDate(val){
	return val.substring(0, val.indexOf(" "));
}
function getTime(val){
	return val.substring(val.indexOf(" ")+1);
}