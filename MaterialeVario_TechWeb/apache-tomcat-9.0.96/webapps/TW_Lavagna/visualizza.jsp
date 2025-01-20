<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>
<%@ page import="java.io.*" %>
<html>
   <head>
      <title>Login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/contatore.js"></script>
   </head>
   <body>
      <p>
      	..Benvenuto, informazioni sul tuo articolo
      </p>
      
    <% 
        HttpSession se = request.getSession();
        Map<String, List<String>> lista = ( Map<String, List<String>>) this.getServletContext().getAttribute("lista");
	    Map<String,String> sessioniAttive = (Map<String, String>) this.getServletContext().getAttribute("user");
	    Map<String, Integer> abilita = ( Map<String, Integer>) this.getServletContext().getAttribute("abilita");
	    
	    //PRENDO ARTICOLO ASSOCIATO A QUELL'UTENTE CHE E' QUELLO CHE HA SCRITTO
	    String articolo = sessioniAttive.get(se.getId());
	    
	    System.out.println("articolo: "+articolo);
	    System.out.println("contenuto: "+ lista.get(articolo));
	    System.out.println("abilitato alla lettura: "+ abilita.get(articolo));

	    //QUANDO CLICCO SU SCRIVI RISERVO IL DIRITTO DI SCRITTURA AD UN SOLO UTENTE
   
	    if(lista!=null && articolo!=null  && abilita!=null){
       %>
       <p>ARTICOLO: <%=articolo%></p>
       
          <textArea id="area" <% if(abilita.get(articolo)==0) { %>disabled<% } %> style="width: 300px;height: 300px;">
             <%=lista.get(articolo)%>
         </textArea> <br>
         <form id="accessoForm" action="servlet1" method="get">
	     <input type="submit" name="dir" value="Scrivi">  
	      <% 
          if(abilita.get(articolo)==1){%>
              <input type="button"  onclick="scrittura()" value="Rilascia" >
        <%
	    }
	      }%>
	    </form>
	    
	    <div id="result"></div>
   </body>
</html>