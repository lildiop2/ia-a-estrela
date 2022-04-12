
public class Rota {
	
	Cidade origem,destino;
	int distancia;
	
	
	
	public Rota(Cidade origem, Cidade destino, int distancia) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}
	
	
	public Cidade getOrigem() {
		return origem;
	}
	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}
	public Cidade getDestino() {
		return destino;
	}
	public void setDestino(Cidade destino) {
		this.destino = destino;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(origem.getNome());
		builder.append("==>");
		builder.append(destino.getNome());
		builder.append(", distancia=");
		builder.append(distancia);
		return builder.toString();
	}
	
	

}
