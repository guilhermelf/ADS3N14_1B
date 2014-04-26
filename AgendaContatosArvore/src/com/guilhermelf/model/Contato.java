package com.guilhermelf.model;

public class Contato implements Comparable<Contato> {
	private String nome;
	private Integer telefone;
	
	public Contato() {
		this.nome = null;
		this.telefone = null;
	}
	
	public Contato(String nome, Integer telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	@Override
	public int compareTo(Contato contato) {
		return this.getNome().compareToIgnoreCase(contato.getNome());
	}
}