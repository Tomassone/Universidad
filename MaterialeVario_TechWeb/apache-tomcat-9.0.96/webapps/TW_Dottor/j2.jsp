
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>
<%@ page import="Beans.*"%>
<%@ page import="java.io.*"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>J2 Page</title>
 </head>
<body>
   <div>
     SONO NELLA J2
       <%  String fls = request.getParameter("jsonData");
           Gson g = new Gson();
           
           //ARRAY DI NOMI DI FILE
           String[] file = g.fromJson(fls, String[].class);
           
           String[] nomi = new String[file.length];

           
           for(int i=0; i<file.length; i++){
              nomi[i] = file[i].trim();
   		      System.out.println(nomi[i]);

           }
           
           //MI PRENDO DOVE STANNO I FILE
		   String dir = this.getServletContext().getInitParameter("dir");
           	
		   try {
		     for(int i=0; i<file.length; i++) {
			   File dire = new File(this.getServletContext().getRealPath("/"+ dir+"/"+nomi[i]));
			   System.out.println(dire+"\n");
			 
			   FileInputStream fs = new FileInputStream(dire);
			   
			   //0->FRONTE RETRO E MANDATO AD UNA SERVLET1
			   //1->SOLO FORNTE E MADNATO AD UNA SERVLET2
			   int pagine = Math.ceilDiv( fs.readAllBytes().length, 100);
			   
			   System.out.println("pagine: "+pagine);
			   
			   fs.close();
			        //FRONTE E RETRO, DA MANDARE ALLA SERVLET1
			     this.getServletContext().setAttribute("nome", nomi[i]);
				   if( pagine > 10 ){
	                  this.getServletContext().setAttribute("s1", pagine);
	                  RequestDispatcher dispatcher = request.getRequestDispatcher("servlet1");
	         	      dispatcher.forward(request, response);

				   }else{//SOLO FRONTE DA MANDARE ALLA SERVLET2
					   this.getServletContext().setAttribute("s2", pagine);
		               RequestDispatcher dispatcher = request.getRequestDispatcher("servlet2");
		         	   dispatcher.forward(request, response);
	 			   }
				   
				  
			   
		     }
		  }catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
       %>
    </div>
</body>
</html>
