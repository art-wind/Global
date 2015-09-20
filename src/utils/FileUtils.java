package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
	public static String readFile(File f) throws IOException{
		String ret = "";
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		String line = "";
		while( (line = reader.readLine())!=null){
			//line = "";
			if(!"".equals(line)){
				ret += line;
			} 
			return ret;
		}
		reader.close();
		return ret;
	}
	
}
