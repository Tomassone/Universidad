package beans;

public class Osservazione {
	private Albergo albergo;
	private int checkIn;
	private int checkOut;
	private boolean finalizzata;
	
	public Osservazione()
	{
		this.albergo = new Albergo();
		this.checkIn = 0;
		this.checkOut = 0;
		this.finalizzata = false;
	}

	public Albergo getAlbergo() {
		return albergo;
	}

	public void setAlbergo(Albergo albergo) {
		this.albergo = albergo;
	}

	public int getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(int checkIn) {
		this.checkIn = checkIn;
	}

	public int getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(int checkOut) {
		this.checkOut = checkOut;
	}

	public boolean isFinalizzata() {
		return finalizzata;
	}

	public void setFinalizzata(boolean finalizzata) {
		this.finalizzata = finalizzata;
	}
}
