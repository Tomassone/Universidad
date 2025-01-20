var tot=0;

function parsificaJson( jsonText ) {
   
	   // variabili di funzione
		var conteggio = JSON.parse(jsonText);
		
	    var element = document.getElementById("result");
	    element.innerText = ""+conteggio;		
		

}// parsificaJSON()


function callback( theXhr ) {
	
	if ( theXhr.readyState === 2 ) {}// if 2
	else if ( theXhr.readyState === 3 ) { }// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
		
		}// if 200
		 else {
			 alert("Impossibile effettuare l'operazione richiesta.");        	
	        }// else (if ! 200)
	}// if 4
} // callback();

function countingAJAX( theXhr, arg) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	try {
		theXhr.open("get", "servletLogin", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "nome="+arg;
	console.log("argomento"+argument);
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()


function scrittura(){
	
			var	xhr = myGetXmlHttpRequest();
			if ( xhr ) 
				countingAJAX(xhr, "rilascia"); 
			else 
				countingframe();
}

function myfunction(){
	
	//Mi prendo nome dell'articolo
	console.log("qui");
	var file = document.getElementById("ar").value;
	
	console.log("nome"+file);
	
	if(file.endsWith("%")){	
		console.log("Sono qui");
		document.getElementById("fileForm").submit();
	}

	
}

