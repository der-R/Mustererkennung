package classification;

import java.util.Arrays;
import java.util.List;

import data.Number;

public class NearestNeighborClassificator {

	int k = 1;
	List<Number> data;

	public NearestNeighborClassificator() {

	}

	public int classify(Number toClassify) {

		class Neighbor implements Comparable<Neighbor> {
			public final double distance;
			public final Number number;

			public Neighbor(Number number, double distance) {
				this.distance = distance;
				this.number = number;
			}

			@Override
			public int compareTo(Neighbor o) {
				if (this.distance < o.distance)
					return -1;
				else
					return 1;
			}
		}

		Neighbor[] neighbors = new Neighbor[getK()];

		for (int i = 0; i < data.size(); i++) {
			double newDistance = data.get(i).distance(toClassify);
			if (i < getK()) {
				neighbors[i] = new Neighbor(toClassify, newDistance);
			} else if (newDistance < neighbors[neighbors.length - 1].distance) {
				neighbors[neighbors.length - 1] = new Neighbor(toClassify, newDistance);
				Arrays.sort(neighbors);
			}
		}
		int[] hist = new int[10];
		for (int i = 0; i < neighbors.length; i++) {
			hist[neighbors[i].number.getNumber()]++;
		}
		int max = 0;
		int number = -1;
		for (int i = 0; i < neighbors.length; i++) {
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

	public List<Number> getData() {
		return data;
	}

	public void setData(List<Number> data) {
		this.data = data;
	}
}
