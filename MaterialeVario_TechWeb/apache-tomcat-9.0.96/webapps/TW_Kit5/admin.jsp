<%@ page session="true"%>
<%@ page import="Beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDate" %>
<html>
   <head>
      <title>admin</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
      <p>
      	Admin, inserisci credenziali!
      </p>
      <div>
      	<form action="servletLogin" method="get">
      		<p>User:</p>
      		<input type="text" name="userb" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" name="pwb" size="20"/><br><br>
      		<input type="submit" value="Login"/>
      	</form>
      </div>
      <div>
      	 <%
        HttpSession sess = request.getSession();
        Map<String, User> utnt = (Map<String, User>) sess.getAttribute("utenti");
        Map<String, Gruppo> grp = (Map<String, Gruppo>) sess.getAttribute("grupponi");
        Integer fin = (Integer) sess.getAttribute("finestra");
        int conta=0;
        

        if (fin != null && fin == 1) {
            if (grp != null && utnt != null) {
            	
            	 System.out.println("Utenti Registrati2");
                 for(String u : utnt.keySet()){
                 	System.out.println(u);
                 }
            	
            	//MI GIRO PER I NOMI DEI GRUPPI
                for (String nomegr : grp.keySet()) { 
                	
                    Gruppo gruppo = grp.get(nomegr);
                    System.out.println(gruppo);
                    %>
                    <h3>Gruppo: <%= gruppo.getId() %></h3>
                    <%
                    for (User user : gruppo.getUtenti()) {
                        System.out.println(user);
                        LocalDate today = LocalDate.now();
                        LocalDate passwordDate = user.getDate();
                        int rimasti = 60 - (today.getDayOfYear() - passwordDate.getDayOfYear());
                        %>
                        <p>Utente: <%= user.getUserName() %></p>
                        <p>Giorni rimanenti prima della scadenza della password: <%= rimasti %></p>
                        <hr>
                        <%
                    }
                }
            } else {
                %>
                <p>Nessun gruppo o utente trovato.</p>
                <%
            }
        }
    %>
      </div>

   </body>
</html>