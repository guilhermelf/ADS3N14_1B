package com.guilhermelf.AgendaTelefonica.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.guilhermelf.AgendaTelefonica.estruturas.BinaryTree;
import com.guilhermelf.AgendaTelefonica.model.Contato;
import com.guilhermelf.AgendaTelefonica.view.ConsoleView;

public class AgendaTelefonicaController {
	private ConsoleView view;
	private BinaryTree<Contato> agenda;
	private ArrayList<Contato> lista;
	private int atual = -1;
	
	public AgendaTelefonicaController(ConsoleView view) {
		this.view = view;
		this.agenda = new BinaryTree<Contato>();
		this.lista = null;
	}
	
	public void showContato() {
		if (atual == -1) {
			view.message("Nenhum contato existente.");
		} else {
			Contato contato = this.lista.get(this.atual);
			view.printContato(contato.getNome(), contato.getTelefone());
		}
	}
	
	public void insert() {
		Contato contato = new Contato();
	
		contato.setNome(view.read("Nome"));
		contato.setTelefone(view.read("Telefone"));
		
		int[] retorno = this.agenda.insert(contato);
		
		if(retorno != null)
			view.message("Contato " + contato.getNome() + " inserido, altura da árvore: " + retorno[0] + ", total de elementos: " + retorno[1] +".");
		else
			view.message("Ja existe um contato de nome " + contato.getNome() + ". Por se tratar de uma arvore binaria, o nome do contato nao pode se repetir.");
			
		updateList();
		this.atual = 0;
	}
	
	public void previousContato() {
		if(lista != null && this.atual > 0)
			this.atual --;
		showContato();
	}
	
	public void nextContato() {
		if(lista != null && this.atual < lista.size() - 1)
			this.atual ++;
		showContato();
	}
	
	public void profundidade() {
		Contato busca = new Contato();
		busca.setNome(view.read("Nome").toLowerCase());
		
		Contato localizado = this.agenda.depthFirstSearch(busca);
		
		if(localizado != null) {
			this.atual = lista.indexOf(localizado);
			showContato();
			view.message("Comparacoes efetuadas: " + agenda.comparacoes);
		} else {
			view.message("Contato nao encontrado.");
			view.message("Comparacoes efetuadas: " + agenda.comparacoes);
		}	
	}
	
	public void largura() {
		Contato busca = new Contato();
		busca.setNome(view.read("Nome").toLowerCase());
		
		Contato localizado = this.agenda.breadthFirstSearch(busca);
		
		if(localizado != null) {
			this.atual = lista.indexOf(localizado);
			showContato();
			view.message("Comparacoes efetuadas: " + agenda.comparacoes);
		} else {
			view.message("Contato nao encontrado.");
			view.message("Comparacoes efetuadas: " + agenda.comparacoes);
		}	
	}
	
	public void updateList() {
		this.lista = this.agenda.infixTraversal();
	}
	
	public void infix() {
		ArrayList<Contato> impressao = this.agenda.infixTraversal();
		
		if(impressao != null)
			for (int i = 0; i < impressao.size(); i++) {
				view.printContato(impressao.get(i).getNome(), impressao.get(i).getTelefone());
			}
		else 
			view.message("Agenda telefonica vazia.");
	}
	
	public void prefix() {
		ArrayList<Contato> impressao = this.agenda.prefixTraversal();
		
		if(impressao != null)
			for (int i = 0; i < impressao.size(); i++) {
				view.printContato(impressao.get(i).getNome(), impressao.get(i).getTelefone());
			}
		else 
			view.message("Agenda telefonica vazia.");
	}
	
	public void postfix() {
		ArrayList<Contato> impressao = this.agenda.postfixTraversal();
		
		if(impressao != null)
			for (int i = 0; i < impressao.size(); i++) {
				view.printContato(impressao.get(i).getNome(), impressao.get(i).getTelefone());
			}
		else 
			view.message("Agenda telefonica vazia.");
	}
	
	public void loadFile(String filename) {
		try {
			Scanner arquivo = new Scanner(new FileReader(filename));
			
			while(arquivo.hasNext()) {
				String nome = arquivo.nextLine();
				String telefone = arquivo.nextLine();
				Contato contato = new Contato(nome, telefone);
				
				agenda.insert(contato);
			}
			
			arquivo.close();
			
			this.lista = agenda.infixTraversal();
			
			if(this.lista != null)
				this.atual = 0;
				

		} catch (FileNotFoundException e) {
			view.logError(e.getMessage());
		}
	}
	
	public void delete() {
		Contato exclusao = new Contato();
		exclusao.setNome(view.read("Nome").toLowerCase());
		
		if(this.agenda.remove(exclusao))
			view.message("Contato " + exclusao.getNome() + " excluido com sucesso!");
		else
			view.message("Contato " + exclusao.getNome() + " nao encontrado!");
	
		updateList();
	}
	
	public void saveFile(String filename) {
		FileWriter arquivo = null;
		try {
			arquivo = new FileWriter(filename, false);
			ArrayList<Contato> salvar = agenda.prefixTraversal();
			
			if(salvar != null && salvar.size() > 0) {
				int indice = 0;
				
				while (indice < salvar.size()) {
					Contato contato = salvar.get(indice);
					
					arquivo.append(contato.getNome() + "\n");
					arquivo.append(contato.getTelefone() + "\n");
					
					indice++;
				}
			} else {
				
			}
		} catch (IOException e) {
			view.message(e.getMessage());
		} finally {
			if (arquivo != null)
				try {
					arquivo.close();
				} catch (IOException e) {
					view.message(e.getMessage());
				}
		}
	}
}
