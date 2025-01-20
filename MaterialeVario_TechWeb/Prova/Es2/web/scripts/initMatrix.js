
var elencoTesti = [];
var nArrived = 0;

let rows = 0;
let columns = 0;

function buildMatrix(elencoTesti) {	
	let elencoVettori = [];
	elencoVettori.push(JSON.parse(elencoTesti[0]));
	elencoVettori.push(JSON.parse(elencoTesti[1]));
	elencoVettori.push(JSON.parse(elencoTesti[2]));
	let matr = [];
	
	for (let i = 0; i < rows; i++) {
		matr[i] = [];
		for (let j = 0; j < columns; j++) {
			let pos = i * columns + j;
			if (pos < (rows * columns) / 3) {
				matr[i][j] = (elencoVettori[0])[pos];
			}
			else if (pos >= 2 * (rows * columns) / 3 && pos < (rows * columns)) {
				matr[i][j] = (elencoVettori[0])[pos - (rows * columns) / 3];
			}
			else {
				matr[i][j] = (elencoVettori[0])[pos - 2*(rows * columns) / 3];
			}
		}
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

function callback(theXhr, part) {
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
  			if (nArrived < 2)
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
				container.innerHTML = buildMatrix(elencoTesti);
			}
        }
        else {
        	// errore di caricamento
        	container.value = "Impossibile effettuare l'operazione richiesta.<br />";
        	container.value += "Errore riscontrato: " + theXhr.statusText;
        }
	}
} 

function setValue(theUri) {
    
    var content = document.getElementById("content");
	var a = document.getElementById("a");
	var b = document.getElementById("b");
	
	if (a.value != null && b.value != null && path.value != null)
	{
		rows = (parseInt(a));
		columns = (parseInt(b));
		
		let initData = JSON.parse(content);
		let numListA = initData.numListA;
		let numListB = initData.numListB;
		let firstListA = [], secondListA = [], thirdListA = [];
		let firstListB = [], secondListB = [], thirdListB = [];

		for (let i = 0; i < (parseInt(a) * parseInt(b)) / 3; i++) {
			firstListA[i] = parseInt(numListA[i]);
			firstListB[i] = parseInt(numListB[i]);
		}
		for (let i = (parseInt(a) * parseInt(b)) / 3; i < 2 * (parseInt(a) * parseInt(b)) / 3; i++) {
			secondListA[i - (parseInt(a) * parseInt(b)) / 3] = parseInt(numListA[i]);
			secondListB[i - (parseInt(a) * parseInt(b)) / 3] = parseInt(numListB[i]);
		}
		for (let i = 2 * (parseInt(a) * parseInt(b)) / 3; i < (parseInt(a) * parseInt(b)); i++) {
			thirdListA[i - 2 * (parseInt(a) * parseInt(b)) / 3] = parseInt(numListA[i]);
			thirdListB[i - 2 * (parseInt(a) * parseInt(b)) / 3] = parseInt(numListB[i]);
		}
		
		var	theXhr1 = new XMLHttpRequest();
		var	theXhr2 = new XMLHttpRequest();
		var	theXhr3 = new XMLHttpRequest();
	    theXhr1.onreadystatechange = function() { trueCallback(theXhr1, 1); };
		theXhr2.onreadystatechange = function() { trueCallback(theXhr2, 2); };
	    theXhr3.onreadystatechange = function() { trueCallback(theXhr3, 3); };
		try {
			theXhr1.open("post", theUri, true);
			theXhr2.open("post", theUri, true);
			theXhr3.open("post", theUri, true);
			theXhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			theXhr2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			theXhr3.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		}
		catch(e) {
			// Exceptions are raised when trying to access cross-domain URIs 
			alert(e);
		}
		theXhr1.send("num=1&p1=" + JSON.stringify(firstListA) + "&p2=" + JSON.stringify(firstListB));
		theXhr2.send("num=2&p1=" + JSON.stringify(secondListA) + "&p2=" + JSON.stringify(secondListB));
		theXhr3.send("num=3&p1=" + JSON.stringify(thirdListA) + "&p2=" + JSON.stringify(thirdListB));
	}
}