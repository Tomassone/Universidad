<%@ page session="true"%>
<html>
   <head>
      <title>Inserisci dati albergo</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <p>
      	..Inserisci dati albergo... &nbsp;
      </p>
      <div>
      	<form action="Finalizza">
      		<p>ID albergo:</p>
      		<input type="text" id="id" size="30"/><br>
      		<p>Data di check-in:</p>
      		<input type="text" id="checkin" size="30"/><br>
      		<p>Data di check-out:</p>
      		<input type=text id="checkout" size="30" onChange="updatePrice('/TW_Esame_StartingKit/CalcoloPrezzo')"/><br><br>
      		<% if (this.getServletContext().getAttribute("prezzoTotale") != null) { %>
      			<p> Prezzo finale: <input type="text" name="prezzo" size="30" value="<%= (double) this.getServletContext().getAttribute("prezzoTotale") %>"/></p>
      			<input type="submit" value="Finalizza"/>
      		<% } %>
      	</form>
      	<a href="pages/login.jsp">Gestione Admin</p>
      </div>
   </body>
</html>

