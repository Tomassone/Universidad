package	servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Articolo;

public class OttieniScrittura extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String wrText = (String) request.getParameter("article");
		Articolo articolo = (Articolo) request.getSession().getAttribute("articolo");
		String testoArticolo = (String) request.getSession().getAttribute("testoArticolo");
		BufferedWriter bw = new BufferedWriter(new FileWriter(articolo.getPath()));
		bw.write(testoArticolo + wrText);
		testoArticolo = testoArticolo + wrText;
		this.getServletContext().setAttribute("articolo", articolo);
		System.out.println(wrText + " " + articolo.getPath());
		bw.close();
		getServletContext().getRequestDispatcher("/pages/articolo.jsp").forward(request, response);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		Articolo articolo = (Articolo) request.getSession().getAttribute("articolo");
		response.setContentType("application/javascript");

		
		if (articolo.getWrSession() == null)
		{
			articolo.setWrSession(request.getSession());
			this.getServletContext().setAttribute("articolo", articolo);
			response.getWriter().println(gson.toJson("yes"));
		}
		else if (articolo.getWrSession().equals(request.getSession()))
		{
			articolo.setWrSession(null);
			response.getWriter().println(gson.toJson("no"));
		}
		else
			response.getWriter().println(gson.toJson("no"));
	}
}
