/**
 * @author Guilherme Fraga
 * Classe responsavel por manipular o arquivo de dados.
 */

package com.senac.aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.senac.estruturas.ContatoIterator;
import com.senac.estruturas.ListaDuplamenteEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.modelos.Contato;

public class ManipularArquivo {
	static ListaDuplamenteEncadeada<Contato> agenda;
	static ListaOrdenada<Contato> agendaOrd;

	public static Boolean atualizar() {
		File arquivo = new File("agenda.txt");
		try {
			FileWriter fw = new FileWriter(arquivo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			ContatoIterator it = new ContatoIterator(agenda);
			
			if(it.getData() != null) {
				Contato contato;
				do {		
					contato = it.getData();
					bw.write(contato.getNome() + ":" + contato.getTelefone().toString());
					bw.newLine();	
				} while(it.next() != false);
				bw.flush();
				Principal.carregar();
			
				
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			
		}
		
		
		return true;
	}
	
	/**
	 * Metodo responsavel por salvar a lista de contatos (duplamente encadeada
	 * nao ordenada) em arquivo
	 * 
	 * @param contato
	 * @return Boolean
	 */
	public static Boolean salvar(Contato contato) {
		File arquivo = new File("agenda.txt");

		try (FileWriter fw = new FileWriter(arquivo, true)) {
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(contato.getNome() + ":" + contato.getTelefone().toString());
			bw.newLine();
			bw.flush();

			bw.close();
			fw.close();

			return true;
		} catch (IOException ex) {
			System.out.println("Erro ao salvar no arquivo");
			return false;
		}
	}

	/**
	 * Metodo responsavel por recuperar os dados contidos no arquivo, gera uma
	 * lista duplamente encadeada e uma ordenada
	 * 
	 * @return Boolean
	 */
	public static Boolean carregar() {
		if (!new File("agenda.txt").exists()) {
			File arquivo = new File("agenda.txt");

			try {
				System.out
						.println("Não foi encontrado o arquivo de contatos, criando novo arquivo.");
				if(!arquivo.createNewFile())
					throw new Exception("Fail");
				agenda = new ListaDuplamenteEncadeada<Contato>();
			} catch (Exception e) {
				System.out.println("Erro ao criar o arquivo!");
			}

			return true;
		} else {
			File arquivo = new File("agenda.txt");
			FileReader fr = null;

			try {
				fr = new FileReader(arquivo);
				agenda = new ListaDuplamenteEncadeada<Contato>();
				agendaOrd = new ListaOrdenada<Contato>();

				BufferedReader br = new BufferedReader(fr);

				String linha = null;

				try {
					linha = br.readLine();
					while (linha != null) {
						if (!linha.equals("")) {
							String nome = linha.split(":")[0];
							Integer telefone = Integer.parseInt(linha
									.split(":")[1]);
							Contato contato = new Contato(nome, telefone);
							agenda.insert(contato);
							agendaOrd.insert(contato);
						}
						linha = br.readLine();
					}

				} catch (Exception e) {
					System.out.println("Não foi possível recuperar os dados!"
							+ e.getMessage());
				}

				try {
					fr.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("Não foi possível ler o arquivo!");
			}

			return true;
		}
	}

}
