package contocorrente.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Movimento {
	private LocalDate dataContabile, dataValuta;
	private Tipologia tipologia;
	private double importo;
	private String causale;
	private NumberFormat currencyFormatter;
		
	public Movimento(LocalDate dataContabile, LocalDate dataValuta, Tipologia tipologia, double importo, String causale)
	{
		// *** DA FARE ***
		// Il costruttore deve verificare con cura gli argomenti ricevuti, verificando in particolare che 
		// non siano nulli (per il valore double, che non sia NaN) e che l’importo – positivo, negativo o nullo – sia 
		// coerente con la tipologia di movimento specificata. 
		// Le formattazioni degli importi devono utilizzare apposito formatter con convenzioni culturali italiane
		
		if (dataContabile != null)
			this.dataContabile = dataContabile;
		else
			throw new IllegalArgumentException("data contabile null");
		
		if (dataValuta != null)
			this.dataValuta = dataValuta;
		else
			throw new IllegalArgumentException("data valuta null");

		if (tipologia != null)
			this.tipologia = tipologia;
		else
			throw new IllegalArgumentException("tipologia null");
				
		if (!Double.isNaN(importo))
			this.importo = importo;
		else
			throw new IllegalArgumentException("importo null");
		
		if ((tipologia.equals(Tipologia.NULLO) && importo != 0) || 
				(!tipologia.equals(Tipologia.NULLO) && importo == 0) ||
					(tipologia.equals(Tipologia.ADDEBITO) && importo > 0) ||
						(tipologia.equals(Tipologia.ACCREDITO) && importo < 0))
			throw new IllegalArgumentException("tipologia non coerente con importo");

		if (causale != null)
			this.causale = causale;
		else
			throw new IllegalArgumentException("importo null");
		
		this.currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
	}
	
	public LocalDate getDataContabile() {
		return dataContabile;
	}
	public LocalDate getDataValuta() {
		return dataValuta;
	}
	public Tipologia getTipologia() {
		return tipologia;
	}
	public double getImporto() {
		return importo;
	}
	public String getCausale() {
		return causale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(causale, dataContabile, dataValuta, importo, tipologia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Movimento other = (Movimento) obj;
		return Objects.equals(causale, other.causale) && Objects.equals(dataContabile, other.dataContabile)
				&& Objects.equals(dataValuta, other.dataValuta)
				&& Double.doubleToLongBits(importo) == Double.doubleToLongBits(other.importo)
				&& tipologia == other.tipologia;
	}

	@Override
	public String toString() {
		return dataContabile + " " + tipologia + " di " + currencyFormatter.format(importo) + " per " + causale;
	}
	
}
