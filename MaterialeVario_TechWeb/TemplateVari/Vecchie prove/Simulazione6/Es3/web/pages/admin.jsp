<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>

<%
	Credenziali utente = (Credenziali) request.getSession().getAttribute("currentUser");
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="/08_TecWeb/scripts/wsocket_2.js"></script>
		<% if (utente == null) { %>
			<meta http-equiv="Refresh" content= "2; URL=login.jsp"/>
		<% } %>
   </head>
   <body>
   		<p><input type="button" name="invia" value="InviaMexMancanti" onclick="myFunction('1')"></p>
   </body>
</html>

