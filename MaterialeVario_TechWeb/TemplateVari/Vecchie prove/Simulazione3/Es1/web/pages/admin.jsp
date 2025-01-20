<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	Map<String, Osservazione> osservazioni = (Map<String, Osservazione>) this.getServletContext().getAttribute("osservazioni");
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
   		<p>Sessioni attive: </p>
   		<ul>
   			<% if (osservazioni != null) {
   				for (Map.Entry<String, Osservazione> el : osservazioni.entrySet()) { 
  			   		if (el.getValue().isFinalizzata()) { %>
						<li><%= el.getKey() %></li>
			<% }}} %>
   		</ul>
   </body>
</html>

