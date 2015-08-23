package vector;

public class FeatureVector {
	private double[]features;
	public FeatureVector(double[] array){
		features = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			features[i] = array[i];
		}
	}
	public double[] getFeatures() {
		return features;
	}
	public void setFeatures(double[] features) {
		this.features = features;
	}
}
