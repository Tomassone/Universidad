var conta=0;
var tot=0;

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
	   
		tot+=conteggio;
		conta++;
		
		if(conta==2){
	    var element = document.getElementById("result");
	    element.innerText =   "Minuscole:"+tot+ '\n';		
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

function countingAJAX( theXhr, inizio, fine, nome, cont) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in POST
	try {
		theXhr.open("post", "servlet1", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "inizio="+inizio+"&fine="+fine+"&nomefile="+nome+"&contenuto="+cont;
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

function invio(){
	
	//Prendo il contenuto della mia text area in modo da mandarla a due servlet
	var f1 = document.getElementById("textarea").value.trim();
	var nome = document.getElementById("file").value.trim();
	
	var div = Math.floor(f1.length/2);
	
	if(f1.length<10 || f1.lenght>30){
		alert("Il numero di caratteri deve essee tra 1000 e 2000");
	}
	
	var	xhr = myGetXmlHttpRequest();
	var	xhr2 = myGetXmlHttpRequest();


    //VERIFICO CHE SIANO STATI CREATI CORRETTAMENTE
	if ( xhr) {
		        countingAJAX(xhr , 0 , div , nome, f1); 
				countingAJAX(xhr2, div, f1.length, nome, f1); 
	}
	else {
		countingframe();}
	
}

