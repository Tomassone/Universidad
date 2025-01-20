<%@ page session="true"%>
<%@ page import="com.google.gson.Gson" %>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
        <script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/split.js"></script>
   </head>
<body> 
  <h5>Inserisci due nomi file presenti lato server</h5>
  <% 
  Gson g = new Gson();
  String[] lista = g.fromJson((String)session.getAttribute("listafile"), String[].class);
  %>
  <div>
  <label>File1: </label>
  <input type="text" name="file1" id="fl1"><br>
  <label>File2: </label>
  <input type="text" name="file2" id="fl2"><br>
  <input type="button" value="Clicca" onclick="invio()">
  </div>
  <div id="result">
  </div><br>
  
  <form action="servletAdmin" method="post">
  <label>Username: </label>
  <input type="text" name="user" ><br>
  <label>Password: </label>
  <input type="text" name="pw" ><br>
  <button type="submit">Login</button>
  </form>
  <% if (request.getParameter("error") != null) { %>
   <p class="error">Nome utente o password non corretti. Riprova.</p>
    <% } %>
  
  
   </body>
</html>

