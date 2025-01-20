<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Coordinate" scope="session"/>
<jsp:useBean id="elencoAttrazioni" class="beans.ElencoAttrazioni" scope="application"/>
<jsp:useBean id="circondario" class="beans.Circondario" scope="application"/>

<!-- inizializzo credenziali utente ai valori ricevuti -->
<%
	if (request.getParameter("x") != null && request.getParameter("y") != null)
	{
		utente.setX(Integer.valueOf(request.getParameter("x")));
		utente.setY(Integer.valueOf(request.getParameter("y")));
		circondario.addElemento(utente);
	}
%>

<html>
	<head>
	<title>Menu</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body onLoad="catalogoAJAX('/TW_Esame_StartingKit/jsonServlet?x=<%= utente.getX() %>&y=<%= utente.getY() %>', document.getElementById('toFill'))">
   		<p>Location più vicine: </p>
   		<ul>
   			<% for(int i = 0; i < elencoAttrazioni.getElementi().size(); i++) { %>
	  			<% if (!elencoAttrazioni.getElementi().get(i).getNome().isEmpty() && elencoAttrazioni.getElementi().get(i).getCoordinate().isNear(utente, 100) && !circondario.isBusy(elencoAttrazioni.getElementi().get(i).getCoordinate())) { %>
					<li><%= elencoAttrazioni.getElementi().get(i).getNome() %></li>
			<% }} %>
   		</ul>
		<p id="toFill"></p>
   </body>
</html>


