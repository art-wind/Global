package utils;

public class ArrayUtil {
	public static int[] addValue(int[]a,int[]b){
		int[]ret = new int[a.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = a[i]+b[i];
		}
		return ret;
		
	}
	public static void  initIntegerAttay(int[] result){
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
	}
}
