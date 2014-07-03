public class Vertice implements Comparable<Vertice>
{
	private Integer id;
	private Double latitude;
	private Double longitude;
	private Double distancia;
	private boolean visitado = false;
	private Vertice pai;

	public Vertice(Integer id, Double latitude, Double longitude)
	{
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	 public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
	}


	public void visitar () {   
	    this.visitado = true;
	 }
	 
	 public boolean verificarVisita() {     
	     return visitado;
	 }
	
	public Vertice() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	
	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Double getLatitude()	{
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Vertice outro = (Vertice) obj;
		if (id == null) {
			if (outro.id != null)
				return false;
		} else if (!id.equals(outro.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + (id == null ? 0 : id.hashCode());
		return resultado;
	}

	@Override
	public int compareTo(Vertice vertice) {
		return id.compareTo(vertice.id);
	}
}
