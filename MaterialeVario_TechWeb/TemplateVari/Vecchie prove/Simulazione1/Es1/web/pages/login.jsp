<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="menu" class="beans.Menu" scope="application"/>
<jsp:useBean id="locale" class="beans.Locale" scope="application"/>

<html>
   <head>
	   <title>User login</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
		<form action="order.jsp">
			<p>Utente: <input type="text" name="user"></p>
			<p>Password: <input type="text" name="password"></p>
			<% if ((boolean) session.getAttribute("isLogged") != true) { %>
				<p>Tavolo: <input type="text" name="tavolo"></p>
			<% } %>
			<p><input type="submit" value="Conferma">	<input type="reset" value="Azzera"></p>
		</form>
   </body>
</html>

