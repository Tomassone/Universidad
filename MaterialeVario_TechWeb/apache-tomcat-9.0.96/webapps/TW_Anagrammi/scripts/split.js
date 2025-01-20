var tot=0;
var conta=0;

function parsificaJson( jsonText ) {
   
	  conta++;
	
	// variabili di funzione
		var conteggio = JSON.parse(jsonText);
		
		if(conta==1){
		if(conteggio.testo == "servlet1"){
			var element = document.getElementById("ris");
            element.innerHTML+="SERVLET1 HA GENERATO: <br>";
			for(let i=0; i<10; i++){
				element.innerHTML+=`<input type="radio" name="par" onclick="invio('s1')">`;
			    element.innerHTML+= conteggio.parole[i] + `</input><br>`;
			}
		}
		else if(conteggio.testo=="servlet2"){
			var element = document.getElementById("tris");
            element.innerHTML+="SERVLET2 HA GENERATO: <br>";
			for(let i=0; i<10; i++){
				element.innerHTML+=`<input type="radio" name="parole" onclick="invio('s2')">`;
			    element.innerHTML+= conteggio.parole[i] + `</input><br>`;
			}
		}
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

function countingAJAX( theXhr, nome, parola) {	
	
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

	var argument = "parola="+parola;
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


//INVIO ALLE DUE SERVLET
function controllo(){
	
	//NOME DEL FILE PRESENTE SERVER SIDE
	var file = document.getElementById("fl1").value;
	
	var	xhr = myGetXmlHttpRequest();
    var	xhr1 = myGetXmlHttpRequest();
	
	//CONTROLLO CHE SIANO CARATTERI ALFABETICI MINUSCOLI
	var pattern = /^[a-z$]+$/;
	var risultato = pattern.test(file);
	console.log(file);
	
	if(!risultato){
		document.getElementById("fl1").value="";
		alert("attenzione! il nome del file può contenere solo caratteri alfabetici minuscoli!")
	}else{	
		
	  if(file.endsWith("$")){	
		console.log("Sono qui");
		
		if(xhr && xhr1){
			countingAJAX(xhr, "servlet1" , file); 
		    countingAJAX(xhr1, "servlet2" ,  file); 
		}else{
			countingframe();
		}
	  }
     
	}
}

function invio(arg){
	
	//SE HA VINTO LA PRIMA IO DEVO TERMINANRE L'ALTRA
	if(arg==="s1"){
	   document.getElementById("t2").value = 1;
	   document.getElementById("v2").submit();
	 }
    else if(arg==="s2"){
		document.getElementById("t1").value = 1;
	    document.getElementById("v1").submit();
	}
	
}