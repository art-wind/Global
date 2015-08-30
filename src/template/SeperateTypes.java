package template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

import vector.VectorManager;
import Runnable.util.FileUtils;

public class SeperateTypes {
	public static void main(String[]args){
		extractTypesFromPlacesDir("data/realPlaces.txt","data/vectors/records/","data/types");
	}
	public static void extractTypesFromPlacesDir(String srcFilename,String tgtdir,String outputDir) {
		File file = new File(srcFilename);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VectorManager vm = new VectorManager();
		int linecount =0;
		int start = 0;
		while(scanner.hasNext() ){
			if(linecount<start){
				linecount++;
				continue;
			}
			String line = scanner.nextLine();
			String id = line.split(" ")[4];
			if(linecount % 5000 == 0){
				System.out.println(linecount);
			}
			
			File tgtFile = new File(tgtdir + id+".txt");
			if(tgtFile.exists()){
				String result = FileUtils.readFile(tgtFile);
				String ret = null;
				try {
					 ret = vm.getType(result,id);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(id);
				}
				
				if(ret != null){
					FileWriter writer = null;
					try {
						writer = new FileWriter(new File("data/types/"+ret+".txt"),true);
						writer.write(id+"\n");
						writer.flush();
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Wrong"+id);
						e.printStackTrace();
					}
					
				}
			}
			linecount++;
		}
	}
}
