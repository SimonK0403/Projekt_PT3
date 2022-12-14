package main;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		CityManager cm = new CityManager();
//		cm.run();
		
		//Der Graph aus der Uebung
		Object[][] primGraph = {
//				  a,  b,  c,  d,  e,  f,  g,  h,  i
				{ 0,  8,  0,  4,  0,  0, 11,  0,  0},
				{ 8,  0,  7,  0,  2,  0,  0,  0,  4},
				{ 0,  7,  0,  0,  0,  9,  0,  0, 14},
				{ 4,  0,  0,  0,  0,  0,  8,  0,  0},
				{ 0,  2,  0,  0,  0,  0,  7,  6,  0},
				{ 0,  0,  9,  0,  0,  0,  0,  0, 10},
				{11,  0,  0,  8,  7,  0,  0,  1,  0},
				{ 0,  0,  0,  0,  6,  0,  1,  0,  2},
				{ 0,  4, 14,  0,  0, 10,  0,  2,  0}
		};
		
		Object[][] dijkstraGraph = {
//				  a,  b,  c,  d,  e,  f,  g,  h,  i
				{ 0, 10,  0,  0,  0,  0,  2,  0,  0},
				{ 0,  0,  0,  0,  0,  0,  0,  0,  4},
				{ 0,  2,  0,  0,  0,  0,  0,  0,  3},
				{ 4,  0,  0,  0,  0,  0,  8,  0,  0},
				{ 0,  2,  0,  0,  0,  0,  0,  6,  0},
				{ 0,  0,  4,  0,  0,  0,  0,  0,  0},
				{ 0,  0,  0,  0,  5,  0,  0,  1,  0},
				{ 0,  0,  0,  0,  0,  0,  0,  0, 12},
				{ 0,  0,  0,  0,  0,  1,  0,  0,  0}
		};
		
		Object[][] vorlDijkstraGraph = {
//				  a,  b,  c,  d,  e,  f
				{ 0,  1,  0,  1,  0,  0},
				{ 0,  0,  7,  6,  0,  3},
				{ 0,  0,  0,  2,  0,  9},
				{ 1,  0,  3,  0,  0,  0},
				{ 2,  0,  0,  0,  9,  0},
				{ 0,  0,  0,  0,  0,  0},
		};
		
//		Algorithms.printMatrix(Algorithms.prim(primGraph));
		System.out.println(Arrays.toString(Algorithms.dijkstra(dijkstraGraph)));
	}

}
