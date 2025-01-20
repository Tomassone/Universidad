package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class UpdateCell extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	
	@Override
	public void init() {
		List<HttpSession> sessioniAttive = new ArrayList<HttpSession>();		
		int richiesteFatte = 0;
		this.getServletContext().setAttribute("sessioniAttive", sessioniAttive);
		this.getServletContext().setAttribute("richiesteFatte", richiesteFatte);
	}
	
	private boolean isNumeric(String str)
	{ 
		try {  
			Integer.parseInt(str);  
			return true;
		} 
		catch(NumberFormatException e) {  
			return false;  
		}  
	}
	
	private boolean areFull(String[][] matr)
	{
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 4; j++)
			{
				if (!this.isNumeric(matr[i][j]))
					return false;
			}
		return true;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String[][] matriceA = (String[][]) this.getServletContext().getAttribute("matriceA");
		String[][] matriceB = (String[][]) this.getServletContext().getAttribute("matriceB");
		
		HttpSession sessioneCorrente = request.getSession();
		List<HttpSession> sessioni = (List<HttpSession>) this.getServletContext().getAttribute("sessioniAttive");
		
		if (!sessioni.contains(sessioneCorrente))
			sessioni.add(sessioneCorrente);
			
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 4; j++)
			{
				if (request.getParameter("a," + i + "," + j) != null && this.isNumeric(request.getParameter("a," + i + "," + j)))
				{
					matriceA[i][j] = (request.getParameter("a," + i + "," + j));
					//System.out.println("matriceA[i][j] = " + (request.getParameter("a," + i + "," + j)));
				}
				if (request.getParameter("b," + i + "," + j) != null && this.isNumeric(request.getParameter("b," + i + "," + j)))
				{
					matriceB[i][j] = (request.getParameter("b," + i + "," + j));
					//System.out.println("matriceB[i][j] = " + (request.getParameter("b," + i + "," + j)));
				}
			}
				
		if (this.areFull(matriceA) && this.areFull(matriceB))
		{
			this.getServletContext().setAttribute("areFull", true);
			System.out.println("Matrici piene!");
		}
		
		getServletContext().getRequestDispatcher("/pages/inputMatrix.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
