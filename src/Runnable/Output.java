package Runnable;

import geographics.Place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Output {

	public static void main(String[] args) throws IOException {
		
		File file = new File("./data/places.txt");
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(file));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {

			}
		}

//		CheckIn ci = new CheckIn(reader.readLine());
//		Place p = new Place(ci);
		ArrayList<Place>array = new ArrayList<Place>();
		String tempString = "";
		while( (tempString = reader.readLine()) != null){
			Double lat = Double.parseDouble(tempString.split(" ") [1]);
			Double longtitude = Double.parseDouble(tempString.split(" ") [2]);
			int id = Integer.parseInt(tempString.split(" ")[0]);
			Place pl = new Place(longtitude, lat, id);
			array.add(pl);
		}
		reader.close();
		Collections.sort(array);
		File output = new File("./data/PlacesSplit2.txt");
		FileWriter writer = new FileWriter(output);
		String placeString = "";
		for(Place pl:array){
			placeString = (pl.getLocationID() +" "+ pl.getLatitude() +" "+pl.getLongtitude() + " "+ "\n");
			//pl.getCheckInSize() +
			
			//System.out.println(placeString);
			writer.write(placeString);
		}
		writer.write(placeString);
		writer.flush();
		writer.close();
	
	}

}
