<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>

<html>
   <head>
      <title>login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/contatore.js"></script>
   </head>
   <body>
      <div>
      	<h3>INSERISCE NOME FILE PRESENTE SERVER SIDE:</h3>
      	<form id="fileForm" action="servlet1" method="post" >
      	<input type="text" name="file" id="fl" oninput="myfunction()"/><br><br>
      	<label>Quali caratteri vuoi contare: </label><br>
      	<input type="radio" name="contare" value="alfa"/>Alfabetici<br>
        <input type="radio" name="contare" value="num"/>Numerici<br>
        <input type="hidden" id="jsonData" name="jsonData" />
        <input type="submit" name="datijson" style="display:none">
        
        </form>
      </div>
       
    <div id="result"></div>
   </body>
</html>