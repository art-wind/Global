package beyasian;

import geographics.CheckIn;
import geographics.Place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Beyasian {
	public static final int NUMBER_OF_Users = 500;
	public static void main(String[] args) throws IOException {
		HashMap<Place, ArrayList<CheckIn>> mapFromPlacetoCheckins =new HashMap<Place, ArrayList<CheckIn>>();
		Person[]personArray = new Person[NUMBER_OF_Users];
		int[][]commonPlaces = new int[NUMBER_OF_Users][NUMBER_OF_Users];
		double[][]intervalRate = new double[NUMBER_OF_Users][NUMBER_OF_Users];
		File file = new File("./data/Gowalla.txt");
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(file));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {

			}
		}
		
		String tempString = "";
		int currentUserUID = 0;
		Person currentPerson = null;
		while( (tempString = reader.readLine()) != null){
			CheckIn ci = new CheckIn(tempString);
			
			if(ci.getUid() < NUMBER_OF_Users ){
				if(personArray[currentUserUID] == null){
					personArray[currentUserUID] = new Person(currentUserUID);
					
				}
				if(ci.getUid() != currentUserUID){
					currentUserUID = ci.getUid();
					personArray[currentUserUID] = new Person(currentUserUID);
				}
				currentPerson = personArray[currentUserUID];
				currentPerson.addCheckIn(ci);
			}
			else{
				break;
			}
		}
			
		System.out.println(mapFromPlacetoCheckins.keySet().size());
		reader.close();
		for(int i=0;i<NUMBER_OF_Users-1;i++){
			for(int j = i+1;j<NUMBER_OF_Users;j++){
				Person personI = personArray[i];
				Person personj = personArray[j];
				if(personI != null && personj!=null){
					System.out.print("i: "+i+" j: "+j);
					commonPlaces[i][j] = personI.getCommonPlaceNumber(personj); 
					intervalRate[i][j] = personI.getIntervalRate(personj);
					System.out.println(" Common: "+commonPlaces[i][j]+" inter: "+intervalRate[i][j]);
				}
				
			}
		}
		
	}

}
