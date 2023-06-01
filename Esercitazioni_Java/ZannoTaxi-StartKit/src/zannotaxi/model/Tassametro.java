package zannotaxi.model;

import java.time.LocalTime;
import java.util.Optional;

public class Tassametro implements ITassametro
{
	private FasciaOraria[] fasceOrarie;
	private ITariffaTaxi[] tariffe;
	
	public Tassametro(ITariffaTaxi[] tariffe, FasciaOraria[] fasceOrarie)
	{
		this.tariffe = tariffe;
		this.fasceOrarie = fasceOrarie;
	}
	
	private Optional<Scatto> findScatto(double spazioPercorsoDaUltimoScatto, int tempoPercorsoDaUltimoScatto, double costoCorrente)
	{
		for (ITariffaTaxi tariffa : this.tariffe)
		{
			if (tariffa.getScattoCorrente(tempoPercorsoDaUltimoScatto, spazioPercorsoDaUltimoScatto, costoCorrente).isPresent())
				return tariffa.getScattoCorrente(tempoPercorsoDaUltimoScatto, spazioPercorsoDaUltimoScatto, costoCorrente);
		}
		return Optional.empty();
	}
	
	private double getScattoIniziale(LocalTime oraInizioCorsa)
	{
		for (FasciaOraria fasciaSingola : this.fasceOrarie)
			if (fasciaSingola.contiene(oraInizioCorsa))
				return fasciaSingola.getCostoScattoIniziale();
		return -1;
	}
	
	@Override
	public double calcolaCostoCorsa(CorsaTaxi corsa)
	{
		double costoCorsa = 0;
		double spazioPercorsoDaUltimoScatto = 0;
		int tempoPercorsoDaUltimoScatto = 0;
		
		Optional<Scatto> possibileScatto;
		
		for (int i = 1; i < corsa.getRilevazioniDistanze().length; i++)
		{
			spazioPercorsoDaUltimoScatto = spazioPercorsoDaUltimoScatto + (corsa.getRilevazioniDistanze()[i] - corsa.getRilevazioniDistanze()[i - 1]);
			tempoPercorsoDaUltimoScatto++;
						
			possibileScatto = this.findScatto(spazioPercorsoDaUltimoScatto, tempoPercorsoDaUltimoScatto, costoCorsa);
			
			if (possibileScatto.isPresent())
			{
				costoCorsa = costoCorsa + possibileScatto.get().getCosto();
				spazioPercorsoDaUltimoScatto = spazioPercorsoDaUltimoScatto - possibileScatto.get().getSpazio();
				tempoPercorsoDaUltimoScatto = tempoPercorsoDaUltimoScatto - possibileScatto.get().getTempo();
			}
		}
		return costoCorsa + this.getScattoIniziale(corsa.getOraPartenza());
	}	
}
