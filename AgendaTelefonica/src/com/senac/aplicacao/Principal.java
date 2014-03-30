package com.senac.aplicacao;

import com.senac.estruturas.*;
import com.senac.modelos.Contato;
import com.senac.view.MenuAdicionar;
import com.senac.view.MenuNavegarAgenda;
import com.senac.view.MenuNavegarContatos;
import com.senac.view.MenuPrincipal;

public class Principal {
	private static ListaDuplamenteEncadeada<Contato> agenda; 
	private static ListaOrdenada<Contato> agendaOrdenada; 
	
	public static void carregar() {
		
		ManipularArquivo.carregar();	
		
		agenda = ManipularArquivo.agenda;
		agendaOrdenada = ManipularArquivo.agendaOrd;
		
		MenuAdicionar.agenda = agenda;
		MenuAdicionar.agendaOrdenada = agendaOrdenada;
		MenuPrincipal.agenda = agenda;
		MenuPrincipal.agendaOrdenada = agendaOrdenada;
		MenuNavegarAgenda.agendaOrdenada = agendaOrdenada;
		MenuNavegarContatos.agenda = agenda;
		MenuNavegarContatos.agendaOrdenada = agendaOrdenada;
	}
	
	public static void main(String[] args) {
		carregar();
		
		MenuPrincipal.menu();
		//agenda.print();
	}
}
