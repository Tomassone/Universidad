<%@ page session="true"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
        <script type="text/javascript" src="scripts/utils.js"></script>
        <script type="text/javascript" src="scripts/split.js"></script>
   </head>
<body>  
  <% //CONTROLLO QUANTI FILE VOGLIO INSERIRE %>
  <label>Quanti file vuoi inserire?(1-4)</label><br>
  <input type="number" min=1 max=4 id="qt" name="qt" oninput="controllo()">
    <input type="text" style="display:none" id="num">
  
  <div id="mydiv" style="display:none">
    <h5>Inserisci nomi file presenti lato server</h5>
  <form id="MyForm" action="j2.jsp" method="post">
    <%   
        for(int i=0; i< 4; i++) {%>
     <label id="n<%=i%>" style="display:none">File <%=i+1%></label>
    <input type="text" name="file<%=i%>" style="display:none" id="fl<%=i%>" oninput="invia()"><br>
    <input type="hidden" id="jsonData" name="jsonData" />
   <input type="submit" name="dati" style="display:none">
   <%}
         %>
   </form>
  </div>
  
 
  
  <div >
   <% String frase = (String) this.getServletContext().getAttribute("mandato"); 
      
      if(frase!=null){ %>
     Contenuto: <%=frase%>
   <%}
      %>
  </div><br>
  
   </body>
</html>

