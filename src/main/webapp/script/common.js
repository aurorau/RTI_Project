function escapeStr(str) 
{
  if (str)
      return str.replace(/([ #;?%&,.+*~\':"!^$[\]()=>|\/@])/g,'\\$1');      

  return str;
}

var removeTableText = function(id){
	var textTable = $('#'+escapeStr(id)+' .pagebanner').text();
	if(textTable == 'No items found.'){
		$('#'+escapeStr(id)+' .pagebanner').hide();
	}else{
		$('#'+escapeStr(id)+' .pagebanner').show();
	}
}
$(document).ready(function() {
    $('[data-toggle="popover"]').popover();
});