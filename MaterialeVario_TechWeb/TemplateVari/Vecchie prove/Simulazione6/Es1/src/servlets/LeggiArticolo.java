package	servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class LeggiArticolo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();
		Map<String, Articolo> articoli = new HashMap<String, Articolo>();
		this.getServletContext().setAttribute("articoli", articoli);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String path = request.getParameter("path");
		System.out.println(path);
		path = path.replace("%", "");
		File articoloF = new File(path);
		
		if (!articoloF.exists())
			request.getSession().setAttribute("testoArticolo", "");
		else
		{
			String testoArticolo = "";
			try {
				BufferedReader bf = new BufferedReader(new FileReader(path));
				String line = bf.readLine();
				while (line != null)
				{
					testoArticolo = testoArticolo + line;
		            line = bf.readLine(); // Continue to the next line
				}
				bf.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("testoArticolo", testoArticolo);
		}
		
		Articolo articolo = new Articolo();
		articolo.setPath(path);
		Map<String, Articolo> articoli = (HashMap<String, Articolo>) this.getServletContext().getAttribute("articoli");
		articoli.put(path, articolo);
		request.getSession().setAttribute("articolo", articolo);
		this.getServletContext().setAttribute("articoli", articoli);
		
		// Instead of redirecting, return a simple string "redirect" to trigger client-side redirection
	    response.setContentType("text/plain");
	    response.getWriter().write("redirect");
		//C:/Users/cosot/Documents/apache-tomcat-9.0.96/webapps/TW_Esame_StartingKit/pages/ciao.txt%
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
