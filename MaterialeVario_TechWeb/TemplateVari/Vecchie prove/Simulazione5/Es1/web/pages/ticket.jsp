<%@ page session="true"%>

<%
	this.getServletContext().setAttribute("stopped", false); //per controllare se un utente è uscito senza confermare.
%>

<html>
   <head>
      <title>Biglietti</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <div>
      	<form action="/TW_Esame_StartingKit/BuyTicket" method="post">
      		<p>Compra Ticket:</p>
      		<input type="submit" value="Finalizza"/>
      		<input type="button" value="Exit" onAction='avviaAjax("/TW_Esame_StartingKit/Exit")'/>
      	</form>
      </div>
   </body>
</html>



