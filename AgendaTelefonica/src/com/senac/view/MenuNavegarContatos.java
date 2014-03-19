package com.senac.view;

import static java.lang.System.out;

import java.util.Scanner;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.estruturas.Nodo;
import com.senac.modelos.Contato;

public class MenuNavegarContatos {
	static Integer op = 0;
	public static Scanner leitor = new Scanner(System.in);
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	public static ListaOrdenada<Contato> agendaOrdenada;
	
	public static void mostrarContato() {
		System.out.println("NAVEGANDO PELA AGENDA");
		
		Nodo<Contato> nodo = agendaOrdenada.getFirst();
		if(nodo != null) {
			do {
				out.printf("Nome: %s\nTelefone: %d\n", nodo.getData().getNome(), nodo.getData().getTelefone());
				out.printf("1 - Voltar ao contato anterior\n2 - Avançar ao próximo contato\n0 - Sair da exibicao de contatos\nDigite a opção escolhida: ");
				try {
					op = Integer.parseInt(leitor.nextLine());
				} catch (Exception e) {
					// TODO: handle exception
				}
				switch (op) {
				case 1:
					if(nodo.getPrev() != null) 
						nodo = nodo.getPrev();
					else 
						System.out.println("Não há contato anterior.");
					break;
				case 2:
					if(nodo.getNext() != null) 
						nodo = nodo.getNext();
					else
						System.out.println("Não há um próximo contato.");
					break;
				case 0:	
					break;
				default:
					break;
				}
				
			} while (op != 0);
		} else {
			System.out.println("Não há elementos na lista!");
		}
	}
}
