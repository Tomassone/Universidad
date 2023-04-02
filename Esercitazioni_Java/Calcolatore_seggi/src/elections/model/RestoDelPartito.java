package elections.model;

public class RestoDelPartito
{
	private String partito;
	private long resto;
	
	public RestoDelPartito(String partito, long resto)
	{
		this.partito = partito; 
		this.resto = resto;
	}
	
	public String getPartito() 
	{ 
		return this.partito; 
	}
	
	public long getResto() 
	{
		return this.resto; 
	}
	
	public void azzeraResto() 
	{ 
		this.resto = 0; 
	}
	
	public String toString()
	{
		return "RestoDelPartito [partito=" + partito + ", resto=" + resto + "]";
	}
}