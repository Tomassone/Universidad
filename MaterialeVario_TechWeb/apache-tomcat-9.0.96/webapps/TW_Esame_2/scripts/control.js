var conta=0;

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		// Ottengo la lista delle sezioni del docuemento
		var element = document.getElementById("ris");
		element.innerText = "TempoB: "+ conteggio.tempoBean+" TempoS: "+conteggio.tempoServlet+'\n';
		element.innerText = element.innerText + "ContaB: "+ conteggio.contaBean+" ContaS: "+conteggio.contaServlet+"";
		

}// parsificaJSON()


function callback( theXhr ) {
	
	if ( theXhr.readyState === 2 ) {}// if 2
	else if ( theXhr.readyState === 3 ) { }// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			parsificaJson(theXhr.responseText);
		}// if 200
		 else {
			 alert("Impossibile effettuare l'operazione richiesta.");        	
	        }// else (if ! 200)
	}// if 4
} // callback();

function countingAJAX( theXhr, elements) {
    //sono gli elementi checkati
	var file1 = elements[0].value;
	var file2 = elements[1].value;
	var file3 = elements[2].value;
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", "S1Servlet", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "file1="+file1+"&file2="+file2+"&file3="+file3;
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()


function controllo(element){
	if(element.checked==true){
		conta++;
		//SE GLI ELEMENTI CHECKATI SONO 3 
		if(conta == 3){
			conta=0;
			
			//MI PRENDO I FILE SCRITTI NEGLI INPUT
			const el = document.getElementsByTagName("input");
			console.log(el.length+"\n");
			var check = [];
			for(let i=0; i<el.length; i++){
				//SE QUESTO FILE è STATO CHECCKATO LO AGGIUNGO
				if(el[i].checked == true)
					check.push(el[i]);
				
				//console.log(el[i]+"\n");
			}
			
			var	xhr = myGetXmlHttpRequest();
			if ( xhr ) 
				countingAJAX(xhr, check); 
			else 
				countingframe();
		}
	}
	
}