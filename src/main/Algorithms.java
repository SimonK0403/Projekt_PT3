package main;

import java.util.Arrays;

public class Algorithms {
	
	private static int infinity = (int)(0.9*Integer.MAX_VALUE);
	
	//Prints a two-dimensional array to the console, used for testing
	public static void printMatrix(Object[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
	//Returns true if the edge is valid.
	//An edge is valid if both vertices are not the same
	//and if only one of them has been visited yet.
	private static boolean isValidEdge(int u, int v, boolean[] wasVisited) {
		if(u == v) {
			return false;
		} else if (!wasVisited[u] && !wasVisited[v]) {
			return false;
		} else if (wasVisited[u] && wasVisited[v]) {
			return false;
		}
		return true;
	}
	
	//Checks if all vertices in a graph have been connected to the MST
	private static boolean allConnected(boolean[] wasVisited) {
		for(boolean b : wasVisited) {
			if(!b) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a minimal spanning tree (MST).<br>
	 * Works only with non-directional graphs.
	 * 
	 * @param matrix The adjacency matrix for the graph
	 * @param maxDegree The maximum degree the vertices should have
	 * @return An adjacency matrix of the MST graph
	 */
	@SuppressWarnings("unused")
	public static Object[][] prim(Object[][] matrix, int maxDegree) {
		Object[][] mst = new Object[matrix.length][matrix.length]; //The MST as a matrix which will be returned
		int[] degree = new int[matrix.length];
		for(int i : degree) {
			i = 0;
		}
		
		boolean[] wasVisited = new boolean[matrix.length]; //Boolean array to keep track of which nodes have been visited
		wasVisited[0] = true;
		
		//Loops through the entire graph, looking for the shortest edge
		for(int x = 0; x < matrix.length-1; x++) { //A MST has n-1 edges, so the loop is traversed n-1 times (n=number of vertices)
			int minDistance = Integer.MAX_VALUE;
			int a=-1, b=-1; //Saves the location of the shortest target for the current node
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					if(((int)matrix[i][j] < minDistance) && ((int)matrix[i][j] != 0)) {
						if((degree[i] < maxDegree) && (degree[j] < maxDegree) && isValidEdge(i, j, wasVisited)) {
							minDistance = (int)matrix[i][j];
							a = i;
							b = j;
						}
					}
				}
			}
			
			
			if((a != -1) && (b != -1)) {
				wasVisited[a] = true;
				wasVisited[b] = true;
				mst[a][b] = minDistance;
				degree[a]++;
				degree[b]++;
			}
			
		}
		
		for(int i = 0; i < mst.length; i++) { //Replaces all null values with 0
			for(int j = 0; j < mst.length; j++) {
				if(mst[i][j] == null) {
					mst[i][j] = 0;
				}
			}
		}
		
		System.out.println(Arrays.toString(degree));
		if(!allConnected(wasVisited)) {
			System.out.println("Not all vertices could be connected.");
		}
		System.out.println();
		
		return mst;
	}
	
	/**
	 * Returns a minimal spanning tree (MST).<br>
	 * Works only with non-directional graphs.
	 * 
	 * @param matrix The adjacency matrix for the graph
	 * @return An adjacency matrix of the MST graph
	 */
	public static Object[][] prim(Object[][] matrix) {
		return prim(matrix, Integer.MAX_VALUE);
	}

	/**
	 * Calculates the minimum distance from the starting position to every other vertice.
	 * 
	 * @param matrix The adjacency matrix for the graph
	 * @param startPosition The vertice from which to calculate distances
	 * @return An array containing all distances
	 */
	public static int[] dijkstra(Object[][] matrix, int startPosition){
		boolean[] wasVisited = new boolean[matrix.length];
		wasVisited[startPosition] = true;
		
		int[] distances = new int[matrix.length];
		for(int i = 0; i < distances.length; i++) { //Initializes all distance values with infinite/MAX_INT
			distances[i] = infinity;
		}
		distances[startPosition] = 0; //Start node gets distance 0
		
		for(int x = 0; x < matrix.length; x++) { //Checks as many times as there are vertices in the graph
			for (int i = 0; i < matrix.length; i++) { //Iterates through each row
				int ownDistance = distances[i];
				for (int j = 0; j < matrix[i].length; j++) { //Checks the values in the row
					//If an edge exists and the current distance + distance to the new vertice is smaller than 
					//the saved distance for the vertice, the distance is updated
					if (((int)matrix[i][j] != 0) && ((ownDistance + (int)matrix[i][j]) < distances[j])) { 
						distances[j] = ownDistance + (int)matrix[i][j]; 
						wasVisited[i] = true;
						wasVisited[j] = true;
					}
				}
			}
		}
		System.out.println(Arrays.toString(wasVisited));
		return distances;
	}
	
	/**
	 * Calculates the minimum distance from the starting position to every other vertice. <br>
	 * Assumes the starting position to be 0.
	 * 
	 * @param matrix The adjacency matrix for the graph
	 * @return An array containing all distances
	 */
	public static int[] dijkstra(Object[][] matrix) {
		return dijkstra(matrix, 0);
	}
	
}
