
$(document).ready(function() {
	//graphLabel();
});

function coordinateGraph(eventCoordinateArray) {
    var s1 = [[0.9, 120, 'Vernors'], [1.8, 140, 'Fanta'], [3.2, 90, 'Barqs'], 
              [4.1, 140, 'Arizon Iced Tea'], [4.5, 91, 'Red Bull'], [6.8, 17, 'Go Girl']];
    var s2 = [[1.3, 44, 'Pepsi'], [2.1, 170, 'Sierra Mist'], [2.6, 66, 'Moutain Dew'], 
              [4, 52, 'Sobe'], [5.4, 16, 'Amp'], [6, 48, 'Aquafina']];
    var s3 = [[1, 59, 'Coca-Cola'], [2, 50, 'Sprite'], [3, 90, 'Mello Yello'], 
              [4, 90, 'Ambasa'], [5, 71, 'Squirt'], [5, 155, 'Youki']];
           
      $('#coordinateGraphDiv').html('');
	  var plot2 = $.jqplot('coordinateGraphDiv',[eventCoordinateArray],{
		  title : 'User Event Coordinates',
	      seriesDefaults:{
	          renderer:$.jqplot.BlockRenderer,
	          rendererOptions: {
	              varyBlockColors: true
	          },
	          pointLabels:{
	              show: true
	          }
	      },
	      axes: {
	          xaxis: {
	              min:0,
	              max: 1700,
	        	  label:'X-coordinate (px)'
	          },
	          yaxis: {
	              min:0,
	              max: 1000,
	        	  label:'Y-coordinate (px)',
	        	  labelRenderer: $.jqplot.CanvasAxisLabelRenderer
	          }
	      }
	  });
}

function coordinateGraph1() {
	 var eventPoints = []; 
	
	  eventPoints.push([0, 5]); 
	  eventPoints.push([10, 15]);
	  eventPoints.push([890, 125]);
	  var plot1 = $.jqplot('coordinateGraphDiv', [eventPoints], {  
	     // series:[{showMarker:false}],
		  title : 'User Event Coordinates',
	      axes:{
	        xaxis:{
	          label:'X-coordinate (px)'
	        },
	        yaxis:{
	          label:'Y-coordinate (px)'
	        }
	      }
	  });
}

function eventPercentageGraph(eventPercentageArray){
/*	 var line1 = [['Sony',7], ['Samsung',13.3], ['LG',14.7], 
	              ['Vizio',5.2], ['Insignia', 1.2]];*/
	           
	 $('#eventPercentageGraphDiv').html('');
	 var plot3 = $.jqplot('eventPercentageGraphDiv', [eventPercentageArray], {
      title: 'User Event Pie Chart',
      seriesDefaults: {
        renderer: $.jqplot.PieRenderer,
        rendererOptions: {
          showDataLabels: true,
/*          padding: 10,
          sliceMargin: 6,
          shadow: false*/
        }
      },
      legend: {
        show: true
      },
      highlighter: {
        show: true,
        useAxesFormatters: false,
        tooltipFormatString: '%s'
      }
   
    });
}
