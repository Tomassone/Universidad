var sottoMatrici = [];
var nArrived = 0;

function parsificaJSONSeq(jsonText) {
	var items = JSON.parse(jsonText);

	result = "<p>Risultato: <p>";
	
	for (var i = 0; i < 2; i++) 
	{
		result = result + "<p>";
		for (var j = 0; j < 4; j++)
			result = result + items[i][j] + " ";
		result = result + "</p>";
	}
	
	console.log(result);

	// restituzione dell'html da aggiungere alla pagina
	return result;
}

function helper(jsonText, part, matr) {
	var items = JSON.parse(jsonText);

	switch(part)
	{
		case 1:
			i_l = 0;
			i_u = 1;
			j_l = 0;
			j_u = 2;
			break;
		case 2:
			i_l = 1;
			i_u = 2;
			j_l = 0;
			j_u = 2;
			break;
		case 3:
			i_l = 0;
			i_u = 1;
			j_l = 2;
			j_u = 4;
			break;
		case 4:
			i_l = 1;
			i_u = 2;
			j_l = 2;
			j_u = 4;
			break;
	};
	
	for (let i = i_l; i < i_u; i++) {
		for (let j = j_l; j < j_u; j++) {
			matr[i][j] = items[i][j];
		}
	}

	return matr;
}

function parsificaJSONPar(elencoTesti) {	
	let matr = [];
	const rows = 2;
	const columns = 4;
	
	for (let i = 0; i < rows; i++) {
		matr[i] = [];
		for (let j = 0; j < columns; j++) {
			matr[i][j] = "0";
		}
	}
	
	for (let i = 1; i <= 4; i++)
	{
		console.log(elencoTesti[i]);
		matr = helper(elencoTesti[i], i, matr);
	}

	result = "<p>Risultato: <p>";
	
	for (var i = 0; i < 2; i++) 
	{
		result = result + "<p>";
		for (var j = 0; j < 4; j++)
			result = result + matr[i][j] + " ";
		result = result + "</p>";
	}
	
	console.log(result);

	// restituzione dell'html da aggiungere alla pagina
	return result;
}

function trueCallback(theXhr, type, part) {
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
        	if (type === 'seq')
  				container.innerHTML = parsificaJSONSeq(theXhr.responseText);
  			else if (type == 'par' && nArrived < 3)
  			{
  				sottoMatrici[part] = theXhr.responseText;
  				nArrived++;
  				//console.log("nArrived = " + nArrived);
  			}
  			else
  			{
				sottoMatrici[part] = theXhr.responseText;
  				nArrived++;
				//console.log("Cambio il DOM");
				container.innerHTML = parsificaJSONPar(sottoMatrici);
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
function avviaAJAX(theUri, type) {
	
    if (type == "seq") {
		var	theXhr = new XMLHttpRequest();
	    theXhr.onreadystatechange = function() { trueCallback(theXhr, type, 0); };
		try {
			theXhr.open("get", theUri, true);
		}
		catch(e) {
			// Exceptions are raised when trying to access cross-domain URIs 
			alert(e);
		}
		theXhr.send(null);
	}
	else {
		var	theXhr1 = new XMLHttpRequest();
		var	theXhr2 = new XMLHttpRequest();
		var	theXhr3 = new XMLHttpRequest();
		var	theXhr4 = new XMLHttpRequest();
	    theXhr1.onreadystatechange = function() { trueCallback(theXhr1, type, 1); };
		theXhr2.onreadystatechange = function() { trueCallback(theXhr2, type, 2); };
	    theXhr3.onreadystatechange = function() { trueCallback(theXhr3, type, 3); };
	    theXhr4.onreadystatechange = function() { trueCallback(theXhr4, type, 4); };
		try {
			theXhr1.open("get", theUri + "?num=1", true);
			theXhr2.open("get", theUri + "?num=2", true);
			theXhr3.open("get", theUri + "?num=3", true);
			theXhr4.open("get", theUri + "?num=4", true);
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
}