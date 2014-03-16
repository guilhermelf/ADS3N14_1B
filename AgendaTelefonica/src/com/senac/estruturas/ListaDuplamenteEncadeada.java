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
	
	public void insert(Nodo<T> nodo, Nodo<T> anterior) {
		nodo.setNext(anterior.getNext());
		anterior.setNext(nodo);
		nodo.setPrev(anterior);
		
		if(anterior == tail)
			tail = nodo;
	}
	
	public void append(Nodo<T> nodo) {
		if(tail != null) {
			tail.setNext(nodo);
			nodo.setPrev(tail);
		} else 
			head = nodo;
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
