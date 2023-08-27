package unident.model;

import java.text.*;
import java.util.*;

public class Carriera
{
	private NumberFormat numberformat;
	private SortedMap<AttivitaFormativa, List<Esame>> esami;
	
	public Carriera(Set<AttivitaFormativa> pianoDidattico)
	{
		this.numberformat = NumberFormat.getNumberInstance(Locale.ITALIAN);
		this.numberformat.setMaximumFractionDigits(2);
		
		this.esami = new TreeMap<AttivitaFormativa, List<Esame>>(Comparator.comparing((AttivitaFormativa attivita) -> attivita.getNome()));
		for (AttivitaFormativa attivita : pianoDidattico)
			this.esami.put(attivita, new ArrayList<Esame>());
	}
	
	public List<Esame> istanzeDi(AttivitaFormativa af)
	{
		return this.esami.get(af);
	}
	
	public double mediaPesata()
	{
		Set<Map.Entry<AttivitaFormativa, List<Esame>>> totale = this.esami.entrySet();
		double result = 0, cfuTotali = 0;
		
		for (Map.Entry<AttivitaFormativa, List<Esame>> coppia : totale)
		{
			if (!coppia.getValue().isEmpty())
				for (Esame esame : coppia.getValue())
					if (!esame.getVoto().equals(Voto.RESPINTO) && !esame.getVoto().equals(Voto.RITIRATO) && !esame.getVoto().equals(Voto.ASSENTE))
					{
						result = result + esame.getVoto().getValue().getAsInt() * esame.getAf().getCfu();
						cfuTotali = cfuTotali + esame.getAf().getCfu();
					}
		}
		
		return result / cfuTotali; 
	}
	
	public void registra(Esame esame)
	{
		if (!this.esami.containsKey(esame.getAf()))
			throw new IllegalArgumentException("Esame non parte della carriera");
		
		List<Esame> listaEsami = this.esami.get(esame.getAf());
		
		for (Esame esameSing : listaEsami)
		{
			if (esameSing.getDate().isBefore(esame.getDate()) && !esameSing.getVoto().equals(Voto.RESPINTO) 
				&& !esameSing.getVoto().equals(Voto.RITIRATO) && !esameSing.getVoto().equals(Voto.ASSENTE))
					throw new IllegalArgumentException("Esame con risultato valido già dato");
			
			if (esameSing.getDate().isAfter(esame.getDate()) && (esameSing.getVoto().equals(Voto.RESPINTO) 
					|| esameSing.getVoto().equals(Voto.RITIRATO) || esameSing.getVoto().equals(Voto.ASSENTE)))
						throw new IllegalArgumentException("Esame con risultato invalido già dato dopo");
			
			if (esame.getDate().equals(esameSing.getDate()))
				throw new IllegalArgumentException("Esame già dato nello stesso giorno");
		}		
		
		if (!listaEsami.contains(esame))
			listaEsami.add(esame);
	}
	
	public String toString()
	{
		Set<Map.Entry<AttivitaFormativa, List<Esame>>> totale = this.esami.entrySet();
		StringBuilder sb = new StringBuilder();
		sb.append("Esami sostenuti:\n");
		
		for (Map.Entry<AttivitaFormativa, List<Esame>> coppia : totale)
		{
			if (!coppia.getValue().isEmpty())
				for (Esame esame : coppia.getValue())
					sb.append(esame.toString() + "\n");
		}
		
		sb.append("Media pesata: " + this.numberformat.format(this.mediaPesata()) + "/30");
		return sb.toString();
	}
}
