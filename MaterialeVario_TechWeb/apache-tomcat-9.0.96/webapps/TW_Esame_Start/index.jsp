<%@ page session="true"%>
<%@ page import="Beans.*"%>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
   <h5>INSERISCI CREDENZIALI:</h5>
   <div>
      <form action="login" method="post">
      <label>Nome utente:</label>
	  	<input type="text" size="20" name="user" ><br>	 
	  <label>Password:</label>
	  	<input type="password" size="20" name="pw"><br>	
	  	<input type="submit" value="Invia">
	  	</form>
	  </div>
   </body>
</html>

