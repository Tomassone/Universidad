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
			if (utente.getTries() > 3)
				response.sendRedirect("pages/error.jsp");
			else
				rd.forward(request, response);
			return;
		} // altrimenti tutto OK, si procede
		
		HttpSession session = request.getSession();
		utente.setSession(session);
		Map<String, Gruppo> gruppi = (Map<String, Gruppo>) this.getServletContext().getAttribute("gruppi");
		Gruppo gruppo = gruppi.get(utente.getIdGroup());
		// ci andrebbe un controllo, ma do per scontato che non torni null
		session.setAttribute("currentUser", utente);
		gruppo.addUtenti(utente);
		session.setAttribute("gruppo", gruppo);
		
		if (gruppo.tooManyBrokenPswd())
			for (Credenziali u : gruppo.getUtenti())
				u.setDaysSinceLastModification(61);
		
		if (utente.isPasswordExpired())
			getServletContext().getRequestDispatcher("/pages/change_pswd.jsp").forward(request, response);
		
		if (utente.isAdmin())
			response.sendRedirect("pages/admin.jsp");
		else
			response.sendRedirect("pages/ok.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
}
