package com.senacrs.BatalhaNaval.controller;

import java.util.ArrayList;

import com.senacrs.BatalhaNaval.model.Tabuleiro;
import com.senacrs.BatalhaNaval.view.BatalhaNavalView;

public class BatalhaNavalController {
	private Tabuleiro tabuleiro = null;
	private BatalhaNavalView view = null;
	private ArrayList<String> indice = new ArrayList<String>();
	private String mensagemAnterior = "";
	
	public void iniciar(BatalhaNavalView view) {
		indice.add("A");
		indice.add("B");
		indice.add("C");
		indice.add("D");
		indice.add("E");
		indice.add("F");
		indice.add("G");
		indice.add("H");
		indice.add("I");
		indice.add("J");
		
		this.tabuleiro = new Tabuleiro();
		this.view = view;
		tabuleiro.iniciar();
		view.message("********************************* BATALHA NAVAL **********************************");
	}
	
	public void imprimirTabuleiro() {
		view.imprimirTabuleiro(tabuleiro.getMatriz());
	}
	
	public void imprimirPontuacao() {
		view.pontuacao(tabuleiro.getPontos());
	}
	
	public void jogar() {
		imprimirTabuleiro();
		imprimirPontuacao();
		if(mensagemAnterior != "")
			view.message(mensagemAnterior);
		
		int linha = 99;
		int coluna = 99;
		
		do {
			try {
				linha = Integer.parseInt(view.read("Linha")) - 1;
				if(linha < 0 || linha > 9)
					view.message("Valor inválido.");
			} catch (Exception e) {
				view.message("Valor inválido");
			}
		} while(linha > 9 || linha < 0);
			
		do {
			try {
				coluna = indice.indexOf(view.read("Coluna").toUpperCase());
				if(coluna < 0 || coluna > 9)
					view.message("Valor inválido.");
			} catch (Exception e) {
				view.message("Valor inválido");
			}
		} while(coluna > 9 || coluna < 0);	
		
		switch (tabuleiro.atirar(linha, coluna)) {
		case -1:
			mensagemAnterior = "Errou o tiro!";
			break;
		case 2:
			mensagemAnterior = "Voce acertou o disparo!";
			break;
		case 7:
			String tipo = tabuleiro.tipoNavio(linha, coluna);
			mensagemAnterior = "Voce acertou o disparo e destruiu um " + tipo + "!";
			break;
		default:
			mensagemAnterior = "Este ponto ja foi atingido!";
		}		
		
		if(tabuleiro.gameIsOver())
			if(tabuleiro.getPontos() < 1) {
				if(mensagemAnterior != "")
					view.message(mensagemAnterior);
				view.message("Voce foi derrotado!");
			} else {
				if(mensagemAnterior != "")
					view.message(mensagemAnterior);
				view.message("Parabens, voce destruiu todos os navios e acabou o jogo com " + tabuleiro.getPontos() + " pontos!");
			}
		else
			jogar();
	}
}
