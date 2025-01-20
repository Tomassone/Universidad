<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	Map<String, Articolo> articoli = (Map<String, Articolo>) this.getServletContext().getAttribute("articoli");
%>

<%
	String path = request.getParameter("path");
	if (path != null)
	{
		for (Map.Entry<String, Articolo> articolo : articoli.entrySet())
			if (articolo.getKey().equals(path))
				articolo.getValue().setWrSession(null);
	}
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
   		<p>Articoli attivi: </p>
   		<form>
		<% for (Map.Entry<String, Articolo> articolo : articoli.entrySet()) { %>
			<input type="button" name="path" value="<%= articolo.getKey() %>">	
		<% } %>
		</form>
   </body>
</html>

