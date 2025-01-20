<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>

<%
	List<HttpSession> sessioni = (List<HttpSession>) this.getServletContext().getAttribute("sessioniAttive");
	int nRichieste = (int) this.getServletContext().getAttribute("richiesteFatte");
%>

<%
	String azzera = request.getParameter("azzeraTutto");
	if (azzera != null && azzera.compareTo("azzeraTutto") == 0)
	{
		for(HttpSession s : sessioni)
			s.invalidate();
		application.setAttribute("sessioniAttive", new ArrayList<HttpSession>());
		application.setAttribute("richiesteFatte", 0);
	}
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
   			%>
			<form action="#" method="get">
				<input type="submit" name="azzeraTutto" value="azzeraTutto">
			</form> 
		<% } %>
   </body>
</html>

