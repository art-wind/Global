import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;

import vector.VectorManager;
import Runnable.util.FileUtils;


public class ddd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("data/realPlaces.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VectorManager vm = new VectorManager();
		int linecount =0;
		while(scanner.hasNext() ){
			String line = scanner.nextLine();
			String id = line.split(" ")[4];
			if(linecount % 5000 == 0){
				System.out.println(linecount);
			}
			
			File tgtFile = new File("data/vectors/records" + id+".txt");
			if(tgtFile.exists()){
				String result = FileUtils.readFile(tgtFile);
				JSONObject ob = new JSONObject(result);
				
			}
			linecount++;
		}
	}

}
