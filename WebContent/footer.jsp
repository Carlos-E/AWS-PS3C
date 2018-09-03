<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- Modal -->
<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="ModalTitle"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="ModalBody"></div>
			<div class="modal-footer">
 				<button type="button" class="btn btn-primary btn-md float-right" id="ModalButton"></button>
 				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			</div>
		</div>
	</div>
</div>


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

<script>


function fillProgressBar(progressBarElement,percentageTofill) {
	  
  	let progress=0;

    let refreshIntervalId = setInterval(()=>{
    	
    if(Number(progressBarElement.style.width.replace(/[^\d\.\-]/g, '')) >= 100){
    	console.log('Progress completed');
    	$(progressBarElement).fadeOut(1200);
    	setTimeout(()=>{progressBarElement.parentNode.removeChild(progressBarElement)},1300);
    	clearInterval(refreshIntervalId);
    	return;
    }
    
    if(progress==percentageTofill){
    	console.log('Limit reached');
    	clearInterval(refreshIntervalId);
    	return;
    }
    
    progress++; 
    progressBarElement.style.width = Number(progressBarElement.style.width.replace(/[^\d\.\-]/g, '')) + 1 + '%'; 
        
    },30);
    
  }
  
fillProgressBar(document.getElementById("progressBar"),10);


</script>

<jsp:include page="/scripts.jsp" />
