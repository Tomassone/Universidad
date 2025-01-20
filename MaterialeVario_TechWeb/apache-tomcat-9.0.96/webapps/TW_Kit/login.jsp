<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statische</title>
</head>
<body>
    <div class="container">
    <% 
    if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
        response.sendRedirect("index.jsp");
        return;
     }
       int tot = (int)session.getAttribute("totaleC"); 
       int max = (int)session.getAttribute("maiuscole");
       int min = (int)session.getAttribute("minuscole");
       int mese =(int)session.getAttribute("mese");
       int anno =(int)session.getAttribute("anno");

       %>
        <h2>Benvenuto, Amministratore</h2>
        <h3>Statistiche Mensili <%=mese%>/<%=anno %></h3>
        <p>Numero totale di caratteri processati: <%=tot%></p>
        <p>Numero totale di maiuscoli contati: <%=max%></p>
        <p>Numero totale di minuscoli contati: <%=min%></p>
    </div>
</body>
</html>
