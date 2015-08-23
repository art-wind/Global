package vector;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class VectorManager {
	//shopping_mall,grocery_or_supermarket
	public static final int NUMBER_OF_CATS = 18;
	
	public static final String[]attrs = {"store","park","transit_station"};
	//0
	public static final String[]OCCUPATIONS = {"accounting","electrician","dentist","doctor","finance","florist","lawyer","locksmith","office","physiotherapist","plumber","police","moving_company","painter"};
	public static final String[]FOOD = {"cafe","restaurant","bakery","food","meal_delivery","meal_takeaway"};
	public static final String[]TRANSPORTATION = {"airport","bus_station","subway_station","taxi_stand","train_station","transit_station"};
	public static final String[]WORSHIP = {"church","hindu_temple","mosque","place_of_worship","synagogue"};
	public static final String[]SCHOOL = {"school","university","library"};
	//5
	public static final String[]ENTERTAINMENTS = {"bar","casino","movie_rental","movie_theater","night_club"}; 
	public static final String[]POLITICAL = {"city_hall","courthouse","embassy","fire_station","local_government_office","post_office"};
	public static final String[]CONVENIENCE = {"pharmacy","atm","bank","laundry"};
	public static final String[]CULTURE = {"art_gallery","museum","aquarium"};
	public static final String[]SPORTS = {"bowling_alley","stadium"};
	//10
	public static final String[]CAR = {"car_dealer","car_rental","car_repair","car_wash","gas_station","parking"};
	public static final String[]CARES = {"hospital","gym","hair_care","health","spa","beauty_salon"};//insurance_agency
	public static final String[]ANIMALS = {"veterinary_care","zoo"};
	public static final String[]AGENCIES = {"insurance_agency","real_estate_agency","travel_agency"};
	public static final String[]TUMBS = {"cemetery","funeral_home"};
	//15
	public static final String[]CONTRACTOR = {"roofing_contractor","general_contractor"};
	public static final String[]STORES = {"book_store","clothing_store","convenience_store","department_store","electronics_store","furniture_store",
						"jewelry_store","home_goods_store","liquor_store","bicycle_store","hardware_store","pet_store","shoe_store","shopping_mall","shopping_mall"};
	public static  final String[]PARKS = {"amusement_park","rv_park","park","campground"};
	
	public static final String[][]CATEGORIES = {
			OCCUPATIONS,FOOD,TRANSPORTATION,WORSHIP,SCHOOL,
			ENTERTAINMENTS,POLITICAL,CONVENIENCE,CULTURE,SPORTS,
			CAR,CARES,ANIMALS,AGENCIES,TUMBS,
			CONTRACTOR,STORES,PARKS};
	private HashMap<String, Integer>catsMap;
	int MAPPED_NUMBER = 9;
	public VectorManager(){
		setUpHash();
	}
	public double[]getVectorFromQueryResult(String result){
//		int count = 0 
		int[]ret = new int[NUMBER_OF_CATS];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = 0;
		}
		JSONObject object = new JSONObject(result);
		JSONArray results = object.getJSONArray("results");
		for (int i = 0; i < results.length(); i++) {
			JSONObject obj = (JSONObject)results.get(i);
			JSONArray typeArray;
			try {
				typeArray = obj.getJSONArray("types");
			} catch (Exception e) {
				try {
					typeArray = new JSONArray(obj.getString("types"));
				} catch (Exception e2) {
					try {
						typeArray = obj.getJSONArray("types");
					} catch(Exception e3){
						typeArray = new JSONArray(obj.getString("type"));
					}
				}
				
			}
			if(typeArray.length() > 2){
//				int j = typeArray.length() - 3;
				for (int cursor = 0; cursor < typeArray.length(); cursor++) {
					String typeString = typeArray.getString(cursor);
					int value = 0;
					try {
						value = catsMap.get(typeString);
						ret[value] ++;
						break;
					}
					catch (Exception e) {
					}
				}
			}
			else{
				int randomID = (int)(Math.random()*ret.length);
				ret[randomID]++;
				/*
				for (int j = 0; j < ret.length; j++) {
					ret[j]++;
				}
				*/
			}
			/*
			try{
				int index = catsMap.get(typeString);
				ret[index] ++;
			}
			catch(Exception e){
				
				
				for (int c = 0; c < ret.length; c++) {
					ret[c]++;
				}
				
				System.out.println("Index not found  "+ typeString);
				int randomID = (int)(Math.random()*ret.length);
				ret[randomID]++;
			}
			
			else{
				int randomID = (int)(Math.random()*ret.length);
				ret[randomID]++;
				
				for (int j = 0; j < ret.length; j++) {
					ret[j]++;
				}
				
			}*/
		}
		/*if(count > 3){
			System.out.println(placeID);
		}
		System.out.println("Vector:  ");
		for (int j = 0; j < ret.length; j++) {
			System.out.print(ret[j]);
		}
		System.out.println();
		*/
		return normalize(ret);
	}
	private double[]normalize(int[]array){
		int count = 0;
		int length = array.length;
		double[]ret = new double[length];
		for (int i = 0; i < length; i++) {
			ret[i] = array[i];
			count += Math.pow(array[i],2);
			
		}
		
		if(count!= 0 ){
			for (int i = 0; i < length; i++) {
				ret[i] /= Math.sqrt(count);
			}
			return ret;
		}
		else{
			for (int i = 0; i < length; i++) {
				ret[i] = 0;
			}
			return ret;
		}
		
	}
	public void setUpHash(){
		catsMap = new HashMap<String,Integer>();
		System.out.println("Len  "+ CATEGORIES.length);
		for (int i = 0; i < CATEGORIES.length; i++) {
			System.out.println(CATEGORIES[i].length);
			for(String s:CATEGORIES[i]){
				System.out.println(s);
				catsMap.put(s, i);
			}
		}
	}
	
}
