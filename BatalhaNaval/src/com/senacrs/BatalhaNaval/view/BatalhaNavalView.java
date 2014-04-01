package com.senacrs.BatalhaNaval.view;

import static java.lang.System.out;
import java.util.Scanner;

import com.senacrs.BatalhaNaval.model.Coordenada;

public class BatalhaNavalView {
	private Scanner teclado = new Scanner(System.in);
	
	public void message(String message) {
		out.println(message);
	}
	
	public void pontuacao(int pontos) {
		out.printf("Ponto(s): %s\n", pontos);
	}
	
	public String read(String prompt) {
		out.print(prompt + ": ");
		return teclado.nextLine();
	}
	
	public void logError(String message) {
		message("Error: " + message);
	}
	
	public void imprimirTabuleiro(Coordenada[][] matriz) {
		System.out.printf("\t A\t B\t C\t D\t E\t F\t G\t H\t I\t J\n");
		for (int x = 0; x < matriz.length; x++) {
			System.out.printf("%d", x + 1);
			for (int y = 0; y < matriz[x].length; y++) {	
				System.out.printf("\t %s", (matriz[x][y].isPlayable() ? "." : (matriz[x][y].getNavio() != null ? "o" : "-")));
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------------");
	}
}

