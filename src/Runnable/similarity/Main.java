package Runnable.similarity;

import geographics.Place;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import vector.FeatureVector;
import vector.VectorManager;

public class Main {
	static final double ALPHA = 0.4;
	public static void main(String[]args) throws IOException {
		VectorManager vm = new VectorManager();
		int number = 0;
		String inputFileName = String.format("data/places/%d.txt", number);
		File inputFile = new File(inputFileName);
		Scanner scanner = new Scanner(inputFile);
		String line = "";
		while(scanner.hasNext()){
			line = scanner.nextLine();
			String id = line.split(" ")[0];
			String placeFileName = String.format("data/finPlaces/%d/%s.txt",number,id);
			
			try {
				File finPlaceFile = new File(placeFileName);
				String finString = readFile(finPlaceFile);
				double[]array = vm.getVectorFromQueryResult(finString);
				printArray(array);
			} catch (Exception e) {
			}
			
		}
	}
	static String readFile(File f){
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
	
	static <T> void printArray(double[] ds){
		System.out.print("[");
		for(double t:ds){
			System.out.print(t+",");
		}
		System.out.print("]");
		System.out.println();
	}
	static double computeSimilarity(String placeName1,String placeName2){
		double ret = 0;
		VectorManager vm = new VectorManager();
		double typeSimilarity = 0;
		double distanceSimilarity = 0;
		try {
			File finPlaceFile = new File(placeName1);
			String finString = readFile(finPlaceFile);
//			String 
//			Place pa = new Place();
			double[]array = vm.getVectorFromQueryResult(finString);
			FeatureVector fv1 = new FeatureVector(array);
			
			File finPlaceFile2 = new File(placeName2);
			finString = readFile(finPlaceFile);
			double[]array2 = vm.getVectorFromQueryResult(finString);
			FeatureVector fv2 = new FeatureVector(array2);
			
			typeSimilarity = vectorMulti(fv1,fv2);
			printArray(array);
		} catch (Exception e) {
		}
		ret = ALPHA * typeSimilarity +(1-ALPHA)*distanceSimilarity;
		return ret;
	}
	static double vectorMulti(FeatureVector a,FeatureVector b ){
		double ret = 0;
		double[]fv1 = a.getFeatures();
		double[]fv2 = b.getFeatures();
		int lengthA = fv1.length;
		int lengthB = fv2.length;
		if(lengthA != lengthB){
			System.out.println("No");
		}
		else{
			for(int i=0;i<lengthA;i++){
				ret += (fv1[i] * fv2[i]);
			}
		}
		return ret;
	}
}
