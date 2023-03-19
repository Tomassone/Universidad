package fractioncollection;

import frazione.Frazione;

public class FractionCollection
{
	private Frazione[] internalConteiner;
	private int size; //dimensione logica dell'array.

	final static int DEFAULT_GROWTH_FACTOR = 2;
	final static int DEFAULT_PHYSICAL_SIZE = 10;
	
	public FractionCollection(int dim)
	{
		this.internalConteiner = new Frazione[dim];
		this.size = 0;
	}
	
	public FractionCollection()
	{
		this.internalConteiner = new Frazione[DEFAULT_PHYSICAL_SIZE];
		this.size = 0;
	}
	
	public FractionCollection(Frazione[] daCopiare)
	{
		this.internalConteiner = new Frazione[daCopiare.length];
		System.arraycopy(daCopiare, 0, internalConteiner, 0, daCopiare.length); //copio l'array ricevuto in quello interno.
		this.size = daCopiare.length;
	}
	
	public String toString()
	{
		String res = "[";
		for (int k = 0; k < this.size(); k++)
		{
			res += this.get(k).toString() + ", ";
		}
		res += "]";
		return res;
	}
	
	public int size()
	{
		return this.size;
	}
	
	public Frazione get(int i)
	{
		return this.internalConteiner[i];
	}
	
	public void remove(int i)
	{
		this.internalConteiner[i] = null;
		for (int j = i; j < this.size; j++)
		{
			this.internalConteiner[j] = this.internalConteiner[j + 1];
		}
		size--;
	}
	
	public void put(Frazione daInserire)
	{
		if (this.size >= this.internalConteiner.length)
		{
			Frazione[] nextInternalConteiner = new Frazione[DEFAULT_GROWTH_FACTOR * internalConteiner.length];
			System.arraycopy(this.internalConteiner, 0, nextInternalConteiner, 0, this.internalConteiner.length); //copio l'array ricevuto in quello interno.
			nextInternalConteiner[this.size] = daInserire;
			this.internalConteiner = nextInternalConteiner; //internalConteiner punterà ora al nuovo vettore creato.
		}
		else
			internalConteiner[this.size] = daInserire;
		this.size++;
	}
	
	private void set(int i, Frazione daInserire)
	{
		internalConteiner[i] = daInserire;
	}
	
	public FractionCollection sum(FractionCollection that)
	{
		if (this.size() != that.size()) 
			return null;
		FractionCollection result = new FractionCollection(that.size());
		for (int k = 0; k < that.size(); k++)
			result.set(k, this.get(k).sum(that.get(k)));
		result.size = that.size();
		return result;
	}
	
	public FractionCollection mul(FractionCollection that)
	{
		if (this.size() != that.size()) 
			return null;
		FractionCollection result = new FractionCollection(that.size());
		for (int k = 0; k < that.size; k++)
			result.set(k, this.get(k).mul(that.get(k)));
		result.size = that.size();
		return result;
	}
}
