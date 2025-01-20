<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ page import="Beans.*" scope="application"%>
<%@ page import="java.util.*" %>
<link type="text/css" href="styles/default.css" rel="stylesheet"></link>

<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>
<h3></h3>
<%  List<Prodotto> prod = (List<Prodotto>) getServletContext().getAttribute("listaprodotti");

	if(prod != null)
	{
		for(Prodotto p : prod)
		{
			%>
				<div>
				  <input type="checkbox" id="prod"><%=p.toString()%><br>
				</div>
			<%
		}
	}
%>
  <input type="button" value="Inserisci" > 
</body>
</html>