package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchThread extends Thread {
	String inputString;
	String finishedNumber = "";
	private int radius = 50;
	private int lineNum = 0;
	private String api = "";
	private ThreadStatus threadStatus;
	private String targetFilePath;
	//String id = "";
	public FetchThread(String finishedNumber,String line,String api,String radius,int lineNum,ThreadStatus stat,String targetFilePath) throws MalformedURLException, IOException {
		// TODO Auto-generated constructor stub
		this.finishedNumber = finishedNumber;
		this.inputString = line;
		this.api = api;
		this.radius = Integer.parseInt(radius);
		this.lineNum = lineNum;
		this.threadStatus = stat;
		this.targetFilePath = targetFilePath;
	}
	@Override
	public void run() {
		String line = this.inputString;
		
		/*
		String placeID = line.split(" ")[0];
		String lattitude = line.split(" ")[1];
		String longitude = line.split(" ")[2];
		*/
		
		String placeID = line.split(" ")[4];
		String lattitude = line.split(" ")[2];
		String longitude = line.split(" ")[3];

		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
		String parameters = String.format("location=%s,%s&key=%s&radius=%s&sensor=false", lattitude,longitude,api,radius);
		URLConnection connection = null;
		try {
			connection = (new URL(url+"?"+parameters)).openConnection();
			System.out.println(connection.toString());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String charset = "UTF-8";
		connection.setRequestProperty("Accept-Charset", charset);
		
		
		
		/*
		 * Read Response from Google
		 */
		InputStream responseFromGoogle;
		String responseString = "";
		try {
			responseFromGoogle = connection.getInputStream();
			String inputLine;
			BufferedReader in = new BufferedReader(new InputStreamReader(responseFromGoogle));
			StringBuffer responseBuffer = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				responseBuffer.append(inputLine);
			}
			in.close();
			responseString = responseBuffer.toString();
			System.out.println("r");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("ssss");
			threadStatus.releaseThread();
			return;
		}
		
		 
		
		
		
		/*
		 * Append JSON Results
		 */
		
		
		JSONObject placesJsonObject = new JSONObject(responseString);
		String status = placesJsonObject.getString("status");
		System.out.println("S:  "+status);
		if(status == "OK"){
			System.out.println("not");
			/*
			 * FileWriter errorWriter;
			try {
				errorWriter = new FileWriter(new File("error.txt"));
				errorWriter.write(line);
				errorWriter.flush();
				errorWriter.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return;
			 */
		}
		else{
			
			FileWriter outputFile;
			try {
				outputFile = new FileWriter(new File(this.targetFilePath+"/"+placeID+".txt"));
//				outputFile.write(outputJson.toString());
				outputFile.write(responseString);
				outputFile.flush();
				outputFile.close();
				System.out.println("# "+lineNum+" : "+placeID);
				
				threadStatus.releaseThread();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		JSONObject outputJson = new JSONObject();
		outputJson.put("placeID", placeID);
		outputJson.put("latitude", lattitude);
		outputJson.put("longitude", longitude);
		JSONArray pois = new JSONArray();
		
		System.out.println("INININIII");
		//Handle the results list
		JSONArray array = placesJsonObject.getJSONArray("results");
		for (int i = 0; i < array.length(); i++)
		{
			JSONObject intputObject = array.getJSONObject(i);
		    JSONObject outputObject = new JSONObject();
			
		    String id = intputObject.getString("id");
		    outputObject.put("id",id);
		    
			String name = intputObject.getString("name");
		    outputObject.put("name",name);
		    
		    JSONArray typesArray = intputObject.getJSONArray("types");
		    String typeString = typesArray.toString();
		    outputObject.put("type", typeString);
		    
		    double rating = 3.0;
			try{
				rating = java.lang.Double.parseDouble(intputObject.get("rating").toString());
				outputObject.put("rating", rating);
			}
			catch(Exception e){
				//e.printStackTrace();
			}
		    
		    pois.put(outputObject);
		}
		outputJson.put("results", pois);
		*/
		
		//Write back the results
		
		
		
	}
}
