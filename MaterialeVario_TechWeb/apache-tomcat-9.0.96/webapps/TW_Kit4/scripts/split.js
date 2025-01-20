let matrixA =[[ null, null ], [ null, null]];
let matrixB =[[ null, null ], [ null, null]];

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		
	    var element = document.getElementById("result");
		
		element.innerText = "Matrice risultante :"+'\n';
		for(let i=0; i<2; i++){
				for(let j=0; j<2; j++){
					element.innerText+=conteggio[i][j];
		}
		element.innerText+= '\n';
	}
	
	

}// parsificaJSON()


function callback( theXhr ) {
	
	if ( theXhr.readyState === 2 ) {}// if 2
	else if ( theXhr.readyState === 3 ) { }// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			parsificaJson(theXhr.responseText);
			
		}// if 200
		 else {
			 alert("Impossibile effettuare l'operazione richiesta.");        	
	        }// else (if ! 200)
	}// if 4
} // callback();

function countingAJAX( theXhr, nome ,mata, matb) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in POST
	try {
		theXhr.open("post", nome, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "matriceA="+JSON.stringify(mata)+"&matriceB="+JSON.stringify(matb);
	console.log(argument);
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()


function sequenz(){
	var	xhr = myGetXmlHttpRequest();

	//DEVO MANDARE A UNA SINGOLA SERVLET
	if( (document.getElementById("bt1").value.trim() ) != null ){
		
		if ( xhr) {
				countingAJAX(xhr, "servlet1" ,matrixA, matrixB); 
		}
		else {
			countingframe();
		}
		
	}
}

function paral(){
	
	var	xhr = myGetXmlHttpRequest();
		
	//DEVO MANDARE SEMPRE A UNA SERVLET MA DA FARE 4 THREAD CONCORRENTI
	if (( document.getElementById("bt2").value.trim() ) != null ){
		//VERIFICO CHE SIANO STATI CREATI CORRETTAMENTE
			if ( xhr) {
					countingAJAX(xhr, "servlet2" ,  matrixA, matrixB); 
			}
			else {
				countingframe();
			 
			 }
			 }
   
	
	
}

function invio(){
	var count=0;
		
		for(let i=0; i<2; i++){
			for(let j=0; j<2; j++){
				
				if( document.getElementById("valueA"+i+j).value.trim() != ""){
					count++;
					//NEL MENTRE RIEMPIO ANCHE LE MATRICI
					matrixA[i][j]=document.getElementById("valueA"+i+j).value.trim();
				}
				
				
			}
		}
		
		for(let i=0; i<2; i++){
				for(let j=0; j<2; j++){
					
					if( document.getElementById("valueB"+i+j).value.trim() != ""){
						count++;
						matrixB[i][j]=document.getElementById("valueB"+i+j).value.trim();

					}
				}
			}
		
			
		//quando arrivo al numero totale di caselle riempite
		
		if(count==8){
			document.getElementById('bt1').style.display = 'inline';
			document.getElementById('bt2').style.display = 'inline';
			}
}
