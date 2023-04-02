package ticketsostaevoluto;
import java.time.*;
import ticketsosta.Tariffa;


public class ParcometroEvoluto
{
	private Tariffa tariffa[];
	
	private double calcolaCosto(double costoOrario, LocalTime inizio, LocalTime fine)
	{
		double durataInMinuti = (double) Duration.between(inizio, fine).toMinutes();
		if (fine.isBefore(inizio) || LocalTime.of(0, 0).equals(fine))
			durataInMinuti = (double) 24*60 - Duration.between(fine, inizio).toMinutes(); //da testare.
		if (tariffa[0].getDurataMinima() != 0 && (durataInMinuti - tariffa[0].getMinutiFranchigia()) < tariffa[0].getDurataMinima())
			return (double) tariffa[0].getDurataMinima()/60 * costoOrario;
		else
			return (durataInMinuti- tariffa[0].getMinutiFranchigia())/60 * costoOrario;
	}
	
	private double calcolaCostoSuPiuGiorni(LocalDateTime inizio, LocalDateTime fine)
	{
		double costoTotale = 0;
		for (int i = 0; i < Duration.between(inizio, fine).toDays(); i++)
			costoTotale = costoTotale + this.calcolaCosto(this.tariffa[inizio.getDayOfWeek().getValue() - 1 + i].getTariffaOraria(), inizio.toLocalTime(), fine.toLocalTime());
		return costoTotale;
	}
	
	public TicketEvoluto emettiTicket(LocalDateTime inizio, LocalDateTime fine)
	{
		TicketEvoluto result = new TicketEvoluto(inizio, fine, this.calcolaCostoSuPiuGiorni(inizio, fine));
		return result;
	}
	
	public ParcometroEvoluto(Tariffa[] tariffa1)
	{
		this.tariffa = tariffa1;
	}
	
	public String toString()
	{
		return this.tariffa.toString();
	}
}
