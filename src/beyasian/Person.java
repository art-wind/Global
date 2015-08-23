package beyasian;

import java.util.ArrayList;
import java.util.HashMap;

import geographics.*;

public class Person {
	private int uid=0;
	private ArrayList<CheckIn> checkIns;
	private HashMap<Place, Integer> placeMapping;
	public Person(int id) {
		// TODO Auto-generated constructor stub
		uid = id;
		checkIns = new ArrayList<CheckIn>();	
		placeMapping = new HashMap<Place,Integer>();
	}
	public void addCheckIn(CheckIn c){
		Place tmpPlace = new Place(c);
		if(placeMapping.get(tmpPlace) != null ){
			int count = placeMapping.get(tmpPlace);
			placeMapping.put(tmpPlace, ++count);
		}
		else{
			placeMapping.put(tmpPlace, 1);
		}
	}
	public int getCommonPlaceNumber(Person person){
		int ret = 0;
		HashMap<Place, Integer>otherMap = person.getPlaceMapping();
		if(otherMap != null){
			for(Place p:otherMap.keySet()){
				
				if(placeMapping.get(p) != null ){
					int count = placeMapping.get(p);
					int otherCount = otherMap.get(p);
					//System.out.println("Loc: "+p.getLocationID());
					ret += Math.min(count, otherCount);
				}
			}
			return ret;
		}
		else{
			return 0;
		}
		
	}
	
	public double getIntervalRate(Person person){
		HashMap<Place, Integer>otherMap = person.getPlaceMapping();
		int count = 0;
		int otherCount = 0;
		for(Place p:otherMap.keySet()){
			int tmp = otherMap.get(p);
			otherCount += tmp;
		}
		for(Place p:getPlaceMapping().keySet()){
			int tmp = placeMapping.get(p);
			count += tmp;
		}
		//System.out.println("III  "+Math.abs(count - otherCount)/Math.max(count, otherCount));
		return (double)Math.abs(count - otherCount)/(double)Math.min(count, otherCount);
	}
	
	
	public HashMap<Place, Integer> getPlaceMapping() {
		return placeMapping;
	}
	public void setPlaceMapping(HashMap<Place, Integer> placeMapping) {
		this.placeMapping = placeMapping;
	}
}
