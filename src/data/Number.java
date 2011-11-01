package data;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgnuplot.Axes;
import org.jgnuplot.Graph;
import org.jgnuplot.LineType;
import org.jgnuplot.Plot;
import org.jgnuplot.PointType;
import org.jgnuplot.Style;

public class Number {

	private int number;
	private Point[] points;

	public Number() {
		this.number = -1;
	}

	public Number(int number) {
		this.number = number;
	}

	public void setPoints(int[] points) {
		this.points = new Point[points.length / 2];
		for (int i = 0; i < this.points.length; i++) {
			this.points[i] = new Point(points[i * 2], points[2 * i + 1]);
		}
	}

	public int getNumber() {
		return number;
	}

	public void plot() throws IOException {
		Plot.setGnuplotExecutable("gnuplot");
		File tmpFile = new File(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + ".tmp");
		Plot.setPlotDirectory(tmpFile.getParent());
		Plot nPlot = new Plot();
		FileWriter nWriter = new FileWriter(tmpFile);
		nWriter.write(getTableString());
		nWriter.close();
		Graph nGraph = new Graph(tmpFile.getAbsolutePath(), "1:2",
				Axes.NOT_SPECIFIED, Style.LINES, LineType.POSTSCRIPT_SOLID,
				PointType.POSTSCRIPT_CIRCLE_DOT);
		nPlot.pushGraph(nGraph);
		System.out.println(tmpFile.getParent());
		nPlot.setTitle("Number: " + Integer.toString(number));
		try {
			nPlot.plot();
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
			e.printStackTrace();
		}
	}

	private String getYString() {

		String yString = "";
		for (int i = 0; i < points.length; i++) {
			yString += Integer.toString(points[i].y) + "\n";
		}
		yString.trim();
		return yString;
	}

	private String getXString() {

		String xString = "";
		for (int i = 0; i < points.length; i++) {
			xString += Integer.toString(points[i].x) + "\n";
		}
		xString.trim();
		return xString;
	}

	private String getTableString() {

		String tString = "";
		for (int i = 0; i < points.length; i++) {
			tString += Integer.toString(points[i].x) + "\t"
					+ Integer.toString(points[i].y) + "\n";
		}
		tString.trim();
		return tString;
	}

	public double distance(Number toClassify) {
		double distance = 0;
		for (int i = 0; i < this.points.length; i++) {
			distance = this.points[i].distance(toClassify.points[i]);
		}
		return 1;
	}

}
