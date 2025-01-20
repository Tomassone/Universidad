<%@ page session="true"%>
<html>
   <head>
      <title>Change Password</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->

      <div>
      	<form action="Change" method="post">
      		<p>Nuova Password:</p>
      		<input type="password" name="pwd" size="30"/><br><br>
      		<input type="submit" value="Change"/>
      	</form>
      </div>
   </body>
</html>


