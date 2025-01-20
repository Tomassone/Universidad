
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="false"%>
<%@ page import="it.unibo.tw.web.beans.Feed"%>
<%@ page import="it.unibo.tw.web.beans.FeedDb"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<jsp:useBean id="feedDb" class="it.unibo.tw.web.beans.FeedDb"  scope = "application"/>

<%

// QUESTO E' IMPORTANTISSIMO AFFINCHE' L'INTERPRETE JAVASCRIPT RICONOSCA IL CONTENUTO COME XML!!!!!!
response.setHeader("Content-Type","text/plain");

%>

<%
		//recupero quello che mi sta scrivendo l'utente
		String cat = request.getParameter("inizio");
			
   if(cat!=null && !cat.trim().isEmpty()){
		// recupero la lista delle categorie
		List<String> someFeeds = feedDb.getCategories(cat);
		
		String categ = null ;
		
		//se la lista non è vuota
		if(!someFeeds.isEmpty()) categ=someFeeds.get(0);
		
		if(categ!=null)
	        response.getWriter().write(categ);
		else 
			response.getWriter().write("");
   }
   else 
	   response.getWriter().write("");

		%>
	
