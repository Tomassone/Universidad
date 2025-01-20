 <%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="Beans.*" %>
<%@ page import="java.io.*" %>


<html>
   <head>
      <title>JN</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
   </head>
   <body>
   <% 
     HttpSession s = (HttpSession) request.getAttribute("sessione");
     String nome = (String) s.getAttribute("nomefile");
	 System.out.println("nome1: "+nome);
     
     if (nome == null) {
         session.setAttribute("errore", 1);
         return;
     }
      
	 File dire = new File(this.getServletContext().getRealPath("/fold/"+nome).trim());
	 if (!dire.exists()) {
         session.setAttribute("errore", 2);
         return;
     }
	 
	 FileInputStream fs = new FileInputStream(dire);
	 
	 int c;
	 //faccio un buffer della lunghezza del file
     byte[] buf = new byte[(int) dire.length()];
	 //leggo lunghezza file e la metto nel buffer
     fs.read(buf);
     fs.close();
     
     //mi creo una stringa del contenuto
     String contenuto = new String(buf);
     //Array di linee
     String[] lines = contenuto.split("\n");
     
     //linea iniziale da confrontare
	 String linea = "Il numero di caratteri numerici del file è uguale a";

	   boolean uguali = false;
	
	   //se ci sono linee da confrontare e vedo se inizia con quello che sto cercando
	   if(lines.length>0 && lines[0].startsWith(linea)){
		   uguali=true;
	   }
	   	 
	 if(uguali==false){	
		FileInputStream fs1 = new FileInputStream(dire);
		 int num=0;

	  while( ( c= fs1.read() ) >0 ) {
		 if ( Character.isDigit(c)) {
			 num++;
		}
	  }	
		 System.out.println("numl: "+num);

     fs1.close();
     
     PrintWriter writer = new PrintWriter(new FileWriter(dire));
     //scrivo la mia linea iniziale
     writer.println("Il numero di caratteri numerici del file è uguale a " + num);
     //poi ci allego il contenuto che mi ero salvata nella stringa
     writer.println(contenuto); 
     writer.close();
     out.println("Il numero di caratteri numerici del file è uguale a " + num);
         
	}else{
		System.out.println("ERRIRE");
		s.setAttribute("errore",3);
	}
   
   %>
      <div>
      
      	STO CONTANDO I CARATTERI NUMERICI
      	  <%    HttpSession ses = (HttpSession) request.getAttribute("sessione");
      	
      	
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
      	<br> HAI GIa' CONTATO I CARATTERI NUMERICI DI QUESTO FILE
      	<%
        ses.removeAttribute("errore");
} }%>
      	
      </div>
   </body>
</html>