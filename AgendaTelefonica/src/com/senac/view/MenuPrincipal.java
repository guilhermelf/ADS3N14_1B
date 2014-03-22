package com.senac.view;

import java.util.Scanner;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class MenuPrincipal {
	public static final Scanner leitor = new Scanner(System.in);
	static int op = 0;
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	public static ListaOrdenada<Contato> agendaOrdenada;
	
	public static void menu() {
				
		/*
		 * Executa um laço até que o usuário digite 0
		 */
		do {
			System.out.printf("AGENDA TELEFÔNICA\n1 - Navegar pela agenda\n2 - Adicionar contato\n0 - Sair \nDigite a opção desejada: ");
			try {
				op = leitor.nextInt();
			} catch (Exception e) {
				System.out.printf("Opção inválida!\n");
				leitor.next();
				menu();
			}
			
			switch (op) {
			case 0:
				System.out.println("Agenda fechada");
				break;
			case 1:
				MenuNavegarAgenda.menu();
				break;
			case 2:
				MenuAdicionar.adicionarContato();
				break;
			default:
				System.out.printf("Opção inválida!%n");
				break;
			}
			
		} while(op != 0);
	}
}

