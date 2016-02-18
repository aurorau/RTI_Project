
var eventType;
var offSet1=-1;

$(document).ready(function() {
	eventType = null;
	//getOffset();
});
function getOffset(){
    offSet1 = $(document).scrollTop() - $('#banner').offset().top;
    
    console.log("$('#banner').offset().top :"+$(document).scrollTop());
	sendEventDetailsToController();
}
//for firefox
$(document).bind('DOMMouseScroll', function(e){

	console.log("firefox"+e.which);
});

//IE, Opera, Safari, chrome
$(document).bind('mousewheel', function(e){
	console.log("IE, Opera, Safari"+e.which);
});
$(document).on('touchstart', function(e){
	eventType ="TS";
	sendEventDetailsToController();
});

$(document).on('touchmove', function(e){
	eventType = "TM";
	sendEventDetailsToController();
});

$(document).on('touchend', function(e){
	eventType = "TE";
	sendEventDetailsToController();
});

$(document).on('click', function(e){
	eventType = "CE";
	sendEventDetailsToController();
});

$(document).on('scroll', function(e){
	eventType = "SE";
	getOffset();
	//sendEventDetailsToController();
});

$(document).on('resize', function(e){
	eventType = "ZE";
	sendEventDetailsToController();
});

function getScreenReolution(){
	
}

function sendEventDetailsToController () {
	var eventTriggeredTime = getCurrentTime();
/*	screenHeight = getScreenHeight;
	screenWidth = getScreenWidth;*/
	//var sessionID = sessionStorage.getItem('sessionID');
	
	$.post('postEventDetails', {
		eventType : eventType,
		offSet1 : offSet1
/*		//coordinateX : coordinateX,
		coordinateY : coordinateY,
		screenHeight : screenHeight,
		screenWidth : screenWidth,
		orientation : orientation,
		sessionID : sessionID,
		eventTriggeredTime : eventTriggeredTime*/
	}, function(data) {
		if (data.status == 'success') {
			if(data.result !=  null) {
				//sessionStorage.setItem('sessionID', data.result);
			}
			
		} else {
			console.log(data.status);
		}
	});
}

var getCurrentTime=function() {
	var d = new Date();
	
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