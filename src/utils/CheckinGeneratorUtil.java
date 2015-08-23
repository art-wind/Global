package utils;

import geographics.CheckIn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CheckinGeneratorUtil {
	static final String BASEPATH = "./data/";
	public static void retrieveCheckInsDataByUID(HashMap<Integer, Integer>uidMapping,String srcName,String outputFilename){
		//checkins60M
		File gowallaFile = new File(BASEPATH+srcName);
		Scanner gowallaScanner = null;
		try {
			 gowallaScanner = new Scanner(gowallaFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String gowallaString="";
		int count = 0;
		String write = "";
		
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(BASEPATH+outputFilename), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (gowallaScanner.hasNext()) {
			gowallaString = (String) gowallaScanner.nextLine();
			if(uidMapping.get( new CheckIn(gowallaString).getUid()) != null){
				write += gowallaString +"\n";
				if(++count % 1000 == 0){
					try {
						writer.write(write);
						writer.flush();
						write = "";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		gowallaScanner.close();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void retrieveCheckInsDataByLoactionID(HashMap<Integer, Integer>placeMap,String srcname,String outputFilename){
		File gowallaFile = new File(BASEPATH+srcname);
		Scanner gowallaScanner = null;
		try {
			 gowallaScanner = new Scanner(gowallaFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String gowallaString="";
		int count = 0;
		String write = "";
		
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(BASEPATH+outputFilename), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (gowallaScanner.hasNext()) {
			gowallaString = (String) gowallaScanner.nextLine();
			if(placeMap.get( new CheckIn(gowallaString).getLocID()) != null){
				write += gowallaString +"\n";
				if(++count % 1000 == 0){
					try {
						writer.write(write);
						writer.flush();
						write = "";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		gowallaScanner.close();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
