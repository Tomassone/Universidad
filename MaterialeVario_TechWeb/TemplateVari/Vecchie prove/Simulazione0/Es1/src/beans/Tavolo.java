package beans;

import java.util.ArrayList;
import java.util.List;

public class Tavolo {
	private List<Drink> elementi;
	private List<Credenziali> utenti;
	private String idTavolo;
	
	public Tavolo() {
		this.elementi = new ArrayList<Drink>();
		this.utenti = new ArrayList<Credenziali>();
		this.idTavolo = new String();
	}

	public List<Drink> getElementi() {
		return elementi;
	}

	public void addElementi(Drink elemento) {
		elementi.add(elemento);
	}

	public String getIdTavolo() {
		return idTavolo;
	}

	public void setIdTavolo(String idGruppo) {
		this.idTavolo = idGruppo;
	}

	public List<Credenziali> getUtenti() {
		return utenti;
	}

	public void addUtenti(Credenziali utente) {
		utenti.add(utente);
	}
	
	public Drink getById(String id)
	{
		boolean trovato = false;
		for (int i = 0; i < this.elementi.size() && trovato == false; i++)
		{
			if (this.elementi.get(i).getIdDrink().equals(id))
			{
				trovato = true;
				return this.elementi.get(i);
			}
		}
		return null;
	}
}
