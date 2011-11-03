package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgnuplot.Axes;
import org.jgnuplot.Graph;
import org.jgnuplot.LineType;
import org.jgnuplot.Plot;
import org.jgnuplot.PointType;
import org.jgnuplot.Style;

public class Representative {

	private Point[] points;
	private int dataClass = -1;

	public Representative(Point[] points, int dataClass) {
		this.points = points;
		this.dataClass = dataClass;
	}

	public Representative(int dataClass) {
		this.dataClass = dataClass;
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public int getDataClass() {
		return dataClass;
	}

	public void setDataClass(int dataClass) {
		this.dataClass = dataClass;
	}

	public void setPoints(double[] points, int dim) {
		this.points = new Point[points.length / dim];
		for (int i = 0; i < this.points.length; i++) {
			double[] tmp = new double[dim];
			for (int j = 0; j < dim; j++) {
				tmp[j] = points[i * dim + j];
			}
			this.points[i] = new Point(tmp);
		}
	}

	public double distance(Representative toClassify) {
		double distance = 0;
		for (int i = 0; i < this.points.length; i++) {
			distance += this.points[i].distance(toClassify.points[i]);
		}
		return distance;
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
		nPlot.setTitle("Number: " + dataClass);
		try {
			nPlot.plot();
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
			e.printStackTrace();
		}
	}

	private String getTableString() {

		String tString = "";
		for (int i = 0; i < points.length; i++) {
			double[] pArray = points[i].getX();
			for (int j = 0; j < pArray.length; j++) {
				tString += pArray[j] + "\t";
			}
			tString = tString.trim() + "\n";
		}
		tString = tString.trim();
		return tString;
	}

}
