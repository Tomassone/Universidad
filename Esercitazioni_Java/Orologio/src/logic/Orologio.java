package logic;

import java.time.*;
import java.time.format.*;
import java.util.Locale;

public class Orologio
{
	private Counter ore, minuti, secondi;
	
	public Orologio(int h, int m, int s)
	{
		this.ore = new Counter(h);
		this.minuti = new Counter(m);
		this.secondi = new Counter(s);
	}
	
	public Orologio(int h, int m)
	{
		this(h, m, 0);
	}
	
	public Orologio(int h)
	{
		this(h, 0, 0);
	}
	
	public Orologio()
	{
		this(12, 0, 0);
	}
	
	public int getHours()
	{
		return this.ore.getValue();
	}
	
	public int getMinutes()
	{
		return this.minuti.getValue();
	}
	
	public int getSeconds()
	{
		return this.secondi.getValue();
	}
	
	public void tic()
	{
		this.secondi.inc();
		if (this.secondi.getValue() == 60) //se sono arrivato a 60 secondi, resetto il conteggio dei secondi e aggiungo uno a quello dei minuti.
		{
			this.secondi.reset();
			this.minuti.inc();
		}
		if (this.minuti.getValue() == 60)
		{
			this.minuti.reset();
			this.ore.inc();
		}
		if (this.ore.getValue() == 24) //se sono arrivato ad avere 24 ore, resetto il conteggio delle ore.
			this.ore.reset();
	}
	
	public String toString()
	{
		LocalTime daStampare = LocalTime.of(getHours(), getMinutes(), getSeconds()); //creo un'istanza della classe "orario locale".
		DateTimeFormatter formattatoreOrari = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.ITALY); //creo un'istanza della classe formattrice di orari.
		return formattatoreOrari.format(daStampare);
	}
	
	public Orologio clone()
	{
		return new Orologio(this.getHours(), this.getMinutes(), this.getSeconds());
	}
}
