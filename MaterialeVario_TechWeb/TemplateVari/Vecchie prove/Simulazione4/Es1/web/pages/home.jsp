<%@ page language="java" session="true"%>
<%@ page import="java.util.*, java.io.*, beans.*"%>

<%
	List<File> elencoFile = (List<File>) this.getServletContext().getAttribute("elencoFile");
	Double durataCalcoloBean = (Double) this.getServletContext().getAttribute("durataCalcoloBean");
	Double durataCalcoloServlet = (Double) this.getServletContext().getAttribute("durataCalcoloServlet");
%>

<html>
	<head>
	<title>Admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
   </head>
   <body>
   		<form>
			<% for (File f : elencoFile) { %>
				<input type="checkbox" id="<%= f.getName() %>" name="option" value="<%= f.getName() %>" onClick="avviaAJAX('/TW_Esame_StartingKit/CountWords')"><%= f.getName() %></input>
			<% } %>
			<button onclick="resetCheckbox()">Reset</button>
		</form>
		<p id="fillMe"></p>
   </body>
</html>