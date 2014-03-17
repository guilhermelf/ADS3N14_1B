package com.senac.aplicacao;

import com.senac.estruturas.*;
import com.senac.modelos.Contato;
import com.senac.view.MenuAdicionar;
import com.senac.view.MenuPrincipal;

public class Principal {
	static ListaDuplamenteEncadeada<Contato> agenda = ManipularArquivo.carregar();
		
	public static void main(String[] args) {
		MenuAdicionar.agenda = agenda;
		MenuPrincipal.agenda = agenda;
		
		
		MenuPrincipal.menu();
		//agenda.print();
	}
}
