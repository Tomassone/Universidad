<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="menu" class="beans.Menu" scope="application"/>
<jsp:useBean id="locale" class="beans.Locale" scope="application"/>

<!-- inizializzo credenziali utente ai valori ricevuti -->
<%
	if (request.getParameter("user") != null && request.getParameter("password") != null)
	{
		utente.setUser(request.getParameter("user"));
		utente.setPassword(request.getParameter("password"));
	}
%>

<!-- controllo la presenza delle credenziali nel db -->
<%
	boolean trovato = false;
	for (int i = 0; i < utenti_salvati.getElementi().size() && trovato == false && ((boolean) session.getAttribute("isLogged") != true); i++)
	{
		if (utente.authenticate(utenti_salvati.getElementi().get(i)))
		{
			session.setAttribute("isLogged", true);
			trovato = true;
			utente.setRuolo(utenti_salvati.getElementi().get(i).getRuolo());
		}
	}
%>

<!-- inizializzo credenziali utente ai valori ricevuti -->
<%
 	trovato = false;
	for (int i = 0; i < locale.getElementi().size() && trovato == false && request.getParameter("tavolo") != null; i++)
	{
		if (locale.getElementi().get(i).getIdTavolo().equals(request.getParameter("tavolo")))
		{
			trovato = true;
			locale.getElementi().get(i).addUtenti(utente);
			System.out.println("Utente registrato al tavolo: " + locale.getElementi().get(i).getIdTavolo());
		}
	}
%>

<!-- aggiungo drink al tavolo -->
<%
	trovato = false;
	for (int i = 0; i < locale.getElementi().size() && trovato == false && request.getParameter("user") == null; i++)
		for (int j = 0; j < menu.getElementi().size() && trovato == false; j++)
			if (locale.getElementi().get(i).getUtenti().contains(utente) && request.getParameter(menu.getElementi().get(j).getIdDrink()) != null)
			{
				System.out.println("Aggiunto drink: " + menu.getElementi().get(j).getIdDrink() + " al tavolo: " + locale.getElementi().get(i).getIdTavolo());
				locale.getElementi().get(i).addElementi(menu.getElementi().get(j));
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
		<% if (utente.getRuolo().equals("cameriere") && (boolean) session.getAttribute("isLogged") == true) { %>
			<meta http-equiv="Refresh" content= "2; URL=manage.jsp"/>
		<% } %>
		<% if (utente.getRuolo().equals("amministratore") && (boolean) session.getAttribute("isLogged") == true) { %>
			<meta http-equiv="Refresh" content= "2; URL=handle.jsp"/>
		<% } %>
   </head>
   <body onLoad="catalogoAJAX('/TW_Esame_StartingKit/jsonServlet', document.getElementById('toFill'))">
		<p id="toFill"></p>
		<% if (true) { %>
				<form action="<%="/TW_Esame_StartingKit/calcoloConto?user="+utente.getUser()+"&password="+utente.getPassword()%>">
					<p><input type="submit" value="Conto"></p>
				</form>
		<% } %>
   </body>
</html>


