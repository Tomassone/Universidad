<%@ page session="true"%>
<html>
   <head>
      <title>login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	..Benvenuto, registrati... &nbsp;
      </p>
      <div>
      	<form action="servletLogin" method="post">
      		<p>User:</p>
      		<input type="text" name="username" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" name="pass" size="20"/><br><br>
      		<p>Gruppo:</p>
      		<input type="text" name="gruppo" size="20"/><br><br>
      		<input type="submit" value="Registra"/>
      	</form>
      </div>
 <%
     HttpSession sess = request.getSession();
    Integer tipo = (Integer) sess.getAttribute("scadenza");
     if(sess != null && tipo !=null){
     
     if( tipo==1 ){
    		 %>
    		 <h2> Password scaduta, modificala</h2>
    		 <% }
     if( tipo==2 ){
    		 %>
    		 <h2> Più di 3 password nel gruppo scadute, rinserite tutti</h2>
    		 <%
     } if(tipo==3){
    %>
          		 <h2> Più di 3 errori nell'inserire la password</h2>
    <%
     }
     }
    %>
   </body>
</html>