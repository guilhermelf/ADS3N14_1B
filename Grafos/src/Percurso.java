import java.util.List;

public class Percurso {
	private int autonomia = 600;
	private int velocidade = 80;
	private int tempoDirigindo = 3;
	private Aresta atual;
	private double horasDirigindo = 0;
	private double totalHoras = 0;
	private double distanciaTotal = 0;
	private double autonomiaAtual = autonomia;
	
	public void paradas(Grafo grafo, List<Vertice> caminho) {
				
		System.out.println("Ponto de saída: " + caminho.get(0).getId());
		
		for (int i = 0; i < caminho.size() - 1; i++) {	
			
			atual = grafo.getArestaByOrigemDestino(caminho.get(i).getId(), caminho.get(i + 1).getId());
			
			totalHoras += (atual.getDistancia()/1000)/velocidade;
			distanciaTotal += atual.getDistancia()/1000;
			autonomiaAtual -= atual.getDistancia()/1000;
			horasDirigindo += (atual.getDistancia()/1000)/velocidade;
			
			if(deveParar(grafo, caminho, i + 1)) {
				System.out.println("Parou no ponto: " + caminho.get(i).getId() + "\n");
							
				parada();
			} else {
				System.out.println("Distância percorrida: " + String.format("%1$,.2f", atual.getDistancia()/1000) + " km");
				System.out.println("Tempo de viagem: " + String.format("%1$,.2f", horasDirigindo) + " hs");
				System.out.println("Parou no ponto: " + caminho.get(i+1).getId() + "\n");

			}
			

		}
		System.out.println("Finalmente chegou ao ponto: " + caminho.get(caminho.size() - 1).getId());
		
		System.out.println("\nDistancia total percorrida: " + String.format("%1$,.2f", distanciaTotal) + " km.");
		System.out.println("Tempo total de viagem: " + String.format("%1$,.2f", totalHoras) + " hs");	
	}
	
	public void parada() {
		horasDirigindo = 0;
		autonomiaAtual = 600;	
	}
	
	public boolean deveParar(Grafo grafo, List<Vertice> caminho, int i) {
		if(caminho.size() - 1 < i) {
			Aresta percurso = grafo.getArestaByOrigemDestino(caminho.get(i - 1).getId(), caminho.get(i).getId());
			
			if(autonomiaAtual + percurso.getDistancia()/1000 > autonomia
					|| horasDirigindo + percurso.getDistancia()/1000/velocidade > tempoDirigindo) {
				
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}
}
