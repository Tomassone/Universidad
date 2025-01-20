package beans;

public class Drink {
	 private String idDrink;
	 private float costo;
	 private int unita;
	 private String stato;
	 
	 public Drink() {
		 this.idDrink = "idDrink";
		 this.costo = 0;
		 this.unita = 0;
		 this.stato = "daConsegnare";
	 }

	public String getIdDrink() {
		return idDrink;
	}

	public void setIdDrink(String idDrink) {
		this.idDrink = idDrink;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getUnita() {
		return unita;
	}

	public void setUnita(int unita) {
		this.unita = unita;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
}
