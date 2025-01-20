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
		utentiRegistrati.put(utente.getUser(), utente);
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		Map<String, Credenziali> utentiRegistrati = (Map<String, Credenziali>) this.getServletContext().getAttribute("utentiRegistrati");
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
			utente.setTries(utente.getTries() + 1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		} // altrimenti tutto OK, si procede
		
		HttpSession session = request.getSession();
		utente.setSession(session);
		session.setAttribute("currentUser", utente);
		
		response.sendRedirect("pages/admin.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
}
