package beans;

import java.util.ArrayList;
import java.util.List;

public class UtentiSalvati {
	private List<Credenziali> elementi;
	
	public UtentiSalvati() {
		this.elementi = new ArrayList<Credenziali>();
	}

	public List<Credenziali> getElementi() {
		return elementi;
	}

	public void addElemento(Credenziali elemento) {
		this.elementi.add(elemento);	
	}
}
