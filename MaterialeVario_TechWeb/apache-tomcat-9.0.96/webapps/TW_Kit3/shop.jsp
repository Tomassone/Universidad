<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="Beans.*" %>
 
 
 <%!

void add(Cart cart, int order) {

	cart.add(order);
			
}
 
double total(Cart cart) {
		double total = 0;
		total = cart.getNumeroTickets() * 5;
		return total;
	}

 %>
 
 <%
  User user = (User) session.getAttribute("currentUser");
   if (user == null) {
     response.sendRedirect("start.html");
     return;
 }
 %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="scripts/utils.js"></script>
<meta charset="UTF-8">
<title>Acquisti</title>
</head>
<body>
 <jsp:useBean id="usersGroup" class="Beans.Gruppo" scope="application" />
 
 <%
	usersGroup = (Gruppo)session.getAttribute("gruppo");
   Cart cart = usersGroup.getCarrello();
   if(cart==null){
	   cart = new Cart();
	   usersGroup.setCarrello(cart);
   }
   
   if ( request.getParameter("empty") != null && request.getParameter("empty").equals("ok") ) {
		cart.empty();
	}
   
   if ( request.getParameter("add") != null && request.getParameter("add").equals("Acquista") ) {
		
		int order = Integer.parseInt( request.getParameter("tickets") );
		
		add(cart,order);
		usersGroup.setCarrello(cart);
		session.setAttribute("gruppo", usersGroup);
	}

 %>
  <h3>Benvenuta <%=user.getUserName() %> del gruppo <%=usersGroup.getId() %></h3>
 
 <form >
 <label>Biglietti: </label>
 <input type="number" size="2" name="tickets" ><br><br>
 <button type="submit" name="add" value="Acquista">Acquista</button>
 </form><br>
 <% 
	if(cart.getNumeroTickets() !=0){  
	%> 
		<tr>
			<td>Biglietti nel Carrello: <%=cart.getNumeroTickets() %></td> 
			<td>x 5 &#8364;</td>
		</tr>
	<% 
	} 
	%>
	<br>
	Total: <span><%= total(cart) %> &#8364;</span>
	<%
	if ( cart.getNumeroTickets() > 0 ) {
	%>
		<br/>
		<a href="?empty=ok">Remove all items from the cart</a>
		<form action="finalize" method="post">
			<input type="submit" name="finalizza" value="Finalizza"/>
		</form>
	<%
	}
	%>
</body>
</html>
