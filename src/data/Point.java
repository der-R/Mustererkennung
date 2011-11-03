package data;

public class Point {

	private double[] x;
	
	public Point(double[] x ) {
		this.x = x;
	}
	
	public double distance(Point otherPoint) {
		if (this.x.length != otherPoint.getX().length)
			return -1;
		double distance = 0;
		for (int i = 0; i < x.length; i++) {
			distance += Math.pow((x[i]+otherPoint.getX()[i]),2);
		}
		return Math.sqrt(distance);
	}
	
	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		this.x = x;
	}
}
