
function callback(theXhr) {
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    console.log("Richiesta inviata...");
	}
	else if ( theXhr.readyState === 3 ) {
    	console.log("Ricezione della risposta...");
	}
	else if ( theXhr.readyState === 4 ) {
	// verifica della risposta da parte del server
        if (theXhr.status === 200) {
        	console.log("Successo!");
        	// Check if the server's response contains a flag to redirect
            if (theXhr.responseText === "redirect") {
                // Perform client-side redirection
                window.location.href = "articolo.jsp";
            }
        }
        else {
        	// errore di caricamento
        	console.log("Impossibile effettuare l'operazione richiesta.");
        	console.log("Errore riscontrato: " + theXhr.statusText);
        	console.log(theXhr.responseText);
        }
	}
} 

function checkPath(path) {
	return (path && path.length > 0 && path[path.length - 1] === "%");
}

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function avviaAJAX(theUri) {
    
    var theElement = document.getElementById("path");
    console.log(theElement.value);
    
        
    if (checkPath(theElement.value))
    {
		// assegnazione oggetto XMLHttpRequest
		var	theXhr = new XMLHttpRequest();
		
		// impostazione controllo e stato della richiesta
		theXhr.onreadystatechange = function() { callback(theXhr); };
			
		// impostazione richiesta asincrona in GET
		// del file specificato
		try {
			theXhr.open("get", theUri + "?path=" + encodeURIComponent(theElement.value), true);
		}
		catch(e) {
			// Exceptions are raised when trying to access cross-domain URIs 
			alert(e);
		}
		
		// invio richiesta
		theXhr.send(null);
	}
}