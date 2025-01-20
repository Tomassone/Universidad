
function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var contenuto = JSON.parse(jsonText);
	    var element = document.getElementById("contenuto");
	
		// Pulisce l'elemento
		element.value = contenuto;

	

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

function countingAJAX( theXhr, metodo , mode) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in POST
	try {
		theXhr.open(metodo, "servletLavagna", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "azione="+mode;
	console.log(argument);
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
}// caricaFeedIframe()


function lettura(){
	
	var	xhr = myGetXmlHttpRequest();
	if ( xhr) {
					countingAJAX(xhr,"get", "lettura"); 
			}
			else {
				countingframe(); }
}

function scrittura(){
	
	var	xhr1 = myGetXmlHttpRequest();
	
	//var contenuto = document.getElementById("content").value;
	
	if ( xhr1) {
		countingAJAX(xhr1, "post", "scrit"); 
	}
	else {
	  countingframe();
	}
			 
}

//FACCIO VEDERE LA LAVAGNA IN CUI SCRIVERE
function invio(tipo){
	document.getElementById('lavagna').style.display = 'block';	

	if(tipo=="let"){
			setInterval(lettura, 10000)
	}else if(tipo=="scrit"){
				scrittura();
	}
	
}
