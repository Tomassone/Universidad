package beans;

import java.util.ArrayList;
import java.util.List;

public class Gruppo {
	private List<Credenziali> utenti;
	private String idGruppo;
	private boolean buying;
	
	public Gruppo() {
		this.utenti = new ArrayList<Credenziali>();
		this.idGruppo = new String();
		this.buying = false;
	}

	public String getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(String idGruppo) {
		this.idGruppo = idGruppo;
	}

	public List<Credenziali> getUtenti() {
		return utenti;
	}

	public void addUtenti(Credenziali utente) {
		utenti.add(utente);
	}
	
	public boolean isBuying() {
		return buying;
	}

	public void setBuying(boolean buying) {
		this.buying = buying;
	}

	public boolean tooManyBrokenPswd()
	{
		int nPswd = 0;
		for (int i = 0; i < this.utenti.size(); i++)
			if (this.utenti.get(i).isPasswordExpired())
				nPswd++;
		return (nPswd > 3);
	}
}
