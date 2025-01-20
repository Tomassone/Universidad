var arrived = false;
var minCons = "bcdfghjklmnpqrstvwxyz";
var minVoc = "aeiou";

function parsificaJSON(jsonText) {
		// Otteniamo la lista degli item dall'RSS 2.0 di edit
	var items = JSON.parse(jsonText);

	// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
	itemNodes = new Array(),

	// la variabile di ritorno, in questo esempio, e' testuale
	risultato = "";
	content = "";

	// ciclo di lettura degli elementi
	for (var a = 0; a < items.length; a++) {
		content = content + items[a] + "\n";
	}

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	if (minCons.includes(content[0]))
		risultato = "<p>Consonanti vincenti</p>"
	else
		risultato = "<p>Vocali vincenti</p>"

	// chiudiamo il form creato
	risultato += "<p>" + content + "</p>"

	// restituzione dell'html da aggiungere alla pagina
	return risultato;
}

function callback(theXhr, theElement) {
	// verifica dello stato
	if ( theXhr.readyState === 2 && arrived == false) {
	    theElement.value = "Richiesta inviata...";
	}
	else if ( theXhr.readyState === 3 && arrived == false) {
    	theElement.value = "Ricezione della risposta...";
	}
	else if ( theXhr.readyState === 4 ) {
	// verifica della risposta da parte del server
        if (theXhr.status === 200) {
        	// operazione avvenuta con successo
        	if (arrived == false)
        	{
			 	var container = document.getElementById("cont");
  				container.innerHTML = parsificaJSON(theXhr.responseText);
  				document.getElementById("button-cont").style.visibility='hidden'; 
  				arrived = true;
  			}
  			else
  				arrived = false;
        }
        else {
        	// errore di caricamento
        	theElement.value = "Impossibile effettuare l'operazione richiesta.<br />";
        	theElement.value += "Errore riscontrato: " + theXhr.statusText;
        	console.log(theXhr.responseText);
        }
	}
} 

function checkWord(word) {
	
	if (word.length > 21 || word.length < 6)
		alert("Parola troppo corta o lunga!");
		
	if (word[word.length - 1] != "%")
		alert("Ultimo elemento non valido!");
		
	var minAlphabet = "abcdefghijklmnopqrstuvwxyz"
	var nCons = 0;
	var nVoc = 0;
	
	for(var c = 0; c < word.length - 1; c++) {
		if (!minAlphabet.includes(word[c]))
			alert("Abbiamo un carattere non alfabetico!");
		if (minCons.includes(word[c]))
			nCons++;
		if (minVoc.includes(word[c]))
			nVoc++;
	};
	
	if (nCons == 0 || nVoc == 0)
		alert("Non ci sono abbastanza vocali o consonanti!");
}

/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function avviaAJAX(theUri) {
    
    // assegnazione oggetto XMLHttpRequest
	var	firstXhr = new XMLHttpRequest();
	var	secondXhr = new XMLHttpRequest();
    var theElement = document.getElementById("word");
    
    checkWord(theElement.value);
    
	// impostazione controllo e stato della richiesta
	firstXhr.onreadystatechange = function() { callback(firstXhr, theElement); };
	secondXhr.onreadystatechange = function() { callback(secondXhr, theElement); };
		
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		firstXhr.open("get", theUri + "?word=" + theElement.value + "25&tipo=voc", true);
		secondXhr.open("get", theUri + "?word=" + theElement.value + "25&tipo=cons", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	
	// invio richiesta
	firstXhr.send(null);
	secondXhr.send(null);
}