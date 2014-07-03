import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
	// Lista que guarda os vertices pertencentes ao menor caminho encontrado
	List<Vertice> menorCaminho = new ArrayList<Vertice>();

	// Variavel que recebe os vertices pertencentes ao menor caminho
	Vertice verticeCaminho = new Vertice();

	// Variavel que guarda o vertice que esta sendo visitado
	Vertice atual = new Vertice();

	// Variavel que marca o vizinho do vertice atualmente visitado
	Vertice vizinho = new Vertice();

	// Lista dos vertices que ainda nao foram visitados
	List<Vertice> naoVisitados = new ArrayList<Vertice>();

	// Algoritmo de Dijkstra
	public List<Vertice> encontrarMenorCaminhoDijkstra(Grafo grafo, Vertice v1, Vertice v2) {

		// Adiciona a origem na lista do menor caminho

		// Colocando a distancias iniciais
		for (Vertice vertice : grafo.getVertices()) {
			if (vertice.getId() == v1.getId()) {
				vertice.setDistancia(0.0);
			} else {
				vertice.setDistancia(9999999999999999999.0);	
			}
			this.naoVisitados.add(vertice);
		}

		menorCaminho.add(v1);

		Collections.sort(naoVisitados);

		// O algoritmo continua ate que todos os vertices sejam visitados
		while (!this.naoVisitados.isEmpty()) {

			atual = this.naoVisitados.get(0);

			for (Aresta aresta : grafo.getArestaByOrigem(atual.getId())) {
				
				vizinho = aresta.getDestino();		
				
				if (!vizinho.verificarVisita()) {
					if (vizinho.getDistancia() > (atual.getDistancia()
							+ aresta.getDistancia() + aresta.getCusto())) {
						
						vizinho.setDistancia(atual.getDistancia()
								+ aresta.getDistancia() * aresta.getCusto());
						
						vizinho.setPai(atual);
						
						if (vizinho == v2) {
							
							menorCaminho.clear();

							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);

							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();
							}

							Collections.sort(menorCaminho);
						}
					}
				}
			}

			atual.visitar();
			this.naoVisitados.remove(atual);
			/*
			 * Ordena a lista, para que o vertice com menor distancia fique na
			 * primeira posicao
			 */
			Collections.sort(naoVisitados);
		}
			
		return menorCaminho;
	}
}