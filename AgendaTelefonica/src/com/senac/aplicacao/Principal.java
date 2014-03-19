package com.senac.aplicacao;

import com.senac.estruturas.*;
import com.senac.modelos.Contato;
import com.senac.view.MenuAdicionar;
import com.senac.view.MenuNavegarAgenda;
import com.senac.view.MenuNavegarContatos;
import com.senac.view.MenuPrincipal;

public class Principal {
	
	public static void main(String[] args) {
		ListaDuplamenteEncadeada<Contato> agenda = ManipularArquivo.carregar();
		ListaOrdenada<Contato> agendaOrdenada = new ListaOrdenada<Contato>();
			
		Nodo<Contato> varrer = agenda.getFirst();
		while(varrer != null) {
			agendaOrdenada.insert(varrer.getData());
			
			varrer = varrer.getNext();
		}

		MenuAdicionar.agenda = agenda;
		MenuAdicionar.agendaOrdenada = agendaOrdenada;
		MenuPrincipal.agenda = agenda;
		MenuPrincipal.agendaOrdenada = agendaOrdenada;
		MenuNavegarAgenda.agenda = agenda;
		MenuNavegarAgenda.agendaOrdenada = agendaOrdenada;
		MenuNavegarContatos.agenda = agenda;
		MenuNavegarContatos.agendaOrdenada = agendaOrdenada;
		
		MenuPrincipal.menu();
		//agenda.print();
	}
}
