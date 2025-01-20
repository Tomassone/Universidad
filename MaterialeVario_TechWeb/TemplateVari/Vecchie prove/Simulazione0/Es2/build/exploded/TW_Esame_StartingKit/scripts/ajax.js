function parsificaJSON(jsonText) {
		// Otteniamo la lista degli item dall'RSS 2.0 di edit
	var items = JSON.parse(jsonText);

	// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
	itemNodes = new Array(),

	// la variabile di ritorno, in questo esempio, e' testuale
	risultato = "";

	// ciclo di lettura degli elementi
	for (var a = 0, b = items.length; a < b; a++) {
		itemNodes[a] = new Object();
		itemNodes[a].coordinate = items[a].coordinate;
		itemNodes[a].nome = items[a].nome;
		itemNodes[a].descrizione = items[a].descrizione;
	}

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	risultato = "<p>Location future: </p>" + "<ul>";

	for( var c = 0; c < itemNodes.length; c++ ) {
		risultato += '<li class="item"><strong>' + itemNodes[c].nome +'</strong><br/></li>';
	};

	// chiudiamo la lista creata
	risultato += "</ul>";
	
	return risultato;
}

function callback(theXhr, theElement) {
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    theElement.innerHTML = "Richiesta inviata...";
	}
	else if ( theXhr.readyState === 3 ) {
    	theElement.innerHTML = "Ricezione della risposta...";
	}
	else if ( theXhr.readyState === 4 ) {
	// verifica della risposta da parte del server
        if ( theXhr.status === 200 ) {
        	// operazione avvenuta con successo
  			theElement.innerHTML = parsificaJSON(theXhr.responseText);
        }
        else {
        	// errore di caricamento
        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
        	theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
        	console.log(theXhr.responseText);
        }
	}
} 

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function catalogoAJAX(theUri, theElement) {
    
    // assegnazione oggetto XMLHttpRequest
	var	theXhr = new XMLHttpRequest();
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// invio richiesta
	theXhr.send(null);
}