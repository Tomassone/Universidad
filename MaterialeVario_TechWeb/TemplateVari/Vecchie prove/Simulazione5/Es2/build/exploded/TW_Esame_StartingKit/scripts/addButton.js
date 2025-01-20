
function updatePage() {
    let inputBox = document.getElementById("input");
    let pathBox = document.getElementById("path");
    let element = document.getElementById("fillMe");
    
    if (inputBox.value !== null && pathBox.value !== null && inputBox.value.length >= 1000)
    {
		let result = "";
		result = result + '<input type="button" value="elab" onClick="avviaAJAX(\'/TW_Esame_StartingKit/MinuscToMaiusc\')">';
		element.innerHTML = result;
	}
}