package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class SottrazioneSequenziale extends HttpServlet {

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
		
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 4; j++)
				result[i][j] = Integer.toString((Integer.valueOf(matriceA[i][j]) - Integer.valueOf(matriceB[i][j])));
		
		// li stampo su out
		response.getWriter().println(gson.toJson(result));
	}
}
