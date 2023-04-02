package ticketsosta;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.text.NumberFormat;
import java.util.Locale;

public class Ticket
{
	private double costo;
	private LocalTime fine;
	private LocalTime inizio;
	
	public double getCosto()
	{
		return costo;
	}
	
	public String getCostoAsString()
	{
		NumberFormat formattatoreCosto = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return formattatoreCosto.format(this.getCosto());
	}
	
	public LocalTime getFine()
	{
		return fine;
	}

	public LocalTime getInizio()
	{
		return inizio;
	}
	
	public Ticket(LocalTime inizio, LocalTime fine, double costo)
	{
		this.inizio = inizio;
		this.fine = fine;
		this.costo = costo;
	}
	
	private String toStringDuration (Duration duration)
	{
		int minuti = duration.toMinutesPart();
		String sMinuti = (minuti < 10 ? "0" : "") + minuti; //aggiungo uno zero alle singole cifre.
		return duration.toHours() + ":" + sMinuti;
	}
	
	public String toString()
	{
		return "Sosta autorizzata\ndalle " + 
		inizio.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) + 
		" alle " +
		fine.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) + 
		"\n" +
		"Durata totale: " + toStringDuration(Duration.between(inizio, fine)) +
		"\n" +
		"Totale pagato: " + getCostoAsString();
	}
	
}
