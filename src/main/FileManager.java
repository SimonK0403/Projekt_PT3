package main;

import java.io.*;
import java.util.Scanner;

public class FileManager {
	
	public static void readFile(File file) {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void printResult(File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file.getPath(), true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
