
<%@ page language="java" session="true"%>

<%
	String[][] matriceA = (String[][]) this.getServletContext().getAttribute("matriceA");
	String[][] matriceB = (String[][]) this.getServletContext().getAttribute("matriceB");
%>

<html>
   <head>
	   <title>Inserimento matrici</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
	   <script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
	   <script src="/TW_Esame_StartingKit/scripts/updateCell.js"></script>
   </head>
   <body>
		<form>
			<p>Matrice A</p>
			<% for (int i = 0; i < 2; i++) { %>
				<p>
					<% for (int j = 0; j < 4; j++) { %>
						<input type="text" name="a,<%= i %>,<%= j %>" value="<%= matriceA[i][j] %>" onChange="updateValue('/TW_Esame_StartingKit/UpdateCell', this)">
					<% } %>
				</p>
			<% } %>
			<br/><p>Matrice B</p>
			<% for (int i = 0; i < 2; i++) { %>
				<p>
					<% for (int j = 0; j < 4; j++) { %>
						<input type="text" name="b,<%= i %>,<%= j %>" value="<%= matriceB[i][j] %>" onChange="updateValue('/TW_Esame_StartingKit/UpdateCell', this)">
					<% } %>
				</p>
			<% } %>
			
			<% if ((boolean) this.getServletContext().getAttribute("areFull") == true) { %>
				<input type="button" value="SottrazioneSequenziale" onClick="avviaAJAX('/TW_Esame_StartingKit/SottrazioneSequenziale', 'seq')">
				<input type="button" value="SottrazioneParallela" onClick="avviaAJAX('/TW_Esame_StartingKit/SottrazioneParallela', 'par')">
			<% } %>
			
			<p id="fillMe"></p>
			<a href="login.jsp">Gestione Admin</p>
		</form>
   </body>
</html>

