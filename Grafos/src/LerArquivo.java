import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LerArquivo {

	public static Grafo carregar() {
		if (!new File("grafo.txt").exists()) {
			File arquivo = new File("grafo.txt");

			System.out.println("Não foi encontrado o arquivo de contatos, criando novo arquivo.");

			return null;
		} else {
			Grafo grafo = new Grafo();
			File arquivo = new File("grafo.txt");
			FileReader fr = null;

			try {
				fr = new FileReader(arquivo);
				

				BufferedReader br = new BufferedReader(fr);

				String linha = null;

				try {
					linha = br.readLine();
					
					if(linha.equals("vertices")) {
						linha = br.readLine();
						
						while(!linha.equals("arestas")) {
							String vertice[] = linha.split(" ");

							grafo.insertVertice(Integer.parseInt(vertice[0]), Double.parseDouble(vertice[1]), Double.parseDouble(vertice[2]));
						
							linha = br.readLine();
						}
								
						if(linha.equals("arestas")) {
							linha = br.readLine();
							
							while(linha != null) {
								String aresta[] = linha.split(" ");
								
								grafo.insertAresta(Integer.parseInt(aresta[0]), Integer.parseInt(aresta[1]), Double.parseDouble(aresta[2]));
								
								linha = br.readLine();
							}
						}
					}
					
				
				} catch (Exception e) {
					System.out.println("Não foi possível recuperar os dados!" + e.getMessage());
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

			return grafo;
		}
	}
	
}
