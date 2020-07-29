$(document).ready(function () {
         $('#btntotalcount').click(function(){
            $.ajax('/covid/getcount',
			{
				dataType: 'json', // type of response data

				success: function (json,status,xhr) {   // success callback function
				    console.log(json);
				    //alert(JSON.stringify(json.data[0]));
					$('p').append(json.data[0]);

					var ctx = $('#totalcountchart');
                    new Chart(ctx, {
                        data:{
                            labels: Object.keys(json.data[0]),
                            datasets:
                                [{
                                    data: Object.values(json.data[0]),
                                    label:'No of Corona Cases',

                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)',
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)'
                                ],
                                borderWidth: 1
                               }]
                        },
                        type: 'bar',
                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            }
                        }
                    });
				},
				error: function (jqXhr, textStatus, errorMessage) { // error callback
					$('p').append('Error: ' + errorMessage);
				}
			});
         });


         $('#btnstatecount').click(function(){
                     $.ajax('/covid/getstatewisedata',
         			{
         				dataType: 'json', // type of response data

         				success: function (json,status,xhr) {   // success callback function

         				    //  var obj = JSON.stringify(json.data);
         				    //  alert(json.data);

                            $.each(json.data, function(i, values) {
                                var link = "<a href='javascript:stateview("+JSON.stringify(values)+")'>"+values.name+"</a>";
                                $('#statediv').append(link).append('<br/>');
                            });

         				},
         				error: function (jqXhr, textStatus, errorMessage) { // error callback
         					$('p').append('Error: ' + errorMessage);
         				}
         			});
         });


});

function stateview(data){

        var ctxstate = $('#statewisechart');
            new Chart(ctxstate, {
                data:{
                    labels: Object.keys(data),
                    datasets:
                        [{
                            data: Object.values(data),
                            label:'No of Corona in every state',
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1
                       }]
                },
                type: 'polarArea',
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
 }




