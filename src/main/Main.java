package main;

public class Main {

	public static void main(String[] args) {
//		CityManager cm = new CityManager();
//		cm.run();
		
		//Der Graph aus der Uebung
		Object[][] graph = {
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
		
		Algorithms.printMatrix(Algorithms.prim(graph));
	}

}
