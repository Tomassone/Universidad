
function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var contenuto = JSON.parse(jsonText);
		console.log(contenuto);
		var element = document.getElementById("result");
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

function countingAJAX( theXhr, nome, arg) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", nome, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "ct="+arg;
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


function controllo(){
	
	//MI PRENDO IL CONTENUTO, LO MANDO ALLA SERVLET CHE MI AGGIORNA LA ROBA
	//QUANDO MI RESTITUISCE NUOVAMENTE IL RISULTATO LO SETTO NELLA TEXT AREA DI NUOVO
	var content = document.getElementById("area").value;
	console.log(content);
	
	document.getElementById("lavagna").innerText="";
	
	var	xhr = myGetXmlHttpRequest();
		
	    //VERIFICO CHE SIANO STATI CREATI CORRETTAMENTE
		if ( xhr) 
			countingAJAX(xhr, "servlet2", content); 		
		else 
		   countingframe();
}

function intervallo(event){
	
	event.preventDefault();  // Impedisce il submit automatico del form per ora

	   if (document.getElementById("tp").value == "L") {
	       // Avvia l'aggiornamento ogni 10 secondi
	       setInterval(aggiorna, 10000);
	   } else {
	       // Se non è "L", invia comunque il form una sola volta
	       document.getElementById("aziona").submit();
	   }
}

function aggiorna(){
	document.getElementById("tp").value = "L";
	document.getElementById("aziona").submit();
	alert("Aggiornato");
}


