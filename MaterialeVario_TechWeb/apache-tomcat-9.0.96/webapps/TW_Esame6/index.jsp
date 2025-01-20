<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/contatore.js"></script>
   </head>
   <body>
      <div>
      	<h3>INSERISCI NOME ARTICOLO:</h3>
      	<form id="fileForm" action="servlet1" method="post" >
      	<input type="text" maxlength="64" name="articolo" id="ar" oninput="myfunction()"/><br><br>
      	<input type="submit" name="dati" style="display:none">
      	</form>
      </div>
    <div id="result"></div><br><br>
    
    <div>
       <h3>Admin, autenticati: </h3>
      	<form action="servletLogin" method="post">
      		<p>User:</p>
      		<input type="text" name="username" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" name="pass" size="20"/><br><br>
      		<input type="submit" value="Login"/>
      	</form>
      	<table>
      	 <% 
        HttpSession se = request.getSession();
        Integer add = (Integer) se.getAttribute("admin");
		Map<String, Sessioni> active = (HashMap<String, Sessioni>) this.getServletContext().getAttribute("sessioni");
      	if( add != null && add == 1 ){ 
      		//per ciascuna sessione
      	for (Map.Entry<String, Sessioni> entry : active.entrySet()) {
            Sessioni sessioni = entry.getValue();
      	%>  
      	 <tr>
      	  <td><%=sessioni.getId()%></td>
      	  <td><%=sessioni.getNumrichieste()/2%></td>
      	 <td><%=sessioni.getDurata()%></td>
      	 
      	  </tr>
      	<%
           }
        se.removeAttribute("ad");
} %>
</table>
      </div>
   </body>
</html>