package beans;

public class AttrazioneTuristica {
	private Coordinate coordinate;
	private String nome;
	private String descrizione;

	public AttrazioneTuristica() {
		this.coordinate = new Coordinate();
		this.nome = "nome";
		this.descrizione = "descrizione";
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
