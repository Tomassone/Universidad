<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="java.util.List"%>

<!-- metodi richiamati nel seguito -->
<%!
//AGGIUNGO AL CART
void add( Item item, Cart cart, int order) {
	boolean alreadyInCart = false;

	for ( Item articolo : cart.getItems() ) {  
		if ( articolo.getDescription().equals( item.getDescription() ) ) {
            alreadyInCart=true;
            //SE E' GIà PRESENTE NEL CARRELLO AGGIUNGO LA QUANTITA'
            order+=cart.getOrder(articolo);
            cart.put(item, order);
            break;
		}
	}
	
	if ( ! alreadyInCart) {
		cart.put(item, order);
	}
}

double total(Cart cart){
	double tot=0;
	for(Item it: cart.getItems()){
		//MOLTIPLICO PREZZO PER LA QUANTITà PRESENTE NEL CARRELLO
		tot+= it.getPrice()*cart.getOrder(it);
	}
	return tot;
}

%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Cart JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">

			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Catalogue" scope="application" />
			<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Cart" scope="session" />
			
			<%
					if ( request.getParameter("add") != null && request.getParameter("add").equals("aggiungi") ) {
						Item item = new Item();
						item.setDescription( request.getParameter("description") );
						item.setPrice( Double.parseDouble( request.getParameter("price") ) );
						item.setQuantity( Integer.parseInt(request.getParameter("quantity") ) );
						int num = Integer.parseInt(request.getParameter("order"));
						
						if(num < item.getQuantity())
						  add(item, cart , num);
						else 
							throw new Exception("Non ci sono abbastanza articoli!");
							
					}
			%>
				
			<div id="left" style="float: left; width: 48%">

				<p>Current catalogue:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Quantity</th>
						<th style="width: 7%"></th>
					</tr>
					<% 
					Item[] items = catalogue.getItems().toArray(new Item[0]);
					for( Item anItem : items ){  
					%> 
					  <form>
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><input type="number" name="order" min="0" step="1"></td>
							<td>
							 <input type="hidden" name="description" value="<%= anItem.getDescription() %>"/>
							 <input type="hidden" name="quantity" value="<%= anItem.getQuantity() %>"/>
							 <input type="hidden" name="price" value="<%= anItem.getPrice() %>"/>
							  <input type="submit" name="add" value="aggiungi" style="width:100%"/>
							</td>
						</tr>
						</form>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
			</div>
			
				<div id="right" style="float: right; width: 48%; border-right: 1px solid grey">
			
				<p>Carrello:</p>
				<table class="formdata">
					<tr>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Quantity</th>
					</tr>
					<% 
					Item[] cartItems = cart.getItems().toArray(new Item[0]);
					for( Item anItem : cartItems ){  
					%> 
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><%= cart.getOrder(anItem) %></td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<p>
				Totale: <span style="color: red;"><%= total(cart) %> &#8364;</span>
				</p>
			</div>
		
		<div class="clear">
				<p>&nbsp;</p>
			</div>	
		</div>
		
		
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
