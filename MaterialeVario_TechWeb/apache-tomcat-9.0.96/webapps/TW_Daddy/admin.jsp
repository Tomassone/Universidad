<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
     <script type="text/javascript" src="scripts/wsocket_2.js"></script>
     <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
<title>Admin</title>
</head>
<body>

<h2>Quale prodotto vuoi eliminare dall'asta:</h2>
<% 
    //ADMIN1  : ESAME: OCCORENZE FILE
    //LISTA DELLE SESSIONI ATTIVE CON NUMERO DI RICHIESTE PER OGNI SESSIONE E NUMERO DI RICHIESTE NEGLI ULTIMI 60 MINUTI
    Catalogo c = (Catalogo) this.getServletContext().getAttribute("catalogo"); 
   
    if(c!=null){
    for(Prodotto item : c.getItems()){
%>
    <p>Prodotto: <%=item.getNome()%></p>
  <%
       }
       %>
         <form action="servletAdmin" method="post">
          <p>Quale vuoi ritirare:</p>
          <input type="text" name="elimina">
          <input type="submit" value="Elimina">
         </form>
      
       <%} %>  
         
            
</body>
</html>
