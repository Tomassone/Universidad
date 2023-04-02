package elections.model;

public class RisultatoDelPartito
{
	private String partito;
	private long voti, seggi;
	
	public RisultatoDelPartito(String nome, long voti, long seggi)
	{
		this.partito = nome;
		this.voti = voti;
		this.seggi = seggi;
	}
	
	public long getSeggi()
	{ 
		return seggi;
	}
	
	public void incSeggi()
	{
		this.seggi++;
	}
	
	public void setSeggi(long seggi)
	{
		this.seggi = seggi; 
	}
	
	public String getNome()
	{
		return this.partito;
	}
	
	public long getVoti()
	{ 
		return this.voti;
	}
	
	public String toString()
	{
		return partito + ",\tvoti=" + voti + ",\tseggi=" + seggi;
	}
}
