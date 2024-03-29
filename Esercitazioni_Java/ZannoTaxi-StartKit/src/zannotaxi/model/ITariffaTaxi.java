package zannotaxi.model;

import java.util.Optional;

public interface ITariffaTaxi
{
	public String getNome();
	
	public double getValoreScatto();
	
	public Optional<Scatto> getScattoCorrente(int tempoPercorsoDaUltimoScatto, double spazioPercorsoDaUltimoScatto, double costoCorrente);
}
