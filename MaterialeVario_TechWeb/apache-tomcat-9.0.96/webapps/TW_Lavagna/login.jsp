<%@ page session="true"%>
<!-- import di classi Java -->
<%@ page import="Beans.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/split.js"></script>
   </head>
   <body>
      <p>
      	..Benvenuto, loggati... &nbsp;
      </p>
      <div>
      	<form action="login" method="post">
      		<p>User:</p>
      		<input type="text" name="username" size="30"/><br>
      		<p>Password:</p>
      		<input type="password" name="pass" size="30"/><br><br>
      		<input type="submit" value="LogIn"/>
      	</form>
      </div>
  <%  
    // Recupero degli attributi dalla request 
    Boolean errore = (Boolean) request.getAttribute("rifai"); 
    if (errore != null && errore==true) { 
%> 
        <h2>HAI SBAGLIATO BELLISSIMA, RILOG</h2> 
<%  
    } 
%> 
   </body>
</html>