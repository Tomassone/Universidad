package	servlets;

import beans.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.hsqldb.SessionContext;

import com.google.gson.Gson;

public class CalcoloConto extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		// recupero il cliente e il locale (implementato come Java Bean)
		/*Locale locale = (Locale) this.getServletContext().getAttribute("locale");
		double costoFinale = 0;
		
		for (int i = 0; i < locale.getElementi().size(); i++)
		{
			if (!locale.getElementi().get(i).getUtenti().isEmpty())
			{
				for (int j = 0; j < locale.getElementi().get(i).getElementi().size(); j++)
				{
					if (locale.getElementi().get(i).getElementi().get(j).getStato().equals("consegnato"))
						costoFinale = costoFinale + locale.getElementi().get(i).getElementi().get(j).getCosto();
					System.out.println("Contato drink: " + locale.getElementi().get(i).getElementi().get(j).getIdDrink() + " nel conto");
				}
			}
		}*/
		
		String result = new String();
		
		result = "<html>\n"
				+ "<head>\n"
				+ "<title>Conto</title>\n"
				+ "<link type=\"text/css\" href=\"styles/default.css\" rel=\"stylesheet\"></link>"
				+ "</head>" 
				+ "<body>" 
				//+ "<p>Prezzo finale: " + costoFinale + "</p>"
			    + "</body>"
				+ "</html>";

		// li stampo su out
		response.getWriter().println(result);
	}
}
