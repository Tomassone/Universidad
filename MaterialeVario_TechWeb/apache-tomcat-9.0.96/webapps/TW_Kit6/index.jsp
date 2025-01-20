<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>

<html>
   <head>
      <title>login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/comanda.js"></script>
   </head>
   <body>
      <div>
      	SCEGLI SE VUOI LEGGERE O SCRIVERE:<br>
      	<input type="button" id="lettura" value="Leggi" onclick="invio('let')"/>
      	<input type="button" id="scritt" value="Scrivi" onclick="invio('scrit')"/>
      </div>
       
       <div id="lavagna" style="display:none;">
         <%
       HttpSession sess = request.getSession();
         User username = (User) sess.getAttribute("currentUser");

       if (sess == null || username == null) {
             response.sendRedirect("login.jsp");
             return;
         }
         
         //se c'è qualcuno che sta leggendo non faccio scrivere da nessun altro e 
         //non faccio comparire il bottone invia
         Boolean isWriting = (Boolean) sess.getAttribute("isWriting");
      %>
     <form id="formArea" action="servletLavagna" method="post">
       <textarea name="contenuto" id="content" rows="10" cols="10" style="border: 2px solid black;<%= (isWriting != null && isWriting) ? "" : "readonly" %>"></textarea> 
       <input type="submit" id="scr" style="<%= (isWriting != null && isWriting) ? "" : "display:none" %>" value="invia"><br>
    </form>
    </div>
    
    <div id="result"></div>
   </body>
</html>