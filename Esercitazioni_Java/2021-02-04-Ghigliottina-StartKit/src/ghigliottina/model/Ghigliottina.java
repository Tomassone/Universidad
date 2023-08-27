package ghigliottina.model;

import java.util.*;

public class Ghigliottina
{
	private Iterator<Terna> iterator;
	private String rispostaEsatta;
	private List<Terna> terne;
	
	public String getRispostaEsatta()
	{
		return this.rispostaEsatta;
	}
	
	public List<Terna> getTerne()
	{
		return this.terne;
	}
	
	public boolean hasNext()
	{
		return this.iterator.hasNext();
	}
	
	public Terna next()
	{
		return this.iterator.next();
	}
	
	public String toString()
	{
		StringBuilder accomulatore = new StringBuilder(); 
		accomulatore.append("Risposta esatta: " + this.getRispostaEsatta() + ";\n");
		accomulatore.append("Terne: ");
		for (Terna terna : this.getTerne())
			accomulatore.append("\n-) " + terna.toString());
		return accomulatore.toString();
	}
	
	public Ghigliottina(List<Terna> terne, String rispostaEsatta)
	{
		if (terne == null)
			throw new IllegalArgumentException("lista di terne vuota");
		if (rispostaEsatta == null)
			throw new IllegalArgumentException("risposta esatta null");
		if (rispostaEsatta.isBlank())
			throw new IllegalArgumentException("risposta esatta composta da soli spazi/tabulazioni");
		if (rispostaEsatta == "")
			throw new IllegalArgumentException("risposta esatta vuota");
		
		this.terne = terne;
		this.rispostaEsatta = rispostaEsatta;
		this.iterator = this.terne.iterator();
	}
}
