<%@ page session="true"%>
<%@ page import="Beans.*"%>
<%@ page import="java.util.*"%>


<html>
   <head>
      <title>Index</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
<body> 

 	<%  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   Map<String, Gruppo> gruppi = (Map<String, Gruppo>)this.getServletContext().getAttribute("gruppi");
		for(String gid : gruppi.keySet())
		{
			Cart groupCart = gruppi.get(gid).getCarrello();
			if(groupCart != null && groupCart.getNumeroTickets()>0)
			{
	%>
 <tr>
	<form action="finalize" method="post">		
		<td><%= gid %></td>
	   <td><%= groupCart.getNumeroTickets() %> </td>
		<td>
		<input type="hidden" name="gid" value="<%=gid%>"/>
		<input type="submit" name="finalize" value="finalize order"/></td>
		<td>
		       <input type="hidden" name="gid" value="<%=gid%>"/>
				<input type="submit" name="reset" value="reset order"/>
			</td>
	
	</form>
</tr>
<% } } %>
   </body>
</html>

