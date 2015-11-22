$(document).ready(function() {
	setInterval(function(){ 
		//$("#getUserDataBtnId").click();
	},10000);
});
var tsx;
var tsy;
var tex;
var tey;
var clickX;
var clickY;
var d = new Date();
var startTime;
var endTime;
var timeDifference;
window.addEventListener('load', function(){ // on page load
	
    document.body.addEventListener('touchstart', function(e){
    	tsx = e.changedTouches[0].pageX;
    	tsy = e.changedTouches[0].pageY;
    	startTime = d.getTime(); 
        console.log("Touch-Start X :"+e.changedTouches[0].pageX); // alert pageX coordinate of touch point
        console.log("Touch-Start Y :"+e.changedTouches[0].pageY);
    }, false)
    
    document.body.addEventListener('touchend', function(e){
    	tex = e.changedTouches[0].pageX;
    	tey = e.changedTouches[0].pageY;
    	endTime = d.getTime(); 
        console.log("Touch-End X :"+e.changedTouches[0].pageX) // alert pageX coordinate of touch point
        console.log("Touch-End Y :"+e.changedTouches[0].pageY)
    }, false)
    
    
    document.body.addEventListener('click', function(e){
/*        tex = e.screenX;
        tey = e.screenY;*/
        console.log("Click X :"+e.screenX); // alert pageX coordinate of touch point
        console.log("Click Y :"+e.screenY);
        clickX = e.screenX;
        clickY = e.screenY;
        //alert("OK1");
    }, false)
 
}, false)

var options = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
};

function success(pos) {
  var crd = pos.coords;

  console.log('Your current position is:');
  console.log('Latitude : ' + crd.latitude);
  console.log('Longitude: ' + crd.longitude);
  console.log('More or less ' + crd.accuracy + ' meters.');
};

function error(err) {
  console.warn('ERROR(' + err.code + '): ' + err.message);
};


function getUserData() {


	console.log("Entered");
	var referer = document.referrer;
	var url = document.URL
	var activeElement = document.activeElement;
	var location  = document.location;
	var docBdyHeight =  document.body.clientHeight;
	var docBdyWidth =  document.body.clientWidth;
	var scrollHeight =  document.body.scrollHeight;
	var scrollWidth =  document.body.scrollWidth;
	var userAgent = navigator.userAgent;
	var cookieEnabled = navigator.cookieEnabled;
	var platform = navigator.platform;
	var Java_Enabled = navigator.javaEnabled;
	var product = navigator.product;
	var appName = navigator.appName;
	var appCodeName = navigator.appCodeName;
	var maxTouchPoints = navigator.maxTouchPoints;
	//var geoLocation  = navigator.geolocation;
	var language = navigator.language;
	var languages = navigator.languages;
	
	var windowHeight = $(window).height();   // returns height of browser viewport
	var windowWidth = $(window).width();   // returns width of browser viewport
	var documentHeight = $(document).height(); // returns height of HTML document
	var documentWidth = $(document).width(); // returns width of HTML document
	
	var screenHeight = screen.height; 
	var screenWidth = screen.width;
	
	//var geoLocation = navigator.geolocation.getCurrentPosition(success, error, options);
	
	
	/*console.log("Referer :"+referer);
	console.log("URL :"+url);
	console.log("ActiveElements :"+activeElement);
	console.log("Location :"+location);*/
	console.log("Document body height :"+docBdyHeight);
	console.log("Document body width :"+docBdyWidth);
	console.log("Document ScrollHeight :"+scrollHeight);
	console.log("Document ScrollWidth :"+scrollWidth);
	
	console.log("Window Height :"+windowHeight);
	console.log("Window Width :"+windowWidth);
	console.log("Document Height :"+documentHeight);
	console.log("Document Width :"+documentWidth);
	
	console.log("Screen Height :"+screenHeight);
	console.log("Screen Width :"+screenWidth);
	
/*	console.log("Geo Location :"+geoLocation); */
	
	console.log("Country Name : " + geoplugin_countryName());
	console.log("Country Code :"+geoplugin_countryCode());
	console.log("Continent Code :"+geoplugin_continentCode());
	console.log("Region :"+geoplugin_region());
	console.log("City :"+geoplugin_city());
	console.log("Latitude :"+geoplugin_latitude());
	console.log("Longitude :"+geoplugin_longitude());
	console.log("Currency Symbol :"+geoplugin_currencySymbol());
	console.log("Currency Code :"+geoplugin_currencyCode());
	
	var countryName = geoplugin_countryName();
	var region = geoplugin_region();
	var city = geoplugin_city();
	//console.log("User Agent :"+userAgent);
	
	/*console.log("CookieEnabled :"+cookieEnabled);
	console.log("Platform :"+platform);
	console.log("Product :"+product);
	console.log("AppName :"+appName);
	console.log("AppCodeName :"+appCodeName);
	console.log("MaxTouchPoints :"+maxTouchPoints);
	console.log("GeoLocation :"+geoLocation);
	console.log("Language :"+language);
	console.log("Languages :"+languages);*/

	timeDifference = endTime-startTime
	
	
	
	
	$.post('homePage/getHeaderString', {
		referer : referer,
		url : url,
		language : language,
		screenHeight : screenHeight,
		screenWidth : screenWidth,
		countryName : countryName,
		region : region,
		platform : platform,
		city : city,
		tsx : tsx,
		tsy : tsy,
		tex : tex,
		tey : tey,
		clickX : clickX,
		clickY : clickY,
		startTime : startTime,
		endTime : endTime,
		userAgent : userAgent
	}, function(data) {
		if (data.status == 'success') {
			console.log("IP Address :"+data.result);
			console.log("DONE");
		} else {
			alert(data.status);
		}
	});
}

function geoFindMe() {
	  var output = document.getElementById("out");

	  if (!navigator.geolocation){
	    output.innerHTML = "<p>Geolocation is not supported by your browser</p>";
	    return;
	  }

	  function success(position) {
	    var latitude  = position.coords.latitude;
	    var longitude = position.coords.longitude;

	    output.innerHTML = '<p>Latitude is ' + latitude + '° <br>Longitude is ' + longitude + '°</p>';

	    var img = new Image();
	    img.src = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=300x300&sensor=false";

	    output.appendChild(img);
	  };

	  function error() {
	    output.innerHTML = "Unable to retrieve your location";
	  };

	  output.innerHTML = "<p>Locating…</p>";

	  navigator.geolocation.getCurrentPosition(success, error);
	}

