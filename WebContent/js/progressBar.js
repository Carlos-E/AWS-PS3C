function fadeOutEffect(element,rate) {
    let fadeEffect = setInterval(function () {
        if (!element.style.opacity) {
        	element.style.opacity = 1;
        }
        if (element.style.opacity > 0) {
        	element.style.opacity -= 0.1;
        } else {
            clearInterval(fadeEffect);
        	element.style.display = 'none';
        	//console.log('fadeOutEffect completed');
        }
    }, rate);
}

function fillProgressBar(progressBarElement,percentageTofill) {
	  
	progressBarElement.style.opacity = 1;
	progressBarElement.style.display = '';
	
  	let progress = 0;

    let IntervalId = setInterval(()=>{
    	
    if(Number(progressBarElement.style.width.replace(/[^\d\.\-]/g, '')) >= 100){
    	
    	console.log('Progress bar completed...fading out');
    	
    	fadeOutEffect(progressBarElement,100);
    	
    	//Reset bar to 0 after completed
    	setTimeout(()=>{    	
    		//console.log('Reseting bar');
    		progressBarElement.style.width = '0%';
    		},1000);
    	
    	clearInterval(IntervalId);
    	return;
    	
    }
    
    if(progress==percentageTofill){
    	console.log('Percentage reached');
    	clearInterval(IntervalId);
    	return;
    }
            
    progress++; 
    progressBarElement.style.width = Number(progressBarElement.style.width.replace(/[^\d\.\-]/g, '')) + 1 + '%'; 
        
    },5);
    
  }

//fillProgressBar(document.getElementById("progressBar"),25);