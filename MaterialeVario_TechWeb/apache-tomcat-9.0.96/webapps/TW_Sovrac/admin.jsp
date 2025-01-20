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
   <p>Admin, inserisci credenziali!</p>
      <div>
      		<p>User:</p>
      		<input type="text" id="user" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" id="pw" size="20"/><br><br>
      		<input type="button" value="Login" onclick="myFunction('admin')" />
      </div>

<h2>Lista delle sessioni attive:</h2>
<% 
    //ADMIN1  : ESAME: OCCORENZE FILE
    //LISTA DELLE SESSIONI ATTIVE CON NUMERO DI RICHIESTE PER OGNI SESSIONE E NUMERO DI RICHIESTE NEGLI ULTIMI 60 MINUTI
    HashMap<HttpSession, Integer> sessioniAttive = (HashMap<HttpSession, Integer>)this.getServletContext().getAttribute("sessioniAttive");
    List<Long> tot = (ArrayList<Long>) this.getServletContext().getAttribute("tot");
    
    int count = 0 ;
    
    System.out.println(sessioniAttive.size());
    if(sessioniAttive!=null){
    for(HttpSession sessione : sessioniAttive.keySet()){
   
  %>
    <p>Sessione: <%=sessione.toString()%></p>
    <p>Num Richieste: <%=sessioniAttive.get(sessione)%></p>
    <%} }
      if(tot!=null){
       for(int i=0; i<tot.size(); i++){ 
          if(tot.get(i) - System.currentTimeMillis() < 60 * 60 *1000)
        	   count++;
       }} %>
          <p>Numero richieste totali negli ultimi 60 minuti: <%=count%></p>
   <% /////////////////////////////////////////////////////////////////// %>
   
   <% %>
    

   <% 

   //ADMIN2 : RECUPERARE PER OGNI GRUPPO QUANTI GIORNI MANCANO PER OGNI UTENTE
   //ESAME 15 01 2020 2
   Map<String, Gruppo> elenco = (Map<String, Gruppo>) request.getAttribute("tuttigruppi"); 

   if (elenco != null) { 

       Gruppo g1 = elenco.get("g01"); 
       Gruppo g2 = elenco.get("g02"); 
       Gruppo g3 = elenco.get("g03"); 
       LocalDate ora = LocalDate.now(); 
       int manca = 0; 
       if (g1 != null) { 
           out.println("<h2> Gruppo 1: " + g1.getUtenti() + "</h2><br>"); 
           if (g1.getUtenti() != null) { 
               for (User u : g1.getUtenti().keySet()) { 
                   manca = 60 - (ora.getDayOfYear() - u.getDate().getDayOfYear()); 
                   out.println ("<h2> Mancano " + manca + " giorni per l'utente: " + u.toString() + "</h2>"); 
               } 
           } 
       } 


       if (g2 != null) { 
           out.println("<h2> Gruppo 2: " + g2.getUtenti() + "</h2><br>"); 
           if (g2.getUtenti() != null) { 
               for (User u : g2.getUtenti().keySet()) { 
                   manca = 60 - (ora.getDayOfYear() - u.getDate().getDayOfYear()); 
                   out.println("<h2> Mancano " + manca + " giorni per l'utente: " + u.toString() + "</h2>"); 
               } 
           } 
       } 

       if (g3 != null) { 
           out.println("<h2> Gruppo 3: " + g3.getUtenti() + "</h2><br>"); 
           if (g3.getUtenti() != null) { 
               for (User u : g3.getUtenti().keySet()) { 
                   manca = 60 - (ora.getDayOfYear() - u.getDate().getDayOfYear()); 
                   out.println("<h2> Mancano " + manca + " giorni per l'utente: " + u.toString() + "</h2>"); 
               } 
           } 
       } 
   } else { 
       out.println("<h2> Nessun gruppo trovato. </h2>"); 
   } 
   /////////////////////////////////////////////////////////
   %> 
   
</body>
</html>
