package com.senac.modelos;

public class Contato {
	private String nome;
	private Integer ddd;
	private Integer telefone;
	
	public Contato() {
		this.nome = null;
		this.ddd = null;
		this.telefone = null;
	}
	
	public Contato(String nome, Integer ddd, Integer telefone) {
		this.nome = nome;
		this.ddd = ddd;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
}
