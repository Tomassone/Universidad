<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="utenti_salvati" class="beans.UtentiSalvati" scope="application"/>
<jsp:useBean id="menu" class="beans.Menu" scope="application"/>
<jsp:useBean id="locale" class="beans.Locale" scope="application"/>

<!-- inizializzo DB fittizio -->
<%
	Credenziali userOne = new Credenziali();
	Credenziali userTwo = new Credenziali();
	Credenziali userThree = new Credenziali();
	userOne.setUser("Cesare");
	userOne.setPassword("Tomasi");
	userOne.setRuolo("cliente");
	userTwo.setUser("Federico");
	userTwo.setPassword("Ferro");
	userTwo.setRuolo("cameriere");
	userThree.setUser("Leonardo");
	userThree.setPassword("Barone");
	userThree.setRuolo("amministratore");
	utenti_salvati.getElementi().add(userOne);
	utenti_salvati.getElementi().add(userTwo);
	utenti_salvati.getElementi().add(userThree);
	
	Drink productOne = new Drink();
	Drink productTwo = new Drink();
	Drink productThree = new Drink();
	productOne.setIdDrink("1");
	productOne.setStato("daConsegnare");
	productOne.setCosto(5);
	productOne.setUnita(8);
	productTwo.setIdDrink("2");
	productTwo.setStato("daConsegnare");
	productTwo.setCosto(5);
	productTwo.setUnita(9);
	productThree.setIdDrink("3");
	productThree.setStato("daConsegnare");
	productThree.setCosto(5);
	productThree.setUnita(10);
	menu.getElementi().add(productOne);
	menu.getElementi().add(productTwo);
	menu.getElementi().add(productThree);
	
	Tavolo table1 = new Tavolo();
	Tavolo table2 = new Tavolo();
	Tavolo table3 = new Tavolo();
	table1.setIdTavolo("1");
	table2.setIdTavolo("2");
	table3.setIdTavolo("3");
	locale.getElementi().add(table1);
	locale.getElementi().add(table2);
	locale.getElementi().add(table3);
%>

<% 
	session.setAttribute("isLogged", false);
%>

<html>
	<head>
		<meta http-equiv="Refresh" content= "2; URL=pages/login.jsp"/>
      	<title>Start Web Application</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   	</head>
   	<body>
		<!-- 
	      ...so we offer the user something to read, meanwhile.
	     
	     This is the first page being shown, while the JSF Servlet starts up the environment,
	     upon the first reqeust.
	     This message avoid letting the user linger without knowing what's going on.
	     -->
      	<p>
      		Please wait for the web application to start... &nbsp;
			<img alt="wait" title="wait" src="images/wait.gif"/>
    	</p>
	</body>
</html>

