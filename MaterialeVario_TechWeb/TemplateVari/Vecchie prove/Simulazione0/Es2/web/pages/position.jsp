<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="elencoAttrazioni" class="beans.ElencoAttrazioni" scope="application"/>

<html>
   <head>
	   <title>Posizione</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
		<form action="attractions.jsp">
			<p>Ascissa (in metri): <input type="text" name="x" id="x"></p>
			<p>Ordinata (in metri): <input type="text" name="y" id="y"></p>
			<p><input type="submit" value="Conferma">	<input type="reset" value="Azzera"></p>
		</form>
   </body>
</html>

