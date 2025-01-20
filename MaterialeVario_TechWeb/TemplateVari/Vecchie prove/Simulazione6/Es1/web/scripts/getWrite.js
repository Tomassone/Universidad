
function parsificaJSONSeq(jsonText, el) {
	var caso = JSON.parse(jsonText);

	if (caso === "yes")
		el.readOnly = false;
	else 
		el.readOnly = true;
}

function trueCallback(theXhr) {
	var article = document.getElementById("article");
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
			console.log("Successo!");
        	// operazione avvenuta con successo
        	parsificaJSONSeq(theXhr.responseText, article);
        	var toBeFilled = document.getElementById("fillMe");
        	toBeFilled.innerHTML = '<input type="submit" value="Scrivi">';
        }
        else {
        	// errore di caricamento
        	article.value = "Impossibile effettuare l'operazione richiesta.<br />";
        	article.value += "Errore riscontrato: " + theXhr.statusText;
        }
	}
} 

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function avviaAJAX(theUri) {
 
	var	theXhr = new XMLHttpRequest();
    theXhr.onreadystatechange = function() { trueCallback(theXhr); };
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	theXhr.send(null);
}