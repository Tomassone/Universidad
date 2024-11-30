import java.io.Serializable;

public class Esito implements Serializable {

	private String nome_file;
	private int num_righe;

	public Esito(String nome_file, int num_righe) {
		this.nome_file = nome_file;
		this.num_righe = num_righe;
	}

	public String getNomeFile() {
		return "" + nome_file; // con "" + nome_file evito il passaggio dell'oggetto private
	}

	public int getNumRighe() {
		return num_righe;
	}

	@Override
	public String toString() {
		return "Il file remoto " + this.nome_file + " ora contiene " + this.num_righe + " righe";
	}

}