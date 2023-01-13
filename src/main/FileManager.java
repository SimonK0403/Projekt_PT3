package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that contains static methods for handling files
 * 
 * @author Simon
 *
 */
public class FileManager {

	// Returns the size of the matrix in the file
	private static int size(File file) {
		int i = 0;
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				i++;
				sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {

		}
		return i - 1;
	}

	/**
	 * Reads data from a file containing an adjacency matrix. <br>
	 * Also works with undirectional matrices that have only the lower diagonal in
	 * the file.
	 * 
	 * @param file The file from which the data is read
	 * @return The result matrix as a two-dimensional Object array
	 */
	public static Object[][] readFile(File file) {
		Object[][] matrix = new Object[size(file)][size(file)]; // The matrix that gets returned
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			for (int i = 0; scanner.hasNext(); i++) {
				String[] line = scanner.nextLine().split("\\s");
				for (String w : line) {
					System.out.println(w);
				}
				System.out.println(" ");
				for (int j = 1; j < line.length; j++) { // Starts at 1 to skip the letter at the first place

					matrix[i][j - 1] = line[j];
				}

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (matrix[0][matrix.length - 1] == null) { // Checks if the graph is undirectional by checking if the upper
													// right value is null
			for (int i = 0; i < matrix.length; i++) { // Rows
				for (int j = 0; j < matrix[i].length; j++) { // Columns
					if (matrix[i][j] == null) { // If the value is null, it gets replaced by the value on the other side
												// of the diagonal
						matrix[i][j] = matrix[j][i];
					}
				}
			}
		}
		return matrix;
	}

	public static void printResult(Object[][] matrix, File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file.getPath(), true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method used for testing readFile, can be deleted later
	public static void printMatrix(Object[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == null) {
					continue;
				}
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
