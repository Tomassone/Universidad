package beans;

import java.time.LocalDate;

public class Richiesta {
	public int durata;
	public LocalDate data;
	
	public Richiesta()
	{
		this.durata = 0;
		this.data = null;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
