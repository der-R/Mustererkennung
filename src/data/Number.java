package data;

import java.awt.Point;

public class Number {
	
	private int number;
	private Point[] points;
	
	public Number(int number){
		this.number = number; 
	}
	
	public void setPoints(int[] points) {
		this.points = new Point[points.length/2];
		for (int i = 0; i < this.points.length; i++) {
			this.points[i] = new Point(points[i*2], points[2*i + 1]);
		}
	}

	public int getNumber() {
		return number;
	}
	
	
}
