<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
     <script type="text/javascript" src="scripts/wsocket_2.js"></script>
     <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
<title>Admin</title>
</head>
<body>
   <p>Admin, inserisci credenziali!</p>
      <div>
      		<p>User:</p>
      		<input type="text" id="user" size="20"/><br>
      		<p>Password:</p>
      		<input type="password" id="pw" size="20"/><br><br>
      		<input type="button" value="Login" onclick="myFunction('admin')" />
      </div>
    	
    	<div id="admindiv" style="display: none;">
        <input type="text" id="adminMessage" placeholder="Inserisci messaggio di update">
        <button id="sendUpdate" onclick="myFunction('admin')">Invia Update</button>
    </div>
</body>
</html>