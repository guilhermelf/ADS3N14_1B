package com.senac.view;

import java.util.Scanner;

import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.Nodo;
import com.senac.modelos.Contato;

public class MenuAdicionar {
	private static Scanner leitor = new Scanner(System.in);
	private static Integer op = null;
	private static String nome = null;
	private static Integer ddd = null;
	private static Integer telefone = null;
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	
	public static void menu() {
		
	}
	
	public static void adicionarContato() {
		Boolean validador = false;
		
		System.out.printf("ADICIONAR CONTATO\nDigite o nome do Contato: ");
		leitor.next();
		nome = leitor.nextLine();
		
		while(!validador) {
			try {
				System.out.printf("Digite o ddd do telefone: ");
				ddd = leitor.nextInt();
				validador = true;
			} catch (Exception e) {
				System.out.print("DDD inválido tente novamente.\n");
				leitor.next();
			}
		}
		
		validador = false;
		
		while(!validador) {
		
			System.out.printf("Digite o telefone: ");
			try {
				telefone = leitor.nextInt();
				validador = true;
			} catch (Exception e) {
				System.out.print("Telefone inválido (digite somente números).\n");
				leitor.next();
			}
		}
		confirmar();
	}		
	
	public static void confirmar() {
		do {
			System.out.printf("1 - Confirmar\n2 - Cancelar e voltar ao menu principal\nDigite a opção desejada: ");
			try {
				op = leitor.nextInt();
			} catch (Exception e) {
				System.out.printf("Opção inválida!\n");
				leitor.next();
				confirmar();
			}
			
			switch (op) {
			case 0:
				System.out.println("Agenda fechada");
				break;
			case 1:
				Contato contato = new Contato(nome, ddd, telefone);
				agenda.insert(new Nodo<Contato>(contato));
				
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
