
<%@ page language="java" session="true"%>

<html>
   <head>
	   <title>Estremi</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
	   <script src="/TW_Esame_StartingKit/scripts/integrate.js"></script>
   </head>
   <body>
   		<p>Integro la funzione: f(x) = 2x</p>
		<form>
			<p>x1:</p>
      		<input type="text" name="x1" id="x1" size="30"/><br>
      		<p>x2:</p>
      		<input type="text" name="x2" id="x2" size="30"/><br><br>
      		<input type="button" value="Calcola" onClick="avviaAJAX('/TW_Esame_StartingKit/Integrate')"/>
			<p id="fillMe"></p>
		</form>
   </body>
</html>

