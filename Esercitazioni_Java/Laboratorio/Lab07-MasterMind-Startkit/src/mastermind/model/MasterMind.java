package mastermind.model;

import java.math.*;

public class MasterMind
{
	private Combinazione segreta;
	
	private int min(int a, int b)
	{
		if (a < b)
			return a;
		else 
			return b;
	}
	
	public Risposta calcolaRisposta(Combinazione tentativo)
	{
		int nPallineNere = 0, nPallineTotali = 0, nPallineBianche = 0, j = 0;
		int[] occorenzeColoriTentativo = this.occorrenze(tentativo);
		int[] occorenzeColoriSegreta = this.occorrenze(this.segreta);
		int[] occorenzeColoriDef = new int[occorenzeColoriSegreta.length];
		Risposta result = new Risposta(4);
		
		for (int i = 0; i < segreta.dim(); i++)
			if (segreta.getPiolo(i).equals(tentativo.getPiolo(i)))
				nPallineNere++;
		
		for (int i = 0; i < occorenzeColoriTentativo.length; i++)
			if (occorenzeColoriTentativo[i] != 0 && occorenzeColoriSegreta[i] != 0)
			{
				occorenzeColoriDef[j] = this.min(occorenzeColoriTentativo[i], occorenzeColoriSegreta[i]);
				j++;
			}
		
		for (int i = 0; i < occorenzeColoriDef.length; i++)
		{
			nPallineTotali = nPallineTotali + occorenzeColoriDef[i];
		}
		
		nPallineBianche = nPallineTotali - nPallineNere;
		
		for (int i = 0; i < nPallineNere; i++)
			result.setPiolo(i, PioloRisposta.NERO); 
		
		for (int i = nPallineNere; i < nPallineNere + nPallineBianche; i++)
			result.setPiolo(i, PioloRisposta.BIANCO);
		
		//System.out.println(result);
		
		return result;
	}
	
	public String combinazioneSegreta()
	{
		return segreta.toString();
	}
	
	public MasterMind(int lunghezzaCodice)
	{
		this.segreta = new Combinazione(lunghezzaCodice);
		this.sorteggiaCombinazione(this.segreta);
	}
	
	private int[] occorrenze (Combinazione tentativo)
	{
		int[] occorrenzeColori = new int[6];
		for (int i = 0; i < tentativo.dim(); i++)
			occorrenzeColori[tentativo.getPiolo(i).ordinal() - 1]++;
		return occorrenzeColori;
	}
	
	protected void sorteggiaCombinazione(Combinazione segreta)
	{
		double valoreRandomico;
		PioloDiGioco[] elencoValPossibili = PioloDiGioco.values();
		for (int i = 0; i < segreta.dim(); i++)
		{
			valoreRandomico = Math.random() * 5 + 1;
			segreta.setPiolo(i, elencoValPossibili[(int) valoreRandomico]);
		}
	}
}
