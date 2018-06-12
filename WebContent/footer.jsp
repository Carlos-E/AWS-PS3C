<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto mb-2">
<div class="col-md-12 col-lg-12">
	<section class="row">
		<footer class="col-md-12 col-lg-12">

			<div class="row">

				<div class="col-md-12 col-lg-9">

					<div class="row">
						<div class="col-md-12 col-lg">
							<p>© 2017-2018 - Proyecto con fines academicos</p>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12 col-lg">
							<p>
								Investigadores y desarrolladores
								<br>
								<a href="https://www.linkedin.com/in/luis-puche/" target="_blank">Luis A. Puche</a>
								y
								<a href="https://www.linkedin.com/in/carlos-e-perez-meza/" target="_blank"> Carlos E. Perez.</a>

							</p>
						</div>

						<div class="col-md-12 col-lg">
							<p>
								Investigador y director de proyecto
								<br>
								<a href="https://www.linkedin.com/in/plinio-puello-marrugo-800a30130/" target="_blank">Plinio Puello M.</a>
							</p>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12 col-lg">
							<p style="font-size: 10px;">
								Plantilla base hecha por
								<a href="https://www.medialoot.com/">Medialoot.</a>
							</p>
						</div>
					</div>

				</div>

				<div class="col-md-12 col-lg">
					<img width=180 height=85 class="float-right" style="filter: invert(55%);" src="https://www.unicartagena.edu.co/templates/ja_university_t3/images/logo.png" alt="Ja University">
				</div>
			</div>
		</footer>
	</section>
</div>
</main>

<jsp:include page="/scripts.jsp" />

<script>
	$(document).ready(function() {
		
		$.ajax({
			url : "/scanTable",
			data : {
				tabla : 'reportes'
			},
			type : "POST",
			dataType : "json",
		}).done(function(response) {
			console.log(response);
			
			let num = 0;
			
			response.forEach(element => {
				if(element.visto===false){
					num++
				}
			});
			
	        $("#numMessages").html(num);

	        if(num==0){
	        	$("#messageNotification").hide();
	        }else{
	        	$("#messageNotification").show();
	        }
	        
		}).fail(function(xhr, status, errorThrown) {
			alert("Algo ha salido mal");
			console.log('Failed Request To Servlet /scanTable')
		}).always(function(xhr, status) {
		});		
		
	});
	</script>
