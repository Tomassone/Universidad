package	servlets;

import beans.*;

import java.io.IOException;
import java.util.ArrayList;
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
		
		// recupero circondario e attrazioni (implementato come Java Bean)
		ElencoAttrazioni elencoAttrazioni = (ElencoAttrazioni) this.getServletContext().getAttribute("elencoAttrazioni");
		Circondario circondario = (Circondario) this.getServletContext().getAttribute("circondario");
		List<AttrazioneTuristica> result = new ArrayList<AttrazioneTuristica>();
		
		int x = Integer.valueOf(request.getParameter("x"));
		int y = Integer.valueOf(request.getParameter("y"));
		
		Coordinate utente = new Coordinate();
		utente.setX(x + 50);
		utente.setY(y);
		
		for (int i = 0; i < elencoAttrazioni.getElementi().size(); i++)
			if (!elencoAttrazioni.getElementi().get(i).getNome().isEmpty() && elencoAttrazioni.getElementi().get(i).getCoordinate().isNear(utente, 100) && !circondario.isBusy(elencoAttrazioni.getElementi().get(i).getCoordinate()))
				result.add(elencoAttrazioni.getElementi().get(i));

		// li stampo su out
		response.getWriter().println(gson.toJson(result.toArray(new AttrazioneTuristica[0])));
	}
}
