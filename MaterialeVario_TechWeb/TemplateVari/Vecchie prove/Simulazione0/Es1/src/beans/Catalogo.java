package beans;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
	private List<Prodotto> elementi;
	
	public Catalogo() {
		this.elementi = new ArrayList<Prodotto>();
	}

	public List<Prodotto> getElementi() {
		return elementi;
	}

	public void setElementi(List<Prodotto> elementi) {
		this.elementi = elementi;
	}
}
