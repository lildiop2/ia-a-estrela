import java.util.ArrayList;
import java.util.Objects;

public class Cidade implements Comparable<Cidade>{

	String nome;
	Cidade anterior;
	ArrayList<Rota> adjacentes;
	int f, g, h = 0;

	public Cidade(String nome, int h) {

		this.nome = nome;
		this.adjacentes = new ArrayList<Rota>();
		this.f = g + f;
		this.h = h;
	}
	
	@Override
	public int compareTo(Cidade other) {
	    if (this.getF() > other.getF()) {
	        return 1;
	    } else if (this.getF() < other.getF()) {
	        return -1;
	    } else {
	        return 0;
	    }
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(nome, other.nome);
	}

	public Cidade(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getAnterior() {
		return anterior;
	}

	public void setAnterior(Cidade anterior) {
		this.anterior = anterior;
	}

	public ArrayList<Rota> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(ArrayList<Rota> adjacentes) {
		this.adjacentes = adjacentes;
	}

	public int getF() {
		return g+h;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cidade [nome=");
		builder.append(nome);
		builder.append(", anterior=");
//		builder.append(anterior);
		builder.append(", adjacentes=");
		builder.append(adjacentes);
		builder.append(", f=");
		builder.append(this.getF());
		builder.append(", g=");
		builder.append(g);
		builder.append(", h=");
		builder.append(h);
		builder.append("]");
		return builder.toString();
	}

}
