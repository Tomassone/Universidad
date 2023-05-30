package zannotaxi.model;

import java.time.LocalTime;

public class FasciaOraria
{
	private double costoScattoIniziale;
	private LocalTime fine;
	private LocalTime inizio;
	
	public boolean contiene(LocalTime data)
	{
		return data.isAfter(this.inizio) && data.isBefore(this.fine);
	}
	
	public FasciaOraria(LocalTime inizio, LocalTime fine, double costoScattoIniziale)
	{
		this.costoScattoIniziale = costoScattoIniziale;
		this.fine = fine;
		this.inizio = inizio;
	}
	
	public double getCostoScattoIniziale()
	{
		return this.costoScattoIniziale;
	}
}
