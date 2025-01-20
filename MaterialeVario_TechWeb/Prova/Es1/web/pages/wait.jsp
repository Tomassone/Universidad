<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	Credenziali utente = (Credenziali) request.getSession().getAttribute("currentUser");
%>

<html>
	<head>
	<title>Tempo di attesa</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
   		<p>Tempo di attesa corrente: <%= utente.getTempoAttesa() %> minuti </p>
   		<form action="/TW_Esame_StartingKit/LogIn" method="web">
			<input type="submit" name="path" value="Rinfresca">	
		</form>
   </body>
</html>

