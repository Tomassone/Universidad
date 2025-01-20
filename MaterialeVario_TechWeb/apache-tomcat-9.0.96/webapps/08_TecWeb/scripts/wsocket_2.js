//crea una nuova connessione socket 
const socket = new WebSocket("ws://localhost:8080/08_TecWeb/actions2");

//prende oggetto data, lo converte in una stringa json e lo invio
//tramite socket
function send( data) {
    var json = JSON.stringify(data);

    socket.send(json);
}

//ogni volta che un messaggio è ricevuto dal server
socket.onmessage =  function (event){
	
	console.log("[socket.onmessage]", event.data);
	
	//converto messaggio json in un oggetti javascript
	var message = JSON.parse(event.data);
	
	//se messaggio contiene update 
	 if(event.data.includes("update"))
		 {
		 	var toUpdate = message.update;
		 	var tuUpdateElement = document.getElementById(toUpdate);
			//aggiorno elemento html con il valore nuovo
		 	tuUpdateElement.value = message.valore;
		 	return;
		 }
    //se messaggio valido aggiorno i campi di oldrisultato e risultato
	//con i valori ricevuti
	 if(message.valid)
		 {
		 	var oldLog = document.getElementById("oldRis");
		 	var log = document.getElementById("risultato");
			log.value = "";
			oldLog.value = "";
		    console.log(event.data);
		    oldLog.value = message.oldRis;
		    log.value = message.risultato;
		 }else{
			 alert("hai superato il limite massimo di richieste per sessione");
		 }
	
}

function myFunction()
{   //recupero operandi e controllo che siano numerici
	var op1 = document.getElementById("op1").value;
	var op2 = document.getElementById("op2").value;
	if(isNaN(op1) || isNaN(op2))
		{
			alert("uno dei due operandi non è un numero");
			return;
		}
   //recupero il tipo di operazione
	var operazione = document.getElementsByName("op");
	var op = false;
	for (var i=0; i< operazione.length;i++)
		{
			if(operazione[i].checked)
				{
					op = operazione[i].value;
					break;
				}
		}
	if (!op) {
		alert("Selezionare operazione");
		return;
	}
	//creo nuovo oggetto operationreq da mandare 
	var operationReq = {};
	operationReq.op1 = op1;
	operationReq.op2 = op2;
	operationReq.operazione = op;
	
	send(operationReq);
	
}

//chiamo per aggiornare il server
function myUpdate(element)
{
	var elemento = document.getElementById(element);
	
	//verifico che elemento sia un numero
	if(isNaN(elemento.value))
	{
		alert("il contenuto inserito in "+element+" non è un numero");
		return;
	}
	
	// empty check
	if (elemento.value.length == 0){
		console.log("[myUpdate] element string is empty, returning");
		return;
	}
	
	//senno creo oggetto updatereq 
	var updateReq = {};
	updateReq.update = element;
	updateReq.valore = elemento.value;
	
	//converto in json e invio tramite websocket
	var json = JSON.stringify(updateReq);
	
	console.log("[myUpdate] sending", json);
    socket.send(json);
}
