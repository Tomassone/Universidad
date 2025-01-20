 <%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>
<%@ page import="java.io.*" %>


<html>
   <head>
      <title>J1</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
   </head>
   <body>
     
     <div>
     
      <% 
        HttpSession se = request.getSession();
        Map<String, List<String>> lista = ( Map<String, List<String>>) this.getServletContext().getAttribute("lista");
	    Map<String,String> sessioniAttive = (Map<String, String>) this.getServletContext().getAttribute("user");
	    Map<String, Integer> abilita = ( Map<String, Integer>) this.getServletContext().getAttribute("abilita");
	    List<String> ric = (  List<String>) this.getServletContext().getAttribute("richieste");
	    //PRENDO ARTICOLO ASSOCIATO A QUELL'UTENTE CHE E' QUELLO CHE HA SCRITTO
	    String articolo = sessioniAttive.get(se.getId());
	    
	    System.out.println("articolo: "+articolo);
	    System.out.println("contenuto: "+ lista.get(articolo));
	    System.out.println("abilitato alla lettura: "+ abilita.get(articolo));
	    System.out.println("Richieste: "+ ric.get(0));
	    
	    //QUANDO CLICCO SU SCRIVI RISERVO IL DIRITTO DI SCRITTURA AD UN SOLO UTENTE
   
	    if(ric!=null && abilita!=null && sessioniAttive!=null){
	    	 for(String nome: ric){
        %>
          <p>ARTICOLO: <%=nome%></p>
        <%  }}  %>
        <p>Rimuovo il diritto di scrittura</p>
         <%
         for( String articoli : sessioniAttive.keySet()){ 
            for(int i=0; i< abilita.size(); i++){
              if(abilita.get(articoli)==1)
            	 abilita.put(articoli, 0);
            		  
            %>
         <% 
            } }%>
      </div>
   </body>
</html>