
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class SetasInvertidas implements Cloneable {
	public static int infinito = 9999;
	ArrayList<Vertice> vertices;
	ArrayList<Aresta> arestas;

	class Vertice {

		int v;
		ArrayList<Aresta> adj = new ArrayList<Aresta>();
		Vertice anterior;
		int distancia;

		public Vertice(int v) {
			this.v = v;
			this.adj = adj;
			this.anterior = anterior;
			this.distancia = distancia;
		}

		@Override
		public String toString() {
			String s = "";
			s = "\nVertice: " + v + "\nDistancia: " + distancia;
			if (anterior != null)
				s += "\nAntecessor: " + anterior.v;
			for (Aresta a : adj) {
				s += "\nDestino: " + a.destino.v;
			}
			return s;
		}

	}

	class Aresta {

		public Vertice origem, destino;
		public int peso;

		public Aresta(Vertice origem, Vertice destino, int peso) {
			this.origem = origem;
			this.destino = destino;
			this.peso = peso;

		}

		@Override
		public String toString() {
			return "Aresta [Origem=" + origem.v + ", Destino=" + destino.v + "]";
		}

	}

	public SetasInvertidas() {
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}

	private void addVertice(int i) {
		vertices.add(new Vertice(i));

	}

	private void addAresta(Vertice origem, Vertice destino, int peso) {
		Aresta a = new Aresta(origem, destino, peso);
		arestas.add(a);
		origem.adj.add(a);

	}

	void dijsktra(Vertice inicio) {
		for (Vertice vertice : vertices) {
			vertice.anterior = null;
			vertice.distancia = infinito;
		}
		inicio.distancia = 0;
		Queue<Vertice> fila = new LinkedList<Vertice>();// fila de prioridade
		fila.add(inicio); // na cauda
		while (!fila.isEmpty()) {
			Vertice v = fila.remove();// cabeca
			for (Aresta a : v.adj) {
				Vertice aux = a.destino;
				if (v.distancia + a.peso < aux.distancia) {
					aux.distancia = v.distancia + a.peso;
					aux.anterior = v;
					fila.add(aux);
//					if (fila.contains(aux))
//						fila.add(aux);
//					else
//						fila.remove(aux);
				}
			}

		}
	}

	ArrayList<Vertice> menorcaminho(Vertice origem, Vertice destino) {
		dijsktra(origem);
		ArrayList<Vertice> caminho = new ArrayList<Vertice>();
		try {
			while (destino.equals(origem) == false) {
				caminho.add(destino);
				destino = destino.anterior;
			}
			caminho.add(destino);

		} catch (Exception e) {
			// System.out.println("BUUUUUUUUUUG");
		}

		return caminho;

	}
	
	public int  somarPeso(ArrayList<Vertice> menorCaminho) {
		Vertice ini=menorCaminho.remove(menorCaminho.size()-1);
		int n=0;
		while(!menorCaminho.isEmpty()) {
			Vertice dest=menorCaminho.remove(menorCaminho.size()-1);
			for (Aresta aresta : arestas) {
				if(ini.v==aresta.origem.v && dest.v==aresta.destino.v) {
					n+=aresta.peso;
					ini=dest;
				}
			}
		}
		
		return n;
		
	}

	public String toString() {
		String r = "";
		for (Vertice u : vertices) {
			r += "[" + u.v + "]" + " -> ";
			for (Aresta e : u.adj) {
				Vertice v = e.destino;
				String aux = "";
				aux += "(" + e.peso + ")";
				r += aux + v.v + ", ";
			}
			r += "\n";
		}
		return r;
	}

	@Override
	public SetasInvertidas clone() throws CloneNotSupportedException {

		return (SetasInvertidas) super.clone();
	}

	public static void main(String[] args) {
		SetasInvertidas g = new SetasInvertidas();
		Scanner scn = new Scanner(System.in);
		while (scn.hasNextInt()) {
			int v = scn.nextInt();
			int a = scn.nextInt();
			int origem = scn.nextInt();
			int destino = scn.nextInt();
			for (int i = 1; i <= v; i++) {
				g.addVertice(i);
			}
			for (int i = 0; i < a; i++) {
				int v1 = scn.nextInt();
				int v2 = scn.nextInt();
				g.addAresta(g.vertices.get((v1 - 1)), g.vertices.get((v2 - 1)), 0);

			}
			ArrayList<Aresta> t = new ArrayList<SetasInvertidas.Aresta>();
			for (Aresta a1 : g.arestas) {

				for (Aresta a2 : g.arestas) {
					if (a1.destino.v != a2.origem.v && a1.origem.v != a2.destino.v) {
						t.add(g.new Aresta(a1.destino, a1.origem, 1));
						break;
					}
				}
			}
			for (Aresta aresta : t) {

				g.addAresta(aresta.origem, aresta.destino, aresta.peso);
			}

			// tenho que verificar se o caminho de voltar nao estiver para adicionar com
			// peso um.
			//System.out.println("---Grafo---\n" + g);
			// g.dijsktra(g.vertices.get(destino-1));
			SetasInvertidas f = new SetasInvertidas();
			try {
				f = g.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Vertice> aux = g.menorcaminho(g.vertices.get(origem - 1), g.vertices.get(destino - 1));
			ArrayList<Vertice> aux1 = f.menorcaminho(f.vertices.get(destino - 1), g.vertices.get(origem - 1));
//			if (!aux.isEmpty()) {
//				if (aux.size() == 1) {
//					System.out.println(" Caminho n�o encontrado!");
//				} else {
//					System.out.println("Caminho: ");
//					for (Vertice vertice : aux) {
//						System.out.print(vertice.v + "==>");
//					}
//					System.out.println();
//				}
//
//			} else {
//				System.out.println(" Nao h� camiho entre esse dois!");
//			}
//			
//			//volta
//			if (!aux1.isEmpty()) {
//				if (aux1.size() == 1) {
//					System.out.println(" Caminho n�o encontrado!");
//				} else {
//					System.out.println("Caminho: ");
//					for (Vertice vertice : aux1) {
//						System.out.print(vertice.v + "==>");
//					}
//					System.out.println();
//				}
//
//			} else {
//				System.out.println(" Nao h� camiho entre esse dois!");
//			}
			int soma1=g.somarPeso(aux);
			int soma2=f.somarPeso(aux1);
			//System.out.println(soma1+"\n"+soma2);
			if(soma1<soma2) {
				System.out.println("Bibi: "+soma1);
			}else if(soma1>soma2) {
				System.out.println("Bibika: "+soma2);
			}else {
				System.out.println("Bibibibika");
			}
		}
	}

}
