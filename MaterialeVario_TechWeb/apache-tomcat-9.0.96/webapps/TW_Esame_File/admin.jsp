<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="java.util.*"%>
<!-- --------------------ATTENZIONE SI DEVE FARE I GIUSTI IMPORT------------------- -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Admin Page</title>
 </head>
<body>
<h2>Lista delle sessioni attive:</h2>
<%  HashMap<HttpSession, Integer> sessioniAttive = (HashMap<HttpSession, Integer>)this.getServletContext().getAttribute("sessioniAttive");
    List<Long> tot = (ArrayList<Long>) this.getServletContext().getAttribute("tot");
    
    int count = 0 ;
    
    System.out.println(sessioniAttive.size());
    
  if(sessioniAttive!=null){
    for(HttpSession sessione : sessioniAttive.keySet()){
   
  %>
    <p>Sessione: <%=sessione.toString()%></p>
    <p>Num Richieste: <%=sessioniAttive.get(sessione)%></p>
    <%} }
      if(tot!=null){
       for(int i=0; i<tot.size(); i++){ 
          if(tot.get(i) - System.currentTimeMillis() < 60 * 60 *1000)
        	   count++;
       }} %>
          <p>Numero richieste totali negli ultimi 60 minuti: <%=count%></p>
   
</body>
</html>
