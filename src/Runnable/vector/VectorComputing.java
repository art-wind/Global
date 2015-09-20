package Runnable.vector;

import geographics.CheckIn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Runnable.util.FileUtils;
import vector.VectorManager;

public class VectorComputing {
	public static final String VECTORPATH = "data/vectors/";
	public static void main(String[]args) throws IOException {
		String checkinFilename = "./data/realPlaces.txt";
		File checkinFile = new File(checkinFilename);
		Scanner ciScanner = new Scanner(checkinFile);
		String string ="";
		HashMap<Integer, Integer>locationMap = new HashMap<Integer,Integer>();
		while (ciScanner.hasNext()) {
			string = ciScanner.nextLine();
			int locationID = new CheckIn(string,0).getLocID();
			locationMap.put(locationID, 1);
		}
		ciScanner.close();



		VectorManager vm = new VectorManager();
		String number = "diff/Difference_1200000";
		String inputFileName = String.format("data/places/%s.txt", number);
		File inputFile = new File(inputFileName);
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		String resultFilenameOut = VECTORPATH+"result/" +"all"+"2.txt";
		FileWriter resultWriter = new FileWriter(new File(resultFilenameOut),true);
		while(scanner.hasNext()){
			line = scanner.nextLine();
			String id = line.split(" ")[0];
			if(locationMap.get(Integer.parseInt(id))!= null){
				
				String placeFileName = String.format("data/finPlaces/%s/%s.txt",number,id);
				
				try {
					File finPlaceFile = new File(placeFileName);
					String finString = FileUtils.readFile(finPlaceFile);
					double[] array = vm.getVectorFromQueryResult(finString);
					resultWriter.write(id);
					String tmp = "";
					for(double d:array){
						tmp += " "+d;
					}
					tmp += "\n";
					resultWriter.write(tmp);
					resultWriter.flush();
					
					FileWriter recordWriter = new FileWriter("data/vectors/records/"+id+".txt");
					recordWriter.write(finString);
					recordWriter.flush();
					recordWriter.close();
				} catch (Exception e) {
					//missingWriter.write(id +"\n");
					//missingWriter.flush();
					e.printStackTrace();
				}

			}

		}
		resultWriter.close();
//		missingWriter.close();
		scanner.close();
	}
}
