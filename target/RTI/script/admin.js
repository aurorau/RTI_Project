$(document).ready(function() {

});

function getData() {
	
	 $.get('getDataForAdminPage', function(data) {
			if (data.status == 'success') {
				console.log("IP Address :"+data.result.IP);
				console.log("DONE");
				$('#ipAddress').text(data.result.IP);
			} else {
				console.log(data.status);
			}
	    });
	
	
	//$('#ipAddress').text($('#hiddenIpAddress').val());
}