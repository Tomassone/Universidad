
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
        	location.reload();
        }
        else {
        	// errore di caricamento
        	console.log("Impossibile effettuare l'operazione richiesta.\n" + "Errore riscontrato: " + theXhr.responseText);
        }
	}
} 

function dayOfTheYear(date)
{
	var start = new Date(date.getFullYear(), 0, 0);
	var diff = date - start;
	var oneDay = 1000 * 60 * 60 * 24;
	var day = Math.floor(diff / oneDay);
	return day;
}

function updatePrice(theUri) {
    
    // assegnazione oggetto XMLHttpRequest
	var	xhr = new XMLHttpRequest();
        
	// impostazione controllo e stato della richiesta
	xhr.onreadystatechange = function() { callback(xhr); };
		
	// impostazione richiesta asincrona in GET
	// del file specificato
	
	var id = document.getElementById("id");
	var checkin = document.getElementById("checkin");
	var checkout = document.getElementById("checkout");
	
	if (checkin == null || checkout == null || id == null)
		alert("Uno dei valori è nullo");
		
	if (checkin > dayOfTheYear(new Date()))
		alert("CheckIn maggiore di oggi!");
	
	if (checkin > checkout)
		alert("CheckIn maggiore di Checkout!");
	
	//console.log(theUri + "?" + theElement.name + "=" + theElement.value);
	
	try {
		xhr.open("get", theUri + "?" + id.name + "=" + id.value + "&" + checkin.name + "=" + checkin.value + "&" +  checkout.name + "=" + checkout.value, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	
	// invio richiesta
	xhr.send(null);
}