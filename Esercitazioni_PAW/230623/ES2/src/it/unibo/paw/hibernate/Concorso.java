package it.unibo.paw.hibernate;

import java.io.Serializable;
import java.util.*;

public class Concorso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ConcorsoId;
	private int codiceConcorso;
	private String classeConcorso;
	private String descrizione;
	private Set<Commissario> commissari;
	private Set<Candidato> candidati;
	
	public Concorso() { 
		this.classeConcorso = null;
		this.descrizione = null;
		this.commissari = new HashSet<Commissario>();
		this.candidati = new HashSet<Candidato>();
	}
	
	public int getConcorsoId() {
		return ConcorsoId;
	}

	public void setConcorsoId(int concorsoId) {
		ConcorsoId = concorsoId;
	}

	public int getCodiceConcorso() {
		return codiceConcorso;
	}

	public void setCodiceConcorso(int codiceConcorso) {
		this.codiceConcorso = codiceConcorso;
	}

	public String getClasseConcorso() {
		return classeConcorso;
	}

	public void setClasseConcorso(String classeConcorso) {
		this.classeConcorso = classeConcorso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Commissario> getCommissari() {
		return commissari;
	}

	public void setCommissari(Set<Commissario> commissari) {
		this.commissari = commissari;
	}

	public Set<Candidato> getCandidati() {
		return candidati;
	}

	public void setCandidati(Set<Candidato> candidati) {
		this.candidati = candidati;
	}
}
