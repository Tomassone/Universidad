
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>J2 Page</title>
 <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
        <script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/split.js"></script>
 </head>
<body>
   <div>
       <%  // String prod1 = (String) this.getServletContext().getAttribute("prodotto");
            HashMap<String, String> prod = (HashMap<String, String>) this.getServletContext().getAttribute("prodottoAsta");
            Gson g = new Gson();
            User u = (User) session.getAttribute("currentUser");

            String pr = prod.get(u.getId());
            
            //Prodotto p = prod.get(u.getId());
            
            System.out.println("prodotto: "+prod);
            
            Map<String, User> vincite = new HashMap<String, User>();
            
            Prodotto p = new Prodotto();
			
			
            //MI PRENDO UTENTE E IL PRODOTTO
            if(u!=null && pr!=null){
            	
             //TANTO PER LO STESSO PRODOTTO MI SOVRASCRVE FINO ALL'ULTIMO CHE SCRIVE CHE
             //SARà IL VINCITORE
              p = g.fromJson(pr , Prodotto.class);
    		  vincite.put(p.getNome(), u);

           %>
             <h3> NOME OGGETTO:</h3> <%=p.getNome() %><br>
             <h3> DESCRIZIONE:</h3> <%=p.getDescription() %><br>
             <h3> PREZZO BASE:</h3> <%=p.getPrice() %><br>
              
              <form id="MyForm" action="servlet1" method="post">
              <input type="text" id="utente" style="display:none" name="vincitore" value="<%=u.getUserName()%>" >
              <input type="text" id="rimasti" style="display:none" value="<%=u.getDenari()%>">
              <input type="text" id="prezzobase" style="display:none" value="<%=p.getPrice()%>">
              <input type="text" id="vinto" style="display:none">
              <label>Inserisci offerta: </label><br>
              <input type="number" id="fl1" min="<%=p.getPrice()%>" onkeyup="controllo()">
              </form>
              
              
  		      
            <%
            Catalogo c = (Catalogo) this.getServletContext().getAttribute("catalogo"); 
		      c.remove(p);
		      this.getServletContext().setAttribute("catalogo", c); 
            }
  		       
  		       
               String errore = (String) this.getServletContext().getAttribute("errore");
               Integer er=null;
               
               if(errore!=null)
                 er = Integer.parseInt(errore);
               
               if( er != null && er==1){
           %>
               <p>FINITI I PRODOTTI NEL CATALOGO</p>
            <% }
            
       %>
    </div>
</body>
</html>
