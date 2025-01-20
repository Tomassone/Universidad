var tot=0;

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		var a = conteggio[0];
		var b = conteggio[1];
		tot++;
		if(tot==2){
	    var element = document.getElementById("result");
	    element.innerText = "Sono state trovate "+a+"+"+b+" occorenze\n";		
		}else {
			console.log("Devo finire di contare");
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

function countingAJAX( theXhr, nomefile, inizio, fine) {	
	
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

	var f = nomefile.slice(inizio, fine);
	//console.log(f);
	var argument = "file="+f;
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

function myfunction(){
	
	//Controllo che i file esistano nella servlet
	var file = document.getElementById("fl").files[0];
	
	// Crea due XMLHttpRequest per le due richieste
    var xhr1 = new XMLHttpRequest();
    var xhr2 = new XMLHttpRequest();
	
	if (file) {
	        // Leggi il file come testo (JSON)
	        var reader = new FileReader();
			
	        reader.onload = function(e) {
	         var jsonText = e.target.result;  // Contenuto del file JSON
	         var lunghezza = Math.ceil(jsonText.length / 2);

	            // Invio delle due richieste
	            countingAJAX(xhr1, jsonText, 0, lunghezza);
	            countingAJAX(xhr2, jsonText, lunghezza, jsonText.length);
	        };
	        reader.readAsText(file);
	}
	else {
		countingframe();}
	
}

