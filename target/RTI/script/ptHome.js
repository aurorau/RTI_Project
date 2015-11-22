
var touchCount=0;
var clickCount=0;
var zoomCount=0;
var scrollCount=0;
var keyPressCount=0;
var scrollEvent=false;
var tapsEvent=false;

$(document).ready(function() {
	console.log("COME BACK");
	$('#searchKeyId').val(geoplugin_countryName()+'/'+geoplugin_region());
	setInterval(function(){ 
		//$("#getUserDataBtnId").click();
	},10000);
	
	$( window ).resize(function() {
		zoomCount = zoomCount+1;
		console.log("Zoom");
	});
	
	$('#gallaryLinkId').on('click', function(){
		//alert("DONE");
	});
	
/*    $(window).scroll(function() {
        if($(window).scrollTop() > 0) {
        	scrollCount = scrollCount+1;
            console.log("From Top :"+$(window).scrollTop() );
        }
    });*/
    
/*    document.body.addEventListener('click', function(e){
    	clickCount = clickCount+1;
    	console.log(getCurrentTime());
    	console.log("Click X :"+e.screenX); // alert pageX coordinate of click point
    	console.log("Click Y :"+e.screenY);
    	clickX = e.screenX;
    	clickY = e.screenY;
    	
    }, false)*/
    


    
/*    document.body.addEventListener('touchstart', function(e){
    	touchCount = touchCount+1;
        console.log("Touch-Start X :"+e.changedTouches[0].pageX); // alert pageX coordinate of touch point
        console.log("Touch-Start Y :"+e.changedTouches[0].pageY);
    }, false)
    
    document.body.addEventListener('touchend', function(e){
        console.log("Touch-End X :"+e.changedTouches[0].pageX) // alert pageX coordinate of touch point
        console.log("Touch-End Y :"+e.changedTouches[0].pageY)
    }, false)*/
	
});
$(document).click(function(e){
	clickCount = clickCount+1;
	console.log("Click X :"+e.screenX); // alert pageX coordinate of click point
	console.log("Click Y :"+e.screenY);
});

$('body').on("swipe",function(){
	tapsEvent = true;
});

$(document).scroll(function(e){
	scrollEvent = true;
	//console.log("From Top :"+$(document).scrollTop() );
	console.log("Scroll Event :"+scrollEvent);
});

$(document).keypress(function(e) {
/*    if(e.which == 13) {
    	setclassroomTable();
    }*/
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
	
	console.log("Country Name : " + geoplugin_countryName());
	console.log("Country Code :"+geoplugin_countryCode());
	console.log("Continent Code :"+geoplugin_continentCode());
	console.log("Region :"+geoplugin_region());
	console.log("City :"+geoplugin_city());
	console.log("Latitude :"+geoplugin_latitude());
	console.log("Longitude :"+geoplugin_longitude());
	console.log("Currency Symbol :"+geoplugin_currencySymbol());
	console.log("Currency Code :"+geoplugin_currencyCode());
	
	var location = $('#searchKeyId').val();
	
	$.post('homePage/getHeaderString', {
		userAgent : userAgent,
		location : location,
		touchCount : touchCount,
		clickCount : clickCount,
		zoomCount : zoomCount,
		scrollCount : scrollCount,
		keyPressCount : keyPressCount,
		scrollEvent : scrollEvent,
		tapsEvent : tapsEvent
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