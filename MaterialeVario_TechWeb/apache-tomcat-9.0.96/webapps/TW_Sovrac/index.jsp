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
  <input type="hidden" name="originUrl" value="<%=request.getRequestURL() %>">
  <label>Contenuto fra 100 e 1000 caratteri: </label><br>
  <textarea style="width: 300px;height: 300px;border: 1px black;border:1px solid black;" name="testo" id="txt" maxlength="1000" oninput="controllo()"></textarea> 
  <input type="hidden" id="jsonData" name="jsonData"/>
  <input type="submit" name="dati" style="display:none">
  </form>
  </div>
  
  <div id="ris">
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
  </div><br><br>
   <p>Admin, inserisci credenziali!</p>
      <div>
             <form action=login method="post">
      		<p>User:</p>
      		<input type="text" id="username" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" id="pass" size="20"/><br><br>
      		<input type="submit" value="Login"/>
      		</form>
      </div>
     
   </body>
</html>

