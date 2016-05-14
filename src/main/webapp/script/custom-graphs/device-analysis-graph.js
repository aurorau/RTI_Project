Morris.Bar({
  element: 'device-analysis-graph',
  data: [
    { y: 'by User Events', a: 100, b: 90 },
    { y: 'by Dimention', a: 75,  b: 65 },
    { y: 'by Oriantation', a: 50,  b: 40 }
  ],
  xkey: 'y',
  ykeys: ['a', 'b'],
  labels: ['Desktop', 'Mobile'],
  barColors:['#9b59b6','#3498db']
});