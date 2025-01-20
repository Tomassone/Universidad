<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="catalogo" class="beans.Catalogo" scope="application"/>
<jsp:useBean id="carrello" class="beans.Carrello" scope="application"/>

<%
	for (int i = 0; i < catalogo.getElementi().size(); i++)
		if (request.getParameter(catalogo.getElementi().get(i).getIdProdotto()) != null)
			carrello.getElementi().add(catalogo.getElementi().get(i));
%>

<html>
	<head>
	<title>Kart</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
   		<form action="sold.jsp">
			<p><input type="submit" value="Finalizza">	<input type="reset" value="Azzera"></p>
		</form>
   </body>
</html>


