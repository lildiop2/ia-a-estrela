import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Mapa {

	ArrayList<Cidade> cidades;
	ArrayList<Rota> rotas;
//	List<Cidade> expandidas = new ArrayList<Cidade>();
	Set<Cidade> exp = new HashSet<Cidade>();
	List<Cidade>caminho= new ArrayList<Cidade>();
	Cidade arvore;

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
//				this.expandidas.add(adj.destino);
				this.exp.add(adj.destino);
				adj.destino.anterior = origem;
			}

		}

//		Collections.sort(this.expandidas);
		List<Cidade> iteretor=new ArrayList<>(this.exp);
		Collections.sort(iteretor);
		System.out.println("=======Expandidas======");
//		for (Cidade cidade : this.expandidas) {
//			System.out.print(cidade.nome + "====>f:" + cidade.getF() + "\t");
//		}
		for (Cidade cidade :iteretor) {
			
			System.out.print(cidade.nome + "====>f:" + cidade.getF() + " g:"+cidade.getG()+" h:"+cidade.getH()+"\t");
		}
//		System.out.println();
//		Cidade menor = this.expandidas.remove(0);
		Cidade menor=iteretor.remove(0);
		this.exp.remove(menor);
		
		if (this.isLink(origem, menor) && this.isLink(menor, origem)) {
			System.out.println("anterior:" + menor.anterior.nome);
			origem.anterior=null;
			Cidade auxOrigem=new Cidade(menor.nome,origem);
			this.arvore=auxOrigem;
			this.caminho.add(menor);
		}else {
//			System.out.println("anterior:" + menor.anterior.nome);
//			System.out.println("Foi retirada:" + this.caminho.peek().nome);
			// Cidade c=this.caminho.peek();

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
//		this.imprimirCaminho(this.arvore);

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
