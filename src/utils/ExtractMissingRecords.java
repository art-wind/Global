package utils;

import geographics.CheckIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ExtractMissingRecords {
	public static void extractMissingPlacesFromDir(String srcFilename,String tgtdir,String outputDir){
		File file = new File(srcFilename);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(outputDir),false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			
			String id = line.split(" ")[4];
			System.out.println(tgtdir + id+".txt");
			File tgtFile = new File(tgtdir + id+".txt");
			if(!tgtFile.exists()){
				try {
					writer.write(line+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}
}
