package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Cart;
import Beans.Catalogo;
import Beans.GruppoUtenti;
import Beans.Item;
import Beans.User;

public class Finalize extends HttpServlet{
	
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		Catalogo catalogo = (Catalogo) this.getServletContext().getAttribute("catalogo");
		Item[] itemsInCatalog = new Item[catalogo.getCatalog().size()];
		itemsInCatalog = catalogo.getCatalog().toArray(itemsInCatalog);
		String strCatalogo = this.g.toJson(itemsInCatalog);
		System.out.println(strCatalogo);
		response.getWriter().println(strCatalogo);
	}

	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		GruppoUtenti gruppo = (GruppoUtenti) session.getAttribute("gruppo");
		Catalogo catalogo = (Catalogo) this.getServletContext().getAttribute("catalogo");
		Catalogo catalogoBackup = (Catalogo) this.getServletContext().getAttribute("catalogo");
		User currentUser = (User) session.getAttribute("currentUser");
		Cart cart = gruppo.getCarrello();
		for(Item itemInCart : cart.getItems().keySet())
		{
			Item itemInCat = catalogoBackup.getItemFromCatalogue(itemInCart);
			int giacenza = itemInCat.getQuantita() - cart.getOrder(itemInCart);
			if( giacenza > 0)
			{
				//ok
				itemInCat.setQuantita(giacenza);
				
			}
			else if(giacenza == 0)
				{
					//ok prodotto finito
					catalogoBackup.remove(itemInCat);
				}
				else
				{
					session.setAttribute("errore", 1);
				
					catalogoBackup.remove(itemInCat);
					catalogo.remove(itemInCat);
					this.getServletContext().setAttribute("catalogo", catalogo);
				
					response.sendRedirect("catalogo.jsp");
					return;
				
				}
		}
		catalogo = catalogoBackup;
		// si, un blocco Synchronized sarebbe stato utile ;)
		this.getServletContext().setAttribute("catalogo", catalogo);
		int sessionCounter =0;
		for(User u : gruppo.getUtenti())
		{
			if(u.equals(currentUser))
			{
				u.setSession(null);
			}		
			if(u.getSession() == null)
			{
				sessionCounter++;
			}		
		}	
		if(gruppo.getUtenti().size() == sessionCounter)
		{
			//finalizza
			session.setAttribute("success", 2);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
		}
		else
		{
			session.setAttribute("success", 1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");		
			rd.forward(request, response);
			return;
		}
			
	}
}
