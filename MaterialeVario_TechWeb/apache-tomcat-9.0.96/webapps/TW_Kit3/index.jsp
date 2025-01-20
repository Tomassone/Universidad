<%@ page session="true"%>
<%@ page import="Beans.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/split.js"></script>
		
   </head>
<body> 
<h2>Inserisci valori nelle due matrici</h2>

<h4>Prima matrice A:</h4>
 	<%  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   for(int i=0; i<2; i++){
 		   for(int j=0; j<2; j++){
	%>
     <input type="number" id="valueA<%=i%><%=j%>" style="width: 40px;" onchange="invio()">
     
<% } %> 
<br>
     
<%} %>

<h4>Seconda matrice B:</h4>
 	<%  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   for(int i=0; i<2; i++){
 		   for(int j=0; j<2; j++){
	%>
     <input type="number" id="valueB<%=i%><%=j%>" style="width: 40px;" onchange="invio()">
     
<% } %> 
<br>
     
<%} %>
            <br>
   			<button style="display:none;" id="bt1" name="seq" onclick="sequenz()"/>Sequenziale</button>
			<button style="display:none;" id="bt2" name="parallelo" onclick="paral()"/>Parallelo</button>
   
   <div id="result"></div><br><br>
   
  <h4>Admin inserisci le tue credenziali:</h4>
 <form action="servletAdmin" method="post">
 <label>Username: </label>
 <input type="text" name="user" ><br>
 <label>Password: </label>
 <input type="password" name="pw" ><br>
 <button type="submit">Login</button>
 </form>
 
  <% 
  HttpSession sessn = request.getSession();
  String user = (String)sessn.getAttribute("user");
  
  if(user!=null){
  Long conta = (Long) this.getServletContext().getAttribute("numoper");
  List<HttpSession> activeSessions = (List<HttpSession>) this.getServletContext().getAttribute("activeSession");
   
  if(conta!=null && activeSessions!=null) {
    if(conta>1 && activeSessions.size()>1){
 
  %>
  <h3>Stato delle Sessioni:</h3>
      <p>Numero di sessioni attive: <%= activeSessions.size() %></p>
       <p>Operazioni effettuate nell'ultima ora: <%= conta %></p>
     <form action="servletAdmin" method="post">
     <input type="submit" id="termina" name="term" value="Forza Terminazione">
    </form>
     <%
    }
     } }%>
   </body>
</html>

