package com.senac.estruturas;

import static java.lang.System.out;
import com.senac.modelos.Contato;

public class ListaDuplamenteEncadeada<T extends Comparable<T>> {
	protected Nodo<T> head;
	protected Nodo<T> tail;
	
	public ListaDuplamenteEncadeada() {
		head = null;
		tail = null;
	}
	
	public Nodo<T> getFirst() {
		return head;
	}
	
	public Nodo<T> getLast() {
		return tail;
	}

	public void insert(T dado) {
		Nodo<T> nodo = new Nodo<T>(dado);
		
		if(head == null) 
			head = nodo;
		else {
			nodo.setNext(head);
			head.setPrev(nodo);
			head = nodo;
		}
		
		if(tail == null)
			tail = nodo;
	}
	
	public void insert(T dado, Nodo<T> anterior) {
		Nodo<T> nodo = new Nodo<T>(dado);
		if (anterior != null) {
			nodo.setNext(anterior.getNext());
			nodo.setPrev(anterior);
			anterior.getNext().setPrev(nodo);
			anterior.setNext(nodo);
			
			if (anterior == tail)
				tail = nodo;
		} else {
			if (tail != null) {
				tail.setNext(nodo);
				nodo.setPrev(tail);
			} else {
				head = nodo;
			}
			tail = nodo;
		}
	}
		
	public void append(T dado) {
		Nodo<T> nodo = new Nodo<T>(dado);
		
		if(tail == null) {
			head = nodo;
		} else {
			tail.setNext(nodo);
			nodo.setPrev(tail);
		}	
		tail = nodo;
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
