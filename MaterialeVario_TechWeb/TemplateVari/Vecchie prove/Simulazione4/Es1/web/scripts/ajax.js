var elencoSelezionati = [];

function parsificaJSON(jsonText) {
		// Otteniamo la lista degli item dall'RSS 2.0 di edit
	var counterRes = JSON.parse(jsonText);
	var items = counterRes.elencoConteggi;

	risultato = "";

	for (var c = 0; c < items.length; c++) {
		risultato += "<p>" + elencoSelezionati[c].value + " ha questo numero di maiuscole: " + items[c] + "</p>";
	};
	
	risultato = risultato + "<p> Durata servlet: " + counterRes.durataServlet + "; durata bean: " + counterRes.durataBean + "</p>";

	// restituzione dell'html da aggiungere alla pagina
	return risultato;

}

function callback(theXhr) {
	var theElement = document.getElementById("fillMe");
	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
		console.log("Richiesta inviata...");
	}
	else if ( theXhr.readyState === 3 ) {
    	console.log("Ricezione della risposta...");
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

function resetCheckbox() {
    const checkboxes = document.getElementsByName("option");
    for (let i = 0; i < checkboxes.length; i++)
		checkboxes[i].checked = false; // Set the checkbox to unchecked
	elencoSelezionati = [];
}

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function avviaAJAX(theUri) {
    
    var elencoOpzioni = document.getElementsByName("option");
    
    for (let i = 0; i < elencoOpzioni.length; i++)
		if (elencoOpzioni[i].checked)
			elencoSelezionati.push(elencoOpzioni[i]);
			
	if (elencoSelezionati.length == 3)
	{
		console.log("3 file selezionati!");
		
		// assegnazione oggetto XMLHttpRequest
		var	theXhr = new XMLHttpRequest();
	    
		// impostazione controllo e stato della richiesta
		theXhr.onreadystatechange = function() { callback(theXhr); };
	
		// impostazione richiesta asincrona in GET
		// del file specificato
		try {
			theXhr.open("get", theUri + "?f1=" + elencoSelezionati[0].value + "&f2=" + elencoSelezionati[1].value + "&f3=" + elencoSelezionati[2].value, true);
		}
		catch(e) {
			// Exceptions are raised when trying to access cross-domain URIs 
			alert(e);
		}
	
		// invio richiesta
		theXhr.send(null);
	}
	else
		elencoSelezionati = [];
}