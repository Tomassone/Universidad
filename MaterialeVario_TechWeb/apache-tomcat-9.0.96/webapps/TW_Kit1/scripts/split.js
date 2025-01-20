

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		var min = conteggio[0][0] + conteggio[0][1]+conteggio[0][2]+ conteggio[0][3]+ conteggio[0][4]+ conteggio[0][5];
		var max = conteggio[1][0] + conteggio[1][1]+conteggio[1][2]+ conteggio[1][3]+ conteggio[1][4]+ conteggio[1][5];
		
		
	    var element = document.getElementById("result");
	    element.innerText =   "Minuscole:"+min + " - Maiuscole:"+max+ '\n';		
		

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

function countingAJAX( theXhr, nomefile1, nomefile2) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", "servlet1", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "file1="+nomefile1+"&file2="+nomefile2;
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
	
	//Controllo che i file esistano nella servlet
	var f1 = document.getElementById("fl1").value.trim();
	var f2 = document.getElementById("fl2").value.trim();
	
	var	xhr = myGetXmlHttpRequest();

    //VERIFICO CHE SIANO STATI CREATI CORRETTAMENTE
	if ( xhr) {
		        countingAJAX(xhr, f1, f2); 
	}
	else {
		countingframe();}
	
}

