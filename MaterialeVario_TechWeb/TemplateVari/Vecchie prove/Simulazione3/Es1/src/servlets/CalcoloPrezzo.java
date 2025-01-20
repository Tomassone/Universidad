package servlets;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import com.google.gson.Gson;

import beans.*;

public class CalcoloPrezzo extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		this.gson = new Gson();
		Map<String, Albergo> alberghi = new HashMap<String, Albergo>();
		BufferedReader br = null;
		String buffer = null;
		Albergo temp = null;

		try {
			br = new BufferedReader(new FileReader("C:\\Users\\cosot\\Documents\\apache-tomcat-9.0.96\\webapps\\TW_Esame_StartingKit\\alberghi.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while ((buffer = br.readLine()) != null && buffer.split(",").length == 3) {
				temp = new Albergo();
				temp.setID_albergo(buffer.split(",")[0]);
				temp.setCamere_totali(Integer.parseInt(buffer.split(",")[1]));
				temp.setPrezzo_STATICO_camera_per_giornata(Double.parseDouble(buffer.split(",")[2]));
				alberghi.put(temp.getID_albergo(), temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().setAttribute("alberghi", alberghi);
		Map<String, Osservazione> osservazioni = new HashMap<String, Osservazione>();
		this.getServletContext().setAttribute("osservazioni", osservazioni);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		Map<String, Albergo> alberghi = (Map<String, Albergo>) this.getServletContext().getAttribute("alberghi");
		Map<String, Osservazione> osservazioni = (Map<String, Osservazione>) this.getServletContext().getAttribute("osservazioni");

		String idAlbergo = request.getParameter("id");
		int checkIn = Integer.valueOf(request.getParameter("checkin"));
		int checkOut = Integer.valueOf(request.getParameter("checkout"));
		Albergo albergo = alberghi.get(idAlbergo);
		this.getServletContext().setAttribute("albergo", albergo);
		
		int nOsservatori = 0;
		for (Map.Entry<String, Osservazione> el : osservazioni.entrySet())
		{
			if (!el.getValue().isFinalizzata() 
				&& el.getValue().getAlbergo().equals(albergo)
				&& !(el.getValue().getCheckIn() > checkOut || el.getValue().getCheckOut() < checkIn))
					nOsservatori++;
		}
		
		double prezzoUnitario = albergo.getPrezzo_STATICO_camera_per_giornata() + 0.1 * nOsservatori * albergo.getPrezzo_STATICO_camera_per_giornata();
		double prezzoTotale = prezzoUnitario * (checkIn - checkOut);
		this.getServletContext().setAttribute("prezzoTotale", prezzoTotale);
		
		Osservazione osservazione = new Osservazione();
		osservazione.setAlbergo(albergo);
		osservazione.setCheckIn(checkIn);
		osservazione.setCheckOut(checkOut);
		osservazioni.put(request.getSession().getId(), osservazione);
		
		response.sendRedirect("index.jsp");
	}	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		this.doPost(request, response);
	}
}