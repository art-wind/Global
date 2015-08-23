package Runnable;
import geographics.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.Doc;

public class Frequency {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// # of observed Users 
		int arraylength =  1000;
		
		//Threshold of check-ins 
		int threshold = 1;
		
		//6,000,000 check-in records
		int checkInLength = 6000000;
		
		//50 Meter
		int stepLength = 50;
		
		//2 hours
		int timeStepLength = 120;
		ArrayList<HashMap<Integer,ArrayList<CheckIn> >>userFrequencyList = new ArrayList <HashMap<Integer,ArrayList<CheckIn>>>();
//		ArrayList < ArrayList<Difference> >distanceArray = new ArrayList < ArrayList<Difference> >();
		for(int i =0;i<arraylength;i++ ){
			userFrequencyList.add(new HashMap<Integer,ArrayList<CheckIn>>());
		}

		File file = new File("./data/Gowalla.txt");
//		File file = new File("./data/Geo.txt");
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(file));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				
			}
		}
		int docedUser = 0;
		int handlingID = 0;
		while(docedUser<arraylength-1){
			CheckIn ci = new CheckIn(reader.readLine());
			int uid = ci.getUid();
			int locID = ci.getLocID();
//			boolean isNewLocation = false;
			if(uid == handlingID){
				// Mapping from location ID to checkIns
				
			}
			else{
				handlingID = uid;
				docedUser++;
//				isNewLocation = true;
			}
			HashMap<Integer, ArrayList<CheckIn>> mapping = userFrequencyList.get(docedUser);
			if(mapping.get(locID) == null){
//				isNewLocation = true;
				ArrayList<CheckIn> tmpCheckIns = new ArrayList<CheckIn>();
				tmpCheckIns.add(ci);
				mapping.put(locID, tmpCheckIns);
			}
			else{
				ArrayList<CheckIn> tmpCheckIns = mapping.get(locID);
				tmpCheckIns.add(ci);
//				mapping.put(locID, tmpCheckIns);
			}
		}
		
		Iterator<HashMap<Integer,ArrayList<CheckIn>>> itMapping = userFrequencyList.iterator();
		while(itMapping.hasNext()){
			HashMap<Integer,ArrayList<CheckIn>> tmpHashMap = itMapping.next();
		    tmpHashMap.keySet();
		    int multipleTimes = 0;
	    	int sumUPTimes = 0;
	    	int uid = 0;
		    for(int t:tmpHashMap.keySet()){
		    	
		    	ArrayList<CheckIn> resultCheckIns = tmpHashMap.get(t);
		    	Iterator<CheckIn>checkInIterator = resultCheckIns.iterator();
		    	sumUPTimes += resultCheckIns.size();
		    	if (resultCheckIns.size() > threshold){
		    		multipleTimes += resultCheckIns.size();
//		    		System.out.println(t);
//		    		while(checkInIterator.hasNext()){
//			    		CheckIn checkIn = checkInIterator.next();
//				    	
//			    		System.out.print(checkIn.uid+" / ");
//			    	}
//			    	System.out.println();
		    	}
		    	
		    }
		    System.out.println("All: "+ sumUPTimes+" Percentage: "+(multipleTimes/(double)sumUPTimes));
		}
		
//		for
		
//		CheckIn ci2 = new CheckIn(reader.readLine());	
//		for(int i =0;i<checkInLength;i++ ){
//			if(ci1.uid == ci2.uid){
//				Difference diff = new Difference(ci1,ci2);
//				double dis  = diff.distance;
////				System.out.println(ci1.uid +" : "+dis);
//				int slot = ((int) dis / stepLength);
//				if(slot >= arraylength){
//					distanceArray.get(arraylength-1).add(diff);
//				}
//				else{
//					distanceArray.get(slot).add(diff);
//				}
//			}
//
//			ci1 = ci2;
//			String tempString = reader.readLine();
//
//			ci2 = new CheckIn(tempString);
//		}
//		reader.close();
//		int i = 0;	
//		Iterator<ArrayList<Difference>> it = distanceArray.iterator();
//		while(it.hasNext()){
//			ArrayList<Difference>mutableArray = it.next();
//			System.out.println(i*stepLength+"~"+ (i+1)*stepLength+"m Count: "+mutableArray.size()+"   Mean Time: " +getMeanTime(mutableArray));
//			i++;
//		}
	}

}
