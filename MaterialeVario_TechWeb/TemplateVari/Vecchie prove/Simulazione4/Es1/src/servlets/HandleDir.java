package servlets;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;

public class HandleDir extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		
		String path = this.getServletContext().getInitParameter("dir");
		File directory = new File(path);
		List<File> elencoFile = new ArrayList<File>();
		
		if (directory.isDirectory())
		{
		    File[] files = directory.listFiles();
		    if (files != null)
		    {
		        for (File file : files)
		        	if (file.isFile())
		        		elencoFile.add(file);
		    }
		    else
		    {
		        System.out.println("Directory is empty.");
		    }
		} 
		else
		    System.out.println("Path is not a directory.");
		
		this.getServletContext().setAttribute("elencoFile", elencoFile);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/pages/home.jsp");
		rd.forward(request, response);
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}
