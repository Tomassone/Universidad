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
      	SCEGLI SE VUOI LEGGERE O PRENOTARTI:
      	<input type="button" id="lettura" value="Leggi" onclick="lettura()"/>
      	<input type="button" id="scritt" value="Scrivi" onclick="invio()"/>
      </div>
       
       <div id="scrittura" style="display:none;">
       <label>Orario inizio: </label>
       <input type="text"  id="inizio" name="seq" />	   
	<div>
	   <%
     HttpSession sess = request.getSession();
     List<Prenotazione> tipo = (List<Prenotazione>) getServletContext().getAttribute("prenotazioni");
     System.out.println(tipo);
     if(tipo!=null && !tipo.isEmpty()){
    	 %> 
      Oppure scegli a quale prenotazione vuoi aggiungerti: <br> <%
     for(int i=0; i<tipo.size(); i++){
    	 Prenotazione prenot = tipo.get(i);
    	   if(prenot.isValido()){
    		 %>
    		 <input type="radio" name="pr" id="prenotazione<%=i%>" value="<%=prenot.getId()%>">
    		 <%=prenot.getOraInizio()%> - Campo <%=prenot.getCampi()%>  - Valido: <%=prenot.isValido()%><br>
    	<%
     }}}
    %>
    </div>   
    <button id="scr" id="pr" onclick="scrittura()">Prenota</button><br>
    </div>
    <div id="result"></div>
   </body>
</html>