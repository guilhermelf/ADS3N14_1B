package com.senac.estruturas;

public class ListaOrdenada<T extends Comparable<T>> extends ListaEncadeada<T> {

	
	public ListaOrdenada() {
		this.head = null;
		this.tail = null;
	}
	
	@Override
	public void insert(T dado, Nodo<T> anterior) {
		insert(dado);
	}

	@Override
	public void append(T dado) {
		insert(dado);
	}

	@Override
	public void insert(T dado) {
		Nodo<T> nodo = new Nodo<T>(dado);
		Nodo<T> percorrer = null;
		Nodo<T> anterior = head;
		
		do {
			if(anterior != null) {
				if(anterior.getData().compareTo(nodo.getData()) == 0) {
					percorrer = anterior;

					if(percorrer.getPrev() != null)
						percorrer.getPrev().setNext(nodo);
					
					nodo.setPrev(percorrer.getPrev());
					percorrer.setNext(nodo);
					nodo.setPrev(percorrer);	
					
					anterior = null;
				} else if(anterior.getData().compareTo(nodo.getData()) > 0) {
					percorrer = anterior;
					
					if(percorrer.getPrev() != null)
						percorrer.getPrev().setNext(nodo);
					
					nodo.setPrev(percorrer.getPrev());
					percorrer.setPrev(nodo);
					nodo.setNext(percorrer);
					
										
					if(percorrer == head)
						head = nodo;
					
					anterior = null;
				} else {
					if(anterior.getNext() == null) {
						nodo.setPrev(tail);
						tail.setNext(nodo);		
						tail = nodo;
						
						anterior = null;
					} else
						anterior = anterior.getNext();
				}		
			} else {
					head = nodo;
					tail = nodo;
			}		
		} while (anterior != null);
	}
}
