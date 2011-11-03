package classification;

import java.util.Arrays;

import data.Representative;

public class NearestNeighborClassificator {

	int k = 1;
	Representative[] data;

	public NearestNeighborClassificator() {

	}

	public int classify(Representative toClassify, int numberOfClasses) {



		Neighbor[] neighbors = new Neighbor[getK()];

		for (int i = 0; i < data.length; i++) {
			double newDistance = data[i].distance(toClassify);
			if (i < getK()) {
				neighbors[i] = new Neighbor(toClassify, newDistance);
			} else if (newDistance < neighbors[neighbors.length - 1].distance) {
				neighbors[neighbors.length - 1] = new Neighbor(toClassify, newDistance);
				Arrays.sort(neighbors);
			}
		}
		int[] hist = new int[numberOfClasses];
		for (int i = 0; i < neighbors.length; i++) {
			hist[neighbors[i].representative.getDataClass()]++;
		}
		int max = 0;
		int number = -1;
		for (int i = 0; i < numberOfClasses; i++) {
			if (hist[i] > max) {
				max = hist[i];
				number = i;
			}
		}
		return number;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public Representative[] getData() {
		return data;
	}

	public void setData(Representative[] data) {
		this.data = data;
	}
}
