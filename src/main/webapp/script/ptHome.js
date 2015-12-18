
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
var TEVENT=null;
var screenWidth = 0;
var screenHeight = 0;
var zoomEvent=null;
var touchX=0;
var touchY=0;
var clickX=0;
var clickY=0;
var eventStartTime=0;
var eventEndTime=0;
var previouseScrollTime = 0;
var previousKey = 'NotYet';
var previouseLeftClickTime = 0;
var globalDBClickEvent = 0;
var previouseScreenWidth = 0;
var previouseScreenHeight = 0;
var zoomInCount = 0;
var zoomOutCount = 0;
var previouseTouchTime = 0;
var previousePosition = 0;

$(document).ready(function() {
	needed();
	previouseScreenWidth = screen.width;
	previouseScreenHeight = screen.height;
	/*setInterval(function(){ 
		//$("#getUserDataBtnId").click();
	},10000);*/

/*	$('#gallaryLinkId').on('click', function(){
		//alert("DONE");
	});*/
	
});

function needed(){
	$('#searchKeyId').val(geoplugin_countryName()+'/'+geoplugin_region());
	screenWidth = screen.width;
	screenHeight = screen.height;
}

function getLeftRightClick(e) {
	if(e.which == 1) {
		leftClickCount = leftClickCount+1;
		clickX = e.screenX;
		clickY = e.screenY;
		var coordinate = clickX+"_"+clickY;
		
	}
	if(e.which == 2) {
		
	}
	if(e.which == 3 || e.which == null) {
		rightClickCount = rightClickCount+1;
	}
}
/*$(document).on("pagecreate",function(){
	catchTapFunction();
	catchScrollEvent();
});*/

/*function catchScrollEvent () {
	$(document).on("scrollstop",function(){
		scrollCount = scrollCount+1;
	});
}*/

$(document).dblclick(function(e){
	dbClickCount = dbClickCount+1;
	leftClickCount = leftClickCount-2;

});

$(document).on('touchstart', function(e){
	TEVENT = e;
	
	var currentTouchTime = Date.now();
	var touchTimeDiffer = (currentTouchTime - previouseTouchTime);

	if(touchTimeDiffer < 400) {
		zoomCount = zoomCount+1;
		touchCount = touchCount-1;
		scrollCount = scrollCount-1;
	} else {
		touchCount = touchCount+1;
	}
	previouseTouchTime = currentTouchTime;
	
	eventType = event.type;
/*	touchX = e.originalEvent.touches[0].pageX;
	touchY = e.originalEvent.touches[0].pageY;*/
	
	touchX = e.originalEvent.touches[0].clientX;
	touchY = e.originalEvent.touches[0].clientY;
});

$(document).on('touchmove', function(e){
	//alert("DONE");
});

$(document).on('mousedown',function(e){
	if(TEVENT == null) {
		eventType = e.type;
		getLeftRightClick(e);
	} else{
		TEVENT = null;
	}
});

$(window).on("orientationchange",function(event){
	  orientation = event.orientation;
});

function scrolled() {
	var currentScrollTime = Date.now();
	
	var timeDiffer= (currentScrollTime-previouseScrollTime);
	if(timeDiffer > 100) {
		scrollCount = scrollCount+1;
	}
	previouseScrollTime = currentScrollTime;
}

$(window).on('scroll',scrolled);

$(document).keypress(function(e) {
	if(e.which == 45) { //firefox
		zoomInCount = zoomInCount+1;
		zoomCount = zoomCount+1;
	}
	if( e.which == 43) {//firefox
		zoomOutCount = zoomOutCount+1;
		zoomCount = zoomCount+1;
	}

	if(previousKey == 'NotYet') {
		keyPressCount = keyPressCount+1;
		previousKey = e.which;
	} else if (e.which != previousKey) {
		keyPressCount = keyPressCount+1;
	}
});

var getCurrentTime=function() {
	var d = new Date();
	/*	var time = d.getDate() + ":"+d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
	return time;*/
	  var hours = d.getHours();
	  var minutes = d.getMinutes();
	  var seconds = d.getSeconds();
	  var ampm = hours >= 12 ? 'pm' : 'am';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ':'+seconds + ' ' + ampm;
	  return strTime;
}

function sendData() {
    setTimeout(function(){
    	sendDataToController();
    }, 500);
}

function sendDataToController() {
	var userAgent = navigator.userAgent;
	var innerWidth = $(window).innerWidth();
	var dataSubmitTime = getCurrentTime();
	
	var latitude = geoplugin_latitude();
	var longitude = geoplugin_longitude();
	
/*	console.log("Latitude :"+geoplugin_latitude());
	console.log("Longitude :"+geoplugin_longitude());*/
	
	
	//alert(window.devicePixelRatio+"\n"+window.screen.width);
	
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
		latitude : latitude,
		longitude : longitude,
		touchCount : touchCount,
		leftClickCount : leftClickCount,
		zoomInCount : zoomInCount,
		scrollCount : scrollCount,
		keyPressCount : keyPressCount,
		scrollEvent : scrollEvent,
		tapsEvent : tapsEvent,
		orientation : orientation,
		rightClickCount : rightClickCount,
		dbClickCount : dbClickCount,
		eventType : eventType,
		zoomOutCount : zoomOutCount,
		screenWidth : screenWidth,
		touchX : touchX,
		touchY : touchY,
		clickX : clickX,
		clickY : clickY,
		location : location,
		languages : languages,
		referer : referer,
		screenHeight : screenHeight,
		zoomCount : zoomCount,
		dataSubmitTime : dataSubmitTime
	}, function(data) {
		if (data.status == 'success') {
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