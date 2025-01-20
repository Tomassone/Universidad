
function controllo(){
	
	//NOME DEL FILE PRESENTE SERVER SIDE
	
	//QUANTI FILE DEVO INSERIRE
	var numero = document.getElementById("qt").value;
	if(numero>4 || numero<1){
		alert("Devono essere compresi tra 1 e 4")
	}else{
		
	   document.getElementById("mydiv").style.display ='block'; 
	   
	   for(let i=0; i<numero;i++){
		document.getElementById("n"+i).style.display='block';
		document.getElementById("fl"+i).style.display='block';
	   }
	}
	//CONTROLLO CHE SIA ALFANUMERICO
	document.getElementById("num").value=numero;

}

var j=0;
var k=0;
var files = [];

function invia(){
	
	var file;
	var numero = document.getElementById("num").value;
	var form = document.getElementById("MyForm");
	
	

    if(k<numero){
		
		file=document.getElementById("fl"+k).value;
		
		if(file.endsWith(" ")){
			
			console.log(file);
			files[k]=file;
			console.log("J"+k);
			k++;
		}
	}
	
	for(let k=0; k<numero; k++){
		console.log("File "+k+":"+files[k]);
	}
	

	if(k==(numero)){
		console.log("Sono qui");
		var jsonString = JSON.stringify(files);
		document.getElementById("jsonData").value = jsonString;
		form.submit();
	}
}