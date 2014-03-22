package com.senac.estruturas;

public class ListaDuplamenteEncadeada<T extends Comparable<T>> extends ListaEncadeada<T>{
	
	public ListaDuplamenteEncadeada() {
		head = null;
		tail = null;
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
	
	public Boolean excluir(T dado) {
		Nodo<T> nodo = head;
		Boolean excluiu = false;
		
		do {
			if(dado == nodo.getData()) {	
				if (nodo.getPrev() != null) {
					nodo.getPrev().setNext(nodo.getNext());
				} else {
					head = nodo.getNext();
					nodo.getNext().setPrev(null);
				}
				
				if(nodo.getNext() != null) {
					nodo.getNext().setPrev(nodo.getPrev());
				} else {
					tail = nodo.getPrev();
					nodo.getPrev().setNext(null); 
				}
				
				nodo = null;
				
				excluiu = true;
			} else
				nodo = nodo.getNext();

		} while(nodo != null);
		
		return excluiu;
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
}
