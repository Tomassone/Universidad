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

public class BuyTicket extends HttpServlet{
	
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
		HttpSession session = request.getSession();
		Gruppo gruppo = (Gruppo) session.getAttribute("gruppo");
		gruppo.setBuying(true);
		Credenziali utente = (Credenziali) session.getAttribute("currentUser");
		utente.setHasFinalized(true);
		
		for (Credenziali u : gruppo.getUtenti())
			while (!u.hasFinalized() && u.isActive());
		
		boolean stopped = (boolean) this.getServletContext().getAttribute("stopped");
		
		if (stopped == true)
			response.sendRedirect("pages/ticket.jsp");
		else
			response.sendRedirect("pages/ok.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
