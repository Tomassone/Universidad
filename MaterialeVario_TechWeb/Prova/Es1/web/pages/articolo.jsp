<%@ page language="java" session="true"%>
<%@ page import="java.util.*, beans.*"%>

<%
	String testoArticolo = (String) request.getSession().getAttribute("testoArticolo");
	Articolo articolo = (Articolo) request.getSession().getAttribute("articolo");
%>

<html>
   <head>
	   <title>Articolo</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
	   <script src="/TW_Esame_StartingKit/scripts/getWrite.js"></script>
   </head>
   <body>
		<form action="/TW_Esame_StartingKit/OttieniScrittura" method="post">
			<p id="cont">Articolo: <input type="text" name="article" id="article" size="100" value="<%= testoArticolo %>" readonly></p>
			<p id="button-cont"><input type="button" value="Richiedi / Rimuovi Scrittura" OnClick="avviaAJAX('/TW_Esame_StartingKit/OttieniScrittura')"></p>
			<p id="fillMe"></p>
		</form>
   </body>
</html>

