package Runnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import geographics.*;
public class OutputForPlaces {

	public static void main(String[] args) throws IOException {
		
		HashMap<Place, ArrayList<CheckIn>> mapFromPlacetoCheckins =new HashMap<Place, ArrayList<CheckIn>>();
//		、、realPlaces
		File file = new File("./data/realPlaces.txt");
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
		
		String tempString = "";
		while( (tempString = reader.readLine()) != null){
			CheckIn ci = new CheckIn(tempString,0);
			Place p = new Place(ci);
			
			ArrayList<CheckIn> checkIns = mapFromPlacetoCheckins.get(p);
			if (checkIns != null){
				checkIns.add(ci);
			}
			else{
				checkIns = new ArrayList<CheckIn>();
				checkIns.add(ci);
				mapFromPlacetoCheckins.put(p, checkIns);
			}
		}
			
		System.out.println(mapFromPlacetoCheckins.keySet().size());
		reader.close();
		/*
		reader = new BufferedReader(new FileReader(new File("data/vectors/result/all.txt")));
		String uid = "";
		while( (uid = reader.readLine()) != null){
			int locationID = Integer.parseInt(uid.split(" ")[0]);
			mapFromPlacetoCheckins.remove( new Place(0.0,0.0,locationID) );
		}
		reader.close();
		*/
		
		
		
		ArrayList<Place>array = new ArrayList<Place>();
		for(Place pl:mapFromPlacetoCheckins.keySet()){
			ArrayList<CheckIn>tmp =  mapFromPlacetoCheckins.get(pl);
			if( tmp == null || tmp.size() == 0) {
				mapFromPlacetoCheckins.remove(pl);
				System.out.println("Error");
				//pl.setSize(0);
				//array.add( pl);
				continue;
			}
			//System.out.println(pl.getLocationID());
			pl.setSize(tmp.size());
			array.add( pl);
		}
		Collections.sort(array);
		System.out.println("Size: "+array.size());
		
		
		File output = new File("./data/missing123.txt");
		FileWriter writer = new FileWriter(output);
		String placeString = "";
		for(Place pl:array){
			placeString = (pl.getLocationID() +" "+ pl.getLatitude() +" "+pl.getLongtitude() + " "+ pl.getCheckInSize()+ "\n");
			//pl.getCheckInSize() +
			
			//System.out.println(placeString);
			writer.write(placeString);
			writer.flush();
		}
		
		writer.close();
		System.out.println("Success");
		
	}

}
