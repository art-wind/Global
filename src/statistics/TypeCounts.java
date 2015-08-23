package statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import vector.VectorManager;

public class TypeCounts {
	public static void main(String[]args) throws IOException {
		VectorManager vm = new VectorManager();
		int number = 3;
		String inputFileName = String.format("data/places/%d.txt", number);
		File inputFile = new File(inputFileName);
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		while(scanner.hasNext()){
			line = scanner.nextLine();
			String id = line.split(" ")[0];
			String placeFileName = String.format("data/finPlaces/%d/%s.txt",number,id);
			
			try {
				File finPlaceFile = new File(placeFileName);
				String finString = readFile(finPlaceFile);
				//System.out.println(finString);
				vm.getVectorFromQueryResult(finString);
//				System.out.println();
			} catch (Exception e) {
//				System.out.println("No such : "+placeFileName);
			}
			
		}
		/*
		for (int readFileNumber = 1000000   ; readFileNumber < 1300000; readFileNumber += 100000) {
			String catString = String.format("%s_%d","Difference",readFileNumber);
			String filename = "data/places/diff/"+catString+".txt";
			File file = new File(filename);
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String line;
			System.out.println(catString);
			HashMap<String, Integer>map = new HashMap<String, Integer>();
			File missingFile = new File("data/missing/"+catString+".txt");

			while(scanner.hasNext()){
				line = scanner.nextLine();
				String id = line.split(" ")[0];
				String placeFileName = "data/finPlaces/diff/"+catString+"/"+id+".txt";
				
				try {
					File finPlaceFile = new File(placeFileName);
					String finString = readFile(finPlaceFile);
				} catch (Exception e) {
					
					FileWriter writer = new FileWriter(missingFile,true);
					writer.append(line+"\n");
					writer.flush();
					writer.close();
					
				
				}
				
			}
			scanner.close();
			String mappingString="";
			for(String s:map.keySet()){
				int i = map.get(s);
				mappingString += (s+" : "+i+"\n");
			}
			mappingString = "";
		}*/
		
	}
	static String readFile(File f){
		String ret = "";
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "";
		while(s.hasNext()){
			line = s.nextLine();
			//line = "";
			if(!"".equals(line)){
				ret += line;
			} 
//			System.out.println("re "+ret);
			return ret;
		}
		s.close();
		return ret;
	}
	
	static <T> void printArray(double[] ds){
		System.out.print("[");
		for(double t:ds){
			System.out.print(t+",");
		}
		System.out.print("]");
		System.out.println();
	}
}
