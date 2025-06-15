package it.unibo.paw.hibernate;

import java.io.Serializable;

public class Commissario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int CommissarioId;
	private int matricola;
	private String nome;
	private String cognome;
	private Concorso concorso;
	
	public Commissario() { 
		this.nome = null;
		this.cognome = null;
		this.concorso = new Concorso();
	}
	
	public int getCommissarioId() {
		return CommissarioId;
	}

	public void setCommissarioId(int commissarioId) {
		CommissarioId = commissarioId;
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

	public Concorso getConcorso() {
		return concorso;
	}

	public void setConcorso(Concorso concorso) {
		this.concorso = concorso;
	}
}
