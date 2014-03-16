package com.senac.estruturas;

import static java.lang.System.out;

public class ListaDuplamenteEncadeada<T> {
	protected Nodo<T> head;
	protected Nodo<T> tail;
	
	public ListaDuplamenteEncadeada() {
		head = null;
		tail = null;
	}
	
	public void insert(Nodo<T> nodo) {
		if(head == null) 
			head = nodo;
		else {
			nodo.setNext(head);
			head = nodo;
		}
		
		if(tail == null)
			tail = nodo;
	}
	
	public void print() {
		Nodo<T> elem = head;
		do {
			out.println(elem.getData());
			elem = elem.getNext();
		} while (elem != null);		
	}
}
