package entropy;

import geographics.CheckIn;
import geographics.Place;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Diversity {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("data/Gowalla.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "";
		HashMap<Integer, ArrayList<CheckIn>>mapping = new HashMap<Integer, ArrayList<CheckIn>>();
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			CheckIn ci = new CheckIn(line);
			int uid = ci.getUid();
			//System.out.println(uid);
			if(uid<200){
				try {
					ArrayList<CheckIn> checkIns = mapping.get(uid);
					checkIns.add(ci);
				} catch (Exception e) {
					// TODO: handle exception
					ArrayList<CheckIn> checkIns = new ArrayList<CheckIn>();
					checkIns.add(ci);
					mapping.put(uid, checkIns);
				}
			}
			else{
				break;
			}
			//scanner.next();
			
		}
		ArrayList<CheckIn> checkIns0 = mapping.get(5);
		ArrayList<CheckIn> checkIns1 = mapping.get(53);
		double diversity = computeCommon(checkIns0, checkIns1);
		System.out.println("D:   "+diversity);
	}
	static double computeCommon(ArrayList<CheckIn>cis1,ArrayList<CheckIn>cis2){
		HashMap<Place, ArrayList<CheckIn>>map = new HashMap<Place, ArrayList<CheckIn>>();
		HashMap<Place, Integer>p2iMap = new HashMap<Place,Integer>();
		for (CheckIn checkIn : cis1) {
			Place tmp = new Place(checkIn);
			try {
				ArrayList<CheckIn> checkIns = map.get(tmp);
				checkIns.add(checkIn);
			} catch (Exception e) {
				// TODO: handle exception
				ArrayList<CheckIn> checkIns = new ArrayList<CheckIn>();
				checkIns.add(checkIn);
				map.put(tmp, checkIns);
			}
		}
		for (CheckIn checkIn : cis2) {
			Place tmp = new Place(checkIn);
			if( (map.get(tmp)) != null){
				try {
					int number = p2iMap.get(tmp);
					number++;
					p2iMap.put(tmp, number);
				} catch (Exception e) {
					p2iMap.put(tmp, 1);
				}
			}
			
		}
		ArrayList<Integer>listI = new ArrayList<Integer>();
		int total = 0;
		for (Place p : p2iMap.keySet()) {
			int number = p2iMap.get(p);
			System.out.println("N |" +number);
			listI.add(number);
			total += number;
		}
		double entropy = 0;
		System.out.println("Renyi:  "+computeRenyiEntropy(0.15,listI,total));
		for(int i:listI){
			double p = (double)i/total;
			entropy += (p) * ( Math.log(p) / Math.log(2.0));
		}
		System.out.println("Size:  "+ p2iMap.keySet().size());
		System.out.println("E:   "+entropy);
		return entropy;
	}
	static double computeEntropy(ArrayList<Integer>list,int total){
		double entropy = 0;
		for(int i:list){
			double p = (double)i/total;
			entropy += (p) * ( Math.log(p) / Math.log(2.0));
		}
		return entropy;
	}
	static double computeRenyiEntropy(double q,ArrayList<Integer>list,int total){
		double entropy = 0;
		double sum = 0;
		for(int i:list){
			double p = (double)i/total;
			sum += Math.pow(p, q);
			
		}
		entropy = ( Math.log(sum) / Math.log(2.0) )/ (q-1);
		return -entropy;
	}

}
