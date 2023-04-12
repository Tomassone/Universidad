package fondt2.tlc;

import java.time.*;
import java.util.Arrays;
import fondt2.tlc.util.*;

public class Rate
{
	private Band[] bands;
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	public Rate(String name, Band[] bands, int intervalInMillis, double startCallCost, String numberRoot)
	{
		this.name = name;
		this.bands = bands;
		this.intervalInMillis = intervalInMillis;
		this.startCallCost = startCallCost;
		this.numberRoot = numberRoot;
	}
	
	public Band[] getBands()
	{
		return this.bands;
	}
	
	public double getCallCost(PhoneCall call)
	{
		long numberOfIntervals = 0;
		Duration durataChiamata = Duration.between(call.getStart(), call.getEnd());
		this.sortBandsByStartTime(this.bands); //riordino le bande da quella che inizia per prima a quella che inizia per ultima.
		numberOfIntervals = (durataChiamata.getSeconds() * 1000) / this.intervalInMillis;
		return startCallCost + getCostPerInterval(call.getStart()) * numberOfIntervals;
	}
	
	private double getCostPerInterval(LocalDateTime dateTime)
	{
		int i; //la inizializzo fuori dal for perchè il suo valore mi serve anche a ciclo concluso.
		for (i = 0; !bands[i].isInBand(dateTime); i++);
		return bands[i].getCostPerInterval();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public boolean isApplicableTo(String destinationNumber)
	{
		return destinationNumber.startsWith(this.numberRoot);
	}
	
	public boolean isValid()
	{
		//controllo che le singole bande siano valide.
		for (int i = 0; i < this.bands.length; i++)
			if (!this.bands[i].isValid()) //se almeno una delle bande non è valida:
				return false;
		//for (int i = 1; i <= 7; i++)
			if (!validateDay(DayOfWeek.MONDAY))
				return false;
		return true;
	}
	
	private Band[] selectBandsInDay(DayOfWeek day)
	{
		Band[] result = new Band[this.bands.length];
		int j = 0;
		for (int i = 0; i < bands.length; i++)
			if (DayOfWeekHelper.isDayIn(day, this.bands[i].getCombinedDays()))
			{
				result[j] = this.bands[i];
				j++;
			}
		return result;	
	}
	
	private Band[] sortBandsByStartTime(Band[] bands) //bubbleSort sotto mentite spoglie.
	{
		int i, n = bands.length; 
		boolean ordinato = false;
		Band temp;
		while (n > 1 && !ordinato)
		{
			ordinato = true;
			for (i = 0; i < n - 1; i++)
				if (bands[i].getStartTime().isAfter(bands[i + 1].getStartTime()))
				{
					temp = bands[i];
					bands[i] = bands[i + 1];
					bands[i + 1] = temp;
					ordinato = false;
				}
			n--;
		}
		return bands;
	}
	
	private boolean validateBandsinDay(Band[] bandsInDay)
	{
		if (bandsInDay[0].getStartTime() != LocalTime.MIN || bandsInDay[bandsInDay.length - 1].getEndTime() != LocalTime.MAX)
			return false;
		for (int i = 0; i < bandsInDay.length - 1; i++)
			if (bandsInDay[i].getEndTime().plusNanos(1).equals(bandsInDay[i + 1].getStartTime()))
				return false;
		return true;
	}
	
	private boolean validateDay(DayOfWeek day)
	{
		Band[] bandsInDay = selectBandsInDay(day);
		sortBandsByStartTime(bandsInDay); //ordino le bande orarie in base alla loro data di inizio.
		return validateBandsinDay(bandsInDay);
	}
}
