package split;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CheckDifference {
	public static void main(String[]args) throws IOException{
		
		
		File file = new File("./data/PlacesSplit.txt");
		File fileExtended = new File("./data/PlacesSplitE.txt");
		BufferedReader reader = null;
		
		try {

			reader = new BufferedReader(new FileReader(file));
			//readerExtended = new BufferedReader(new FileReader(fileExtended));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {

			}
		}

//		CheckIn ci = new CheckIn(reader.readLine());
//		Place p = new Place(ci);
		String outString = "";
		String tempString = "";
		String tempStringE = "";
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		while( (tempString = reader.readLine()) != null){
			int id = Integer.parseInt(tempString.split(" ")[0]);
			map.put(id,1);
		}
		
		reader.close();
		System.out.println("End Hashing");
		
		BufferedReader readerExtended = null;
		try {
			readerExtended = new BufferedReader(new FileReader(fileExtended));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {

			}
		}
		System.out.println("End Hashing");
		int line = 0;
		while( (tempStringE = readerExtended.readLine())!= null ){
			 int idExtend = Integer.parseInt(tempStringE.split(" ")[0]);
			 if(map.get(idExtend) == null){
				 outString += tempStringE +"\n";
			 }
			 else{
				 map.remove(idExtend);
			 }
			 System.out.println("Line:"+line);
			 if(line% 100000 == 0 ){
				File output = new File("./data/Difference_"+line+".txt");
				FileWriter writer = new FileWriter(output);
				
				writer.write(outString);
				writer.flush();
				writer.close();
				
				outString = "";
			 }
			
			 line++;
			 //System.out.println("I "+id +" E: "+ idExtend);
			/* if(idExtend > id){
				 outString += tempStringE +"\n";
			 }
			 else{
				 break;
			 }*/
		 }
		readerExtended.close();
		
		
		
		
		
	}
}
