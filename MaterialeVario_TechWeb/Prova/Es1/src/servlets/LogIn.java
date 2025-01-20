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
import java.time.*;
import beans.*;

public class LogIn extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		Map<String, Credenziali> utentiRegistrati = new HashMap<String, Credenziali>();
		Map<String, Credenziali> utentiAttivi = new HashMap<String, Credenziali>();
		Credenziali utente = new Credenziali();
		utente.setUser("Cesare");
		utente.setPassword("Tomasi");
		utente.setTipoRichiesta(1);
		utente = new Credenziali();
		utente.setUser("sindaco");
		utente.setPassword("vip");
		utente.setIsVip(true);
		utente.setTipoRichiesta(1);
		utentiRegistrati.put(utente.getUser(), utente);
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		this.getServletContext().setAttribute("utentiAttivi", utentiAttivi);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		Map<String, Credenziali> utentiRegistrati = (Map<String, Credenziali>) this.getServletContext().getAttribute("utentiRegistrati");
		Map<String, Credenziali> utentiAttivi = (Map<String, Credenziali>) this.getServletContext().getAttribute("utentiAttivi");

		Credenziali utente = utentiRegistrati.get(name);		
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
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		} // altrimenti tutto OK, si procede
		
		HttpSession session = request.getSession();
		utente.setSession(session);
		int nGentePrima = 0;
		int x = Integer.parseInt(this.getServletConfig().getInitParameter("x"));
		int y = Integer.parseInt(this.getServletConfig().getInitParameter("y"));
		
		if(!utente.isVip()) {
			for (Map.Entry<String,Credenziali> u : utentiAttivi.entrySet()) {
				if (u.getValue().getTipoRichiesta() == utente.getTipoRichiesta()) {
					nGentePrima++;
				}
			}
			if (utente.getTipoRichiesta() == 1)
				utente.setTempoAttesa(nGentePrima * x);
			else
				utente.setTempoAttesa(nGentePrima * y);
		}
		else {
			long attesaTotale = 0;
			for (Map.Entry<String,Credenziali> u : utentiAttivi.entrySet()) {
				attesaTotale = attesaTotale + u.getValue().getTempoAttesa();
			}
			
			LocalTime ora = LocalTime.now();
			LocalTime chiusura = LocalTime.of(12, 45);
			
			if ((chiusura.getSecond() - ora.getSecond()) >= attesaTotale / 1000) {
				for (Map.Entry<String,Credenziali> u : utentiAttivi.entrySet()) {
					if (u.getValue().getTipoRichiesta() == utente.getTipoRichiesta()) {
						if (utente.getTipoRichiesta() == 1)
							u.getValue().setTempoAttesa(u.getValue().getTempoAttesa() + x);
						else	
							u.getValue().setTempoAttesa(u.getValue().getTempoAttesa() + y);
					}
				}
			}
		}
		
		utentiAttivi.putIfAbsent(utente.getUser(), utente);
		session.setAttribute("currentUser", utente);
		session.setAttribute("utentiAttivi", utentiAttivi);
		
		response.sendRedirect("pages/wait.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		Credenziali utente = (Credenziali) request.getSession().getAttribute("currentUser");
		long minutiPassati = utente.getSession().getLastAccessedTime() / (1000 * 60);
		if ((utente.getTempoAttesa() - minutiPassati) > 0)
			utente.setTempoAttesa(utente.getTempoAttesa() - (int) minutiPassati);
		else
			utente.setTempoAttesa(0);
	}
}
