package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public class Vertice {
		private int valor;
		private List<Vertice> adjacentes;

		private int distanciaRaiz;
		private Vertice pai;

		public Vertice(int valor) {
			this.valor = valor;
			this.adjacentes = new ArrayList<>();
			this.distanciaRaiz = 0;
		}

		public int getValor() {
			return valor;
		}

		public List<Vertice> getAdjacentes() {
			return adjacentes;
		}

		public int getDistanciaRaiz() {
			return distanciaRaiz;
		}

		public void setDistanciaRaiz(int distanciaRaiz) {
			this.distanciaRaiz = distanciaRaiz;
		}

		public void adicionarAresta(Vertice destino) {
			this.adjacentes.add(destino);
		}

		public Vertice getPai() {
			return pai;
		}

		public void setPai(Vertice pai) {
			this.pai = pai;
		}

	}

	public class Grafo {
		private List<Vertice> vertices;
		private int verticeInicial;
		private int[][] pesos;

		public Grafo(int numeroVertices, int verticeInicial) {
			this.vertices = new ArrayList<>();
			this.verticeInicial = verticeInicial;
			pesos = new int[numeroVertices][numeroVertices];
			for (int i = 0; i < numeroVertices; i++) {
				this.vertices.add(new Vertice(verticeInicial + i));
				for (int j = 0; j < numeroVertices; j++) {
					pesos[i][j] = 0;
				}
			}
		}

		public List<Vertice> getVertices() {
			return vertices;
		}

		public void setVertices(List<Vertice> vertices) {
			this.vertices = vertices;
		}

		public int getPesoAresta(Vertice origem, Vertice destino) {
			return pesos[origem.getValor() - verticeInicial][destino.getValor()
					- verticeInicial];
		}

		public void inicializarVertices() {
			for (Vertice vertice : this.vertices) {
				// Distancia infinita
				vertice.setDistanciaRaiz(Integer.MAX_VALUE);
				vertice.setPai(null);
			}
		}

		public void adicionarAresta(int origem, int destino, int peso) {
			Vertice verticeOrigem = this.vertices.get(origem - verticeInicial);
			Vertice verticeDestino = this.vertices
					.get(destino - verticeInicial);
			verticeOrigem.adicionarAresta(verticeDestino);
			pesos[origem][destino] = peso;
		}

		public boolean podeRelaxarAresta(Vertice origem, Vertice destino) {
			return destino.getDistanciaRaiz() > origem.getDistanciaRaiz()
					+ getPesoAresta(origem, destino);
		}

		public void relaxarAresta(Vertice origem, Vertice destino) {
			if (podeRelaxarAresta(origem, destino)) {
				destino.setDistanciaRaiz(origem.getDistanciaRaiz()
						+ getPesoAresta(origem, destino));
				destino.setPai(origem);
			}
		}

		public boolean possuiCicloNegativo(Vertice verticeInicial) {
			inicializarVertices();
			verticeInicial.setDistanciaRaiz(0);
			// Repita |V| - 1 vezes
			for (int i = 0; i < this.vertices.size() - 1; i++) {
				for (Vertice vertice : this.vertices) {
					for (Vertice adj : vertice.getAdjacentes()) {
						relaxarAresta(vertice, adj);
					}
				}
			}
			for (Vertice vertice : this.vertices) {
				for (Vertice adj : vertice.getAdjacentes()) {
					if (podeRelaxarAresta(vertice, adj)) {
						return true;
					}
				}
			}
			return false;
		}

	}

	public static void main(String[] args) {
		Main mainObject = new Main();
		Scanner scanner = new Scanner(System.in);
		int numeroDeCasos = scanner.nextInt();
		int numeroVertices = 0;
		int numeroArestas = 0;
		// String linhaDimensaoGrafo, linhaArestaGrafo;
		int arestaOrigem, arestaDestino, arestaPeso;
		for (int caso = 1; caso <= numeroDeCasos; caso++) {
			// linhaDimensaoGrafo = scanner.nextLine();
			numeroVertices = scanner.nextInt();
			numeroArestas = scanner.nextInt();
			// Cria grafo usando mainObject por Grafo ser uma inner class
			Grafo grafo = mainObject.new Grafo(numeroVertices, 0);

			for (int i = 0; i < numeroArestas; i++) {
				arestaOrigem = scanner.nextInt();
				arestaDestino = scanner.nextInt();
				arestaPeso = scanner.nextInt();
				// Carrega as arestas do grafo
				grafo.adicionarAresta(arestaOrigem, arestaDestino, arestaPeso);
			}
			// A origem eh do vertice 0, se tiver ciclo negativo, eh possivel
			// ver o big bang
			System.out.println(grafo.possuiCicloNegativo(grafo.getVertices()
					.get(0)) ? "possible" : "not possible");
		}
	}

}
