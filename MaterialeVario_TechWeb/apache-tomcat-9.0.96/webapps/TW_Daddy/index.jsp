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
  <h5>Inserisci nome file presenti lato server</h5>
  <div>
  <form id="MyForm" action="servlet1" method="post">
  <label>File1: </label>
  <input type="text" name="file1" id="fl1" oninput="myfunction()"><br>
  <input type="submit" name="dati" style="display:none">
  </form>
  </div>
  
  
  <div >
  <%
      Gson g = new Gson();
      Risultato res = g.fromJson( (String) this.getServletContext().getAttribute("result"), Risultato.class);

      if(res!=null){
  
  %>
       Testo: <%= res.getTesto() %><br>
       Conteggio maisucole: <%=res.getConteggio()%>
  <%} 
     
      this.getServletContext().removeAttribute("result");
  
    %>
  </div><br>
  
   </body>
</html>

