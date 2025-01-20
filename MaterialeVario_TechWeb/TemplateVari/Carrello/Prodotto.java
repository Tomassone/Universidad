package beans;

public class Prodotto {
	 private String idProdotto;
	 private String testoPresent;
	 private float costo;
	 private int unitaDisp;
	 
	 public Prodotto() {
		 this.idProdotto = "idProdotto";
		 this.testoPresent = "testoPresente";
		 this.costo = 0;
		 this.unitaDisp = 0;
	 }

	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getTestoPresent() {
		return testoPresent;
	}

	public void setTestoPresent(String testoPresent) {
		this.testoPresent = testoPresent;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getUnitaDisp() {
		return unitaDisp;
	}

	public void setUnitaDisp(int unitaDisp) {
		this.unitaDisp = unitaDisp;
	}
}
