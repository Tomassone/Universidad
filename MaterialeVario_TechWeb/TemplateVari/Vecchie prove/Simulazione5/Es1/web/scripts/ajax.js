
function callback(theXhr) {
	// verifica dello stato
	if ( theXhr.readyState === 2) {
	    console.log("Richiesta inviata...");
	}
	else if ( theXhr.readyState === 3) {
    	console.log("Ricezione della risposta...");
	}
	else if ( theXhr.readyState === 4 ) {
	// verifica della risposta da parte del server
        if (theXhr.status === 200) {
        	// operazione avvenuta con successo
        	console.log("Successo!");
        }
        else {
        	// errore di caricamento
        	console.log("Impossibile effettuare l'operazione richiesta.\n" + "Errore riscontrato: " + theXhr.responseText);
        }
	}
}

function adminAjax(theUri, el) {
    
    // assegnazione oggetto XMLHttpRequest
	var	xhr = new XMLHttpRequest();
        
	// impostazione controllo e stato della richiesta
	xhr.onreadystatechange = function() { callback(xhr); };
	
	try {
		xhr.open("get?id=" + el.value, theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	
	// invio richiesta
	xhr.send(null);
} 

function avviaAjax(theUri) {
    
    // assegnazione oggetto XMLHttpRequest
	var	xhr = new XMLHttpRequest();
        
	// impostazione controllo e stato della richiesta
	xhr.onreadystatechange = function() { callback(xhr); };
	
	try {
		xhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	
	// invio richiesta
	xhr.send(null);
}