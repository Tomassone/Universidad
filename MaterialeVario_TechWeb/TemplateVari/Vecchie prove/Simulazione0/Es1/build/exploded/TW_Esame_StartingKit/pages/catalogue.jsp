<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="catalogo" class="beans.Catalogo" scope="application"/>

<!-- inizializzo credenziali utente ai valori ricevuti -->
<%
	utente.setUser(request.getParameter("user"));
	utente.setPassword(request.getParameter("password"));
%>

<!-- controllo la presenza delle credenziali nel db -->
<%
	boolean trovato = false;
	for (int i = 0; i < utenti_salvati.getElementi().size() && trovato == false; i++)
	{
		if (utente.authenticate(utenti_salvati.getElementi().get(i)))
		{
			session.setAttribute("isLogged", true);
			trovato = true;
			utente.setIdGroup(utenti_salvati.getElementi().get(i).getIdGroup());
		}
	}
%>

<html>
	<head>
	<title>Catalogue</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
	   	<% if (trovato != true  && (boolean) session.getAttribute("isLogged") != true) { %>
			<meta http-equiv="Refresh" content= "2; URL=login.jsp"/>
		<% } %>
   </head>
   <body onLoad="catalogoAJAX('/TW_Esame_StartingKit/jsonServlet', document.getElementById('toFill'))">
		<p id="toFill"></p>
   </body>
</html>


