package fondt2.tlc;

import java.time.*;
import java.util.Arrays;

public class Band
{
	private double costPerInterval;
	private LocalTime startTime;
	private LocalTime endTime;
	private DayOfWeek[] combinedDays;
	
	public Band(LocalTime startTime, LocalTime endTime, DayOfWeek[] combinedDays, double costPerInterval)
	{
		this.costPerInterval = costPerInterval;
		this.startTime = startTime;
		this.endTime = endTime;
		this.combinedDays = Arrays.copyOf(combinedDays, combinedDays.length);
	}
	
	public double getCostPerInterval()
	{
		return this.costPerInterval;
	}
	
	public LocalTime getStartTime()
	{
		return this.startTime;
	}
	
	public LocalTime getEndTime()
	{
		return this.endTime;
	}
	
	public DayOfWeek[] getCombinedDays()
	{
		return this.combinedDays;
	}
	
	public boolean isInBand(LocalDateTime dateTime)
	{
		//creo un'istanza della classe "orario" a partire dall'oggetto data+tempo che ho ricevuto.
		LocalTime controlDate = LocalTime.of(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), dateTime.getNano());
		return controlDate.isAfter(this.startTime) && controlDate.isBefore(this.endTime);
	}
	
	public boolean isValid() //funzione di controllo di validità della banda.
	{
		//l'orario d'inizio deve venire prima di quello di fine; deve essere applicabile ad almeno un giorno; il costo non può essere negativo.
		return (this.startTime.isBefore(this.endTime)) && (combinedDays.length > 0) && (costPerInterval >= 0); 
	}
}
