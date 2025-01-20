package beans;

import java.util.ArrayList;
import java.util.List;

public class Locale {
	private List<Tavolo> elementi;
	
	public Locale() {
		this.elementi = new ArrayList<Tavolo>();
	}

	public List<Tavolo> getElementi() {
		return elementi;
	}

	public void addElementi(Tavolo elemento) {
		elementi.add(elemento);
	}
}
