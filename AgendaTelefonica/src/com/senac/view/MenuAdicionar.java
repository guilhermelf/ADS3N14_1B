package com.senac.view;

import java.util.Scanner;

import com.senac.aplicacao.ManipularArquivo;
import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class MenuAdicionar {

	private static Integer op = null;
	private static String nome = null;
	private static Integer telefone = null;
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	public static ListaOrdenada<Contato> agendaOrdenada;
	
	private static Scanner leitor = new Scanner(System.in);
	
	public static void menu() {
		
	}
	
	public static void adicionarContato() {
		Boolean validador = false;
		
		System.out.printf("ADICIONAR CONTATO\nDigite o nome do Contato: ");
		nome = leitor.nextLine();
			
		while(!validador) {
		
			System.out.printf("Digite o telefone: ");
			try {
				telefone = Integer.parseInt(leitor.nextLine());
				validador = true;
			} catch (Exception e) {
				System.out.print("Telefone inválido (digite somente números).\n");
			}
		}
		confirmar();
	}		
	
	public static void confirmar() {
		do {	
			System.out.printf("\n1 - Confirmar e salvar\n2 - Cancelar e voltar ao menu principal\nDigite a opção desejada: ");
			try {
				op = Integer.parseInt(leitor.nextLine());
			} catch (Exception e) {
				System.out.printf("Opção inválida!\n");
				confirmar();
			}
			
			switch (op) {
			case 0:
				System.out.println("Agenda fechada");
				break;
			case 1:
				Contato contato = new Contato(nome, telefone);
				try {
					agenda.append(contato);
					agendaOrdenada.insert(contato);
				} catch (Exception e) {
					System.out.println("aki");
				}
				
				
				ManipularArquivo.salvar(contato);			
				System.out.printf("\nContato adicionado com sucesso!\n");
				
				op = 0;
				break;
			case 2:
				op = 0;
				break;
			default:
				System.out.printf("Opção inválida!%n");
				break;
			}
			
		} while(op != 0);
		
	}
	
}
