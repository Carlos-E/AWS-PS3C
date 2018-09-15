$(document).ready(function() {
	
	// request permission on page load
	document.addEventListener('DOMContentLoaded', function () {
	  if (!Notification) {
	    alert('Notificaciones no disponibles en tu navegador.'); 
	    return;
	  }

	  if (Notification.permission !== "granted")
	    Notification.requestPermission();
	});

	function notifyMe(title,message) {
	  if (Notification.permission !== "granted")
	  { 
		  Notification.requestPermission();
	  }
	  else {
	    var notification = new Notification(title, {
	      icon: '/img/favicon.ico',
	      body: message,
	    });
	    
	    notification.onclick = function () {
	      window.open("/envios/reportes.jsp");      
	    };

	  }

	}
	
	//var bell = new Audio('/audio/bell.mp3');
	var aux=0;
	
	function checkMessages(){
		{
			//console.log('checking');
			
			$.ajax({
				url : "/getNumReports",
				type : "POST",
				dataType : "json",
			}).done(function(response) {
				//console.log(response);
				
		        $("#numMessages").html(response.num);
		    
		        if(response.num==0){
		        	$("#messageNotification").hide();
		        }else{
		        	$("#messageNotification").show();
		        }
		        
		        if(aux!=response.num && aux<response.num){
		        	aux = response.num;
		        	notifyMe('Reportes Nuevos','Haz click aqui para verlos');
		        	//bell.play();
		        }
		        
			}).fail(function(xhr, status, errorThrown) {
				console.log('Failed Request To Servlet /getNumReports')
			}).always(function(xhr, status) {
			});		
			
		}
	}
	
	//console.log('Checking Messages');
	checkMessages();
	setInterval(checkMessages,5000);
	
});
