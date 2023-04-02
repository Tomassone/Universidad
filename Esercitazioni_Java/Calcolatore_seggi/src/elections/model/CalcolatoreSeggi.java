package elections.model;

public class CalcolatoreSeggi
{
	private String nome;
	private StringBuilder sb;
	
	public CalcolatoreSeggi(String nome, StringBuilder sb)
	{
		this.nome = nome; 
		this.sb = sb;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public StringBuilder getLogger()
	{
		return this.sb;
	}
	
	public void calcolaSeggi(long seggiDaAssegnare, RisultatoDelPartito[] partiti)
	{
		long votiTotali = getVotiTotali(partiti);
		double quoziente = (double) votiTotali / seggiDaAssegnare;
		sb.setLength(0); // clear the buffer
		for (RisultatoDelPartito p : partiti)
		{
			sb.append(p + System.lineSeparator());
		}
		sb.append("Seggi da assegnare: " + seggiDaAssegnare + System.lineSeparator());
		sb.append("Quoziente elettorale: " + quoziente + System.lineSeparator());
		int seggiAssegnati = 0;
		int indexResti = 0;
		RestoDelPartito[] resti = new RestoDelPartito[partiti.length];
		for (RisultatoDelPartito p : partiti)
		{
			p.setSeggi( (long) Math.floor(p.getVoti() / quoziente));
			seggiAssegnati += p.getSeggi();
			resti[indexResti++] = new RestoDelPartito(
			p.getNome(), (long) (p.getVoti() % quoziente));
		}
		sb.append("Seggi assegnati al primo giro: " + System.lineSeparator());
		for (RisultatoDelPartito p : partiti)
		{
			sb.append(p + System.lineSeparator());
		}
		sb.append("Resti dopo il primo giro: " + System.lineSeparator());
		for (RestoDelPartito r : resti)
		{
			sb.append(r + System.lineSeparator());
		}
		sb.append("Seggi ancora da assegnare: " +
		(seggiDaAssegnare-seggiAssegnati) + System.lineSeparator());
		while(seggiDaAssegnare>seggiAssegnati)
		{
			RestoDelPartito max = trovaMax(resti);
			incSeggiPartito(max.getPartito(), partiti);
			seggiAssegnati++;
			max.azzeraResto();
		}
		sb.append("Seggi assegnati alla fine: " + System.lineSeparator());
		for (RisultatoDelPartito p : partiti)
			sb.append(p + System.lineSeparator());
	}
	
	private long getVotiTotali(RisultatoDelPartito[] elenco)
	{
		long result = 0;
		for (int i = 0; i < elenco.length; i++)
			result = result + elenco[i].getVoti();
		return result;
	}
	
	private void incSeggiPartito(String partitoMaxResto, RisultatoDelPartito[] elenco)
	{
		for (int i = 0; i < elenco.length; i++)
			if (partitoMaxResto.equals(elenco[i].getNome()))
				elenco[i].incSeggi();
	}
	
	private RestoDelPartito trovaMax(RestoDelPartito[] elenco)
	{
		int indexOfResult = 0; //indice del valore che verrà restituito.
		for (int i = 1; i < elenco.length; i++)
			if (elenco[i].getResto() > elenco[indexOfResult].getResto())
				indexOfResult = i;
		return elenco[indexOfResult];
	}
}
