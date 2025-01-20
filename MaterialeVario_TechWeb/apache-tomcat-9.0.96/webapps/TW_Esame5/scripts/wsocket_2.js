//crea una nuova connessione socket 
const socket = new WebSocket("ws://localhost:8080/TW_Esame5/actions");

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
	
	console.log("messaggio: "+message)
	
	//se messaggio deve essere visualizzato 
	 if(Array.isArray(message))
		 { 
			console.log("Voglio visualizzare: "+ message);
		    var element = document.getElementById("result");
			element.innerHTML="MESSAGGI RICEVUTI: \n";
			for(let i=0; i<message.length; i++){
				element.innerHTML+= message[i]+"\n";
				
			}
		 	
		   return;
			
		}
}

function myFunction(argument)
{   
	
	//se argomento è visualizza io voglio visualizzare 
	var contenuto = null;
	
	//SE VOGLIO INVIARE MESSAGGIO 
	if(argument=="invia"){
	    
		console.log("voglio inviare");
		var cont= document.getElementById("mess").value;
		send(cont);
	 
    //SE VOGLIO VISUALIZZARE TUTTI I MESS
	}else if(argument=="visualizza")	{
		
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



