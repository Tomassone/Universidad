package beans;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
	private List<Prodotto> elementi;
	private String idGruppo;
	
	public Carrello() {
		this.elementi = new ArrayList<Prodotto>();
		this.idGruppo = new String();
	}

	public List<Prodotto> getElementi() {
		return elementi;
	}

	public void addElementi(Prodotto elemento) {
		elementi.add(elemento);
	}

	public String getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(String idGruppo) {
		this.idGruppo = idGruppo;
	}
}
