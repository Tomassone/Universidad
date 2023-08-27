package contocorrente.model;

import java.time.LocalDate;

public class MyAnalizzatore implements Analizzatore {
	
	private ContoCorrente cc;
	
	public MyAnalizzatore(ContoCorrente cc) {
		if(cc==null) throw new IllegalArgumentException("CC nullo nel costruttore dell'analizzatore dati");
		this.cc=cc;
	}

	@Override
	public double getSaldo(LocalDate data) {
		// *** DA FARE ***
		// il metodo getSaldo effettua la somma algebrica dei movimenti effettuati entro la data contabile specificata, 
		// avendo cura però di escludere le righe relative ai saldi diversi dal saldo iniziale (altrimenti, gli importi 
		// verrebbero sommati più volte e il totale sarebbe inconsistente); in presenza di un saldo intermedio deve invece 
		// verificare che la somma dei movimenti fino a quel momento coincida con quella dichiarata nel saldo stesso 
		// (a meno di € 0.01): ove così non sia, deve lanciare l’apposita InconsistentBalanceException (fornita)
		double saldoAgg = 0; 
		double saldoIniziale = 0;
		
		for (Movimento m : this.cc.getMovimenti())
			if (m.getCausale().equals("saldo iniziale") && 
					m.getDataContabile().compareTo(data) <= 0)
						saldoIniziale = m.getImporto();
		
		for (Movimento m : this.cc.getMovimenti())
		{
			if (m.getDataContabile().compareTo(data) <= 0)
			{
				if (!m.getTipologia().equals(Tipologia.SALDO) && !m.getCausale().equals("saldo iniziale"))
					saldoAgg = saldoAgg + m.getImporto();
				
				if (m.getTipologia().equals(Tipologia.SALDO) && !(Math.abs(m.getImporto() - 0.01) < saldoIniziale + saldoAgg))
					throw new InconsistentBalanceException("Saldo registrato diverso da quello iniziale");
			}
		}
		
		return saldoAgg + saldoIniziale; // (fake)
	}
	
	@Override
	public double getSommaPerTipologia(LocalDate data, Tipologia tipologia) {
		// *** DA FARE ***
		// il metodo getSommaPerTipologia procede similmente, limitando tuttavia la somma ai soli movimenti della tipologia 
		// specificata, che può essere solo ACCREDITO o ADDEBITO: in caso contrario, dev’essere lanciata IllegalArgumentException
		
		double saldoAgg = 0; 
		double saldoIniziale = 0;
		
		if (!(tipologia.equals(Tipologia.ACCREDITO) || tipologia.equals(Tipologia.ADDEBITO)))
			throw new IllegalArgumentException("Tipologia errata");
		
		for (Movimento m : this.cc.getMovimenti())
			if (m.getCausale().equals("saldo iniziale") && 
					m.getDataContabile().compareTo(data) <= 0 && m.getTipologia().equals(tipologia))
						saldoIniziale = m.getImporto();
		
		for (Movimento m : this.cc.getMovimenti())
		{
			if (m.getDataContabile().compareTo(data) <= 0 && m.getTipologia().equals(tipologia))
			{
				if (!m.getCausale().equals("saldo iniziale"))
					saldoAgg = saldoAgg + m.getImporto();
			}
		}
		
		return saldoAgg + saldoIniziale; // (fake)
	}
	
}
