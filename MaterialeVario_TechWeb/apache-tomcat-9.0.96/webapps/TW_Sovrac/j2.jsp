
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
            HttpSession sessione = request.getSession();
            String text  = testo.toString();
	        Gson g = new Gson();
	        Random random = new Random();
	        char ch =' ';
			StringBuilder str2 = new StringBuilder(); 

			int index = random.nextInt(9); //estraggo numeri da 0 a 9
			 for(int i=0; i<testo.length(); i++) {
				   ch = testo.charAt(i);
				   System.out.println(i+"-"+ch);
				   //MI PRENDO IL CARATTERE E QUANDO L'INDICE NON E' MULTIPLO LO APPENDO
				  if( (index!=0 && i%index!=0)|| (i==0 && index!=0) && ch!=' ')
					  str2.append(ch);
			  }
	        
	        System.out.println("Numero: "+ index);
	        System.out.println("Modificato2: \n"+ str2);
            int conteggio=0;
	        
	        /*for(int i=0; i<mod.length(); i++){
	        	int c =mod.charAt(i); 
 
		           if(c>=65 && c<= 90) //se è una lettera maiscola
		            conteggio++; 
	        }*/
	       
            Risultato res = new Risultato();
            res.setTesto(str2.toString());
     
            //MI PRENDO IL NUMERO DI RICHIESTE ATTIVE E LE DIMINUISCO
            /*Integer t = (Integer) sessione.getAttribute("numrichieste");
            if(t!=null) sessione.setAttribute("numrichieste", t--);*/
            
            this.getServletContext().setAttribute("result", g.toJson(res));
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
   	        dispatcher.forward(request, response);
       %>
    </div>
</body>
</html>
