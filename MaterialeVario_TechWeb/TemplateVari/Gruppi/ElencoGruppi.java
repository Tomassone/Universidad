package beans;

import java.util.ArrayList;
import java.util.List;

public class ElencoGruppi {
	private List<Gruppo> elementi;
	
	public ElencoGruppi() {
		this.elementi = new ArrayList<Gruppo>();
	}

	public List<Gruppo> getElementi() {
		return elementi;
	}

	public void addElementi(Gruppo elemento) {
		elementi.add(elemento);
	}
}
