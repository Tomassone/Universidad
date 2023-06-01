package zannotaxi.model;

import java.util.Optional;

public class TariffaADistanza implements ITariffaTaxi
{
	private String nome;
	private double distanzaDiScatto;
	private double valoreScatto;
	private double velocitaMassima;
	private double velocitaMinima;
	private double costoMassimo;
	private double costoMinimo;
	
	public double getCostoMassimo()
	{
		return this.costoMassimo;
	}

	public double getCostoMinimo()
	{
		return this.costoMinimo;
	}

	@Override
	public String getNome()
	{
		return this.nome;
	}
	
	public double getDistanzaDiScatto()
	{
		return this.distanzaDiScatto;
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
	
	public TariffaADistanza(String nome, double velocitaMinima, double velocitaMassima, double costoMinimo, double costoMassimo, double valoreScatto, double distanzaDiScatto)
	{
		this.nome = nome;
		this.valoreScatto = valoreScatto;
		this.velocitaMassima = velocitaMassima;
		this.velocitaMinima = velocitaMinima;
		this.distanzaDiScatto = distanzaDiScatto;
		this.costoMassimo = costoMassimo;
		this.costoMinimo = costoMinimo;
	}
	
	@Override
	public Optional<Scatto> getScattoCorrente(int tempoPercorsoDaUltimoScatto, double spazioPercorsoDaUltimoScatto, double costoCorrente)
	{
		double velocitaMedia = (spazioPercorsoDaUltimoScatto * 1/1000) / ((double) tempoPercorsoDaUltimoScatto * 1/3600);
		if (velocitaMedia >= this.getVelocitaMinima() && velocitaMedia < this.getVelocitaMassima() && costoCorrente >= this.getCostoMinimo() && costoCorrente < this.costoMassimo && Math.round(spazioPercorsoDaUltimoScatto) >= distanzaDiScatto)
			return Optional.of(new Scatto(tempoPercorsoDaUltimoScatto, this.getDistanzaDiScatto(), this.getValoreScatto()));
		else	
			return Optional.empty();
	}
	
}
