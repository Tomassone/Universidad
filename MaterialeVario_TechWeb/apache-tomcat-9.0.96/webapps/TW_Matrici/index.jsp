<!DOCTYPE html>
<!-- Inizio codice html -->

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/wsocket_2.js"></script>
	<title>Index</title>
</head>

<body>

<!-- Sezione utenti non autenticati WEBSOCKET -->

<div id="webSocket">
	<h4>Matrice condivisa:</h4>
 	<%  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   for(int i=0; i<3; i++){
 		   for(int j=0; j<3; j++){
	%>
     <input type="number" id="valueA<%=i%><%=j%>" style="width: 30px;" onchange="check()">
<% } %> 
<br> 
<%} %>
	
</div>
<div id="result"></div>

<!-- Sezione di autenticazione utenti -->
<div id="login">
	<h1>Devi autenticarti? Accedi qui</h1>
    
    <form action="servletLogin" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <button type="submit">Login</button>
    </form>
    
    <% 
        String error = request.getParameter("error");
        if ("invalid action".equals(error)) {
    %>
        <p style="color: red">Errore: azione non valida</p>
    <% 
        } else if ("invalid credentials".equals(error)){
    %>
     	<p style="color: red">Errore: Credenziali non valide</p>
    <%
        }
    %>
</div>

</body>
</html>
