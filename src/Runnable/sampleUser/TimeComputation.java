package Runnable.sampleUser;


import geographics.CheckIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class TimeComputation {
	public static void main(String[]args) throws IOException{
		System.out.println("sadsad");
		File sampleUser = new File("data/sampleUser/sample_user.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(sampleUser);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, ArrayList<CheckIn>>map = new HashMap<Integer, ArrayList<CheckIn>>();
		String line = "";
		//System.out.println(scanner.next());
		//scanner.next();
		while(scanner.hasNext()){
			line = scanner.nextLine();
			int uid = Integer.parseInt(line);
			
			if(map.get(uid)!= null){
				System.out.println("C: "+uid);
			}
			else{
				ArrayList<CheckIn> tmp = new ArrayList<CheckIn>();
				map.put(uid,tmp);
			}
			
		}
		scanner.close();
		System.out.println(map.keySet().size());
		System.out.println("Mapping Dones");
		
		File gowallaFile = new File("./data/Gowalla.txt");
		try {
			scanner = new Scanner(gowallaFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNext()){
			line = scanner.nextLine();
//			CheckIn c = new CheckIn(line);
			int uid = Integer.parseInt(line.split("	")[0]);
			try {
				ArrayList<CheckIn> tmp = map.get(uid);
				CheckIn c = new CheckIn(line);
				tmp.add(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		scanner.close();
		System.out.println("Gowalla Down");
		File outputFile = new File("data/sampleUser/output2.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile,false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int key:map.keySet()){
			String head = String.format("uid: %d\n", key);
			try {
				writer.append(head);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<CheckIn> cis = map.get(key);
			HashMap<Integer, ArrayList<CheckIn>>place2Cis = new HashMap<Integer, ArrayList<CheckIn>>();
			for(CheckIn ci:cis){
				int locationID = ci.getLocID();
				try {
					ArrayList<CheckIn> desiredCheckIns = place2Cis.get(locationID);
					desiredCheckIns.add(ci);
				} catch (Exception e) {
					// TODO: handle exception
					ArrayList<CheckIn> desiredCheckIns = new ArrayList<CheckIn>();
					desiredCheckIns.add(ci);
					place2Cis.put(locationID, desiredCheckIns);
				}
			}
			ArrayList<ArrayList<CheckIn>>aacins = new ArrayList<ArrayList<CheckIn>>();
			for(int locationID:place2Cis.keySet()){
				ArrayList<CheckIn> desiredCheckIns = place2Cis.get(locationID);
				insertArray(aacins,desiredCheckIns,3);
				
			}
			for(ArrayList<CheckIn> cins:aacins){
				
				for(CheckIn ci:cins){
					
					String string = String.format("%d %s\n", ci.getLocID(),ci.getTs().toString());
					writer.write(string);
				}
				
			}
		}
		writer.close();
		 
	}
	static void insertArray(ArrayList<ArrayList<CheckIn>>aacins,ArrayList<CheckIn> desiredCheckIns,int size){
		int aaSize = aacins.size();
		int desireSize = desiredCheckIns.size();
		if( aaSize > 0 ){
			for (int i = 0; i < aaSize; i++) {
				
				int singleSize = aacins.get(i).size();
				if(desireSize <= singleSize ){
					aacins.add(i, desiredCheckIns);
					break;
				}
				if( i == aaSize - 1){
					aacins.add(aaSize, desiredCheckIns);
				}
			}
			if(aaSize == size){
				aacins.remove(0);
			}
		}
		else{
			aacins.add(desiredCheckIns);
		}
		
	}
}
