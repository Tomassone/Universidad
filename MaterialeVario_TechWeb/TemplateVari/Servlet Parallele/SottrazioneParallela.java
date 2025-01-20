package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class SottrazioneParallela extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		int nRichieste = (int) this.getServletContext().getAttribute("richiesteFatte");
		this.getServletContext().setAttribute("richiesteFatte", nRichieste + 1);
		
		String[][] matriceA = (String[][]) this.getServletContext().getAttribute("matriceA");
		String[][] matriceB = (String[][]) this.getServletContext().getAttribute("matriceB");
		String[][] result = new String[2][4];
		
		int part = Integer.valueOf(request.getParameter("num"));
		int i_l = 0, i_u = 0, j_l = 0, j_u = 0;
		
		switch(part)
		{
			case 1:
				i_l = 0;
				i_u = 1;
				j_l = 0;
				j_u = 2;
				break;
			case 2:
				i_l = 1;
				i_u = 2;
				j_l = 0;
				j_u = 2;
				break;
			case 3:
				i_l = 0;
				i_u = 1;
				j_l = 2;
				j_u = 4;
				break;
			case 4:
				i_l = 1;
				i_u = 2;
				j_l = 2;
				j_u = 4;
				break;
		}
		
		for (int i = i_l; i < i_u; i++)
			for (int j = j_l; j < j_u; j++)
				result[i][j] = Integer.toString((Integer.valueOf(matriceA[i][j]) - Integer.valueOf(matriceB[i][j])));
		
		// li stampo su out
		response.getWriter().println(gson.toJson(result));
	}
}
