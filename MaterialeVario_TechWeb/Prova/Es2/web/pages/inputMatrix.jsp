
<%@ page language="java" session="true"%>

<html>
   <head>
	   <title>Inserimento file matrici</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
	   <script src="/TW_Esame_StartingKit/scripts/ajax.js"></script>
	   <script src="/TW_Esame_StartingKit/scripts/initMatrix.js"></script>
   </head>
   <body>
		<form>
			<input type="text" id="a" value="" onChange="setValue('/TW_Esame_StartingKit/SommaSequenziale')">
			<input type="text" id="b" value="" onChange="setValue('/TW_Esame_StartingKit/SommaSequenziale')">
			<textarea id="content" name="content" onChange="setValue('/TW_Esame_StartingKit/SommaSequenziale')"></textarea>
			<p id="fillMe"></p>
		</form>
   </body>
</html>

