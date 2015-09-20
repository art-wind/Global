package Runnable;

import geographics.CheckIn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONString;

import utils.FileUtils;
import utils.ArrayUtil;
import vector.VectorManager;

public class TFIDF {
	static String DIRECTORY = "data/vectors/";
	public static void main(String[]args) throws IOException{
		VectorManager vm = new VectorManager();
		int[]result = new int[vm.NUMBER_OF_CATS];
		int[]tmp = new int[vm.NUMBER_OF_CATS];
		ArrayUtil.initIntegerAttay(result);
		ArrayUtil.initIntegerAttay(tmp);
		//vm.getType(result, id);
		File originFile = new File("./data/realPlaces.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(originFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		String line = "";
		while( (line = reader.readLine()) != null){
			CheckIn checkIn = new CheckIn(line,1);
			int location = checkIn.getLocID();
			File tgtFile = new File(DIRECTORY+"records/"+location+".txt");
			String jsonString = "";
			try {
				jsonString = FileUtils.readFile(tgtFile);
			} catch (Exception e) {
				// TODO: handle exception
				tgtFile = new File(DIRECTORY+"missing/result/"+location+".txt");
				jsonString = FileUtils.readFile(tgtFile);
			}
			try {
				tmp = vm.getCountFromQueryResult(jsonString);
				result = ArrayUtil.addValue(result, tmp);
				
			} catch (Exception e) {
				// TODO: handle exception
				/*System.out.println(jsonString);
				System.out.println("All "+count);
				for(int i:result){
					System.out.print(i+" ");
				}*/
				System.out.println("Wrong "+location);
				continue;
			}
			if(count++ %1000 == 0 ){
				System.out.println("C: "+count);
			}
			
			
		}
		System.out.println("All "+count);
		for(int i:result){
			System.out.print(i+" ");
		}
		
	}
}
