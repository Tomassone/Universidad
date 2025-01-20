<%@ page language="java" session="true"%>
<%@ page import="java.util.List, java.util.ArrayList, beans.*"%>
<jsp:useBean id="elencoAttrazioni" class="beans.ElencoAttrazioni" scope="application"/>

<!-- inizializzo DB fittizio -->
<%
	AttrazioneTuristica firstAttraction = new AttrazioneTuristica();
	AttrazioneTuristica secondAttraction = new AttrazioneTuristica();
	AttrazioneTuristica thirdAttraction = new AttrazioneTuristica();
	Coordinate firstCoord = new Coordinate();
	Coordinate secondCoord = new Coordinate();
	Coordinate thirdCoord = new Coordinate();
	firstCoord.setX(1);
	firstCoord.setY(1);
	secondCoord.setX(10);
	secondCoord.setY(10);
	thirdCoord.setX(100);
	thirdCoord.setY(100);
	firstAttraction.setCoordinate(firstCoord);
	firstAttraction.setNome("sasso");
	firstAttraction.setDescrizione("bello");
	secondAttraction.setCoordinate(secondCoord);
	secondAttraction.setNome("fiume");
	secondAttraction.setDescrizione("bello");
	thirdAttraction.setCoordinate(thirdCoord);
	thirdAttraction.setNome("albero");
	thirdAttraction.setDescrizione("bello");
	elencoAttrazioni.getElementi().add(firstAttraction);
	elencoAttrazioni.getElementi().add(secondAttraction);
	elencoAttrazioni.getElementi().add(thirdAttraction);
%>

<html>
	<head>
		<meta http-equiv="Refresh" content= "2; URL=pages/position.jsp"/>
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

