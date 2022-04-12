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
				
//				System.out.println(mapa.cidades.contains(new Cidade(valorComSplit[0])));
//				System.out.println(mapa.cidades.contains(new Cidade(valorComSplit[1])));
//				System.out.println(mapa.cidades.indexOf(new Cidade(valorComSplit[0])));
//				System.out.println(mapa.cidades.indexOf(new Cidade(valorComSplit[1])));
//				System.out.println(mapa.cidades.get(mapa.cidades.indexOf(new Cidade(valorComSplit[0]))));
//				System.out.println(mapa.cidades.get(mapa.cidades.indexOf(new Cidade(valorComSplit[1]))));
				

//				for (String s : valorComSplit) {
//					System.out.println(s);
//				}
//
//				System.out.println("=========================");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapa.caminhar(mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Arad"))), mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Bucareste"))));
		System.out.println(mapa.toString());
		
		mapa.imprimirCaminho( mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Bucareste"))));
//			distancia_reta = {
//					'arad': 366,
//					'bucharest': 0,
//					'craiova': 160,
//					'dobreta': 242,
//					'eforie': 161,
//					'fagaras': 176,
//					'giurgiu': 77,
//					'hirsova': 151,
//					'iasi': 226,
//					'lugoj': 244,
//					'mehadia': 241,
//					'neamt': 234,
//					'oradea': 380,
//					'pitesti': 100,
//					'rimnicu vikea': 193,
//					'sibiu': 253,
//					'timisoara': 329,
//					'urziceni': 80,
//					'vaslui': 199,
//					'zerind': 374
//			     }
	}

}
