function callback( Xhr , Element) {

	if ( Xhr.readyState === 4 ) {
		// verifica della risposta da parte del server
	if ( Xhr.status === 200 ) {
				// La risposta è arrivata, quindi aggiorniamo il campo di input
				 var category = Xhr.responseText.trim();  // La risposta dal server
				 if (category) {
				        Element.value = category;  // Sostituiamo il valore dell'input con la categoria trovata
				  }		
		}// if 200
	}// if 4

}

// Funzione che viene chiamata quando l'utente digita nel campo di input
function completa(Uri, Element) {
       // Crea una richiesta AJAX per inviare il parametro al server
       var xhr = new XMLHttpRequest();
    
	   if(xhr){
		   xhr.open("get", Uri, true);
	      
	       xhr.onreadystatechange = function() {
			callback(xhr, Element);
	       };
		   
	       xhr.send(null);  // Invia la richiesta al server
	   }
}



