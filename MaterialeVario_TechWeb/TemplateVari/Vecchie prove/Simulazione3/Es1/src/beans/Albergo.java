package beans;

public class Albergo {
	private String ID_albergo;
	private int camere_totali;
	private double prezzo_STATICO_camera_per_giornata;
	
	public Albergo()
	{
		this.ID_albergo = "id";
		this.camere_totali = 0;
		this.prezzo_STATICO_camera_per_giornata = 0;
	}
	
	public String getID_albergo() {
		return ID_albergo;
	}
	public void setID_albergo(String iD_albergo) {
		ID_albergo = iD_albergo;
	}
	public int getCamere_totali() {
		return camere_totali;
	}
	public void setCamere_totali(int camere_totali) {
		this.camere_totali = camere_totali;
	}
	public double getPrezzo_STATICO_camera_per_giornata() {
		return prezzo_STATICO_camera_per_giornata;
	}
	public void setPrezzo_STATICO_camera_per_giornata(double prezzo_STATICO_camera_per_giornata) {
		this.prezzo_STATICO_camera_per_giornata = prezzo_STATICO_camera_per_giornata;
	}
}
