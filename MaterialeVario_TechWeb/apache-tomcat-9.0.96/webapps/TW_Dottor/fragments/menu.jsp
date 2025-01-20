<div id="menu">
	<ul id="tabs">
		</li>
		<li <%= request.getRequestURI().contains("cart") ? "style=\"background-color: #aaa;\"" : ""%>>
			<a href="<%= request.getContextPath() %>/pages/cart.jsp">Manage cart</a>
		</li>
	</ul>
</div>