package main;

import java.util.Arrays;

public class Algorithms {
	
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
	 * Returns a minimal spanning tree (MST)
	 * @param matrix The adjacency matrix for the graph
	 * @return An adjacency matrix of the MST graph
	 */
	public static Object[][] prim(Object[][] matrix) {
		Object[][] mst = new Object[matrix.length][matrix.length]; //The MST as a matrix which will be returned
		
		boolean[] wasVisited = new boolean[matrix.length]; //Boolean array to keep track of which nodes have been visited
		wasVisited[0] = true;
		
		//Loops through the entire graph, looking for the shortest edge
		while(!allConnected(wasVisited)) { //While there are unconnected nodes
			int minDistance = Integer.MAX_VALUE;
			int a=-1, b=-1; //Saves the location of the shortest target for the current node
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					if(((int)matrix[i][j] < minDistance) && ((int)matrix[i][j] != 0)) {
						if(isValidEdge(i, j, wasVisited)) {
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
			}
			
		}
		
		for(int i = 0; i < mst.length; i++) { //Replaces all null values with 0
			for(int j = 0; j < mst.length; j++) {
				if(mst[i][j] == null) {
					mst[i][j] = 0;
				}
			}
		}
		
		return mst;
	}
	
	public static int[] dijkstra(int[][] matrix){
		boolean[] wasVisited = new boolean[matrix.length];
		wasVisited[0] = true;
		
		int[] distances = new int[matrix.length];
		for(int i = 0; i < distances.length; i++) { //Initializes all distance values with infinite/MAX_INT
			distances[i] = (int) (0.5*Integer.MAX_VALUE);
		}
		distances[0] = 0; //Start node gets distance 0
		
//		while(!allConnected(wasVisited)) {
			
			for (int i = 0; i < matrix.length; i++) {
				int ownDistance = distances[i];
				for (int j = 0; j < matrix[i].length; j++) {
					if ((matrix[i][j] != 0) && ((ownDistance + matrix[i][j]) < distances[j])) {
						System.out.println(matrix[i][j]);
						distances[j] = ownDistance + matrix[i][j];
					}
				}
			}
			
//		}
		
		return distances;
	}
	
}
