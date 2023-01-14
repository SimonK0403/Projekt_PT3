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
	public static String[] matrixToString(Object[][] matrix) {
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
	 * Creates a <code>.txt</code> file in the specified directory.
	 * @param directory The directory in which the file should be created. Example: <code>C:\\Users\\Username\\Desktop\\</code>.
	 * @param name The name of the file. Automatically appends <code>.txt</code> as file ending.
	 */
	public static void createFile(String directory, String name) {
		File file = new File(directory + name + ".txt");
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
