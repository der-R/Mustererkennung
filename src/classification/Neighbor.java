package classification;

import data.Representative;

public class Neighbor implements Comparable<Neighbor> {
	public final double distance;
	public final Representative representative;

	public Neighbor(Representative representative, double distance) {
		this.distance = distance;
		this.representative = representative;
	}

	@Override
	public int compareTo(Neighbor o) {
		if (this.distance < o.distance)
			return -1;
		else
			return 1;
	}
}
