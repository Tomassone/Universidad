
function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var prenotazioni = JSON.parse(jsonText);
	    var element = document.getElementById("result");
	
		// Pulisce l'elemento
		element.innerHTML = '';
		element.innerHTML+="PRENOTAZIONI DISPONIBILI:\n";

		// Aggiungi ogni prenotazione all'elemento
		for(let i=0; i<prenotazioni.length; i++){
		    var prenotazioneDiv = document.createElement("div");
		    prenotazioneDiv.innerText = 'ID: ' + prenotazioni[i].id + ', Ora Inizio: ' + prenotazioni[i].oraInizio +
			 ', Utente1: ' + prenotazioni[i].utente1 + (prenotazioni[i].utente2 ? ', Utente2: ' + prenotazioni[i].utente2 : '')
			 + ', Valido: ' + prenotazioni[i].valido;
		    element.appendChild(prenotazioneDiv);
		};
	

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

function countingAJAX( theXhr, elemento, orario, comando) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in POST
	try {
		theXhr.open("post", "servletAdmin", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "comando="+comando+"&gruppo="+elemento+"&orario="+orario;
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
					countingAJAX(xhr,0,0, "lettura"); 
			}
			else {
				countingframe(); }
}

function scrittura(){
	
	var	xhr1 = myGetXmlHttpRequest();
	var inizio = document.getElementById("inizio").value;
	var gruppo = document.getElementsByName("pr");
	
	if(inizio==null || inizio.trim() === "")
		inizio=0;
	
	var el=null;
	for(let j=0; j<gruppo.length; j++){
		console.log("valore: "+gruppo[j].value);
		if(gruppo[j].checked){
			el = gruppo[j].value;
			break;
			}
	}
	
	if(el==null){
		el=0;
	}
	
	if ( xhr1) {
		countingAJAX(xhr1, el , inizio, "scrit"); 
	}
	else {
	  countingframe();
	}
			 
}

function invio(){
			document.getElementById('scrittura').style.display = 'block';	
}

setInterval(function() {
    lettura();  // Chiama la funzione lettura ogni 3 secondi
},3000);
