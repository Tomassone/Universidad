package	servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import beans.Richiesta;

public class SommaSequenziale extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	public int tempoRichiesta = 0;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num")); 
		Integer el1[] = gson.fromJson(request.getParameter("p1"), Integer[].class);
		Integer el2[] = gson.fromJson(request.getParameter("p2"), Integer[].class);
		
		int somma[] = new int[el1.length];
		
		for (int i = 0; i < somma.length; i++)
			somma[i] = el1[i] + el2[i];
		
		// li stampo su out
		response.getWriter().println(gson.toJson(somma));
	}
}
