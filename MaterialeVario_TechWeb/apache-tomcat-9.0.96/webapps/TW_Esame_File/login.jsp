<%@ page session="true"%>
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
      	<form action="login" method="post">
      		<p>User:</p>
      		<input type="text" name="username" size="30"/><br>
      		<p>Password:</p>
      		<input type="password" name="pass" size="30"/><br><br>
      		<input type="submit" value="LogIn"/>
      	</form>
      </div>

 
   </body>
</html>