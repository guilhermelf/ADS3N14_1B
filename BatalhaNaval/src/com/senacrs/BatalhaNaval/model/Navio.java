package com.senacrs.BatalhaNaval.model;

import java.util.ArrayList;

public class Navio {
	protected ArrayList<Integer> embarcacao = new ArrayList<Integer>();
	protected Boolean vertical = false;
	
	public Boolean isDestroyed() {
		return (this.embarcacao.isEmpty() ? true : false);
	}
	
	public Boolean destroy(int parte) {
		embarcacao.remove(parte - 1);
		
		return isDestroyed();
	}

	public ArrayList<Integer> getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(ArrayList<Integer> embarcacao) {
		this.embarcacao = embarcacao;
	}

	public Boolean getVertical() {
		return vertical;
	}

	public void setVertical(Boolean vertical) {
		this.vertical = vertical;
	}
}
