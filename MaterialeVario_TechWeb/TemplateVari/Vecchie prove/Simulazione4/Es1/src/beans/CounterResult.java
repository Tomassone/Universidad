package beans;

public class CounterResult {
	public double durataServlet = 0;
	public double durataBean = 0;
	public int[] elencoConteggi;
	
	public CounterResult()
	{
		this.durataBean = 0;
		this.durataServlet = 0;
		this.elencoConteggi = new int[15];
	}

	public double getDurataServlet() {
		return durataServlet;
	}

	public void setDurataServlet(double durataServlet) {
		this.durataServlet = durataServlet;
	}

	public double getDurataBean() {
		return durataBean;
	}

	public void setDurataBean(double durataBean) {
		this.durataBean = durataBean;
	}

	public int[] getElencoConteggi() {
		return elencoConteggi;
	}

	public void setElencoConteggi(int[] elencoConteggi) {
		this.elencoConteggi = elencoConteggi;
	}
}
