<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="catalogo" class="beans.Catalogo" scope="application"/>
<jsp:useBean id="carrello" class="beans.Carrello" scope="application"/>

<%
	for (int i = 0; i < utenti_salvati.getElementi().size(); i++)
		if (utente.authenticate(utenti_salvati.getElementi().get(i)))
			utenti_salvati.getElementi().get(i).setHasFinalized(true);
%>

<html>
	<head>
	<title>Sold</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
	   	<% for (int i = 0; i < utenti_salvati.getElementi().size(); i++) {
			if (utente.getIdGroup().equals(utenti_salvati.getElementi().get(i).getIdGroup()) && utenti_salvati.getElementi().get(i).hasFinalized() == false) { %>
			<meta http-equiv="Refresh" content= "2; URL=login.jsp"/> 
		<% }} %>
   </head>
   <body>
   		<p>Finalizzato!</p>
   		<%
   		for (int i = 0; i < catalogo.getElementi().size(); i++)
   			for (int j = 0; j < carrello.getElementi().size(); j++)
   				if (catalogo.getElementi().get(i).getIdProdotto().equals(carrello.getElementi().get(j).getIdProdotto()))
   					catalogo.getElementi().remove(catalogo.getElementi().get(i));
		%>
   </body>
</html>


