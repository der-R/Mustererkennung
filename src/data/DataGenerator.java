package data;

import java.util.Random;

public class DataGenerator {

	Random r = new Random();

	public double[] generateData(int n, int m, int sd) {
		double[] data = new double[n];
		for (int i = 0; i < data.length; i++) {
			data[i] = m + r.nextGaussian() * sd;
		}
		return data;
	}
	
	public Representative[] generateDataSet(int n, int m1, int sd1, int m2, int sd2) {
		double[] half1 = generateData(n/2, m1, sd1);
		double[] half2 = generateData(n/2, m2, sd2);
		Representative[] data = new Representative[n];
		for (int i = 0; i < n/2; i++) {
			double[] tmp = new double[1];
			data[i] = new Representative(0); 
			tmp[0] = half1[i];
			data[i].setPoints(tmp, 1);
			data[n/2+i] = new Representative(1); 
			tmp[0] = half2[i];
			data[n/2+i].setPoints(tmp, 1);
		}
		return data;		
	}
}
