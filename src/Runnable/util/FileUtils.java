package Runnable.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
	public static String readFile(File f){
		String ret = "";
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = "";
		while(s.hasNext()){
			line = s.nextLine();
			//line = "";
			if(!"".equals(line)){
				ret += line;
			} 
//			System.out.println("re "+ret);
			return ret;
		}
		s.close();
		return ret;
	}
}
