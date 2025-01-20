<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	//Credenziali utente = (Credenziali) session.getAttribute("currentUser");
	Map<String, Gruppo> gruppi = (Map<String, Gruppo>) this.getServletContext().getAttribute("gruppi");
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
		<% for (Map.Entry<String, Gruppo> gruppo : gruppi.entrySet()) { %>
			<p><%= gruppo.getValue().getIdGruppo() %></p>
			<ul>
				<% for (Credenziali utente : gruppo.getValue().getUtenti()) { %>
					<li><%= utente.getUser() %> - <%= 60 - utente.getDaysSinceLastModification() %> </li>
				<% } %>
			</ul>
		<% } %>
   </body>
</html>