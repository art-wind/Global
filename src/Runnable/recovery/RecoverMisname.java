package Runnable.recovery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecoverMisname {
	public static void main(String[] args) {  
        getName("data/finPlaces/all/");  
    }  
  
    public static void getName(String path) {  
        File file = new File(path);  
        if (file.isDirectory()) {  
            File[] dirFile = file.listFiles();  
            for (File f : dirFile) {  
                if (f.isDirectory())  
                    getName(f.getAbsolutePath());  
                else {  
                	//System.out.println(f.getName());
                	try{
                		String fileName = f.getName().replace(".txt", "");
//                        if (fileName.startsWith("2")){
                        	try {
    							Scanner scanner = new Scanner(f);
    							String result = "";
    							while(scanner.hasNext()){
    								result += scanner.nextLine();
    							}
    							scanner.close();
    							JSONObject object = new JSONObject(result);
    							try {
    								String id = (String) object.get("placeID");
    								if(id!=null){
    									if( !id.equals(fileName)){
    										System.out.println("Wrong "+fileName);
    										FileWriter writer = new FileWriter(new File(path+id+".txt"));
    										writer.write(result);
    										writer.flush();
    										writer.close();
    										//f.delete();
    										//System.out.println("Wrong "+fileName);
    										
    									}
    								}
    							} catch (Exception e) {
    								// TODO: handle exception
    							}
    						} catch (FileNotFoundException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                	}
                	catch(Exception e){
                		
                	}
                	
                }  
            }  
        }  
    }
}
