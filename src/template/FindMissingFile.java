package template;

import utils.ExtractMissingRecords;

public class FindMissingFile {
	public static String BASEPATH = "data/vectors/missing/";
	public static void main(String[]args){
		
		String srcFile = BASEPATH + "all3.txt";
		String tgt = BASEPATH + "result/";
		String outputDir = BASEPATH + "miss2.txt";
		ExtractMissingRecords.extractMissingPlacesFromDir(srcFile, tgt, outputDir);
	}
}
