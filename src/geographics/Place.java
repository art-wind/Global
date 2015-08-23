package geographics;

import java.io.Serializable;

public class Place implements Comparable<Place>,Serializable {
	private double longtitude= 0;
	private double latitude = 0;
	private int locationID = 0;
	
	//签到的次数 可以不设置
	private int checkInSize = 0;
	public Place(Double longtitude,Double latitude,int locationID) {
		this.setLongtitude(longtitude);
		this.setLatitude(latitude);
		this.setLocationID(locationID);
		
	}
	public Place(CheckIn c){
		this.setLongtitude(c.getGeoLong());
		this.setLatitude(c.getGeoLat());
		this.setLocationID(c.getLocID());
		
//		System.out.println("ID:" + locationID);
	}
	public Place(Place pl) {
		this.setLongtitude(pl.getLongtitude());
		this.setLatitude(pl.getLatitude());
		this.setLocationID(pl.getLocationID());
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Place){
			return (((Place)obj).getLocationID() == this.getLocationID());
		}
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return getLocationID();
	}
	public void setSize(int size){
		this.setCheckInSize(size);
	}
	@Override
	public int compareTo(Place o) {
		
		if (this.getCheckInSize() > o.getCheckInSize() ){
			return -1;
		}
		else if (this.getCheckInSize() < o.getCheckInSize()){
			return 1;
		}
		
		/*
		if(this.getLocationID() > o.getLocationID()){
			return -1;
		}
		if(this.getLocationID() < o.getLocationID()){
			return 1;
		}*/
		// TODO Auto-generated method stub
		return 0;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public int getCheckInSize() {
		return checkInSize;
	}
	public void setCheckInSize(int checkInSize) {
		this.checkInSize = checkInSize;
	}

}
