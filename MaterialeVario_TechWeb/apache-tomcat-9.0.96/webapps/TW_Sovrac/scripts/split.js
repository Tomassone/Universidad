var tot=0;
var conta=0;
var requestCounter = 0;
var sessionStartTime = -1;
var lastCall = -1;

function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		
		tot += conteggio;
		conta++;
		
		// Ottengo la lista delle sezioni del documento
		if(conta==4){
		  var element = document.getElementById("ris");
		  element.innerText = "Calcolo integrale: "+ tot + '\n';		
		}

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

function countingAJAX( theXhr, nome, est1, est2) {	
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", nome, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "estr1="+est1+"&estr2="+est2;
	console.log(argument);
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()

function closeSession()
{
	//chiudure la sessione dopo una durata di 60 min
	//chiudere dopo inattività di 10 minuti
	requestCounter = 10;
	lastCall = 60000;
	alert('Sessione scaduta');
}

function sessionCounter()
{
	//impostare un timer 
	sessionStartTime = new Date().getTime();
	lastCall = sessionStartTime;
	setTimeout(closeSession(), 1000*60*60);
}

var inattività=0; 
function resetInactivityTimer() 
{ 
   clearTimeout(inattività); 
   inattività=setTimeout(closeSession, 1000*60*10); 
} 

function myfunction(){
			
	var x = parseFloat(document.getElementById("estremo1").value);
	var y= parseFloat(document.getElementById("estremo2").value);
	
	if(y<x){
		alert("Estremo y deve essere maggiore di x");
		return; 
   }
	
	var d = (y-x)/4;
	
	var actualCall = new Date().getTime();
	var current = Math.round(actualCall / (1000 * 60));
	var last = Math.round(lastCall / (1000 * 60));
	if(requestCounter >= 5 && (current - last)<=10)
			{
				lastCall = actualCall;
				requestCounter++;
	
	var	xhr = myGetXmlHttpRequest();
	var	xhr1 = myGetXmlHttpRequest();
	var	xhr2 = myGetXmlHttpRequest();
	var	xhr3 = myGetXmlHttpRequest();

	
    //VERIFICO CHE SIANO STATI CREATI CORRETTAMENTE
	if ( xhr && xhr1 && xhr2 && xhr3) {
		        countingAJAX(xhr, "Servlet1" , x , x+d); 
				countingAJAX(xhr1, "Servlet2" , x+d, x+ (2*d) ); 
				countingAJAX(xhr2, "Servlet3" , x +(2*d), x + (3*d)); 
				countingAJAX(xhr3, "Servlet4" , x +3*d , x+4*d ); 
	}
			else 
				countingframe();
			}else
			alert("Sessione terminata");
			
			resetInactivityTimer();
}

var k=0;

function controllo(){
	
	//NOME DEL FILE PRESENTE SERVER SIDE
	var file = document.getElementById("txt").value;
	var form = document.getElementById("MyForm");
	
	//CONTROLLO CHE SIA ALFANUMERICO
	var pattern = /^[a-zA-Z0-9 ]+$/;
	var risultato = pattern.test(file);
	console.log(file);
	
	if(risultato)
		k++;
	

	  if(k==10){	
		var jsonString = JSON.stringify(file);
	    document.getElementById("jsonData").value = jsonString;
		console.log("Sono qui");
		form.submit();
	  
	}
}