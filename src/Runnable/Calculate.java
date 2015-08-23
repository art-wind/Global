package Runnable;
import geographics.CheckIn;
import geographics.Difference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Calculate {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// 300 slots,each of which is 50m wide
		int arraylength =  300;
		
		//6,000,000 check-in records
		int checkInLength = 6000000;
		
		//50 Meter
		int stepLength = 50;
		
		//2 hours
		int timeStepLength = 120;
		ArrayList < ArrayList<Difference> >distanceArray = new ArrayList < ArrayList<Difference> >();
		for(int i =0;i<arraylength;i++ ){
			distanceArray.add(new ArrayList<Difference>());
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

		CheckIn ci1 = new CheckIn(reader.readLine());
		CheckIn ci2 = new CheckIn(reader.readLine());	
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
		
		
		for(int i =0;i<checkInLength;i++ ){
			if(ci1.getUid() == ci2.getUid()){
				Difference diff = new Difference(ci1,ci2);
				int minites = diff.getMinutes();

				int slot = (minites / timeStepLength);
				if(slot >= arraylength){
					distanceArray.get(arraylength-1).add(diff);
				}
				else{
					distanceArray.get(slot).add(diff);
				}
			}

			ci1 = ci2;
			String tempString = reader.readLine();

			ci2 = new CheckIn(tempString);

			
			//			CheckIn ci = new CheckIn
		}
		reader.close();
		reader.close();
		int i = 0;	
		Iterator<ArrayList<Difference>> it = distanceArray.iterator();
		while(it.hasNext()){
			ArrayList<Difference>mutableArray = it.next();
			System.out.println(i*timeStepLength+"~"+ (i+1)*timeStepLength+" min Count: "+mutableArray.size()+"   Mean Distance: " +getMeanDistance(mutableArray));
			i++;
		}
		
		
		
		
		
		
		
		
	}
	private static double getMeanDistance(ArrayList<Difference>arr){
		Iterator<Difference> it = arr.iterator();
		int size=0;
		double totalDistance = 0;
		while(it.hasNext()){
			size++;
			totalDistance += it.next().getDistance();
		}
		
		return totalDistance/size;
	}
	private static double getMeanTime(ArrayList<Difference>arr){
		Iterator<Difference> it = arr.iterator();
		int size=arr.size();
		int totalMinutes = 0;
		double meanMinutes = 0;
		while(it.hasNext()){
			totalMinutes += it.next().getMinutes();
//			meanMinutes += it.next().minutes/size;
		}
		if (totalMinutes == 0 ){
			return 0;
		}
		return totalMinutes/size;
				//totalMinutes/size;
	}
	/*
	 * 
	 * 
	 * 
	 * ]
	 	CheckIn ci = new CheckIn("0 2010-10-19T23:55:27Z 30.2359091167 -97.7951395833 22847");
		CheckIn ci1 = new CheckIn("0 2010-10-18T22:17:43Z 30.2691029532 -97.7493953705 420315");
		//		CheckIn ci1 = new CheckIn("0 2010-10-18T22:17:43Z 30.2369091167 -97.7961395833 420315");
		CheckIn ci2 = new CheckIn("0 2010-10-17T23:42:03Z 30.2557309927 -97.7633857727 316637");
		CheckIn ci3 = new CheckIn("0 2010-10-17T19:26:05Z 30.2634181234 -97.7575966669 16516");
		CheckIn ci4 = new CheckIn("0 2010-10-16T18:50:42Z 30.2742918584 -97.7405226231 5535878");
		CheckIn ci5 = new CheckIn("0 2010-10-12T23:58:03Z 30.261599404 -97.7585805953 15372");
		CheckIn ci6 = new CheckIn("0 2010-10-12T22:02:11Z 30.2679095833 -97.7493124167 21714");
		ArrayList<CheckIn >cis  = new  ArrayList<CheckIn>();
		cis.add(ci6);
		cis.add(ci5);
		cis.add(ci4);
		cis.add(ci3);
		cis.add(ci2);
		cis.add(ci1);
		cis.add(ci);
		for(int i = 6 ;i>0;i--){
			for(int j = 6 ;j>0;j--){
				if(i > j){
					System.out.print(i + " "+j+" :");

					System.out.println( 
							getDistance(cis.get(i).geoLat,cis.get(i).geoLong,cis.get(j).geoLat,cis.get(j).geoLong));

				}
			}
		}
	 * 
	 * 
	 */
}
