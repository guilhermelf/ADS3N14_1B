package com.senac.view;

import static java.lang.System.out;

import java.util.Scanner;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.estruturas.Nodo;
import com.senac.modelos.Contato;

public class MenuNavegarAgenda {
	public static Scanner leitor = new Scanner(System.in);
	static int op = 0;
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	public static ListaOrdenada<Contato> agendaOrdenada;
	
	public MenuNavegarAgenda() {
		
	}
	
	public static void menu() {
				
		/*
		 * Executa um laço até que o usuário digite 0
		 */
		do {
			System.out.printf("NAVEGAR PELA AGENDA\n1 - Navegar pelos contatos\n2 - Listar todos os contatos\n3 - Buscar contato\n0 - Voltar ao menu principal \nDigite a opção desejada: ");
			try {
				op = Integer.parseInt(leitor.nextLine());
			} catch (Exception e) {
				System.out.printf("Opção inválida!\n");
				menu();
			}
			
			switch (op) {
			case 0:
				op = 0;
				break;
			case 1:
				MenuNavegarContatos.mostrarContato();
				break;
			case 2:
				out.printf("\nLISTANDO TODOS OS CONTATOS\n");
				agendaOrdenada.printContato();
				break;
			default:
				System.out.printf("Opção inválida!%n");
				break;
			}
			
		} while(op != 0);
	}
}

