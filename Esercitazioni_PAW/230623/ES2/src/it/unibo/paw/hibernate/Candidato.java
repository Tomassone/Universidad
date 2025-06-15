package it.unibo.paw.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Candidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int CandidatoId;
	private int matricola;
	private String nome;
	private String cognome;
	private Set<Concorso> concorsi;
	
	public Candidato() { 
		this.nome = null;
		this.cognome = null;
		this.concorsi = new HashSet<Concorso>();
	}
	
	public int getCandidatoId() {
		return CandidatoId;
	}

	public void setCandidatoId(int candidatoId) {
		CandidatoId = candidatoId;
	}

	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Set<Concorso> getConcorsi() {
		return concorsi;
	}

	public void setConcorsi(Set<Concorso> concorsi) {
		this.concorsi = concorsi;
	}
}
