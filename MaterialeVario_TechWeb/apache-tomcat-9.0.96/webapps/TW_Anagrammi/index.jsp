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
  <h5>Inserisci parola</h5>
  <div>
  <label>parola: </label><br>
  <input type="text" id="fl1" oninput="controllo()" maxlength="8"><br>
  </div>
  
    
    <form id="v1" style="display:none" action="servlet1" method="post" >
    <input type="text" id="t1" name="vittoria2" id="servl"><br>
    <input type="submit">
   </form>
   
    <form id="v2" style="display:none" action="servlet2" method="post" >
    <input type="text" id="t2" name="vittoria1" id="servl"><br>
     <input type="submit">
   </form>
  
  <div >
   <div id="ris"></div>
    <div id="tris"></div>
  </div><br>
 
   </body>
</html>

