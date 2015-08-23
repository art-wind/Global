package Runnable.vector;

import geographics.CheckIn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import javax.sound.sampled.Line;
import javax.swing.text.TabableView;

import org.json.JSONArray;
import org.json.JSONObject;

import vector.VectorManager;

public class ComputeVector {
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
//		int number = 5;
		//Difference_1200000
//		8_2
		String number = "diff/Difference_1200000";
		String inputFileName = String.format("data/places/%s.txt", number);
		File inputFile = new File(inputFileName);
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		//String docFilenameOut = VECTORPATH+"document/" +number+"2.txt";
		//number
//		String missingFilenameOut = VECTORPATH+"missing/" +"all"+"2.txt";
		String resultFilenameOut = VECTORPATH+"result/" +"all"+"2.txt";
		//FileWriter docWriter = new FileWriter(new File(docFilenameOut),true);
//		FileWriter missingWriter = new FileWriter(new File(missingFilenameOut),true);
		FileWriter resultWriter = new FileWriter(new File(resultFilenameOut),true);
		while(scanner.hasNext()){
			line = scanner.nextLine();
			String id = line.split(" ")[0];
			if(locationMap.get(Integer.parseInt(id))!= null){
				
				String placeFileName = String.format("data/finPlaces/%s/%s.txt",number,id);
				
				try {
					File finPlaceFile = new File(placeFileName);
					String finString = readFile(finPlaceFile);
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
