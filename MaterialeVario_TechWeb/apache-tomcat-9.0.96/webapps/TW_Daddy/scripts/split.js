

id=0;
function controllo(){
	
	clearTimeout(id);
	id = setTimeout( invio , 1000*4);
	//FORM CHE INVIERO' ALLA SERVELT1 IN CASO DI PASSATI ALMENO 60 SECONDI
	
}

//SE PASSANO PIU' DI 60 SECONDI LO RIMANDO QUA 
function invio(){
	//VALORE DELL'OFFERTA

	var file = parseInt(document.getElementById("fl1").value);
	var rim = parseInt(document.getElementById("rimasti").value);
	var base =parseInt(document.getElementById("prezzobase").value);
	//MI SETTO IL VALORE DELL'OFFERTA IN UN CAMPO HIDDEN
	
	//POI INVIO IL FORM ALLA SERVLET1
	var form = document.getElementById("MyForm");
	console.log("Sono qui");
	
	if(file>=base){
		
		//SE NON HO ABBASTANZA DANARI
	  if(rim>=file) {
		
		document.getElementById("vinto").value=1;
		alert("HAI VINTO");
		form.submit();
	  
	   }
       else{
		alert("Non hai abbastanza danari per quest'offerta");
		return; }
	//DEVO FARE OFFERTA BASE
	}else{
		alert("Almeno prezzo base");
		return;
	}
}