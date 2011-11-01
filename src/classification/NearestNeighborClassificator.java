package classification;

import java.util.List;

import data.Number;

public class NearestNeighborClassificator {

	int k =1;
	List<Number> data;
	
	public NearestNeighborClassificator() {
		
	}
	
	public int classify(Number toClassify) {
		double distance = Double.MAX_VALUE;
		Number nearestNeighbor = null;
		for (int i = 0; i < data.size(); i++) {
			double newDistance = data.get(i).distance(toClassify);
			if (newDistance < distance) {
				distance = newDistance;
				nearestNeighbor = data.get(i);
			}
		}
		return nearestNeighbor.getNumber();
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
