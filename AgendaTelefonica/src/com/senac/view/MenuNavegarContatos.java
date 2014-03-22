package com.senac.view;

import static java.lang.System.out;
import java.util.Scanner;

import com.senac.aplicacao.ManipularArquivo;
import com.senac.estruturas.ContatoIterator;
import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaDuplamenteEncadeadaContatos;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class MenuNavegarContatos {
	static Integer op = 0;
	public static final Scanner leitor = new Scanner(System.in);
	
	public static ListaDuplamenteEncadeada<Contato> agenda;
	public static ListaOrdenada<Contato> agendaOrdenada;
	
	public static void mostrarContato(Boolean pesquisar) {
		System.out.println("NAVEGANDO PELA AGENDA\n");
		ContatoIterator it = null;
		if(!pesquisar) {
			it = new ContatoIterator(agendaOrdenada);
		} else {
			System.out.printf("Digite o nome do contato: ");
			String nome = leitor.nextLine();
			
			it = ListaDuplamenteEncadeadaContatos.getByName(nome, agendaOrdenada);
		
		}
		if(it != null && it.hasNext() != false) {
			Contato contato = it.getData();
			do {
				out.printf("Nome: %s%nTelefone: %d%n", contato.getNome(), contato.getTelefone());

				out.printf("\n1 - Voltar ao contato anterior\n2 - Avançar ao próximo contato\n3 - Excluir contato\n0 - Sair da exibicao de contatos\nDigite a opção escolhida: ");
				try {
					op = Integer.parseInt(leitor.nextLine());
				} catch (Exception e) {
					// TODO: handle exception
				}
				switch (op) {
				case 1:
					if(it.hasPrev() != false)  {
						it.prev();
						contato = it.getData();
					} else 
						System.out.println("Não há contato anterior.");
					break;
				case 2:
					if(it.hasNext() != false) {
						it.next();
						contato = it.getData();
					} else
						System.out.println("Não há um próximo contato.");
					break;
				case 3:
					
					agenda.excluir(it.getData());
					ManipularArquivo.atualizar();
					System.out.println("Contato excluido com sucesso!");
					op = 0;
					
					break;
				case 0:	
					break;
				default:
					break;
				}
				
			} while (op != 0);
		} else {
			System.out.println("Contato não localizado!");
		}
	}
}
