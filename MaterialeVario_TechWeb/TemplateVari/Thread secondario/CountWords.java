package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.*;

public class CountWords extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		this.gson = new Gson();
	}
	
	public int countWords(String path)
	{
		int nMaiusc = 0;
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String line = bf.readLine();
			while (line != null)
			{
				for (int i = 0; i < line.length(); i++)
					if (Character.isUpperCase(line.charAt(i)))
						nMaiusc++;
	            line = bf.readLine(); // Continue to the next line
			}
			bf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nMaiusc;
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		List<File> elencoFile = (List<File>) this.getServletContext().getAttribute("elencoFile");
		String f1 = request.getParameter("f1");
		String f2 = request.getParameter("f2");
		String f3 = request.getParameter("f3");
		int[] elencoConteggi = new int[3];
		double durataCalcolo = System.currentTimeMillis();
		CounterBean counter = new CounterBean();
		counter.setF1(f1);
		counter.setF2(f2);
		counter.setF3(f3);
		counter.setElencoFile(elencoFile);
		counter.start();
		System.out.println("Inizio conteggio!");
		for (File f : elencoFile)
		{
			System.out.println("Sto ciclando elenco file." + " " + f.getName().equals(f1) + " " + f.getName().equals(f2) + " " + f.getName().equals(f3));
			if (f.getName().equals(f1))
			{
				elencoConteggi[0] = this.countWords(f.getAbsolutePath());
				System.out.println("File 1 ok");
			}
			if (f.getName().equals(f2))
				elencoConteggi[1] = this.countWords(f.getAbsolutePath());
			if (f.getName().equals(f3))
				elencoConteggi[2] = this.countWords(f.getAbsolutePath());
		}
		for (int i : elencoConteggi)
		{
			System.out.println(i);
		}
		
		durataCalcolo = ((-durataCalcolo + System.currentTimeMillis()));
		this.getServletContext().setAttribute("durataCalcoloServlet", durataCalcolo);
		try {
			counter.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().setAttribute("durataCalcoloBean", counter.getDurataCalcolo());
		System.out.println("Settate durate e killato thread");
        response.setContentType("application/javascript");
        
        CounterResult result = new CounterResult();
        result.setDurataBean(counter.getDurataCalcolo());
        result.setDurataServlet(durataCalcolo);
        result.setElencoConteggi(elencoConteggi);
        
		response.getWriter().println(gson.toJson(result));
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}