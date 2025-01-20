 <%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>
<%@ page import="java.io.*" %>


<html>
   <head>
      <title>J1</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
   </head>
   <body>
   <% 
     HttpSession s = (HttpSession) request.getSession();
     String nome = (String) s.getAttribute("nomefile");
	 System.out.println("nome1: "+nome);
     
     if (nome == null) {
         s.setAttribute("errore", 1);
         return;
     }
      
	 File dire = new File(this.getServletContext().getRealPath("/fold/"+nome).trim());
	 if (!dire.exists()) {
         s.setAttribute("errore", 2);
         return;
     }
	 
	 FileInputStream fs = new FileInputStream(dire);
	 
	 int c;
     byte[] buf = new byte[(int) dire.length()];
     fs.read(buf);
     fs.close();
     
     String contenuto = new String(buf);
     String[] lines = contenuto.split("\n");
	 String linea1 = "Il numero di caratteri alfabetici del file è uguale a";

	   boolean uguali = false;
	   Integer numprec= (Integer) this.getServletContext().getAttribute("numeroprecAlfa");
	   
	   if(numprec==null){
		   numprec=-1;
		   this.getServletContext().setAttribute("numeroprec", numprec);
	   }
	   
	  String linea = linea1 + numprec;
	  System.out.println(linea);
	   	 
	  FileInputStream fs1 = new FileInputStream(dire);
	  int numlettere=0;
	  
	  if(lines.length>1 && (lines[0].startsWith(linea1) || lines[1].startsWith(linea1)))
	   fs1.skip(linea.length());

	  while( ( c= fs1.read() ) >0 ) {
		 if ( Character.isLetter(c)) {
			        numlettere++;
		}
	  }
	  
	  if(lines.length>1 && (lines[0].startsWith(linea) || lines[1].startsWith(linea)) ){
		   
		   if(numlettere == numprec)
		    uguali=true;
	   }

	    this.getServletContext().setAttribute("numeroprecAlfa", numlettere);
	   
		System.out.println("numprec: "+numprec);

		System.out.println("numl: "+numlettere);

     fs1.close();
     
     if(uguali==false){
     PrintWriter writer = new PrintWriter(new FileWriter(dire));
     writer.println("Il numero di caratteri alfabetici del file è uguale a " + numlettere);
     writer.println(contenuto); 
     writer.close();
     out.println("Il numero di caratteri alfabetici del file è uguale a " + numlettere);
         
	}else{
		System.out.println("ERRIRE");
		s.setAttribute("errore",3);
	}
   
   %>
      <div>
      	STO CONTANDO I CARATTERI ALFABETICI
      	<% 
       HttpSession ses = (HttpSession) request.getSession();
      	
      	
        Integer errore = (Integer) ses.getAttribute("errore");
        if(errore != null){
      	if(errore == 1){ %>
      	<br> ERRORE NEL NOME DEL FILE
      	<% 
        ses.removeAttribute("errore");
}else if ( errore==2) {%>
      	<br> ERRORE: FILE NON ESISTE
      	<%
        ses.removeAttribute("errore");

      	}else if(errore==3){
      	%>
      	<br> HAI GIa' CONTATO I CARATTERI ALFABETICI DI QUESTO FILE
      	<%
        ses.removeAttribute("errore");
} }%>
      </div>
   </body>
</html>