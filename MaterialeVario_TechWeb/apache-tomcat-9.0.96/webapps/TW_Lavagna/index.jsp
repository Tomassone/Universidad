<%@ page session="true"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet" ></link>
        <script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/split.js"></script>
   </head>
<body> 
     <div>
      	SCEGLI SE VUOI LEGGERE O SCRIVERE:(L-S)<br>
      	<form id="aziona" action="servlet1" method="post" onsubmit="intervallo(event)">
      	<input type="text" name="tip" id="tp" placeholder="L-S"/>
        <input type="submit" >
        </form>
      </div>
        <%
       HttpSession sess = request.getSession();
       User username = (User) sess.getAttribute("currentUser");

       if (sess == null || username == null) {
             response.sendRedirect("login.jsp");
             return;
         }
         
		 Boolean iswriting = (Boolean)this.getServletContext().getAttribute("iswrite"); 	    
		 Integer lettura = ( Integer) sess.getAttribute("lettura");
         Integer attiva = ( Integer) sess.getAttribute("attiva");
         Integer group = (Integer) session.getAttribute("gr");
         String contenuto=(String)this.getServletContext().getAttribute("testo");
         
         if(group!=null){
         if(group==1){
             contenuto = (String)this.getServletContext().getAttribute("testo");
         }else if(group==2){
             contenuto = (String)this.getServletContext().getAttribute("testo2");
         }
         }
         
         if(contenuto==null){
        	 contenuto="";
        	 this.getServletContext().setAttribute("testo", contenuto);
        	 this.getServletContext().setAttribute("testo2", contenuto);
         }
        	 
		  System.out.println("attiva: "+attiva);
		  
		 

        if(attiva!=null && lettura!=null && contenuto!=null){
        	if(attiva==1){
        		 
        	%> 
        	<button id="intervallo" style="display:none" value="<%=lettura%>"></button>
        	<%
        	 
        		
       %>
         <div id="lavagna" >
          <textArea id="area" <% if(lettura==1) {%>disabled<%} %> style="width: 300px;height: 300px;"
          onchange="controllo()">
          <%=contenuto%>
         </textArea> 
         <button <% if(lettura==1) { %>disabled<% } %>  onclick="controllo()">Invia</button>
       <% }else{ 
          %>
    	    "Premi per iniziare"
    	 
    	   <% 
    	   sess.removeAttribute("attiva");
        } }%>
     </div>
     <div><% Integer errore = ( Integer) sess.getAttribute("errore");
     
            if(errore!=null && errore==1){
            	%>
            	Qualcun'altro sta scrivendo, aspetta !
            	<%
            	
            	sess.removeAttribute("errore");
            } %>
</div>
     <div id="result">
       
     </div>
   </body>
</html>

