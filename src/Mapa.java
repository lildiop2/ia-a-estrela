import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Mapa {

	ArrayList<Cidade> cidades;
	ArrayList<Rota> rotas;
	Set<Cidade> exp = new HashSet<Cidade>();
	List<Cidade>caminho= new ArrayList<Cidade>();


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
		if (!origem.adjacentes.isEmpty()) {
			for (Rota adj : origem.adjacentes) {
				adj.destino.setG(origem.g + adj.distancia);
				this.exp.add(adj.destino);
				adj.destino.anterior = origem;
			}

		}

		List<Cidade> iteretor=new ArrayList<>(this.exp);
		Collections.sort(iteretor);
		System.out.println("Anterior à Cidade do menor f:"+iteretor.get(0).anterior.nome);
		System.out.println("=======Estado da Expanção======");
		for (Cidade cidade :iteretor) {
			
			System.out.print(cidade.nome + "====>f:" + cidade.getF() + " g:"+cidade.getG()+" h:"+cidade.getH()+"\t");
		}
		System.out.println();
		Cidade menor=iteretor.remove(0);
		this.exp.remove(menor);
		
		if (this.isLink(origem, menor) && this.isLink(menor, origem)) {
			origem.anterior=null;
			this.caminho.add(menor);
		}else {
			
			this.caminho.add(menor);
			
			
		}
		
		
		
		System.out.println("menor:"+menor.nome + "====>f:" + menor.getF() + " g:"+menor.getG()+" h:"+menor.getH()+"\t\n");

		return menor;
	}

	public void caminhar(Cidade origem, Cidade destino) {
		this.caminho.add(origem);
		

		
		while (!origem.equals(destino)) {
			origem = this.vaiParaProxima(origem);

		}

	}

	public void imprimirCaminho(Cidade destino) {
		Stack<Cidade> percurso= new Stack<Cidade>();
		percurso.push(destino);
		Cidade aux=destino;
		while (aux.anterior != null) {
			aux = aux.anterior;
			percurso.push(aux);
		}
		
		for (Cidade cidade : percurso) {
			System.out.println(cidade.nome);
		}

	}

	 boolean isLink(Cidade origem, Cidade destino) {
		for (Rota rota : origem.adjacentes) {
			if (destino.equals(rota.destino)) {
				return true;
			}

		}

		return false;

	}

	
	public ArrayList<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(ArrayList<Cidade> cidades) {
		this.cidades = cidades;
	}

	public ArrayList<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(ArrayList<Rota> rotas) {
		this.rotas = rotas;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mapa [cidades=");
		builder.append(cidades);
		builder.append("\n\n, rotas=");
		builder.append(rotas);
		builder.append("\n\n, exp=");
		builder.append(exp);
		builder.append("]");
		return builder.toString();
	}

}
