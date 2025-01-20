
<html>
   <head>
	   <title>Inserimento Parola</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
	   <script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
		<form action="order.jsp">
			<p id="cont">Parola: <input type="text" name="word" id="word"></p>
			<p id="button-cont"><input type="button" value="Invia" OnClick="avviaAJAX('/TW_Esame_StartingKit/cifraServlet')"></p>
		</form>
   </body>
</html>

