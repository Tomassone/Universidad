//crea una nuova connessione socket 
const socket = new WebSocket("ws://localhost:8080/TW_Matrici/actions2");

let matrixA =[[ null, null, null ], [ null, null, null] , [null, null, null]];

//prende oggetto data, lo converte in una stringa json e lo invio
//tramite socket
function send( data) {
    var json = JSON.stringify(data);
    socket.send(json);
}

//ogni volta che un messaggio è ricevuto dal server
socket.onmessage =  function (event){
	
	//console.log("[socket.onmessage]", event.data);
	
	//converto messaggio json in un oggetti javascript
	var message = JSON.parse(event.data);
	//console.log(message)
	
	if (message.type === "update") {
	    	const { row, col, value } = message;
			matrixA[row][col] = value;
			document.getElementById(`valueA${row}${col}`).value = value;
   }
}


function parsificaJson( jsonText ) {
   
	// variabili di funzione
		var risultato = JSON.parse(jsonText);
		
		var element = document.getElementById("result");

		 element.innerText = "Matrice magica :"+ risultato.magica +'\n';
		
		if(risultato.magica==true){
		element.innerText+= "La somma e': "+risultato.somma+'\n';
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

function countingAJAX( theXhr, nome ,mata ) {	
	
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	try {
		theXhr.open("post", nome, true);
	}
	catch(e) {
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	var argument = "matriceA="+JSON.stringify(mata);
	console.log(argument);
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()



function countingframe() {
	alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
}// caricaFeedIframe()


//CONTROLLO PER OGNI INSERIMENTO E MANDO IN CASO DI COMPLETAMENTO
function check(){
	var count=0;
	        const inseriti = [];
			var elemento;
			var k = 0;
			for(let i=0; i<3; i++){
				for(let j=0; j<3; j++){
					
					elemento = document.getElementById("valueA"+i+j).value.trim();
					
					//SE E' UN NUMERO ED E' POSITIVO
					if( !isNaN(elemento) && parseInt(elemento)>0 && !inseriti.includes(elemento)){
						console.log("sono dentro");
						count++;
						inseriti[k] = elemento;
						//NEL MENTRE RIEMPIO ANCHE La MATRICe
						matrixA[i][j]= parseInt(elemento);
						
						//MANDO IL VALORE DI QUELLA CASELLA E SE HO COMPLETATO O MENO DI RIEMPIRE
						send({ type: "update", row: i, col: j, value: parseInt(elemento) });
					}
				
				}
			}
				
			//quando arrivo al numero totale di caselle riempite
			if(count==9){
				//MANDO CHE HO COMPLETATO DI RIEMPIRE LE CASELLE
				var	xhr = myGetXmlHttpRequest();
				var	xhr1 = myGetXmlHttpRequest();
	
						if ( xhr && xhr1) {
							countingAJAX(xhr, "servlet1" ,matrixA );
							countingAJAX(xhr, "servlet2" ,matrixA );
							
							} 
						else 
							countingframe();
	
				}
}
