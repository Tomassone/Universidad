package zannotaxi.model;

import java.util.Optional;

public class TariffaATempo implements ITariffaTaxi
{
	private String nome;
	private int tempoDiScatto;
	private double valoreScatto;
	private double velocitaMassima;
	private double velocitaMinima;
	
	@Override
	public String getNome()
	{
		return this.nome;
	}
	
	public int getTempoDiScatto()
	{
		return this.tempoDiScatto;
	}

	public double getVelocitaMassima()
	{
		return this.velocitaMassima;
	}

	public double getVelocitaMinima()
	{
		return this.velocitaMinima;
	}

	@Override
	public double getValoreScatto()
	{
		return this.valoreScatto;
	}
	
	public TariffaATempo(String nome, double velocitaMinima, double velocitaMassima, double valoreScatto, int tempoDiScatto)
	{
		this.nome = nome;
		this.valoreScatto = valoreScatto;
		this.velocitaMassima = velocitaMassima;
		this.velocitaMinima = velocitaMinima;
		this.tempoDiScatto = tempoDiScatto;
	}
	
	@Override
	public Optional<Scatto> getScattoCorrente(int tempoPercorsoDaUltimoScatto, double spazioPercorsoDaUltimoScatto, double costoCorrente)
	{
		double velocitaMedia = (spazioPercorsoDaUltimoScatto * 1/1000) / ((double) tempoPercorsoDaUltimoScatto * 1/3600);
		if (velocitaMedia >= this.getVelocitaMinima() && velocitaMedia < this.getVelocitaMassima() && tempoPercorsoDaUltimoScatto >= this.tempoDiScatto)
			return Optional.of(new Scatto(this.getTempoDiScatto(), spazioPercorsoDaUltimoScatto, this.getValoreScatto()));
		else	
			return Optional.empty();
	}
	
}
