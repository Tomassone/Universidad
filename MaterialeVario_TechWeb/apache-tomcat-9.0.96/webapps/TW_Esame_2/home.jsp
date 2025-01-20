<%@ page session="true"%>
<%@ page import="com.google.gson.Gson" %>
<html>
   <head>
      <title>Home</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
        <script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/control.js"></script>
   </head>
   <body>
   <h4>Seleziona 3 file: </h4>
   <% 
      Gson g = new Gson();
      String[] files = g.fromJson((String)session.getAttribute("listafile"), String[].class);
     
     %>
     <div>
    <% if(files!=null && files.length>0){
       for(int i=0; i<files.length; i++){ %>
       
       <input type="checkbox" name="file<%=i%>" value="<%=files[i] %>" onchange="controllo(this);"> <%=files[i]%> 
      
     <% } } else 
      System.out.println("Errore nell'inizializzazione della lista di file");%>
     </div>
     <div id="ris">
     </div>
   </body>
</html>

