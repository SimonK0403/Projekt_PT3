package main;

import java.util.HashMap;

public class Vertice {
	public String name;
	public HashMap<Vertice, Integer> adjNodes = new HashMap<Vertice, Integer>();
	
	//Used for algorithms
	public boolean wasVisited = false;
	public int distanceToFirst;
	
	public Vertice() {
		
	}
	
	public Vertice(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}