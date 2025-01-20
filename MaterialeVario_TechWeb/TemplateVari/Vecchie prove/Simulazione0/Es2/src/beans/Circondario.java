package beans;

import java.util.ArrayList;
import java.util.List;

public class Circondario {
	private List<Coordinate> elementi;
	
	public Circondario() {
		this.elementi = new ArrayList<Coordinate>();
	}

	public List<Coordinate> getElementi() {
		return elementi;
	}

	public void addElemento(Coordinate elemento) {
		this.elementi.add(elemento);	
	}
	
	public boolean isBusy(Coordinate utente)
	{
		int occorrenze = 0;
		for (Coordinate elemento : this.elementi)
			if (elemento.isNear(utente, 100))
				occorrenze++;
		return (occorrenze >= 10);
	}
}
