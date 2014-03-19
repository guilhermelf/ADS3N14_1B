package com.senac.estruturas;

import static java.lang.System.out;

import com.senac.modelos.Contato;

public class ListaOrdenada<T extends Comparable<T>> {
	protected Nodo<T> head;
	protected Nodo<T> tail;
	
	public ListaOrdenada() {
		this.head = null;
		this.tail = null;
	}
	
	public Nodo<T> getFirst() {
		return head;
	}
	
	public Nodo<T> getLast() {
		return tail;
	}
	
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

	public void print() {
		Nodo<T> elem = head;
		do {
			out.println(elem.getData());
			elem = elem.getNext();
		} while (elem != null);		
	}
	
	public void printContato() {
		Nodo<Contato> nodo = (Nodo<Contato>) head;
		do {						
			out.printf("Nome: %s\nTelefone: %d\n\n", nodo.getData().getNome(), nodo.getData().getTelefone());		
			
			nodo = nodo.getNext();
		} while (nodo != null);				
	}
	
}
