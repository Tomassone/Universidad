<%@ page session="true"%>
<html>
   <head>
      <title>Login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	..Benvenuto, loggati... &nbsp;
      </p>
      
    <% 
        HttpSession se = request.getSession();
        Integer add = (Integer) se.getAttribute("ad");
        System.out.println(add);
      	if( add != null && add == 1 ){ %>
      	<form action="servlet1" method="get">
      	<label>Inserisci file: </label><br>
      	<br><input type="text" name="fil"/><br>
      	<label>Inserisci linea che vuoi eliminare: </label><br>
      	<br><input type="number" name="elimina"/>
      	 <input type="submit" value="ELIMINA"/><br>
      	</form>
      	<%
        se.removeAttribute("ad");
} %>
   </body>
</html>