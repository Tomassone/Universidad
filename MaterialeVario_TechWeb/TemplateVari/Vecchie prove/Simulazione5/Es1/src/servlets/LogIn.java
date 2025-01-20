package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;

public class LogIn extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		Map<String, Credenziali> utentiRegistrati = new HashMap<String, Credenziali>();
		Credenziali utente = new Credenziali();
		utente.setUser("Cesare");
		utente.setPassword("Tomasi");
		utente.setIdGroup("g01");
		utentiRegistrati.put(utente.getUser(), utente);
		Credenziali utente1 = new Credenziali();
		utente1.setUser("Federico");
		utente1.setPassword("Ferro");
		utente1.setIdGroup("g01");
		utentiRegistrati.put(utente1.getUser(), utente1);
		Credenziali utente2 = new Credenziali();
		utente2.setUser("Andrea");
		utente2.setPassword("Tonelli");
		utente2.setIdGroup("g02");
		utentiRegistrati.put(utente2.getUser(), utente2);
		Credenziali utente3 = new Credenziali();
		utente3.setUser("admin");
		utente3.setPassword("admin");
		utente2.setIdGroup("g00");
		utentiRegistrati.put(utente3.getUser(), utente3);
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		
		Map<String, Gruppo> gruppi = new HashMap<String, Gruppo>();
		Gruppo gu = new Gruppo();
		gu.setIdGruppo("g00");
		gruppi.put(gu.getIdGruppo(), gu);
		gu = new Gruppo();
		gu.setIdGruppo("g01");
		gruppi.put(gu.getIdGruppo(), gu);
		gu = new Gruppo();
		gu.setIdGruppo("g02");
		gruppi.put(gu.getIdGruppo(), gu);
		this.getServletContext().setAttribute("gruppi", gruppi);
		this.getServletContext().setAttribute("stopped", false); //per controllare se un utente è uscito senza confermare.
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		Map<String, Credenziali> utentiRegistrati = (Map<String, Credenziali>) this.getServletContext().getAttribute("utentiRegistrati");
		Credenziali utente = utentiRegistrati.get(name);
		utente.setActive(true);
		if (utente == null)
		{
			//utente non registrato
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");	
			rd.forward(request, response);
			return;
		}		
		if (utente.getPassword().compareTo(request.getParameter("pwd")) != 0)
		{
			//			pwd errata		
			utente.setTries(utente.getTries() + 1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		} // altrimenti tutto OK, si procede
		
		HttpSession session = request.getSession();
		utente.setSession(session);
		Map<String, Gruppo> gruppi = (Map<String, Gruppo>) this.getServletContext().getAttribute("gruppi");
		Gruppo gruppo = gruppi.get(utente.getIdGroup());
		// ci andrebbe un controllo, ma do per scontato che non torni null
		session.setAttribute("currentUser", utente);
		gruppo.addUtenti(utente);
		session.setAttribute("gruppo", gruppo);
		
		if (utente.isAdmin())
			response.sendRedirect("pages/admin.jsp");
		else
			response.sendRedirect("pages/ticket.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
