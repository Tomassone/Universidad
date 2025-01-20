//crea una nuova connessione socket 
const socket = new WebSocket("ws://localhost:8080/TW_Esame3/actions");

let id = null;

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
	
	console.log(message)
	
	//se messaggio contiene update 
	 if(message.includes("iniziata"))
		 { 
		   document.getElementById("ritiro").disabled = false ;
		   document.getElementById("tombola").disabled = false;
		   id = setInterval(function() { send("estrai");}, 10000 );
		 	return;
			
		 }if(message.includes("terminata") || message==""){
			clearInterval(id);
			id = null;
			return;
		 }
}

function myFunction(argument)
{   
	var contenuto = null;
	
	if(argument!="admin"){
		
	 console.log("azione: "+argument);
	 send(argument);
	 
	}else{
		var nome = document.getElementById("user").value;
		var pass = document.getElementById("pw").value;
		
		 console.log(nome);
		 console.log(pass);
		
		if(nome=="admin" && pass=="admin"){		
			
			console.log("sono admin");	
			document.getElementById("admindiv").style.display = "block";			
            
			contenuto = document.getElementById('adminMessage').value;
			
			console.log(contenuto);
			
	        if(contenuto!=null){
			    send("update: "+contenuto);
			}
				
	 }else {
			console.log("Login sbagliato");
		}
   }
   
   
	
		
}



