package entropy;

import geographics.CheckIn;
import geographics.Place;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class LocationEntropy {
	public static void main(String[]args) throws FileNotFoundException, IOException{
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("data/Gowalla.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "";
		
		/*
		HashMap<Place, HashMap<Integer, ArrayList<CheckIn>>>mapping = new HashMap<Place, HashMap<Integer, ArrayList<CheckIn>>>();
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			CheckIn ci = new CheckIn(line);
			Place p = new Place(ci);
			
			int uid = ci.getUid();
			try{
				HashMap<Integer, ArrayList<CheckIn>>i2CheckInsMap = mapping.get(p);
				//i2CheckInMap.put(uid, ci);
				try {
					ArrayList<CheckIn> cIns = i2CheckInsMap.get(uid);
					cIns.add(ci);
				} catch (Exception e) {
					// TODO: handle exception
					ArrayList<CheckIn> cIns = new ArrayList<CheckIn>();
					cIns.add(ci);
					i2CheckInsMap.put(uid, cIns);
				}
			}
			catch(Exception e){
				HashMap<Integer, ArrayList<CheckIn>>i2CheckInsMap = new HashMap<Integer, ArrayList<CheckIn>>();
				ArrayList<CheckIn> cIns = new ArrayList<CheckIn>();
				cIns.add(ci);
				i2CheckInsMap.put(uid, cIns);
				mapping.put(p, i2CheckInsMap);
			}
		}
		File file =  new File("data/objectOutput/locationEntropy.obj");
//		mapping
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(file));
		oos.writeObject(mapping);
		oos.close();
		//HashMap<Place, HashMap<Integer, ArrayList<CheckIn>>>mapping = (HashMap<Place, HashMap<Integer, ArrayList<CheckIn>>>)ObjectInputStream(new FileInputStream(file));
		for(Place p:mapping.keySet()){
			HashMap<Integer, ArrayList<CheckIn>> value = mapping.get(p);
			int checkInCounts = 0;
			for(int uid:value.keySet()){
				ArrayList<CheckIn> cis = value.get(uid);
				int size = cis.size();
				checkInCounts += size;
			}
			
			double entropy = 0;
			
			for(int uid:value.keySet()){
				int size = value.get(uid).size();
				double proportion = (double)size/checkInCounts;
				entropy += (proportion) * ( Math.log(proportion) / Math.log(2.0));
			}
			System.out.println("Place: "+p.getLocationID() + "  Entropy: "+entropy);
			
		}*/
		
		
		HashMap<Integer, HashMap<Integer, Integer>>mapping = new HashMap<Integer, HashMap<Integer, Integer>>();
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			CheckIn ci = new CheckIn(line);
			Place place = new Place(ci);
			int p = place.getLocationID();
			int uid = ci.getUid();
			try{
				HashMap<Integer, Integer>i2CheckInsMap = mapping.get(p);
				//i2CheckInMap.put(uid, ci);
				try {
					int cIns = i2CheckInsMap.get(uid);
					cIns++;
					i2CheckInsMap.put(uid, cIns);
					//cIns.add(ci);
				} catch (Exception e) {
					// TODO: handle exception
					i2CheckInsMap.put(uid, 1);
				}
			}
			catch(Exception e){
				HashMap<Integer, Integer>i2CheckInsMap = new HashMap<Integer, Integer>();
				i2CheckInsMap.put(uid, 1);
				mapping.put(p, i2CheckInsMap);
			}
		}
		File file =  new File("data/objectOutput/locationEntropy.obj");
//		mapping
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(file));
		oos.writeObject(mapping);
		oos.close();
		HashMap<Integer, Double>entropyMap = new HashMap<Integer,Double>();
		//HashMap<Integer, HashMap<Integer, Integer>>mapping = (HashMap<Integer, HashMap<Integer, Integer>>)ObjectInputStream(new FileInputStream(file));
		for(int p:mapping.keySet()){
			HashMap<Integer, Integer> value = mapping.get(p);
			int checkInCounts = 0;
			for(int uid:value.keySet()){
				int size = value.get(uid);
				checkInCounts += size;
			}
			
			double entropy = 0;
			
			for(int uid:value.keySet()){
				int size = value.get(uid);
				double proportion = (double)size/checkInCounts;
				entropy += (proportion) * ( Math.log(proportion) / Math.log(2.0));
			}
			System.out.println("Place: "+p + "  Entropy: "+entropy + "Size: "+checkInCounts);
			entropyMap.put(p, entropy);
		}
		File fileMap =  new File("data/objectOutput/locationEntropyMapping.obj");
//		mapping
		ObjectOutputStream oosE = new ObjectOutputStream( new FileOutputStream(fileMap));
		oosE.writeObject(entropyMap);
		oosE.close();
		
	}
}
