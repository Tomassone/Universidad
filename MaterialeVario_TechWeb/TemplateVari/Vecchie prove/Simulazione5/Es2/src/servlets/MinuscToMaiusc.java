package servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class MinuscToMaiusc extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public boolean done = false;
	private Gson gson;
	public String result;

	@Override
	public void init() {
		gson = new Gson();
		this.result = null;
	}
	
	synchronized public String getResult()
	{
		return this.result;
	}
	
	synchronized public void setResult(String string)
	{
		this.result = string;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		int nCaratteriModificati = 0;
		String path = request.getParameter("path");
		String localResult = request.getParameter("input");
		int part = Integer.parseInt(request.getParameter("num"));
		
		StringBuilder resultBuilder = new StringBuilder(localResult);
        int mid = localResult.length() / 2;

        if (part == 1)
        {
            // Process the first half
            for (int i = 0; i < mid; i++)
            {
                if (Character.isLowerCase(localResult.charAt(i)))
                {
                    resultBuilder.setCharAt(i, Character.toUpperCase(localResult.charAt(i)));
                    nCaratteriModificati++;
                }
            }
			this.setResult(resultBuilder.substring(0, mid));
        } 
        else 
        {
            // Process the second half
            for (int i = mid; i < localResult.length(); i++)
            {
                if (Character.isLowerCase(localResult.charAt(i)))
                {
                    resultBuilder.setCharAt(i, Character.toUpperCase(localResult.charAt(i)));
                    nCaratteriModificati++;
                }
            }
            String result = this.getResult();
			while (result == null)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result = this.getResult();
			}
			this.setResult(result + resultBuilder.substring(mid, localResult.length()));
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(this.getResult());
			bw.close();
        }
		
		response.setContentType("application/json;charset=UTF-8");
		// li stampo su out
		response.getWriter().println(gson.toJson(nCaratteriModificati));
	}
}
