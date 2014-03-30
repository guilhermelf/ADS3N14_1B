package com.senac.estruturas;

import java.util.ArrayList;

public class ListaOrdenada<T extends Comparable<T>> extends ListaEncadeada<T> {
	private ArrayList<Comparable<T>> index = null;

	public void updateIndex() {
		Nodo<T> nodo = head;
		index = new ArrayList<Comparable<T>>();
		do {
			index.add(nodo.getData());
			nodo = nodo.getNext();
		} while (nodo != null);
	}

	public Object binarySearch(T dado) {
		if (index == null) 
			updateIndex();
		
		int buscas = 0;
	    int low = 0;
        int high = index.size() - 1;
        int mid;
       
        while(low <= high) {
        	buscas ++;
            mid = ( low + high ) / 2;
            if(index.get(mid).compareTo(dado) < 0)
                low = mid + 1;
            else if(index.get(mid).compareTo(dado) > 0 )
                high = mid - 1;
            else {
                System.out.printf("Foram feitas %d comparações na busca binária.%n", buscas);
            	return index.get(mid);
            }
        }
        System.out.printf("Foram feitas %d comparações na busca binária.%n", buscas);
        return null; 
	}

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
			if (anterior != null) {
				if (anterior.getData().compareTo(nodo.getData()) == 0) {
					percorrer = anterior;

					if (percorrer.getPrev() != null)
						percorrer.getPrev().setNext(nodo);

					nodo.setPrev(percorrer.getPrev());
					percorrer.setNext(nodo);
					nodo.setPrev(percorrer);

					anterior = null;
				} else if (anterior.getData().compareTo(nodo.getData()) > 0) {
					percorrer = anterior;

					if (percorrer.getPrev() != null)
						percorrer.getPrev().setNext(nodo);

					nodo.setPrev(percorrer.getPrev());
					percorrer.setPrev(nodo);
					nodo.setNext(percorrer);

					if (percorrer == head)
						head = nodo;

					anterior = null;
				} else {
					if (anterior.getNext() == null) {
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
