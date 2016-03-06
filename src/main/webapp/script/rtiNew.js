
var orientation=-1;
var eventType = -1;
var getScreenWidth=0;
var getScreenHeight=0;
var previouseScrollTime = 0;
var previouseTouchTime = 0;
var previouseSwapTime = 0;
var coordinateX = 0;
var coordinateY = 0;
var getBrowserWidth=0;
var getBrowserHeight=0;
var tagName = -1;
var elementId = -1;
var elementClass = -1;
var elementHeight = -1;
var elementWidth = -1;
var elementOffsetTop = -1;
var elementOffsetLeft = -1;
var numberOfFingers = 0;
var zoomEvent = -1;
var scrollTopPx=-1;
var elementScrollTop = -1;
var viewportHeight = -1;
var viewportWidth = -1;
var timeZoneOffset = -1;
var globalEventType= -1;
var imageName = -1;

$(document).ready(function() {
	clearValues();
	eventType = "RF";
	sendEventDetailsToController();
	setInterval(function(){ 
		heartBeat();
	},10000);
});

function heartBeat(){
	var sessionID = -1;
	sessionID = sessionStorage.getItem('sessionID');
	setTimeZoneInCookie();
	$.get('heartBeat', {
		sessionID : sessionID,
		timeZoneOffset : timeZoneOffset
	}, function(data) {
		if (data.status == 'SUCCESS') {
			if(data.result !=  'SUCCESS') {
				sessionStorage.setItem('sessionID',"");
			}
		} else {
			console.log(data.status);
		}
	});
}

function identifyDeviceWidthHeight(){
	//getProperHeightWidth();
	var deviceMinWidthStatus = window.matchMedia('(min-device-width: '+screen.width+'px)').matches;
	var deviceMinHeightStatus = window.matchMedia('(min-device-height:'+screen.height+'px)').matches;
	
	var deviceMaxWidthStatus = window.matchMedia('(max-device-width: '+screen.width+'px)').matches;
	var deviceMaxHeightStatus = window.matchMedia('(max-device-height:'+screen.height+'px)').matches;
	
	var orientationLand  = window.matchMedia("(orientation: landscape)").matches;

	//for laptop and Desktop
	if((screen.width > screen.height) && orientationLand){
		if(deviceMinWidthStatus && deviceMaxWidthStatus && deviceMinHeightStatus && deviceMaxHeightStatus){
			getProperHeightWidth();
		} else {
			getFraudHeightWidth("mx");
		}
	} else if((screen.width < screen.height) && !orientationLand){
		if(deviceMinWidthStatus && deviceMaxWidthStatus && deviceMinHeightStatus && deviceMaxHeightStatus){
			getProperHeightWidth();
		} else {
			getFraudHeightWidth("mx");
		}

	} else {
		getFraudHeightWidth("s");
	}

	//for tablet and mobile
	if((screen.width < screen.height) && !orientationLand){
		if(deviceMinWidthStatus && deviceMaxWidthStatus && deviceMinHeightStatus && deviceMaxHeightStatus){
			getProperHeightWidth();
		} else {
			getFraudHeightWidth("mx");
		}
	}
	viewportHeight =window.innerHeight;
	viewportWidth = window.innerWidth;
	
/*	console.log("deviceMinWidthStatus :"+deviceMinWidthStatus+"\ndeviceMinHeightStatus :"+deviceMinHeightStatus+"\norientaton :"+orientaton);
	console.log("///////////////////");
	console.log("deviceMaxWidthStatus :"+deviceMaxWidthStatus+"\ndeviceMaxWidthStatus :"+deviceMaxWidthStatus+"\norientaton :"+orientaton);
	var browserWidthStatus = window.matchMedia("(min-width: 1366px)").matches;
	var browserHeightStatus = window.matchMedia("(min-height: 768px)").matches;*/
}


function getProperHeightWidth(){
	getScreenWidth = screen.width;
	getScreenHeight = screen.height;
}

function getFraudHeightWidth(status){
	getScreenWidth = -100;
	getScreenHeight = -100;
}

$(document).dblclick(function(e){
	globalEventType = "DC";
	eventType = "DC";
	coordinateX = Math.round(e.clientX);
	coordinateY = Math.round(e.clientY);
	eventTriggredPositionDetails(e);
	sendEventDetailsToController();
});

function touchTest(e){
	if (e.originalEvent) {
        if (e.originalEvent.touches && e.originalEvent.touches.length) {
            alert("touches"+e.originalEvent.touches.length);
        } else if (e.originalEvent.changedTouches && e.originalEvent.changedTouches.length) {
        	alert("changedTouches"+ e.originalEvent.changedTouches);
        }
    }
}

$(document).on('touchstart', function(e){
	numberOfFingers = e.originalEvent.touches.length;
	//identifyDeviceWidthHeight();
	
	var currentTouchTime = Date.now();
	var touchTimeDiffer = (currentTouchTime - previouseTouchTime);
	globalEventType = "TS";
	
/*	if(globalEventType == -1) {
		globalEventType = "TS";
	} else {*/
		if(touchTimeDiffer < 400) {
			setTimeout(function(){
				if(numberOfFingers == 1) {
					eventType = "TZE";
					coordinateX = Math.round(e.originalEvent.touches[0].clientX);
					coordinateY = Math.round(e.originalEvent.touches[0].clientY);
					eventTriggredPositionDetails(e);
					sendEventDetailsToController();
				}
			},400);
		}
	//}
	previouseTouchTime = currentTouchTime;
	
/*	globalEventType = "TS";
	eventType = "TS";
	coordinateX = e.originalEvent.touches[0].clientX;
	coordinateY = e.originalEvent.touches[0].clientY;
	eventTriggredPositionDetails(e);
	sendEventDetailsToController();*/
	 
});

$(document).on('touchmove', function(e){
	numberOfFingers = e.originalEvent.touches.length;
	if(globalEventType == "TS" && numberOfFingers > 1) {
		var currentSwapTime = Date.now();
		eventType = "STZE";
		coordinateX = Math.round(e.originalEvent.touches[0].clientX);
		coordinateY = Math.round(e.originalEvent.touches[0].clientY);
		eventTriggredPositionDetails(e);
		sendEventDetailsToController();
		numberOfFingers = -1;
	} else if(globalEventType == "TS" && numberOfFingers == 1){
		globalEventType = "TM";
		eventType = "TM";
		coordinateX = Math.round(e.originalEvent.touches[0].clientX);
		coordinateY = Math.round(e.originalEvent.touches[0].clientY);
		eventTriggredPositionDetails(e);
		sendEventDetailsToController();
	} 
});

function setSwapZoom(currentSwapTime){
	var timeDiffer= (currentSwapTime-previouseSwapTime);
	if(timeDiffer > 100) {
		eventType = "STZE";
		coordinateX = Math.round(e.originalEvent.touches[0].clientX);
		coordinateY = Math.round(e.originalEvent.touches[0].clientY);
		eventTriggredPositionDetails(e);
		sendEventDetailsToController();
	}
	previouseSwapTime = currentSwapTime;
}

$(document).on('click', function(e){
	if(e.which == 1) {
		
		if(globalEventType == "TS" ){
			eventType = "TS";
		} else {
			eventType = "LC";
			globalEventType = "LC"
		}
		coordinateX = Math.round(e.clientX);
		coordinateY = Math.round(e.clientY);
		eventTriggredPositionDetails(e);
		
		if(e.originalEvent.touches) {
			numberOfFingers = e.originalEvent.touches.length;
		}
		sendEventDetailsToController();
	}
	if(e.which == 3){
		globalEventType = "RC";
		eventType = "RC";
		coordinateX = Math.round(e.clientX);
		coordinateY = Math.round(e.clientY);
		eventTriggredPositionDetails(e);
		
		if(e.originalEvent.touches) {
			numberOfFingers = e.originalEvent.touches.length;
		}
		sendEventDetailsToController();
	}

});

function eventTriggredPositionDetails(e){
	var name = e.target.tagName;
	var id =  e.target.id;
	var clz = $(e.target).attr('class');
	//var hsClz = $(e.target).hasClass();
	
	//var x = $("."+clz).offset();
  //  console.log("Top: " + x.top + " Left: " + x.left);
	if(name == "IMG"){
		imageName = $(e.target).attr('src');
		console.log("ImageName :"+imageName);
	}
	if(id == null || id == ''){
		$(e.target).attr('id',name+'test');
	}
	
	if(document.getElementById(e.target.id).getBoundingClientRect()) {
		var rect = document.getElementById(e.target.id).getBoundingClientRect();
		console.log("Top: " + rect.top);
		console.log("left: " + rect.left);
		console.log("width : " + rect.width );
		console.log("height : " + rect.height );
	}
/*	console.log("Tag Name :"+name);
	console.log("beforeid :"+id);
	console.log("afterid :"+e.target.id);
	console.log("clz :"+clz);*/
	//console.log("hsClz :"+hsClz);
	

	
	
	console.log("e.target.attributes :"+e.target.attributes.length);
	var attibuteList = e.target.attributes;
	var index =0;
	
	tagName = name;
	
	elementId = e.target.id;
	elementClass = $(e.target).attr('class');
	elementHeight = rect.height;
	elementWidth = rect.width;
	elementOffsetTop = rect.top;
	elementOffsetLeft = rect.left;
	
	var x = $("#"+e.target.id).offset();
	elementScrollTop = Math.round(x.top);
	console.log("Element Scroll Top :"+elementScrollTop);
}

$(window).on("orientationchange",function(event){
	  orientation = event.orientation;
});

$(document).on('scroll',function(e){
	
	var currentScrollTime = Date.now();
	globalEventType = "SE";
	eventType = "SE";
	numberOfFingers = 0;
	if(e.originalEvent.touches) {
		numberOfFingers = e.originalEvent.touches.length;
	}
	scrollTopPx = $(window).scrollTop();
	setScroll(currentScrollTime);
	
/*	if(globalEventType == "TS") {
		globalEventType = "TS";
	} else if(globalEventType == "TM") {
		var currentScrollTime = Date.now();
		globalEventType = "TSE";
		eventType = "TSE";
		numberOfFingers = 0;
		if(e.originalEvent.touches) {
			numberOfFingers = e.originalEvent.touches.length;
		}
		scrollTopPx = $(window).scrollTop();
		setScroll(currentScrollTime);
	} else {
		var currentScrollTime = Date.now();
		globalEventType = "DSE";
		eventType = "DSE";
		numberOfFingers = 0;
		if(e.originalEvent.touches) {
			numberOfFingers = e.originalEvent.touches.length;
		}
		scrollTopPx = $(window).scrollTop();
		setScroll(currentScrollTime);
	}*/
	
});
function setScroll(currentScrollTime){
	var timeDiffer= (currentScrollTime-previouseScrollTime);
	if(timeDiffer > 100) {
		coordinateX = 0;
		coordinateY = 0;
/*		tagName = 0;
		elementId = 0;
		elementClass = 0;
		elementHeight = 0;
		elementWidth = 0;
		elementOffsetTop = 0;
		elementOffsetLeft = 0;*/
		sendEventDetailsToController();
	}
	previouseScrollTime = currentScrollTime;
}

$(document).keypress(function(e) {
	eventTriggredPositionDetails(e);
	if(e.originalEvent.touches) {
		numberOfFingers = e.originalEvent.touches.length;
	}
/*	if(e.which == 45) { //firefox
		eventType = "DZE";
		
	} else if( e.which == 43) {//firefox
		eventType = "DZE";
		
	} else {
		eventType = "KP";
	}*/
	eventType = "KP";
	sendEventDetailsToController();
});

var getCurrentTime=function() {
	var d = new Date();
	  var year = d.getFullYear();
	  var month = d.getMonth() + 1;
	  var day = d.getDate();
	  var hours = d.getHours();
	  var minutes = d.getMinutes();
	  var seconds = d.getSeconds();
	  var milSec = d.getMilliseconds(); 
	  var ampm = hours >= 12 ? 'pm' : 'am';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	 // var strTime = hours + ':' + minutes + ':'+seconds + ' ' + ampm;
	  var strTime = year +'-'+month+'-'+day+' ' +hours + ':' + minutes + ':'+seconds+':'+milSec + ' '+ampm;
	  return strTime;
}

function sendEventDetailsToController () {
	var eventTriggeredTime = getCurrentTime();
	var sessionID = sessionStorage.getItem('sessionID');
	identifyDeviceWidthHeight();
	screenHeight = getScreenHeight;
	screenWidth = getScreenWidth;
	setTimeZoneInCookie();
	var country = "America/Los_Angeles";
		//sessionStorage.getItem('sessionID');
	//$('#hiddenSessionId').val(sessionID);
	
	//console.log("Hidden Session Id :"+$('#hiddenSessionId').val());
	
	$.post('postEventDetails', {
		eventType : eventType,
		coordinateX : coordinateX,
		coordinateY : coordinateY,
		screenHeight : screenHeight,
		screenWidth : screenWidth,
		orientation : orientation,
		sessionID : sessionID,
		tagName : tagName,
		elementId : elementId,
		elementClass : elementClass,
		elementHeight : elementHeight,
		elementWidth : elementWidth,
		elementOffsetTop : elementOffsetTop,
		elementOffsetLeft : elementOffsetLeft,
		numberOfFingers : numberOfFingers,
		scrollTopPx : scrollTopPx,
		viewportHeight : viewportHeight,
		viewportWidth : viewportWidth,
		elementScrollTop : elementScrollTop,
		country : country,
		timeZoneOffset : timeZoneOffset,
		imageName : imageName,
		eventTriggeredTime : eventTriggeredTime
	}, function(data) {
		if (data.status == 'success') {
			if(data.result !=  null) {
				sessionStorage.setItem('sessionID', data.result);
				//$('#hiddenSessionId').val(data.result);
			}
			
		} else {
			console.log(data.status);
		}
	});
	clearValues();
}

function clearValues() {
	eventType = -1;
	coordinateX = -1;
	coordinateY = -1;
	screenHeight = -1;
	screenWidth = -1;
	orientation = -1;
	sessionID = -1;
	tagName = -1;
	elementId = -1;
	elementClass = -1;
	elementHeight = -1;
	elementWidth = -1;
	elementOffsetTop = -1;
	elementOffsetLeft = -1;
	numberOfFingers = -1;
	scrollTopPx = -1;
	eventTriggeredTime = -1;
	viewportHeight = -1;
	viewportWidth = -1;
	elementScrollTop = -1;
	timeZoneOffset = -1;
	imageName = -1 ;
}

function getLocation() {
	navigator.geolocation.getCurrentPosition(foundLocation, noLocation);
    function foundLocation(position) {
    	var lat = position.coords.latitude;
    	var long = position.coords.longitude;
    	console.log("Position :"+position);
    	console.log("LAT : "+lat +", LONG : "+long);
    }

    function noLocation() {
    	console.log('Could not find location');
    }
}

function setTimeZoneInCookie() {
    var _myDate = new Date();
    var _offset = _myDate.getTimezoneOffset();
    timeZoneOffset = _offset;
   // document.cookie = "TIMEZONE_COOKIE=" + _offset; //Cookie name with value
}