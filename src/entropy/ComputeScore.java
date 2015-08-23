package entropy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class ComputeScore {
	double alpha = 0;
	double beta = 0;
	double gamma = 0;
	static final String LOCATION_ENTROPYMAPPING_FILEPATH = "data/objectOutput/locationEntropyMapping.obj";
	public static void main(String[]args) throws FileNotFoundException, IOException, ClassNotFoundException{
		File fileMap =  new File(LOCATION_ENTROPYMAPPING_FILEPATH);
		ObjectInputStream inputStream = new ObjectInputStream( new FileInputStream(fileMap));
		HashMap<Integer, Double>entropyMap = (HashMap<Integer, Double>) inputStream.readObject();
		inputStream.close();
		
		
		System.out.println("Specify the user IDS");
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int uid0 = Integer.parseInt(line.split(" ") [0]);
		int uid1 = Integer.parseInt(line.split(" ") [1]);
		double diversity = getDiversity(uid0,uid1);
		
		double entropy = getEntropy(uid0,uid1);
		
		if(checkFriend(uid0,uid1)){
			
		}
		else{
			
		}
	}
	private static boolean checkFriend(int uid0, int uid1) {
		// TODO Auto-generated method stub
		return false;
	}
	private static double getEntropy(int uid0, int uid1) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static double getDiversity(int uid0, int uid1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
