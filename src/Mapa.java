import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapa {

	ArrayList<Cidade> cidades;
	ArrayList<Rota> rotas;
	List<Cidade> expandidas = new ArrayList<Cidade>();

	public Mapa() {
		this.cidades = new ArrayList<Cidade>();
		this.rotas = new ArrayList<Rota>();

	}

	public void addCidade(String nome, int h) {
		cidades.add(new Cidade(nome, h));
	}

	public void addRota(Cidade origem, Cidade destino, int distancia) {
		Rota rota = new Rota(origem, destino, distancia);
		rotas.add(rota);
		origem.adjacentes.add(rota);
		 rota = new Rota(destino, origem, distancia);
			rotas.add(rota);
			destino.adjacentes.add(rota);
	}

	public Cidade vaiParaProxima(Cidade origem) {
		// Cidade origem=this.cidades.get(this.cidades.indexOf(new Cidade("Arad")));
		if (!origem.adjacentes.isEmpty()) {
			for (Rota adj : origem.adjacentes) {
				adj.destino.setG(origem.g + adj.distancia);
				this.expandidas.add(adj.destino);
				adj.destino.anterior = origem;
			}

		}

		Collections.sort(this.expandidas);
		System.out.println("=======Expandidas======");
		for (Cidade cidade : this.expandidas) {
			System.out.print(cidade.nome + "====>f:" + cidade.getF() + "\t");
		}
		Cidade menor = this.expandidas.remove(0);
		if (this.isLink(origem, menor) || this.isLink(menor, origem)) {
			System.out.println("anterior:" + menor.anterior.nome);
			menor.anterior = menor.anterior;
		}else {
			System.out.println("anterior:" + menor.anterior.nome);
			menor.anterior = origem;
		}

		System.out.println("\nmenor:" + menor.nome);

		return menor;
	}

	public void caminhar(Cidade origem, Cidade destino) {

		while (!origem.equals(destino)) {
			origem = this.vaiParaProxima(origem);

		}

	}

	public void imprimirCaminho(Cidade destino) {
		Cidade percurso = destino;
		System.out.println(percurso.nome);
		while (percurso.anterior != null) {
			percurso = percurso.anterior;
			System.out.println(percurso.nome);
		}

	}

	private boolean isLink(Cidade origem, Cidade destino) {
		for (Rota rota : origem.adjacentes) {
			if (destino.equals(rota.destino)) {
				return true;
			}

		}

		return false;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mapa [cidades=");
		builder.append(cidades);
		builder.append(", \nrotas=");
		builder.append(rotas);
		builder.append(", \nexpandidas=");
		builder.append(expandidas);
		builder.append("]");
		return builder.toString();
	}

}
