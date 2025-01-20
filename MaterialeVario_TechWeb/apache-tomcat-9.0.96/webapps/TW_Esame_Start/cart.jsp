<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="Beans.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%>


<!-- metodi richiamati nel seguito -->
<%!

void add(Cart cart, Prodotto item, int order) {

	boolean alreadyInCart = false;
	
	for ( Prodotto itemInCart : cart.getItems() ) {
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
	for ( Prodotto item : cart.getItems() ) {
		total += item.getPrice() * cart.getOrder(item);
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
    System.out.println(err);
   if(err !=null && err ==1)
	{
%>
		<body onload="myFunc();">
	<%}
	  else
	  {%>
		  <body>
	  <%}%>
			<jsp:useBean id="usersGroup" class="Beans.Gruppo" scope="application" />
			<div id="main" class="clear">
<%
           System.out.println("ERRORE");
			Gson g = new Gson();
			String cat = (String) session.getAttribute("catalogo");
	        System.out.println("cat"+ cat);

			Catalogo catalogo = new Catalogo();
			Prodotto[] itemsInCatalog = g.fromJson(cat, Prodotto[].class);
			for(int i =0 ; i< itemsInCatalog.length; i++)
			{
				catalogo.add(itemsInCatalog[i]);
			}

			usersGroup = (Gruppo)session.getAttribute("gruppi");
		    System.out.println(usersGroup);

			Cart cart = usersGroup.getCarrello();
		    System.out.println("2");


			if ( request.getParameter("empty") != null && request.getParameter("empty").equals("ok") ) {
				cart.empty();
			}

		    System.out.println("2");

			
			if ( request.getParameter("add") != null && request.getParameter("add").equals("add to cart") ) {
				Prodotto item = new Prodotto();
				item.setDescription( request.getParameter("descrizione") );
				item.setPrice( Double.parseDouble( request.getParameter("prezzo") ) );
				item.setQuantity( Integer.parseInt(request.getParameter("quantita") ) );
				int order = Integer.parseInt( request.getParameter("order") );
				if ( order > item.getQuantity() ) 
					throw new Exception("There are not enough items to complete the order!");
				add(cart,item,order);
				usersGroup.setCarrello(cart);
				session.setAttribute("gruppi", usersGroup);
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
						Prodotto[] catalogueItems = catalogo.getItems().toArray(new Prodotto[0]);
						for( Prodotto aCatalogueItem : catalogueItems ){  
					%> 
					<tr>
					<form action="#">
						
								<td><%= aCatalogueItem.getDescription() %></td>
								<td><%= aCatalogueItem.getPrice() %> &#8364;</td>
								<td><input type="text" name="order" style="background-color: #c3c3d7;"/></td>
								<td>
									<input type="hidden" name="descrizione" value="<%= aCatalogueItem.getDescription() %>"/>
									<input type="hidden" name="quantita" value="<%= aCatalogueItem.getQuantity() %>"/>
									<input type="hidden" name="prezzo" value="<%= aCatalogueItem.getPrice() %>"/>
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
					Set<Prodotto> cartItems = cart.getItems();
					for( Prodotto aCartItem : cartItems ){  
					%> 
						<tr>
							<td><%= aCartItem.getDescription() %></td>
							<td><%= aCartItem.getPrice() %> &#8364;</td>
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
					<form action="carrello">
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
