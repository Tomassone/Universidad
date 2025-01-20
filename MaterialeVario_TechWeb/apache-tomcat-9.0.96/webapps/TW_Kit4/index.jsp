<%@ page session="true"%>
<%@ page import="java.util.*" %>
<html>
   <head>
      <title>login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	..Benvenuto, loggati... &nbsp;
      </p>
      <div>
      	<form action="servletLogin" method="get">
      		<p>User:</p>
      		<input type="text" name="user" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" name="pw" size="20"/><br><br>
      		<input type="submit" value="Login"/>
      	</form>
      </div>
   
   </body>
</html>