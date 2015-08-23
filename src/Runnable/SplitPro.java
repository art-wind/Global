package Runnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class SplitPro {
	public static void main(String[]args) throws IOException {
//		File placesFile = new File("data/Places.txt");
		File placesFile = new File("data/places/7.txt");
		Scanner scanner = new Scanner(placesFile);
		int fileNum = 0;
		
		int lineNum = 0;
		int bound = 10000;
		String lines = "";
		while(scanner.hasNext() || lineNum < bound){
			String tmp = scanner.nextLine();
			lines += tmp;
			lines += "\n";
			lineNum++;
			System.out.println("L: "+lineNum);
			if(lineNum == bound){
//				FileWriter writer = new FileWriter(new File("data/places/7_"+ fileNum+ ".txt"));
				FileWriter writer = new FileWriter(new File("data/places/"+ fileNum+ ".txt"));
				lineNum = 0;
				fileNum++;
				writer.write(lines);
				writer.flush();
				lines = "";
			}
		}
	}
}
