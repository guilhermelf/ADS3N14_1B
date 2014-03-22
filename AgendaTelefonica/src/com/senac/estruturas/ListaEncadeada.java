package com.senac.estruturas;

public class ListaEncadeada<T> {

	protected Nodo<T> head;
	protected Nodo<T> tail;
	
	public void insert(T dado) {
		Nodo<T> nodo = new Nodo<T>(dado);
		
		nodo.setNext(head);
		head = nodo;
		if (tail == null)
			tail = nodo;
	}
	
	public Boolean isEmpty() {
		return (head == null) ? true : false;	
	}
	
	public void insert(T dado, Nodo<T> anterior) {
		Nodo<T> nodo = new Nodo<T>(dado);
		
		nodo.setNext(anterior.getNext());
		anterior.setNext(nodo);
		if (anterior == tail)
			tail = nodo;
	}
	
	public void append(T dado) {
		Nodo<T> nodo = new Nodo<T>(dado);
		
		if (tail != null)
			tail.setNext(nodo);
		else
			head = nodo;
		tail = nodo;
	}	
}
