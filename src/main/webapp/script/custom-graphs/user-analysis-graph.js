Morris.Bar({
  element: 'user-analysis-graph',
  data: [
    { y: 'Browser Id', a: 100, b: 90 },
    { y: 'Time Zone', a: 75,  b: 65 },
    { y: 'OS Name', a: 50,  b: 40 },
    { y: 'Event Sequence', a: 50,  b: 40 },
    { y: 'Proxy Count', a: 50,  b: 40 },
    { y: 'User Location', a: 50,  b: 40 }
  ],
  xkey: 'y',
  ykeys: ['a', 'b'],
  labels: ['Positive', 'Negative'],
  barColors:['#5CB85C','#D9534F']
});