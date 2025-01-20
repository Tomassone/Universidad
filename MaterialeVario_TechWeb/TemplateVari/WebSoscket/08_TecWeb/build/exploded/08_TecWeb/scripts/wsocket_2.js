const socket = new WebSocket("ws://localhost:8080/08_TecWeb/actions2");

function send(data) {
    var json = JSON.stringify(data);

    socket.send(json);
}


socket.onmessage = function (event){
	
	 var message = JSON.parse(event.data);
	 if (event.data.includes("update"))
	 {
		 var toUpdate = message.update;
		 var log = document.getElementById(toUpdate);
		 log.value = message.valore;
	 }
	 if (message.valid)
	 {
	 	var log = document.getElementById("risultato");
		log.value = "";
	    log.value = message.risultato;
	 } else{
		 alert("hai superato il limite massimo di richieste per sessione");
	 }
}

function updateValue(element)
{
	var update = element.name;
	var valore = element.value;
	
	if (isNaN(valore))
	{
		alert("l'operando non è un numero");
		return;
	}
	
	var updateReq = {};
	updateReq.update = update;
	updateReq.valore = valore;
	
	send(updateReq);
}

function myFunction()
{
	var op1 = document.getElementById("op1").value;
	var op2 = document.getElementById("op2").value;
	if(isNaN(op1) || isNaN(op2))
		{
			alert("uno dei due operandi non è un numero");
			return;
		}
	var operazione = document.getElementsByName("op");
	var op;
	for (var i = 0; i < operazione.length; i++)
	{
		if (operazione[i].checked)
		{
			op = operazione[i].value;
			break;
		}
	}
	var updateOperator = {};
	updateOperator.op1 = op1;
	updateOperator.op2 = op2;
	updateOperator.operazione = op;
	
	send(updateOperator);
	
}