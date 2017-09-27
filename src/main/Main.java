package main;

import java.util.Scanner;

public class Main {

	public class Vertice{
		
	}
	
	
	
	public class Grafo {
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numeroDeCasos = scanner.nextInt();
		int numeroVertices = 0;
		int numeroArestas = 0;
		String linhaDimensaoGrafo, linhaArestaGrafo;
		int arestaOrigem, arestaDestino, arestaPeso;
		for (int caso = 1; caso <= numeroDeCasos; caso++) {
			linhaDimensaoGrafo = scanner.nextLine();
			numeroVertices = scanner.nextInt();
			numeroArestas = scanner.nextInt();
			//Cria grafo
			
			for (int i = 0; i < numeroArestas; i++) {
				arestaOrigem = scanner.nextInt();
				arestaDestino = scanner.nextInt();
				arestaPeso = scanner.nextInt();
				
				//Carrega as arestas do grafo
				
			}
		}
	}
	
}
