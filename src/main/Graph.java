package main;

import java.util.LinkedList;

public class Graph {
	
	public LinkedList<Vertice> vertices = new LinkedList<Vertice>();
	
	public void addVertice(Vertice v) {
		vertices.add(v);
	}
	
	public void removeVertice(Vertice v) {
		vertices.remove(v);
		for(Vertice ver : vertices) {
			ver.adjNodes.remove(v);
		}
	}
	
	public void addEdge(Vertice from, Vertice to) {
		addEdge(from, to, 1);
	}
	
	public void addEdge(Vertice from, Vertice to, int length) {
		from.adjNodes.put(to, length);
	}
	
	public void removeEdge(Vertice from, Vertice to) {
		from.adjNodes.remove(to);
	}
	
	public void printGraph() {
		for(Vertice v : vertices) {
			System.out.println(v.name);
			System.out.println(v.adjNodes);
		}
	}
	
}
