package com.senac.view;

import static java.lang.System.out;

import java.util.Scanner;

import com.senac.estruturas.ContatoIterator;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class MenuNavegarAgenda {
	public static final Scanner leitor = new Scanner(System.in);
	static int op = 0;
	
	public static ListaOrdenada<Contato> agendaOrdenada;	
	
	public static void menu() {
				
		/*
		 * Executa um la�o at� que o usu�rio digite 0
		 */
		do {
			System.out.printf("NAVEGAR PELA AGENDA\n1 - Navegar pelos contatos\n2 - Listar todos os contatos\n3 - Buscar contato\n4 - Busca bin�ria\n0 - Voltar ao menu principal \nDigite a op��o desejada: ");
			try {
				op = Integer.parseInt(leitor.nextLine());
			} catch (Exception e) {
				System.out.printf("Op��o inv�lida!\n");
				menu();
			}
			
			switch (op) {
			case 0:
				op = 0;
				break;
			case 1:
				MenuNavegarContatos.mostrarContato(false);
				break;
			case 2:
				out.printf("\nLISTANDO TODOS OS CONTATOS\n");
				
				ContatoIterator it = new ContatoIterator(agendaOrdenada);
				
				if(it.getData() != null) {
					Contato contato;
					do {		
						contato = it.getData();
						out.printf("Nome: %s%nTelefone: %d%n", contato.getNome(), contato.getTelefone());
					} while(it.next() != false);
				}
				break;
			case 3:
				MenuNavegarContatos.mostrarContato(true);
				break;
			case 4:
				System.out.println("BUSCA BIN�RIA:");
				System.out.printf("Digite o nome do contato: ");
				String nome = leitor.nextLine();
				Contato contato = new Contato(nome, 0);
				Contato resultado = (Contato) agendaOrdenada.binarySearch(contato);
				if(resultado != null) 
					System.out.printf("Nome: %s%nTelefone: %d%n", resultado.getNome(), resultado.getTelefone());
				else
					System.out.println("Nenhum contato foi encontrado");
				break;
			default:
				System.out.printf("Op��o inv�lida!%n");
				break;
			}
			
		} while(op != 0);
	}
}

