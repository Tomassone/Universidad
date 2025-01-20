var elencoTesti = [];
var nArrived = 0;

function helper(jsonText) {
	var numb = JSON.parse(jsonText);
	return numb;
}

function parsificaJSONPar(elencoTesti) {	
	let finalNumber = 0;
	
	for (let i = 1; i <= 2; i++)
	{
		console.log(elencoTesti[i]);
		finalNumber = finalNumber + helper(elencoTesti[i]);
	}

	result = "<p>Risultato: " + finalNumber + "<p>";
	
	console.log(result);

	// restituzione dell'html da aggiungere alla pagina
	return result;
}

function trueCallback(theXhr, part) {
	var container = document.getElementById("fillMe");
	// verifica dello stato
	if ( theXhr.readyState === 2) {
	    container.value = "Richiesta inviata...";
	}
	else if ( theXhr.readyState === 3) {
    	container.value = "Ricezione della risposta...";
	}
	else if ( theXhr.readyState === 4 ) {
	// verifica della risposta da parte del server
        if (theXhr.status === 200) {
        	// operazione avvenuta con successo
  			if (nArrived < 1)
  			{
  				elencoTesti[part] = theXhr.responseText;
  				nArrived++;
  				//console.log("nArrived = " + nArrived);
  			}
  			else
  			{
				elencoTesti[part] = theXhr.responseText;
  				nArrived++;
				//console.log("Cambio il DOM");
				container.innerHTML = parsificaJSONPar(elencoTesti);
			}
        }
        else {
        	// errore di caricamento
        	container.value = "Impossibile effettuare l'operazione richiesta.<br />";
        	container.value += "Errore riscontrato: " + theXhr.statusText;
        }
	}
} 

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function avviaAJAX(theUri) {
    let input = document.getElementById("input").value;
    let path = document.getElementById("path").value;

    let theXhr1 = new XMLHttpRequest();
    let theXhr2 = new XMLHttpRequest();

    theXhr1.onreadystatechange = function() { trueCallback(theXhr1, 1); };
    theXhr2.onreadystatechange = function() { trueCallback(theXhr2, 2); };

    try {
        // Define parameters for the POST requests
        let params1 = `num=1&path=${encodeURIComponent(path)}&input=${encodeURIComponent(input)}`;
        let params2 = `num=2&path=${encodeURIComponent(path)}&input=${encodeURIComponent(input)}`;

        // Open POST requests
        theXhr1.open("post", theUri, true);
        theXhr2.open("post", theUri, true);

        // Set Content-Type header for POST requests
        theXhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        theXhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        // Send POST requests with parameters
        theXhr1.send(params1);
        theXhr2.send(params2);
    } catch (e) {
        // Exceptions are raised when trying to access cross-domain URIs
        alert(e);
    }
}