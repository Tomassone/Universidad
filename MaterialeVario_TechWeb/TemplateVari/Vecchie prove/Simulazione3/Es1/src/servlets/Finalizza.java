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

public class Finalizza extends HttpServlet{
	
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
		Map<String, Osservazione> osservazioni = (Map<String, Osservazione>) this.getServletContext().getAttribute("osservazioni");
		Albergo albergo = (Albergo) this.getServletContext().getAttribute("albergo");
		
		albergo.setCamere_totali(albergo.getCamere_totali() - 1);
		osservazioni.get(request.getSession().getId()).setFinalizzata(true);

		response.sendRedirect("pages/ok.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
