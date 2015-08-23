package Runnable.recovery;

import geographics.CheckIn;
import geographics.Place;

import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class TransferFile {
	public static void main(String[]args){
		File places = new File("data/realPlaces.txt");
		HashMap<Place, String>p2iMap = new HashMap<Place,String>();
		Scanner placesScanner = null;
		try {
			placesScanner = new Scanner(places);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(placesScanner.hasNext()){
			String line = placesScanner.nextLine();
			//System.out.println(line);
			CheckIn checkIn = new CheckIn(line,0);
			Place p = new Place(checkIn);
			p2iMap.put(p, line);
		}
		placesScanner.close();
		System.out.println("Map done.");
		for(Place p:p2iMap.keySet()){
			int locationID = p.getLocationID();
			File recordFile = new File("data/vectors/records/"+locationID+".txt");
			System.out.println(locationID);
			if(!recordFile.exists()){
				try {
					File finAllFile = new File("data/finPlaces/all/"+locationID+".txt");
					Scanner scanner = new Scanner(finAllFile);
					String result = "";
					while(scanner.hasNext()){
						result += scanner.nextLine();
					}
					scanner.close();
					FileWriter fileWriter = new FileWriter(recordFile);
					fileWriter.write(result);
					fileWriter.close();
				}
				catch (Exception f2) {
					File missingFile = new File("data/vectors/missing/all3.txt");
					System.out.println();
					try {
						FileWriter writer = new FileWriter(missingFile,true);
						writer.write(p2iMap.get(p)+"\n");
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			// TODO: handle exception
			
		}
	}
}
