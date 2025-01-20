<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->

<%@ page import="Beans.Cart"%>
<%@ page import="Beans.Item"%>
<%@ page import="Beans.GruppoUtenti"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="Beans.Catalogo"%>
<%@ page import="java.util.*"%>


<!-- metodi richiamati nel seguito -->
<%!

void add(Cart cart, Item item, int order) {

	boolean alreadyInCart = false;
	
	
	
	for ( Item itemInCart : cart.getItems().keySet() ) {
		if ( itemInCart.equals( item ) ) {
			alreadyInCart = true;
			order += cart.getOrder(itemInCart);
			cart.put(item, order);
			break;
		}
	}
	
	if ( ! alreadyInCart ) {
		cart.put(item,order);
	}
			
}

double total(Cart cart) {
	double total = 0;
	for ( Item item : cart.getItems().keySet() ) {
		total += item.getPrezzo() * cart.getOrder(item);
	}
	return total;
}

%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="RV">
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/catalogue.js"></script>
		<title>Cart JSP</title>
		
	</head>

<%
	Integer err = (Integer)session.getAttribute("errore");
	if(err !=null && err ==1)
	{
%>

		
		<body onload="myFunc();">
	<%}
	  else
	  {%>
		  <body>
	  <%}%>

				
			<jsp:useBean id="usersGroup" class="Beans.GruppoUtenti" scope="application" />
			<div id="main" class="clear">
			
<%
			Gson g = new Gson();
			String cat = (String) session.getAttribute("catalogo");
							
			Catalogo catalogo = new Catalogo();
			Item[] itemsInCatalog = g.fromJson(cat, Item[].class);
			for(int i =0 ; i< itemsInCatalog.length; i++)
			{
				catalogo.add(itemsInCatalog[i]);
			}
			usersGroup = (GruppoUtenti)session.getAttribute("gruppo");
			Cart cart = usersGroup.getCarrello();
			
			if ( request.getParameter("empty") != null && request.getParameter("empty").equals("ok") ) {
				cart.empty();
			}

			if ( request.getParameter("add") != null && request.getParameter("add").equals("add to cart") ) {
				Item item = new Item();
				item.setDescrizione( request.getParameter("descrizione") );
				item.setPrezzo( Double.parseDouble( request.getParameter("prezzo") ) );
				item.setQuantita( Integer.parseInt(request.getParameter("quantita") ) );
				int order = Integer.parseInt( request.getParameter("order") );
				if ( order > item.getQuantita() ) 
					throw new Exception("There are not enough items to complete the order!");
				add(cart,item,order);
				usersGroup.setCarrello(cart);
				session.setAttribute("gruppo", usersGroup);
			}
%>			
	<div id="left" style="float: left; width: 48%; border-right: 1px solid grey">
			
				<p>Select an item from the catalogue:</p>
				<table class="formdata">
					<tr>
						<th style="width: 25%">Description</th>
						<th style="width: 25%">Price</th>
						<th style="width: 25%">Your order</th>
						<th style="width: 25%"></th>
					</tr>
					
						
					<%
					
					if(err == null )
					{
						Item[] catalogueItems = catalogo.getCatalog().toArray(new Item[0]);
						for( Item aCatalogueItem : catalogueItems ){  
					%> 
					<tr>
					<form action="#">
						
								<td><%= aCatalogueItem.getDescrizione() %></td>
								<td><%= aCatalogueItem.getPrezzo() %> &#8364;</td>
								<td><input type="text" name="order" style="background-color: #c3c3d7;"/></td>
								<td>
									<input type="hidden" name="descrizione" value="<%= aCatalogueItem.getDescrizione() %>"/>
									<input type="hidden" name="quantita" value="<%= aCatalogueItem.getQuantita() %>"/>
									<input type="hidden" name="prezzo" value="<%= aCatalogueItem.getPrezzo() %>"/>
									<input type="submit" name="add" value="add to cart"/>
								</td>
						
					</form>
						
					</tr>
					<% } 
					}
					else
					{
					%>
						<tr id="myTr">
						
						</tr>
					<% }%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
			</div>
			
			<div id="right" style="float: right; width: 48%">
				<p>Cart content:</p>
				<table class="formdata">
					<tr>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Your order</th>
					</tr>
					<% 
					Set<Item> cartItems = cart.getItems().keySet();
					for( Item aCartItem : cartItems ){  
					%> 
						<tr>
							<td><%= aCartItem.getDescrizione() %></td>
							<td><%= aCartItem.getPrezzo() %> &#8364;</td>
							<td><%= cart.getOrder(aCartItem) %></td>
						</tr>
					<% 
					} 
					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
				<br/>
				<p>
				Total: <span style="font-size: x-large; color: red;"><%= total(cart) %> &#8364;</span>
				</p>
				
				<%
				if ( cart.getItems().size() > 0 ) {
				%>
					<br/>
					<a href="?empty=ok">Remove all items from the cart</a>
					<form action="Finalize">
						<input type="submit" name="finalizza" value="Finalizza Ordine"/>
					</form>
				<%
				}
				%>
			</div>
		
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			
		</div>
			
			
	
	</body>
</html>