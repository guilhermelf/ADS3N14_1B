import java.util.ArrayList;
import java.util.List;

public class Principal {
	public static void main(String[] args) {
		Dijkstra d = new Dijkstra();
		
		Grafo grafo = LerArquivo.carregar();
		
		List<Vertice> vertices = d.encontrarMenorCaminhoDijkstra(grafo, grafo.getVerticeById(5), grafo.getVerticeById(15));
		
		System.out.print("Caminho a percorrer: ");
		for (Vertice vertice : vertices) {
			System.out.print(vertice.getId() + " ");
		}
		System.out.println("\n");
		
		Percurso p = new Percurso();
		
		p.paradas(grafo, vertices);

		
	}
}
