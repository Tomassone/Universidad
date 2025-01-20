<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	//Credenziali utente = (Credenziali) session.getAttribute("currentUser");
	Map<String, Gruppo> gruppi = (Map<String, Gruppo>) this.getServletContext().getAttribute("gruppi");
	String gId = (String) request.getParameter("id");
	 
	if (gId != null)
	{
		Gruppo gruppo = gruppi.get(gId);
		for (Credenziali u : gruppo.getUtenti())
			u.setHasFinalized(true);
	}
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
   		<form>
		<% for (Map.Entry<String, Gruppo> gruppo : gruppi.entrySet()) { %>
			<% if (gruppo.getValue().isBuying()) { %>
				<input type="button" value="<%= gruppo.getKey() %>" onClick='adminAjax("/TW_Esame_StartingKit/pages/admin.jsp", this)'>	
		<% }} %>
		</form>
   </body>
</html>