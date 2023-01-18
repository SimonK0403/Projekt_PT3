package main;

import java.io.*;
import java.util.Scanner;

/**
 * A class that contains static methods for handling files
 */
public class FileManager {
	
	private static String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	//Returns the size of the matrix in the file
	private static int size(File file) {
		int i = 0;
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				i++;
				sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}
		return i-1;
	}
	
	/**
	 * Takes an upper triangular matrix and turns it into a lower triangular matrix.
	 * @param matrix The upper triangular matrix.
	 * @return The matrix as a lower triangular matrix, filling the upper triangle with <code>null</code> values.
	 */
	public static Object[][] toLowerTriangle(Object[][] matrix){
		for(int i = 0; i < matrix.length; i++) {
			for(int j = i+1; j < matrix.length; j++) {
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = null;
			}
		}
		return matrix;
	}
	
	/**
	 * Converts a matrix to a String-Array that also contains axis descriptions as letters.
	 * @param matrix The Object matrix.
	 * @return The matrix as String-Array.
	 */
	private static String[] matrixToString(Object[][] matrix) {
		String[] matrixLines = new String[matrix.length + 1];
		matrixLines[0] = "  ";
		
		for(int i = 0; i<matrix.length; i++) { //Fist line with letters only
			matrixLines[0] += letters[i] + " ";
		}
		
		for(int i = 0; i<matrix.length; i++) { //Other Lines with letter at start and then numbers
			matrixLines[i+1] = letters[i] + " ";
			for(int j = 0; j<matrix[i].length; j++) {
				if(matrix[i][j] != null) {
					matrixLines[i+1] += matrix[i][j] + " ";
				}
			}
		}
		return matrixLines;
	}
	
	/**
	 * Checks if there are still values that haven't been used/taken. Used for the fireworks stuff.
	 * @param wasVisited The array which saves which values have been taken.
	 * @return <code>true</code> if all values have been taken, <code>false</code> if not.
	 */
	private static boolean allTaken(boolean[] wasVisited) {
		for(boolean b : wasVisited) {
			if(!b) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the index of the smallest value that hasn't been used/taken already. Used for the fireworks stuff.
	 * @param array The array that contains all the distances.
	 * @param wasTaken The array that stores which values have been already taken/used.
	 * @return The index of the smallest value that has not yet been taken.
	 */
	private static int getSmallestNumberIndex(int[] array, boolean[] wasTaken) {
		int index = -1;
		int minimum = Integer.MAX_VALUE;
		for(int i=0; i < array.length; i++) {
			if((!wasTaken[i]) && (array[i] < minimum)) {
				index = i;
				minimum = array[i];
			}
		}
		return index;
	}
	
	/**
	 * Converts an array of integers to an array of strings where they are ordered by size and get their letter.
	 * Used for fireworks stuff
	 * @param array The int-Array returned by <code>Algorithms.dijkstra()</code>.
	 * @return The string-Array.
	 */
	public static String[] intArrayToStringArray(int[] array) {
		boolean[] isTaken = new boolean[array.length]; //The array that keeps track of which values of distanceArray have been put into stringArray
		String[] stringArray = new String[array.length]; //The array in which the distance to each vertice is saved with its letter
		
		//Takes the smallest available value from the distanceArray and adds it into the stringArray with its letter
		for(int i = 0; !allTaken(isTaken); i++) { //While there are values int distanceArray that haven't been put into the stringArray
			int index = getSmallestNumberIndex(array, isTaken);
			isTaken[index] = true;
			stringArray[i] = letters[index] + " " + array[index];
		}
		return stringArray;
	}
	
	/**
	 * Reads data from a file containing an adjacency matrix. <br>
	 * Also works with undirected matrices that have only the lower diagonal in the file.
	 * @param file The file from which the data is read
	 * @return The result matrix as a two-dimensional Object array
	 */
	public static Object[][] readFile(File file) {
		Object[][] matrix = new Object[size(file)][size(file)]; //The matrix that gets returned
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			for(int i = 0; scanner.hasNext(); i++) {
				String[] line = scanner.nextLine().split("\\s");
				for(int j = 1; j < line.length; j++) { //Starts at 1 to skip the letter at the first place
					matrix[i][j-1] = line[j];
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(matrix[0][matrix.length-1] == null) { //Checks if the graph is undirected by checking if the upper right value is null
			for(int i = 0; i < matrix.length; i++) { //Rows
				for(int j = 0; j < matrix[i].length; j++) { //Columns
					if(matrix[i][j] == null) { //If the value is null, it gets replaced by the value on the other side of the diagonal
						matrix[i][j] = matrix[j][i];
					}
				}
			}
		}
		return matrix;
	}
	
	/**
	 * Prints a matrix into the selected file.
	 * @param matrix The matrix to print.
	 * @param file The file to print the matrix to.
	 * @param upperTriangularMatrix Whether or not the input matrix is an upper triangular matrix. If true, it is flipped down.
	 */
	public static void printResultMatrix(Object[][] matrix, File file, boolean upperTriangularMatrix) {
		if(upperTriangularMatrix) {
			matrix = toLowerTriangle(matrix);
		}
		String[] matrixLines = FileManager.matrixToString(matrix);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getPath(), true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			for(String s : matrixLines) {
				writer.append(s);
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts an array of integers to Strings where the integers get their letter and are ordered by size, 
	 * then prints the list to the specified file. Used for the fireworks stuff.
	 * @param distanceArray The array containing the distances returned by <code>Algorithms.dijkstra()</code>.
	 * @param file The file to print the list to.
	 */
	public static void printResultList(int[] distanceArray, File file) {
		String[] listLines = intArrayToStringArray(distanceArray);
		printResultList(listLines, file);
	}
	
	public static void printResultList(String[] stringArray, File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file.getPath());
			BufferedWriter writer = new BufferedWriter(fw);
			
			for(String s : stringArray) {
				writer.append(s);
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a <code>.txt</code> file in the specified directory.
	 * @param directory The directory in which the file should be created. Example: <code>C:\\Users\\Username\\Desktop\\</code>.
	 * @param file The instance of <code>File</code> that is to be created on the system.
	 */
	public static void createFile(File file) {
		try {
			if(file.createNewFile()) {
				System.out.println("File created successfully");
			} else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method used for testing readFile
	public static void printMatrix(Object[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == null) {
					continue;
				}
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
