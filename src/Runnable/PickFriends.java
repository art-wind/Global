package Runnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PickFriends {
	public static void main(String[]args) throws IOException{
		//Gowalla
		//checkins60M
		File gowallaFile = new File("data/checkins60M.txt");
		Scanner gowallaScanner = null;
		gowallaScanner = new Scanner(gowallaFile);
		HashMap<Integer, Integer>uidToUidMap = new HashMap<Integer, Integer>();


		HashMap<Integer, ArrayList<Integer>>mapping = new HashMap<Integer, ArrayList<Integer>>();
		String gowallaString = "";

		while(gowallaScanner.hasNext()){
			gowallaString = gowallaScanner.nextLine();
			int uid = Integer.parseInt(gowallaString.split("	")[0]);
			uidToUidMap.put(uid, 1);
		}
		gowallaScanner.close();


		
		File input = new File("data/Gowalla_edges.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = "";
		while(scanner.hasNext()){
			string = scanner.nextLine(); 
			int sourceID = Integer.parseInt(string.split("	")[0]);
			int targetID = Integer.parseInt(string.split("	")[1]);
			if(sourceID!=131095 && uidToUidMap.get(sourceID) != null && uidToUidMap.get(targetID )!= null){
				ArrayList<Integer>list = null;
				if((list = mapping.get(sourceID)) == null ){
					list = new ArrayList<Integer>();
					list.add(targetID);
				}
				else{
					list.add(targetID);
				}
				mapping.put(sourceID, list);
			}
		}
		scanner.close();
		
		
		int pairSize = 100;
		int count =0;
		System.out.println("Good:");
		String goodString = "";
		HashMap<Integer,Integer>resultHash = new HashMap<Integer,Integer>(); 
		double frequencyThreshold =1;
		
		for(int key:mapping.keySet()){
			if(count<pairSize){
				double random = Math.random();
				if(random<frequencyThreshold){
					int size = mapping.get(key).size();
					int index = (int) (Math.random() * size) ;
					System.out.println(index+" / "+size);
					
					int anotherKey = mapping.get(key).get(index);
					
					resultHash.put(key, anotherKey);
					goodString += (key+"	"+ anotherKey+"\n");
					count ++;
				}
				else{
					break;
				}
			}
			else{
				count = 0;
				break;
			}

		}
		FileWriter fileWriterGoodFileWriter = null;
		try {
			fileWriterGoodFileWriter= new FileWriter(new File("data/GoodPairs.txt"));
			//System.out.println(goodString);
			fileWriterGoodFileWriter.write(goodString);
			fileWriterGoodFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





		System.out.println("Bad:");
		FileWriter fileWriterBad = null;
		try {
			fileWriterBad= new FileWriter(new File("data/BadPairs.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String badString = "";
		System.out.println(resultHash.keySet().size());
		for(int src :resultHash.keySet()){
			int tgt = resultHash.get(src);
			ArrayList<Integer>list = mapping.get(tgt);
			boolean con = true;
			for(int anotherKey:list){
				if(src != anotherKey){
					if(!mapping.get(src).contains(anotherKey)){
						if(isBelong(uidToUidMap, anotherKey)){
							badString += src+"	"+ anotherKey +"\n";
							con = false;
							break;
						}
					}
				}
			}
			if(con){
				ArrayList<Integer>anotherList = mapping.get(src);
				int number = 0;
				while(number < anotherList.size()){
					int index = (int)(Math.random() * anotherList.size());
					int friend = anotherList.get(index);
					if(tgt != friend){
						if(!mapping.get(tgt).contains(friend)){
							if(isBelong(uidToUidMap, friend)){
								badString += tgt+"	"+ friend +"\n";
								con = false;
								break;
							}
						}
						
					}
					number ++;
				}
			}
			if(con){
				System.out.println("Nono: "+ src + ":  "+tgt);
			}
		}
		fileWriterBad.write(badString);
		fileWriterBad.flush();
		fileWriterBad.close();


	}
	static boolean isNotFriendAmongList(int src,ArrayList<Integer>list,HashMap<Integer, ArrayList<Integer>> hashMap){

		for(int i :list){
			ArrayList<Integer>hashList = hashMap.get(i);
			if(hashList.contains(src)){
				return false;
			}
		}
		return true;
	}
	static boolean isBelong(HashMap<Integer, Integer>i2iMap,int key){
		return i2iMap.get(key) != null;
//		try {
//			int n = i2iMap.get(key);
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return false;
//		}
	}
}
