package com.senac.estruturas;

public class Nodo<T> {
	
	private T  dado;
	private Nodo<T> next;
	
	private Nodo<T> prev;

	public Nodo(T dado)	{
		this.dado = dado;
		next = null;
		prev = null;
	}
	
	public void setData(T dado)	{
		this.dado = dado;
		next = null;
		prev = null;
	}
	
	public T getData() {
		return dado;
	}
	
	public Nodo<T> getPrev() {
		return prev;
	}

	public void setPrev(Nodo<T> prev) {
		this.prev = prev;
	}


	public void setNext(Nodo<T> next) {
		this.next = next;
	}
	
	public Nodo<T> getNext() {
		return next;
	}
}
