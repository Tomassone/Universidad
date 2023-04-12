package mastermind.model;
import java.util.Arrays;
import java.util.StringJoiner;

public class Combinazione
{
	private PioloDiGioco[] combinazione;
	private int dim;
	
	public Combinazione(int dim)
	{
		this.dim = dim;
		this.combinazione = new PioloDiGioco[this.dim];
		for (int i = 0; i < this.dim(); i++)
			this.combinazione[i] = PioloDiGioco.VUOTO;
	}
	
	public int dim()
	{
		return this.dim;
	}
	
	public boolean equals(Combinazione that)
	{
		if (this.dim() != that.dim())
			return false;
		for (int i = 0; i < this.dim(); i++)
			if (!this.getPiolo(i).equals(that.getPiolo(i)))
				return false;
		return true;
	}
	
	public PioloDiGioco getPiolo(int index)
	{
		return this.combinazione[index];
	}
	
	public void setPiolo(int index, PioloDiGioco c)
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
}
