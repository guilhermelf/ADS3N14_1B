public class Aresta {
	
	private static final double RAIO = 6372.795477598;
	private static final double PI = 3.1415;
	private Vertice origem;
	private Vertice destino;
	private double custo;
	private double distancia;

	public Aresta(Vertice origem, Vertice destino, double custo) {
		this.origem = origem;
		this.destino = destino;
		this.custo = custo;
		this.distancia = calcularDistancia(origem.getLatitude(), origem.getLongitude(), destino.getLatitude(), destino.getLongitude());
	}

	public Vertice getOrigem() {
		return origem;
	}

	public Vertice getDestino()	{
		return destino;
	}

	public double getCusto() {
		return custo;
	}

	public double getDistancia() {
		return distancia;
	}

	/**
	 * Calcula e retorna a distancia (em metros) entre dois pontos geograficos.
	 * @param latitudeA Latitude do ponto A.
	 * @param longitudeA Longitude do ponto A.
	 * @param latitudeB Latitude do ponto B.
	 * @param longitudeB Longitude do ponto B.
	 * @return A distancia (em metros) entre os dois pontos geograficos.
	 */
	private static double calcularDistancia(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
		double latA = Math.pow(longitudeA, 2);
		double lonA = Math.pow(longitudeA, 2);
		double latB = Math.pow(longitudeB, 2);
		double lonB = Math.pow(longitudeB, 2);

		double dLat = latB - latA;
		double dLon = lonB - lonA;

		return Math.sqrt(Math.pow(dLat, 2) + Math.pow(dLon, 2));
	}
}
