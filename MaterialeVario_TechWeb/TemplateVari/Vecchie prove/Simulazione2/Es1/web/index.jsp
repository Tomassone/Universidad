<%@ page session="true"%>
<html>
   <head>
      <title>Register</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <p>
      	..Benvenuto, registrati... &nbsp;
      </p>
      <div>
      	<form action="Register" method="post">
      		<p>User:</p>
      		<input type="text" name="userName" size="30"/><br>
      		<p>Password:</p>
      		<input type="text" name="pwd" size="30"/><br>
      		<p>Gruppo:</p>
      		<input type=text name="gruppo" size="30"/><br><br>
      		<input type="submit" value="Register"/>
      	</form>
      </div>
   </body>
</html>

