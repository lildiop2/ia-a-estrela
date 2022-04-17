import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class principal {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		Map<String, Integer> distanciaReta = new HashMap<String, Integer>();

		distanciaReta.put("arad", 366);
		distanciaReta.put("bucharest", 0);
		distanciaReta.put("craiova", 160);
		distanciaReta.put("dobreta", 242);
		distanciaReta.put("eforie", 161);
		distanciaReta.put("fagaras", 176);
		distanciaReta.put("giurgiu", 77);
		distanciaReta.put("hirsova", 151);
		distanciaReta.put("iasi", 226);
		distanciaReta.put("lugoj", 244);
		distanciaReta.put("mehadia", 241);
		distanciaReta.put("neamt", 234);
		distanciaReta.put("oradea", 380);
		distanciaReta.put("pitesti", 100);
		distanciaReta.put("rimnicu vikea", 193);
		distanciaReta.put("sibiu", 253);
		distanciaReta.put("timisoara", 329);
		distanciaReta.put("urziceni", 80);
		distanciaReta.put("vaslui", 199);
		distanciaReta.put("zerind", 374);

		Mapa mapa = new Mapa();

		Scanner scn;
		try {
			scn = new Scanner(new FileReader(
					"C:\\Users\\Abdul Kevin Alexis\\eclipse-workspace\\ia-a-estrela\\src\\Heuristica.txt"));

//			System.out.println("=====Heuristica=====");
			while (scn.hasNextLine()) {
				String line = scn.nextLine();
				String[] valorComSplit = line.split(";", 2);

				mapa.addCidade(valorComSplit[0], Integer.parseInt(valorComSplit[1]));
//
//				for (String s : valorComSplit) {
//					System.out.println(s);
//				}
//
//				System.out.println("=========================");
			}

			scn = new Scanner(
					new FileReader("C:\\Users\\Abdul Kevin Alexis\\eclipse-workspace\\ia-a-estrela\\src\\Grafo.txt"));

//			System.out.println("=====Rotas=====");
			while (scn.hasNextLine()) {
				String line = scn.nextLine();
				String[] valorComSplit = line.split(";", 3);

				if (mapa.cidades.contains(new Cidade(valorComSplit[0])) && mapa.cidades.contains(new Cidade(valorComSplit[1]))) {
					Cidade origem=mapa.cidades.get(mapa.cidades.indexOf(new Cidade(valorComSplit[0])));
					Cidade destino=mapa.cidades.get(mapa.cidades.indexOf(new Cidade(valorComSplit[1])));
					mapa.addRota(origem,destino, Integer.parseInt(valorComSplit[2]));	
					

				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapa.caminhar(mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Arad"))), mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Bucareste"))));
		//System.out.println(mapa.caminho);
		
		for (Cidade cidade : mapa.caminho) {
			System.out.print(cidade.nome+"==>");
		}
		
//		Cidade atual=mapa.caminho.remove(0);
//		Cidade proxima=new Cidade(null);
//		System.out.print(atual.nome+"==>");
//		while (!mapa.caminho.isEmpty()) {
//			proxima=mapa.caminho.remove(0);
//			if(mapa.isLink(atual,proxima)||mapa.isLink(atual,proxima)) {
//				System.out.print(proxima.nome+"==>");
//				
//			}else {
//			//proxima=mapa.caminho.remove(0);
//				
//			}
//			atual=proxima;
//			//Cidade aux=atual;
//			
//			
//			
//
//			
//			
//		}
		
//	mapa.imprimirCaminho( mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Bucareste"))));

	}

}
