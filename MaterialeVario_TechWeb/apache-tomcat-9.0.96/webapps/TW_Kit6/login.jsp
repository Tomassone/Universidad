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
      <div>
      	<form action="servletLogin" method="post">
      		<p>User:</p>
      		<input type="text" name="username" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" name="pass" size="20"/><br><br>
      		<input type="submit" value="Login"/>
      	</form>
      </div>

   </body>
</html>