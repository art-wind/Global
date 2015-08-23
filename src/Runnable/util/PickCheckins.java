package Runnable.util;

import geographics.CheckIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

import utils.CheckinGeneratorUtil;
/*
 * 
 * This program aims to fetch all checkins from a place.txt file
 * Write back to a file
 * 
 * Author: Bill,Xu
 * 2015/8/22 11:40:00
 */
public class PickCheckins {
	static final String PLACE = "realPlaces.txt";
	static final String BASEPATH = "./data/";
	public static void main(String[]s){
		/*
		File placeFile = new File(BASEPATH + PLACE);
		Scanner placeScanner = null;
		try {
			placeScanner = new Scanner(placeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String placeLine = "";
		HashMap<Integer, Integer>placeMap = new HashMap<Integer,Integer>();
		while(placeScanner.hasNext()){
			placeLine = placeScanner.nextLine();
			int id = (new CheckIn(placeLine,1).getLocID());
			placeMap.put(id, 1);
		}
		placeScanner.close();
		//retrieveCheckInsDataByLoactionID(placeMap, placeLine);
		*
		*/
		
		File placeFile = new File(BASEPATH + "GoodPairs.txt");
		Scanner placeScanner = null;
		try {
			placeScanner = new Scanner(placeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int uid = 0;
		HashMap<Integer, Integer>uidMap = new HashMap<Integer,Integer>();
		while(placeScanner.hasNext()){
			uid = placeScanner.nextInt();
			uidMap.put(uid, 1);
		}
		
		placeFile = new File(BASEPATH + "BadPairs.txt");
		try {
			placeScanner = new Scanner(placeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(placeScanner.hasNext()){
			uid = placeScanner.nextInt();
			uidMap.put(uid, 1);
		}
		CheckinGeneratorUtil.retrieveCheckInsDataByUID(uidMap,"checkins60M.txt", "pairCheckins.txt");
		
		
		
	}
	
	
	
	
}
