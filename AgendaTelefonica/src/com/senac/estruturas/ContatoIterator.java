package com.senac.estruturas;

import com.senac.modelos.Contato;

public class ContatoIterator implements Iterator{
	
	private ListaEncadeada<Contato> lista;
	private Nodo<Contato> contato;

	public ContatoIterator(ListaEncadeada<Contato> agenda) {
		this.lista = agenda;
		this.contato = this.lista.head;
	}
	
	public Contato getData() {
		return this.contato.getData();
	}
	
	@Override
	public Boolean hasNext() {
		return (this.lista.head != null && contato.getNext() != null) ? true : false;
	}

	public Boolean hasPrev() {
		return (this.lista.head != null && contato.getPrev() != null) ? true : false;
	}
	
	@Override
	public Boolean next() {
		if(hasNext()) {
			contato = contato.getNext();			
			return true;
		} else 
			return false;
	}
	
	public Boolean prev() {
		if(hasPrev()) {
			contato = contato.getPrev();			
			return true;
		} else 
			return false;
	}
}
