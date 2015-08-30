package similarity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlaceSimilarity {
	public static void main(String[]args){
		ArrayList<HashMap<Integer, Integer>>list = new ArrayList<HashMap<Integer, Integer>>();
		int number = 10;
		int numberMax = 5;
		for (int i = 0; i < number; i++) {
			HashMap<Integer, Integer>map = new HashMap<Integer,Integer>();
			for(int j=0;j<number;j++){
				int time = (int)(Math.random() * numberMax);
//				int time = 1;
				if(time != 0) {
					map.put(j, time);
				}
			}
			list.add(map);
		}
		int[][]mainArray = new int[number][number];
		double[][]result = new double[number][number];
		for (int i = 0; i < mainArray.length; i++) {
			for (int j = 0; j < mainArray[i].length; j++) {
				mainArray[i][j] = 0;
				result[i][j] = 0.0;
			}
		}
		int[]count = new int[number];
		
		for (int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		
		for (int i = 0; i < number; i++) {
			HashMap<Integer, Integer>map = list.get(i);
			for(int a:map.keySet()){
				count[a] += map.get(a);
				for(int b:map.keySet()){
					mainArray[a][b] += map.get(b);
//					if(a != b){
//						mainArray[a][b] += map.get(b);
//					}
						
				}
			}
		}
		
		for (int i = 0; i < mainArray.length; i++) {
			int rowCount = 0;
			for (int j = 0; j < mainArray[i].length; j++) {
				result[i][j] = (double)mainArray[i][j] / count[i] ;
				rowCount += result[i][j];
//				System.out.print(mainArray[i][j] +"/" +count[i]+" ");
				if(result[i][j]>0){
					System.out.print(result[i][j]+" ");
				}
				
			}
			System.out.println();
		}
		
	}
}
