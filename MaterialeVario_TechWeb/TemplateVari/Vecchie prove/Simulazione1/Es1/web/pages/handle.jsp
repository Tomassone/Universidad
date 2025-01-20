<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="menu" class="beans.Menu" scope="application"/>
<jsp:useBean id="locale" class="beans.Locale" scope="application"/>

<!-- servo drink al tavolo -->
<%
	boolean trovato = false;
	for (int i = 0; i < locale.getElementi().size() && trovato == false; i++)
		for (int j = 0; j < menu.getElementi().size() && trovato == false; j++)
			if (locale.getElementi().get(i).getUtenti().contains(utente) && request.getParameter(menu.getElementi().get(j).getIdDrink()) != null)
			{
				locale.getElementi().get(i).getById(menu.getElementi().get(j).getIdDrink()).setStato("servito");
				trovato = true;
			}
%>	

<html>
	<head>
	<title>Menu</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
	   	<% if (trovato != true  && (boolean) session.getAttribute("isLogged") != true) { %>
			<meta http-equiv="Refresh" content= "2; URL=login.jsp"/>
		<% } %>
   </head>
   <body>
   		<% for (int i = 0; i < locale.getElementi().size(); i++) { %>
   			<% for (int j = 0; j < locale.getElementi().get(i).getElementi().size(); j++) { %>
				<p>Tavolo: <%= locale.getElementi().get(i).getIdTavolo() %> <p>
				<p>Drink: <%= locale.getElementi().get(i).getElementi().get(j).getIdDrink() %> ; <%= locale.getElementi().get(i).getElementi().get(j).getStato() %> <p>
			<% }} %>
   </body>
</html>


