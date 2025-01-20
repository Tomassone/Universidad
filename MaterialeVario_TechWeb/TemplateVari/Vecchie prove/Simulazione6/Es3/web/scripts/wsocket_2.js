const socket = new WebSocket("ws://localhost:8080/08_TecWeb/actions2");

function send(data) {
    var json = JSON.stringify(data);
    socket.send(json);
}


socket.onmessage = function (event){
	
	var message = JSON.parse(event.data);
	var log = document.getElementById("storico");
	console.log("Messaggio {" + message.mex + "} arrivato!");
	if (message.tipo == 0 && log != null)
		log.value = message.mex + "; " + log.value;
	else if (message.tipo == 1 && log != null)
		log.value = message.mex;
}

function myFunction(tipo)
{
	var tipoMex = {};
	if (tipo == 0) {
		var messaggio = document.getElementById("mex");
		tipoMex.mex = messaggio.value;
	}
	else {
		tipoMex.mex = "nothing";
	}
	tipoMex.tipo = parseInt(tipo);
	console.log("Invio {" + tipoMex.mex + "} al server, tipo: " + tipoMex.tipo);
	send(tipoMex);
}