
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>J2 Page</title>
 </head>
<body>
   <div>
       <%   StringBuilder testo = (StringBuilder) this.getServletContext().getAttribute("testo");
             String text  = testo.toString();
	        String mod = "";
	        Gson g = new Gson();
	        Random random = new Random();
		    char randomchar = (char) ('a' + random.nextInt(26));
		    mod= text.replaceAll(String.valueOf(randomchar), "");
	        
	        System.out.println("Lettera2: "+ randomchar);
	        System.out.println("Modificato2: \n"+ mod);
            int conteggio=0;
	        
	        /*for(int i=0; i<mod.length(); i++){
	        	int c =mod.charAt(i); 
 
		           if(c>=65 && c<= 90) //se è una lettera maiscola
		            conteggio++; 
	        }*/
	       
            Risultato res = new Risultato();
            res.setTesto(mod);
     
            this.getServletContext().setAttribute("result", g.toJson(res));
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
   	        dispatcher.forward(request, response);
       %>
    </div>
</body>
</html>
