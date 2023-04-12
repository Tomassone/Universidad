package mastermind.model;

public class Gioco
{
	private int dim;
	private int maxTentativi;
	private int numTentativi;
	private Risposta[] risposte;
	private Status status;
	private Combinazione[] tentativi;
	
	public int dimensione()
	{
		return this.dim;
	}
	
	public int maxTentativi()
	{
		return this.maxTentativi;
	}
	
	public Gioco(int maxTentativi, int dim)
	{
		this.dim = dim;
		this.maxTentativi = maxTentativi;
		this.numTentativi = 0;
		this.status = Status.IN_CORSO;
		this.risposte = new Risposta[maxTentativi];
		this.tentativi = new Combinazione[maxTentativi];
	}
	
	public Status registraMossa(Combinazione tentativo, Risposta risposta)
	{
		MasterMind controlloPartita = new MasterMind(tentativo.dim());
		this.risposte[numTentativi] = risposta;
		this.tentativi[numTentativi] = tentativo;
		numTentativi++;
		if (risposta.vittoria())
			return Status.VITTORIA;
		else if (this.maxTentativi == this.numTentativi)
			return Status.PERSO;
		else
			return Status.IN_CORSO;
	}
	
	public Risposta risposta(int index)
	{
		return this.risposte[index];
	}
	
	public Status stato()
	{
		return this.status;
	}
	
	public int tentativiRimasti()
	{
		return this.maxTentativi() - this.tentativiEffettuati();
	}
	
	public int tentativiEffettuati()
	{
		return this.numTentativi;
	}
	
	public Combinazione tentativo(int index)
	{
		return this.tentativi[index];
	}
	
	public Risposta ultimaRisposta()
	{
		if (numTentativi == 0)
			return null;
		else
			return this.risposta(numTentativi - 1);
	}
	
	public Combinazione ultimoTentativo()
	{
		if (numTentativi == 0)
			return null;
		else
			return this.tentativo(numTentativi - 1);
	}
	
	public String situazione()
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numTentativi; i++)
		{
			sb.append((i + 1) + ") " + tentativi[i].toString() + "\t\t" + (tentativi[i].toString().length() < 20 ? "\t" : "") + 
			risposte[i].toString() + System.lineSeparator());
		}

		return sb.toString();
	}
	
	public String toString()
	{
		return ("Situazione:" + System.lineSeparator() + situazione() + System.lineSeparator() + "Gioco: " + stato() + System.lineSeparator());
	}
}