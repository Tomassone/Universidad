package ticketsostaevoluto;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.text.NumberFormat;
import java.util.Locale;

public class TicketEvoluto
{
	private double costo;
	private LocalDateTime fine;
	private LocalDateTime inizio;
	
	public double getCosto()
	{
		return costo;
	}
	
	public String getCostoAsString()
	{
		NumberFormat formattatoreCosto = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return formattatoreCosto.format(this.getCosto());
	}
	
	public LocalDateTime getFine()
	{
		return fine;
	}

	public LocalDateTime getInizio()
	{
		return inizio;
	}
	
	public TicketEvoluto(LocalDateTime inizio, LocalDateTime fine, double costo)
	{
		this.inizio = inizio;
		this.fine = fine;
		this.costo = costo;
	}
	
	private String toStringDuration (Duration duration)
	{
		int minuti = duration.toMinutesPart();
		String sMinuti = (minuti < 10 ? "0" : "") + minuti; //aggiungo uno zero alle singole cifre.
		return duration.toDays() + " e " + duration.toHours() + ":" + sMinuti;
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
