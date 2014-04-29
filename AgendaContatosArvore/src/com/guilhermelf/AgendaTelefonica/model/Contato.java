package com.guilhermelf.AgendaTelefonica.model;

public class Contato implements Comparable<Contato> {
	private String nome;
	private String telefone;
	
	public Contato() {
		this.nome = null;
		this.telefone = null;
	}
	
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int compareTo(Contato contato) {
		return this.getNome().compareToIgnoreCase(contato.getNome());
	}
}