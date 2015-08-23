package geographics;
import java.sql.Timestamp;


public class Difference {
	double distance;
	int minutes;
	public Difference(CheckIn first,CheckIn second){
		Timestamp t1 = first.getTs();
		Timestamp t2 = second.getTs();
		minutes = (int) ((t1.getTime() - t2.getTime())/60/1000);
		distance = getDistance(first.getGeoLat(),first.getGeoLong(),second.getGeoLat(),second.getGeoLong());
	}
	
	public static double getDistance(double lat1, double lon1, double lat2, double lon2){
		double radLat1 = lat1 * Math.PI / 180;
		double radLat2 = lat2 * Math.PI / 180;
		double a = radLat1 - radLat2;
		double b = lon1 * Math.PI / 180 - lon2 * Math.PI / 180;
		a = Math.abs(a);
		b = Math.abs(b);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;

		return s;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
