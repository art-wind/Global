package Runnable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import utils.FetchThread;
import utils.ThreadStatus;

public class Fetch {
	static String API_KEY = "AIzaSyDsL6qS7fjPLTClrVbckuGY5Cv9ZhpJwhE";
	
	//Modify the finishedNumber when 50,000 response has been reached
	static String finishedNumber = "vectors/missing/";
	//16326
	//10040 
	static int startLine = 140000;
	//10_0000
	static int end = 140001;
	static ThreadStatus status;
	static int threadMax = 50;
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		String radius = "50";
		
		File placesFile = new File("data/"+finishedNumber+"missing123.txt");
		Scanner scanner = new Scanner(placesFile);
		status = new ThreadStatus();
		
		int lineNum = 0;
		while(scanner.hasNext()){
			if(lineNum < end){
				if(lineNum<startLine){
					scanner.nextLine();
					lineNum++;
					continue;
				}
				while(!status.goodToGo(threadMax)){
					Thread.sleep(1000);
					System.out.println("Sleep");
				}
				status.addThread();
				String line = scanner.nextLine();
				String targetFilePath = "data/vectors/records";
				FetchThread thread = new FetchThread(finishedNumber, line, API_KEY, radius,lineNum,status,targetFilePath);
				thread.start();
				lineNum++;
			}
		}
		scanner.close();
	}
}
