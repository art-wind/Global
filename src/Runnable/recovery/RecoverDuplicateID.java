package Runnable.recovery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RecoverDuplicateID {
	public static void main(String[]a){
		String[]strings = {"entertainment.txt","food.txt","store.txt","occ.txt"};
		String basePath = "data/types/";
		for(String s:strings){
			File file = new File(basePath + s);
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<Integer, Integer>map = new HashMap<Integer,Integer>();
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				map.put(Integer.parseInt(line), 1);
			}
			scanner.close();
			FileWriter writer = null;
			try {
				writer = new FileWriter(new File(basePath +"2"+s));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int key:map.keySet()){
				try {
					writer.write(key+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
