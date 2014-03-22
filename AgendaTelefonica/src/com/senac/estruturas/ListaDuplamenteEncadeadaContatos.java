package com.senac.estruturas;

import com.senac.modelos.Contato;

public class ListaDuplamenteEncadeadaContatos extends ListaDuplamenteEncadeada<Contato> {
	
	public static ContatoIterator getByName(String nome, ListaEncadeada<Contato> lista) {
		ContatoIterator it = new ContatoIterator(lista);
		
		if (nome.equals("")) {
			return null;
		}
		
		do {
			if (it.getData().getNome().toLowerCase().startsWith(nome.toLowerCase())) {
				return it;
			}		
		} while (it.next() != false);
		
		return null;
	}
}
