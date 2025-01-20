

function myfunction(){
	
	var filet = document.getElementById("fl").value;
	var tipot = document.getElementsByName("contare");

	var el=null;
		for(let j=0; j<tipot.length; j++){
			console.log("valore: "+tipot[j].value);
			if(tipot[j].checked){
				el = tipot[j].value;
				break;
				}
		}
	
	
	if(filet.endsWith("txt")){	
		
		var argument = 	{
				  "nomefile": filet,
				  "tip": el 
		};
				
			var jsonString = JSON.stringify(argument);
			document.getElementById("jsonData").value = jsonString;

		
		//document.getElementById("file").value = file.replace("£££", "").trim();
		console.log("sono entrato");
		document.getElementById("fileForm").submit();
		//document.getElementById("fileForm").submit();
	}
			 
}

