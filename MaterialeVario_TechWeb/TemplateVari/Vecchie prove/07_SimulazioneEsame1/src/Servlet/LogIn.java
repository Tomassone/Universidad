package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Catalogo;
import Beans.GruppoUtenti;
import Beans.Item;
import Beans.User;

public class LogIn extends HttpServlet{
	
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
		Map<String,User> utentiRegistrati = new HashMap<String, User>();
		User u = new User();
		u.setUserName("pinco pallino");
		u.setPwd("asdasd");
		u.setGroupId("g01");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		
		u = new User();
		u.setUserName("tizio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		u = new User();
		u.setUserName("caio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		u = new User();
		u.setUserName("sempronio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		
		Map<String, GruppoUtenti> gruppi = new HashMap<String, GruppoUtenti>();
		GruppoUtenti gu = new GruppoUtenti();
		gu.setId("g01");
		gruppi.put(gu.getId(), gu);
		gu = new GruppoUtenti();
		gu.setId("g02");
		gruppi.put(gu.getId(), gu);
		this.getServletContext().setAttribute("gruppi", gruppi);
		
		
		Catalogo catalogo = new Catalogo();
		Item i = new Item();
		i.setItemId("001");
		i.setDescrizione("Salvagente");
		i.setPrezzo(4);
		i.setQuantita(5);
		
		catalogo.add(i);
		
		i = new Item();
		i.setItemId("002");
		i.setDescrizione("pinne");
		i.setPrezzo(5);
		i.setQuantita(3);
		
		catalogo.add(i);
		
		i = new Item();
		i.setItemId("003");
		i.setDescrizione("secchiello e paletta");
		i.setPrezzo(3);
		i.setQuantita(7);
		
		catalogo.add(i);
		this.getServletContext().setAttribute("catalogo", catalogo);
		
	}
	
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		Map<String,User> utentiRegistrati = (Map<String, User>) this.getServletContext().getAttribute("utentiRegistrati");
		User utente = utentiRegistrati.get(name);		
		if(utente == null)
		{
			//utente non registrato
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");	
			rd.forward(request, response);
			return;
		}		
		if(utente.getPwd().compareTo(request.getParameter("pwd"))!=0)
		{
			//			pwd errata		
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");	
			rd.forward(request, response);
			return;
		} // altrimenti tutto OK, si procede
		
		HttpSession session = request.getSession();
		utente.setSession(session);
		Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>)this.getServletContext().getAttribute("gruppi");
		GruppoUtenti gruppo = gruppi.get(utente.getGroupId());
		// ci andrebbe un controllo, ma do per scontato che non torni null
		session.setAttribute("currentUser", utente);
		gruppo.addUserToGroup(utente);
		
		Catalogo catalogo = (Catalogo)this.getServletContext().getAttribute("catalogo");
		Item[] itemsInCatalog = new Item[catalogo.getCatalog().size()];
		itemsInCatalog = catalogo.getCatalog().toArray(itemsInCatalog);
		String strCatalogo = this.g.toJson(itemsInCatalog);
		System.out.println(strCatalogo);
		session.setAttribute("catalogo", strCatalogo);
		session.setAttribute("gruppo", gruppo);
		
		response.sendRedirect("catalogo.jsp");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/catalogo.jsp");
//		
//		rd.forward(request, response);
		
	}
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
	

}
