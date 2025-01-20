var elencoTesti = [];
var nArrived = 0;

function helper(jsonText, value) {
	var newPart = JSON.parse(jsonText);
	value = value + parseFloat(newPart);
	return value;
}

function parsificaJSONPar(elencoTesti) {	
	var value = 0.0;
	for (let i = 1; i <= 4; i++)
	{
		console.log(elencoTesti[i]);
		value = helper(elencoTesti[i], value);
	}
	result = "<p>Risultato: " + value + "</p>";
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
  			if (nArrived < 3)
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
	var el1 = document.getElementById("x1");
	var el2 = document.getElementById("x2");
	var	theXhr1 = new XMLHttpRequest();
	var	theXhr2 = new XMLHttpRequest();
	var	theXhr3 = new XMLHttpRequest();
	var	theXhr4 = new XMLHttpRequest();
    theXhr1.onreadystatechange = function() { trueCallback(theXhr1, 1); };
	theXhr2.onreadystatechange = function() { trueCallback(theXhr2, 2); };
    theXhr3.onreadystatechange = function() { trueCallback(theXhr3, 3); };
    theXhr4.onreadystatechange = function() { trueCallback(theXhr4, 4); };
	try {
		theXhr1.open("get", theUri + "?x1=" + el1.value + "&x2=" + el2.value + "&num=1", true);
		theXhr2.open("get", theUri + "?x1=" + el1.value + "&x2=" + el2.value + "&num=2", true);
		theXhr3.open("get", theUri + "?x1=" + el1.value + "&x2=" + el2.value + "&num=3", true);
		theXhr4.open("get", theUri + "?x1=" + el1.value + "&x2=" + el2.value + "&num=4", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	theXhr1.send(null);
	theXhr2.send(null);
	theXhr3.send(null);
	theXhr4.send(null);
}