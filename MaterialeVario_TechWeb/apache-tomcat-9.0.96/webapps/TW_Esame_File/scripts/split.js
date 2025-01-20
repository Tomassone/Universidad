

function myfunction(){
	
	//NOME DEL FILE PRESENTE SERVER SIDE
	var file = document.getElementById("fl1").value;
	var form = document.getElementById("MyForm");
	
	//CONTROLLO CHE SIA ALFANUMERICO
	var pattern = /^[a-zA-Z0-9. ]+$/;
	var risultato = pattern.test(file);
	console.log(file);
	
	if(!risultato){
		alert("attenzione! il nome del file può contenere solo caratteri alfanumerici!")
	}else{	
		
	  if(file.endsWith(" ")){	
		console.log("Sono qui");
		form.submit();
	  }
     
	}
}