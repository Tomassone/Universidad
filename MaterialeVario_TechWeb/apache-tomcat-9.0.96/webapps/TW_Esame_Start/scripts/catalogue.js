

function loadCat( jsonText ) {
   
	// variabili di funzione
		// Ottengo la lista delle sezioni del docuemento
		var catalogo = JSON.parse(jsonText);
		var i = 0;
		var editable = "";
		var myTr = document.getElementById("myTr")
		
		var result = "";
		
		result = result + "</tr>";
		
		
		for(i =0; i< catalogo.length ; i++)
			{
			result = result + "<tr>";
				
				result = result + "<form action=\"#\">";
				
				result = result + "<td>"+catalogo[i].descrizione+"</td>";
				result = result + "<td>"+catalogo[i].prezzo+" &#8364;</td>";
				result = result + "<td><input type=\"text\" name=\"order\" style=\"background-color: #c3c3d7;\"/></td>";
				result = result + "<td>";
				result = result + "<input type=\"hidden\" name=\"descrizione\" value=\""+catalogo[i].descrizione+"\"/>";
				result = result + "<input type=\"hidden\" name=\"quantita\" value=\""+catalogo[i].quantita+"\"/>";
				result = result + "<input type=\"hidden\" name=\"prezzo\" value=\""+catalogo[i].prezzo+"\"/>";
				result = result + "<input type=\"submit\" name=\"add\" value=\"add to cart\"/>";
				
				result = result + "</td>";
				
				result = result + "</form>";
				
				
				result = result + "</tr>";
			
			}
		
		result = result + "<tr>";
		
		myTr.innerHTML = result;
	
	
     

}// parsificaJSON()





/*
 * Funzione di callback
 */
function callback( theXhr ) {

	
	if ( theXhr.readyState === 2 ) {
	    	//theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	//	theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			loadCat(theXhr.responseText);
			alert("Modifiche non caricate: Errore di Versione!!\n nuova versione scaricata");
			//location.reload();
		}// if 200

		 else {
	        	// errore di caricamento
			 alert("Impossibile effettuare l'operazione richiesta.");
//	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
//	        	theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
	        }// else (if ! 200)
	}// if 4

} // callback();


function pullCatframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	Alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



function pullCatAJAX( theXhr) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", "carrello", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

} // caricaFeedAJAX()

function myFunc()
{
	// assegnazione oggetto XMLHttpRequest
		var	xhr = myGetXmlHttpRequest();
		if ( xhr ) 
			pullCatAJAX(xhr); 
		else 
			pullCatframe(); 

}