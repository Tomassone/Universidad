<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>

<%
	List<HttpSession> sessioni = (List<HttpSession>) this.getServletContext().getAttribute("sessioniAttive");
	int nRichieste = (int) this.getServletContext().getAttribute("richiesteFatte");
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
      	<p>Numero richieste: <%= nRichieste %></p>
   		<p>Sessioni attive: </p>
   		<ul>
   			<% for (HttpSession sessione : sessioni) { %>
				<li><%= sessione.getId() %></li>
			<% } %>
   		</ul>
   		<% if (nRichieste > 1000 && sessioni.size() > 10) { %>
   			<form>
   				<input type="button" value="CacciaViaTutto">
   			</form>
		<% } %>
   </body>
</html>

