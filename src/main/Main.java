package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public class Vertice{
		private int valor;
		private List<Vertice> adjacentes;
		
		public Vertice(int valor) {
			this.valor = valor;
			this.adjacentes = new ArrayList<>();
		}
		
		public void adicionarAresta(Vertice destino) {
			this.adjacentes.add(destino);
		}
	}
	
	
	
	public class Grafo {
		private List<Vertice> vertices;
		private int verticeInicial;
		private int [][] pesos;
		
		public Grafo(int numeroVertices, int verticeInicial) {
			this.vertices = new ArrayList<>();
			this.verticeInicial = verticeInicial;
			pesos = new int[numeroVertices][numeroVertices];
			for (int i = 0; i < numeroVertices; i++) {
				this.vertices.add(new Vertice(verticeInicial+i));
				for (int j = 0; j < numeroVertices; j++) {
					pesos[i][j] = 0;
				}
			}
		}
		
		public void adicionarAresta(int origem, int destino, int peso) {
			Vertice verticeOrigem = this.vertices.get(origem - verticeInicial);
			Vertice verticeDestino = this.vertices.get(destino - verticeInicial);
			verticeOrigem.adicionarAresta(verticeDestino);
			pesos[origem][destino] = peso;
		}
		
	}
	
	public static void main(String[] args) {
		Main mainObject = new Main();
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
			//Cria grafo usando mainObject por Grafo ser uma inner class
			Grafo grafo = mainObject.new Grafo(numeroVertices,0);
			
			for (int i = 0; i < numeroArestas; i++) {
				arestaOrigem = scanner.nextInt();
				arestaDestino = scanner.nextInt();
				arestaPeso = scanner.nextInt();
				//Carrega as arestas do grafo
				grafo.adicionarAresta(arestaOrigem, arestaDestino, arestaPeso);
				
			}
		}
	}
	
}
