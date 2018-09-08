$(document).ready(function() {
		
		$('#myForm').submit(function(e) {
						
			e.preventDefault();

			$('#ModalButton').hide();
			
			let submitText = $('#submit').html();
			
			switch (Math.floor((Math.random() * 5) + 1)) {
			case 1:
				$('#submit').html('<i class="fas fa-spinner fa-spin fa-lg"></i>');
				break;
			case 2:
				$('#submit').html('<i class="fas fa-circle-notch fa-spin fa-lg"></i>');
				break;
			case 3:
				$('#submit').html('<i class="fas fa-sync fa-spin fa-lg"></i>');
				break;
			case 4:
				$('#submit').html('<i class="fas fa-cog fa-spin fa-lg"></i>');
				break;
			case 5:
				$('#submit').html('<i class="fas fa-spinner fa-pulse fa-lg"></i>');
				break;
			}

			let url = $(this).attr('action');
			let data = $(this).serializeArray();
			
			console.log('url'+JSON.stringify(url,null,2));
			console.log('data'+JSON.stringify(data,null,2));
			
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
				
			}).always(function(xhr, statusText,a,b) {
								
				if(a!=''){
					xhr = a;
				}
				
				console.log(JSON.stringify(xhr, null, 2));
				console.log(JSON.stringify(statusText, null, 2));
				console.log(JSON.stringify(a, null, 2));
				console.log(JSON.stringify(b, null, 2));

				if (typeof xhr.responseJSON != 'undefined') {
					if (typeof xhr.responseJSON.title != 'undefined') {
						$('#ModalTitle').html(xhr.responseJSON.title);
					}
					if (typeof xhr.responseJSON.message != 'undefined') {
						$('#ModalBody').html(xhr.responseJSON.message);
					}
					if(typeof xhr.responseJSON.sendRedirect != 'undefined'){
						window.location.replace(xhr.responseJSON.sendRedirect);
					}
				}
				
				$('#Modal').modal();

				$('#submit').html(submitText);
				
				if(typeof scanTable != 'undefined'){
					console.log('scanFunction');
					scanTable(table,function(list){
						if(typeof fillInputs != 'undefined'){
							console.log('fillInputs');
							fillInputs();
						}
					});
				}
				
				if(typeof getRemolquesDisponibles != 'undefined'){
					getRemolquesDisponibles();
				}
				
				if(typeof getConductoresDisponibles != 'undefined'){
					getConductoresDisponibles();
				}
			});

		});
		
	});