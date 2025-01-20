package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Integrate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
		List<HttpSession> sessioniAttive = new ArrayList<HttpSession>();		
		int richiesteFatte = 0;
		this.getServletContext().setAttribute("sessioniAttive", sessioniAttive);
		this.getServletContext().setAttribute("richiesteFatte", richiesteFatte);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		HttpSession sessioneCorrente = request.getSession();
		List<HttpSession> sessioni = (List<HttpSession>) this.getServletContext().getAttribute("sessioniAttive");
		if (!sessioni.contains(sessioneCorrente))
			sessioni.add(sessioneCorrente);
		
		int nRichieste = (int) this.getServletContext().getAttribute("richiesteFatte");
		this.getServletContext().setAttribute("richiesteFatte", nRichieste + 1);
		
		if (nRichieste > 5)
		{
			System.out.println("Troppe richieste!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/limitiRaggiunti.jsp");	
			rd.forward(request, response);
		}
		
		double durataSessione = ((System.currentTimeMillis() - sessioneCorrente.getCreationTime())/1000)/60;
		double durataInattivita = ((System.currentTimeMillis() - sessioneCorrente.getLastAccessedTime())/1000)/60;

		
		if (durataSessione >= 60) 
		{
			System.out.println("Sessione troppo lunga!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/limitiRaggiunti.jsp");	
			rd.forward(request, response);
			sessioneCorrente.invalidate();
		}
		if (durataInattivita >= 10) 
		{
			System.out.println("Inattività troppo lunga!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/limitiRaggiunti.jsp");	
			rd.forward(request, response);
			sessioneCorrente.invalidate();
		}
		
		int part = Integer.valueOf(request.getParameter("num"));
		double x1 = (double) Integer.valueOf(request.getParameter("x1"));
		double x2 = (double) Integer.valueOf(request.getParameter("x2"));
		
		double i_l = 0, i_u = 0;
		double result = 0;
		
		switch(part)
		{
			case 1:
				i_l = x1;
				i_u = x1 + (x2 - x1)/4.0;
				break;
			case 2:
				i_l = x1 + (x2 - x1)/4.0;
				i_u = x1 + (x2 - x1)/2.0;
				break;
			case 3:
				i_l = x1 + (x2 - x1)/2.0;
				i_u = x1 + 3.0 * (x2 - x1)/4.0;
				break;
			case 4:
				i_l = x1 + 3.0 * (x2 - x1)/4.0;
				i_u = x2;
				break;
		}
		
		result = (i_u * i_u) - (i_l * i_l);
		response.getWriter().println(gson.toJson(result));
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException {
		this.doPost(request, response);
	}
}
	
	
