
var touchCount=0;
var leftClickCount=0;
var rightClickCount=0;
var zoomCount=0;
var scrollCount=0;
var keyPressCount=0;
var scrollEvent=false;
var tapsEvent=false;
var dbClickCount=0;
var orientation;
var eventType;
var event=null;
var screenWidth = 0;
var screenHeight = 0;
var zoomEvent=null;
var touchX=0;
var touchY=0;
var clickX=0;
var clickY=0;
var eventStartTime=0;
var eventEndTime=0;

$(document).ready(function() {
	$('#searchKeyId').val(geoplugin_countryName()+'/'+geoplugin_region());
	/*setInterval(function(){ 
		//$("#getUserDataBtnId").click();
	},10000);*/

	$('#gallaryLinkId').on('click', function(){
		//alert("DONE");
	});
	screenWidth = screen.width;
	screenHeight = screen.height;
});

function getLeftRightClick(e) {
	if(e.which == 1) {
		leftClickCount = leftClickCount+1;
		clickX = e.screenX;
		clickY = e.screenY;
	}
	if(e.which == 2) {
		
	}
	if(e.which == 3) {
		rightClickCount = rightClickCount+1;
	}
}

$(document).dblclick(function(e){
	dbClickCount = dbClickCount+1;

});

$(document).on('touchstart', function(e){
	event = e;
	touchCount = touchCount+1;
	eventType = event.type;
	touchX = e.originalEvent.touches[0].pageX;
	touchY = e.originalEvent.touches[0].pageY;
	//resizeTest();
});

function resizeTest() {
	if(screenWidth > touchX || screenHeight > touchY) {
		zoomEvent = 'zoomOut'
	}
	if(screenWidth < touchX || screenHeight < touchY) {
		zoomEvent = 'zoomIn'
	}
	else {
		zoomEvent = 'NoZooming'
	}
}

$(document).on('click', function(e){
	if(event == null && e.type == 'click') {
		eventType = e.type;
		getLeftRightClick(e);
	}
});

$(window).on("orientationchange",function(event){
	  orientation = event.orientation;
});

/*$(document).scroll(function(e){
	scrollEvent = true;
	console.log(e.getTop);
	console.log("Scroll Event :"+scrollEvent);
});*/

function scrolled() {
	console.log("DONE");
	console.log("offSet :"+$('#banner').offset().top);
    //do by scroll start
    $(this).off('scroll')[0].setTimeout(function(){
        //do by scroll end
        $(this).on('scroll',scrolled);
    }, 1000)
}
$(window).on('scroll',scrolled);

$(document).keypress(function(e) {
	keyPressCount = keyPressCount+1;
	console.log("Key press :"+e.which);
});

var getCurrentTime=function() {
	var d = new Date();
	var time = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
	return time;
}

function sendData() {
    setTimeout(function(){
    	sendDataToController();
    }, 500);
}

function sendDataToController() {
	var userAgent = navigator.userAgent;
	var innerWidth = $(window).innerWidth();
	//var screenWidth = screen.width;
	
	console.log("innerWidth :"+innerWidth);
	console.log("screenWidth :"+screenWidth);
	
	console.log("Referer :"+document.referrer);
	console.log("clickY :"+clickY);
	//getLocation();
	
/*	console.log("Country Name : " + geoplugin_countryName());
	console.log("Country Code :"+geoplugin_countryCode());
	console.log("Continent Code :"+geoplugin_continentCode());
	console.log("Region :"+geoplugin_region());
	console.log("City :"+geoplugin_city());
	console.log("Latitude :"+geoplugin_latitude());
	console.log("Longitude :"+geoplugin_longitude());
	console.log("Currency Symbol :"+geoplugin_currencySymbol());
	console.log("Currency Code :"+geoplugin_currencyCode());*/
	
	var location = $('#searchKeyId').val();
	var languages = navigator.language;
	var referer = document.referrer;
	
	$.post('getHeaderString', {
		userAgent : userAgent,
		location : location,
		touchCount : touchCount,
		leftClickCount : leftClickCount,
		zoomCount : zoomCount,
		scrollCount : scrollCount,
		keyPressCount : keyPressCount,
		scrollEvent : scrollEvent,
		tapsEvent : tapsEvent,
		orientation : orientation,
		rightClickCount : rightClickCount,
		dbClickCount : dbClickCount,
		eventType : eventType,
		zoomEvent : zoomEvent,
		screenWidth : screenWidth,
		touchX : touchX,
		touchY : touchY,
		clickX : clickX,
		clickY : clickY,
		location : location,
		languages : languages,
		referer : referer,
		screenHeight : screenHeight
	}, function(data) {
		if (data.status == 'success') {
			console.log("IP Address :"+data.result.IP);
			console.log("DONE");
			$('#hiddenIpAddress').val(data.result.IP);
		} else {
			console.log(data.status);
		}
	});
}

function getLocation() {
	navigator.geolocation.getCurrentPosition(foundLocation, noLocation);
    function foundLocation(position) {
    	var lat = position.coords.latitude;
    	var long = position.coords.longitude;
    	console.log(position);
    	console.log("LAT : "+lat +", LONG : "+long);
    }

    function noLocation() {
    	console.log('Could not find location');
    }
}