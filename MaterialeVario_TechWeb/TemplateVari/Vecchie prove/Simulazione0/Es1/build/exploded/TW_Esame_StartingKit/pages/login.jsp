<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utente" class="beans.Credenziali" scope="session"/>
<!-- creo DB fittizio -->
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="catalogo" class="beans.Catalogo" scope="application"/>
<!-- inizializzo DB fittizio -->
<%
	Credenziali userOne = new Credenziali();
	Credenziali userTwo = new Credenziali();
	Credenziali userThree = new Credenziali();
	userOne.setUser("Cesare");
	userOne.setPassword("Tomasi");
	userOne.setIdGroup("1");
	userTwo.setUser("Federico");
	userTwo.setPassword("Ferro");
	userTwo.setIdGroup("2");
	userThree.setUser("Leonardo");
	userThree.setPassword("Barone");
	userThree.setIdGroup("1");
	utenti_salvati.getElementi().add(userOne);
	utenti_salvati.getElementi().add(userTwo);
	utenti_salvati.getElementi().add(userThree);
	
	Prodotto productOne = new Prodotto();
	Prodotto productTwo = new Prodotto();
	Prodotto productThree = new Prodotto();
	productOne.setIdProdotto("1");
	productOne.setTestoPresent("coltelli");
	productOne.setCosto(5);
	productOne.setUnitaDisp(8);
	productTwo.setIdProdotto("2");
	productTwo.setTestoPresent("forchette");
	productTwo.setCosto(5);
	productTwo.setUnitaDisp(9);
	productThree.setIdProdotto("3");
	productThree.setTestoPresent("cucchiai");
	productThree.setCosto(5);
	productThree.setUnitaDisp(10);
	catalogo.getElementi().add(productOne);
	catalogo.getElementi().add(productTwo);
	catalogo.getElementi().add(productThree);
%>

<% 
	session.setAttribute("isLogged", false);
%>

<html>
   <head>
	   <title>User login</title>
	   <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>
		<form action="catalogue.jsp">
			<p>Utente: <input type="text" name="user"></p>
			<p>Password: <input type="text" name="password"></p>
			<p><input type="submit" value="Conferma">	<input type="reset" value="Azzera"></p>
		</form>
   </body>
</html>

