package com.senacrs.BatalhaNaval.model;

public class Coordenada {
	private Navio navio = null;
	private int parte = 0;
	private Boolean jogavel = true;
	
	public int getParte() {
		return parte;
	}

	public void setParte(int parte) {
		this.parte = parte;
	}

	public Navio getNavio() {
		return navio;
	}
	
	public void setNavio(Navio navio) {
		this.navio = navio;
	}
	
	public Boolean isPlayable() {
		return jogavel;
	}	
	
	public void fire() {
		jogavel = false;
	}
}
