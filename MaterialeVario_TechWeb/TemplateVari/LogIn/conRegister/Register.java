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

public class Register extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		Map<String, Credenziali> utentiRegistrati = new HashMap<String, Credenziali>();
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		
		Map<String, Gruppo> gruppi = new HashMap<String, Gruppo>();
		Gruppo gu = new Gruppo();
		gu.setIdGruppo("g01");
		gruppi.put(gu.getIdGruppo(), gu);
		gu = new Gruppo();
		gu.setIdGruppo("g02");
		gruppi.put(gu.getIdGruppo(), gu);
		this.getServletContext().setAttribute("gruppi", gruppi);
		
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String gruppo = request.getParameter("gruppo");


		Map<String, Credenziali> utentiRegistrati = (Map<String, Credenziali>) this.getServletContext().getAttribute("utentiRegistrati");
		Credenziali utente = new Credenziali();
		
		utente.setUser(name);
		utente.setPassword(pwd);
		utente.setIdGroup(gruppo);
		
		if (utente.getUser().equals("admin"))
			utente.setIsAdmin(true);
		
		utentiRegistrati.put(name, utente);

		response.sendRedirect("pages/login.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
}
