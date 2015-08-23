package utils;


public class VectorUtils {
	public static double calculateSimilarity(double[]arrayA,double[]arrayB ){
		double ret = 0;
		for (int i = 0; i < arrayA.length; i++) {
			ret += (arrayA[i]*arrayB[i]);
		}
		return ret;
	}
}
