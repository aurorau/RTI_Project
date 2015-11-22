
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

$(document).ready(function() {
	console.log("COME BACK");
/*	$('#searchKeyId').val(geoplugin_countryName()+'/'+geoplugin_region());
	setInterval(function(){ 
		//$("#getUserDataBtnId").click();
	},10000);*/
	
	$( window ).resize(function() {
		zoomCount = zoomCount+1;
		console.log("Zoom");
	});
	
	$('#gallaryLinkId').on('click', function(){
		//alert("DONE");
	});
	
});
$(document).click(function(e){
	
	console.log("Left Right Click :"+e.which);
	console.log("Click X :"+e.screenX); // alert pageX coordinate of click point
	console.log("Click Y :"+e.screenY);
	
	if(e.which == 1) {
		//if(e.type == 'click') {
			leftClickCount = leftClickCount+1;
		//} 
	}
	if(e.which == 2) {
		
	}
	if(e.which == 3) {
		rightClickCount = rightClickCount+1;
	}
});

$(document).dblclick(function(e){
	dbClickCount = dbClickCount+1;

});

$(document).on({'touchstart': function(){ 
	touchCount = touchCount+1;
}});

$(window).on("orientationchange",function(event){
	  orientation = event.orientation;
});

$(document).scroll(function(e){
	scrollEvent = true;
	console.log("Scroll Event :"+scrollEvent);
});

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
	var screenWidth = screen.width;
	
	console.log("innerWidth :"+innerWidth);
	console.log("screenWidth :"+screenWidth);
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
	
	$.post('homePage/getHeaderString', {
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
		dbClickCount : dbClickCount
	}, function(data) {
		if (data.status == 'success') {
			console.log("IP Address :"+data.result);
			console.log("DONE");
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