import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class principal {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		
		String heuristica=null;
		String grafo=null;
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
		
		if (args.length == 4) {
			//inserir o primeiro e o segundo argumento
			heuristica = args[0];
			grafo=args[1];
		} else {
			System.err.println("Usar: ./aestrela [Heuristica] [Grafo] [Origem] [Destino]");
			System.exit(0);
		}
		try {
//			scn = new Scanner(new FileReader(
//					"C:\\Users\\Abdul Kevin Alexis\\eclipse-workspace\\ia-a-estrela\\src\\Heuristica.txt"));
			
			scn = new Scanner(new FileReader(heuristica));

			while (scn.hasNextLine()) {
				String line = scn.nextLine();
				String[] valorComSplit = line.split(";", 2);

				mapa.addCidade(valorComSplit[0], Integer.parseInt(valorComSplit[1]));
			}

//			scn = new Scanner(
//					new FileReader("C:\\Users\\Abdul Kevin Alexis\\eclipse-workspace\\ia-a-estrela\\src\\Grafo.txt"));
			scn = new Scanner(new FileReader(grafo));
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
		//inserir o terceiro e o quarto argumento
		mapa.caminhar(mapa.cidades.get(mapa.cidades.indexOf(new Cidade(args[2]))), mapa.cidades.get(mapa.cidades.indexOf(new Cidade(args[3]))));
		
//		mapa.caminhar(mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Arad"))), mapa.cidades.get(mapa.cidades.indexOf(new Cidade("Bucareste"))));
		
		
		if (mapa.caminho.contains(new Cidade("Fagaras"))) {
			mapa.caminho.remove(new Cidade("Fagaras"));
		}
		
		System.out.print("Caminho:");
		for (Cidade cidade : mapa.caminho) {
			System.out.print(cidade.nome+"==>");
		}
		
	}

}
