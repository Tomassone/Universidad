package	servlets;

import beans.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class JSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		// recupero il catalogo (implementato come Java Bean)
		Menu menu = (Menu) this.getServletContext().getAttribute("menu");
		List<Drink> products = menu.getElementi();

		// li stampo su out
		response.getWriter().println(gson.toJson(products.toArray(new Drink[0])));
	}
}
