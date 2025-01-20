package beans;

import java.util.ArrayList;
import java.util.List;

public class ElencoAttrazioni {
	private List<AttrazioneTuristica> elementi;
	
	public ElencoAttrazioni() {
		this.elementi = new ArrayList<AttrazioneTuristica>();
	}

	public List<AttrazioneTuristica> getElementi() {
		return elementi;
	}

	public void addElemento(AttrazioneTuristica elemento) {
		this.elementi.add(elemento);	
	}
}
