package ticketsosta;
import java.time.*;

public class Parcometro
{
	private Tariffa tariffa;
	
	private double calcolaCosto(double costoOrario, LocalTime inizio, LocalTime fine)
	{
		double durataInMinuti = (double) Duration.between(inizio, fine).toMinutes();
		if (fine.isBefore(inizio) || LocalTime.of(0, 0).equals(fine))
			durataInMinuti = (double) 24*60 - Duration.between(fine, inizio).toMinutes(); //da testare.
		if (tariffa.getDurataMinima() != 0 && (durataInMinuti - tariffa.getMinutiFranchigia()) < tariffa.getDurataMinima())
			return (double) tariffa.getDurataMinima()/60 * costoOrario;
		else
			return (durataInMinuti- tariffa.getMinutiFranchigia())/60 * costoOrario;
	}
	
	public Ticket emettiTicket(LocalTime inizio, LocalTime fine)
	{
		Ticket result = new Ticket(inizio, fine, this.calcolaCosto(this.tariffa.getTariffaOraria(), inizio, fine));
		return result;
	}
	
	public Parcometro(Tariffa tariffa)
	{
		this.tariffa = tariffa;
	}
	
	public String toString()
	{
		return this.tariffa.toString();
	}
}
