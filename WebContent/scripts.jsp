<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.16/af-2.2.2/b-1.5.1/b-colvis-1.5.1/b-flash-1.5.1/b-html5-1.5.1/b-print-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.js"></script>

<script src="/js/custom.js"></script>

<script>
/* 
$(document).ready(function() {
    $('#tabla').DataTable( {
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
        }
    } );
} );
 */
</script>

<script>

//$("#messageNotification").hide();
<%
if (!session.getAttribute("rol").equals("cliente")||!session.getAttribute("pagina").equals("Consultar Reportes")) {
%>		

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
<%
}
%>
	
</script>

<script>
	$(document).ready(function() {
		
		$('#myForm').submit(function(e) {
						
			e.preventDefault();

			$('#ModalButton').hide();

			
			let url = $(this).attr('action');
			let data = $(this).serializeArray();
			
			$.ajax({
				url : url,
				data : data,
				type : "POST",
				dataType : "json",
			}).done(function(data, statusText, xhr) {

				$('#ModalTitle').html('Operaci&oacute;n exitosa');
				$('#ModalBody').html('Operaci&oacute;n completada');
				
			}).fail(function(xhr, statusText) {

				$('#ModalTitle').html('C&oacute;digo de Estado: ' + xhr.status);
				$('#ModalBody').html('Oopss a ocurrido un error');
				
			}).always(function(xhr, statusText,a) {
								
				if(a!=''){
					xhr = a;
				}
				
				//console.log(JSON.stringify(xhr, null, 2));

				if (typeof xhr.responseJSON != 'undefined') {
					if (typeof xhr.responseJSON.title != 'undefined') {
						$('#ModalTitle').html(xhr.responseJSON.title);
					}
					if (typeof xhr.responseJSON.message != 'undefined') {
						$('#ModalBody').html(xhr.responseJSON.message);
					}
				}
				
				$('#Modal').modal();
				
				if(typeof scanFunction != 'undefined'){
				scanFunction(table);
				}
			});

		});
		
	});
</script>
