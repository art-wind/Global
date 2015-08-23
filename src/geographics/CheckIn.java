package geographics;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.crypto.Data;


public class CheckIn implements Serializable {
	private double geoLat;
	private double geoLong;
	int uid;
	private Timestamp ts;
	
	private int locID;
	public CheckIn(double lattitude,double longtitude){
		setGeoLat(lattitude);
		setGeoLong(longtitude);
	}
	public CheckIn(String str){
		Date d = new Date();
		String[]arr = str.split("	");
//		String[]arr = str.split(" ");
		if (arr.length>2){
			int uidI = Integer.parseInt(arr[0]);
			double lattitude = Double.parseDouble(arr[2]);
			double longtitude = Double.parseDouble(arr[3]);
			int locationID = Integer.parseInt(arr[4]);
			
			setTs(Timestamp.valueOf(arr[1].replace("T", " ").replace("Z", "")));
			uid = uidI;
			setGeoLat(lattitude);
			setGeoLong(longtitude);
			setLocID(locationID);
		}
		else{
			arr = str.split(" ");
			int uidI = 0;
			double lattitude = Double.parseDouble(arr[0]);
			double longtitude = Double.parseDouble(arr[1]);
			String day = arr[5];
			String time = arr[6];
			setTs(Timestamp.valueOf(day + " "+time));
			uid = uidI;
			setGeoLat(lattitude);
			setGeoLong(longtitude);
		}
		
	}
	public CheckIn(String str,int i ){
		Date d = new Date();
//		String[]arr = str.split("	");
		String[]arr = str.split(" ");
		if (arr.length>2){
			int uidI = Integer.parseInt(arr[0]);
			double lattitude = Double.parseDouble(arr[2]);
			double longtitude = Double.parseDouble(arr[3]);
			int locationID = Integer.parseInt(arr[4]);
			
			setTs(Timestamp.valueOf(arr[1].replace("T", " ").replace("Z", "")));
			uid = uidI;
			setGeoLat(lattitude);
			setGeoLong(longtitude);
			setLocID(locationID);
		}
		else{
			arr = str.split(" ");
			int uidI = 0;
			double lattitude = Double.parseDouble(arr[0]);
			double longtitude = Double.parseDouble(arr[1]);
			String day = arr[5];
			String time = arr[6];
			setTs(Timestamp.valueOf(day + " "+time));
			uid = uidI;
			setGeoLat(lattitude);
			setGeoLong(longtitude);
		}
		
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Timestamp getTs() {
		return ts;
	}
	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
	public double getGeoLong() {
		return geoLong;
	}
	public void setGeoLong(double geoLong) {
		this.geoLong = geoLong;
	}
	public double getGeoLat() {
		return geoLat;
	}
	public void setGeoLat(double geoLat) {
		this.geoLat = geoLat;
	}
	public int getLocID() {
		return locID;
	}
	public void setLocID(int locID) {
		this.locID = locID;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CheckIn){
			if(this.locID == ((CheckIn) obj).getLocID()){
				Timestamp t = this.getTs();
				Timestamp t2 = ((CheckIn) obj).getTs();
//				double interval = t.
			}
		}
		return super.equals(obj);
	}
}
