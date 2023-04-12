package mastermind.model;
import java.util.Arrays;
import java.util.StringJoiner;

public class Risposta
{
	private PioloRisposta[] combinazione;
	private int dim;
	
	public Risposta(int dim)
	{
		this.dim = dim;
		this.combinazione = new PioloRisposta[this.dim];
		for (int i = 0; i < this.dim(); i++)
			this.combinazione[i] = PioloRisposta.VUOTO;
	}
	
	public int dim()
	{
		return this.dim;
	}
	
	public boolean equals(Risposta that)
	{
		if (this.dim() != that.dim())
			return false;
		for (int i = 0; i < this.dim(); i++)
			if (!this.getPiolo(i).equals(that.getPiolo(i)))
				return false;
		return true;
	}
	
	public PioloRisposta getPiolo(int index)
	{
		return this.combinazione[index];
	}
	
	public void setPiolo(int index, PioloRisposta c)
	{
		this.combinazione[index] = c;
	}
	
	public String toString()
	{
		StringJoiner result = new StringJoiner(",");
		for (int i = 0; i < this.combinazione.length; i++)
			result.add(this.combinazione[i].toString());
		return result.toString();
	}
	
	public boolean vittoria()
	{
		for (int i = 0; i < this.dim(); i++)
			if (!this.getPiolo(i).equals(PioloRisposta.NERO))
				return false;
		return true;
	}
}
