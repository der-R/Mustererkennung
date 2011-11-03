package exercises;

import input.DataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jgnuplot.Axes;
import org.jgnuplot.Graph;
import org.jgnuplot.LineType;
import org.jgnuplot.Plot;
import org.jgnuplot.PointType;
import org.jgnuplot.Style;

import classification.NearestNeighborClassificator;
import data.DataGenerator;
import data.Representative;

public class First {

	public static void main(String[] args) {

		String input = "";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter which exercise you want to test!");
		try {
			input = reader.readLine();
		} catch (IOException e1) {
			System.out.println("Input could not be read!");
			e1.printStackTrace();
		}
		int ex = Integer.parseInt(input);
		if (ex == 1)
			exercise1();
		else if (ex == 2)
			exercise2();
		else if (ex == 3)
			exercise3();

	}

	private static void exercise3() {
		DataGenerator dG = new DataGenerator();
		NearestNeighborClassificator kNN = new NearestNeighborClassificator();
		for (int i = 10; i <= 1000; i = i + 10) {
			Representative[] trainingData = dG.generateDataSet(i, 1, 1, 2, 2);
			kNN.setData(trainingData);
			Representative[] testData = dG.generateDataSet(1000, 1, 1, 2, 2);
			String tableString = "";
			int[] kArray = new int[7];
			double[] recall = new double[7];
			for (int j = 1; j <= kArray.length; j++) {
				kNN.setK(j);
				recall[j - 1] = e2classify(testData, kNN, 2);
				kArray[j - 1] = j;
				tableString += j + "\t" + recall[j - 1] + "\n";
			}
//			tableString = tableString.trim();
//			Plot.setGnuplotExecutable("gnuplot");
//			File tmpFile = new File(System.getProperty("user.dir")
//					+ System.getProperty("file.separator") + ".tmp");
//			Plot.setPlotDirectory(tmpFile.getParent());
//			Plot nPlot = new Plot();
//			try {
//				FileWriter nWriter = new FileWriter(tmpFile);
//				nWriter.write(tableString);
//				nWriter.close();
//				Graph nGraph = new Graph(tmpFile.getAbsolutePath(), "1:2",
//						Axes.NOT_SPECIFIED, Style.LINES,
//						LineType.POSTSCRIPT_SOLID,
//						PointType.POSTSCRIPT_CIRCLE_DOT);
//				nPlot.pushGraph(nGraph);
//				nPlot.setTitle("Erkennungsrate über k");
//				nPlot.plot();
//			} catch (InterruptedException e) {
//				System.out.println("Interrupted!");
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			nPlot.clear();
//			String input = "";
//			BufferedReader reader;
//			reader = new BufferedReader(new InputStreamReader(System.in));
//			System.out
//					.println("Enter k for the kNN-classificator. If you enter -1, "
//							+ "the classificator will iterate through k = {1,..,7}");
//			try {
//				input = reader.readLine();
//			} catch (IOException e1) {
//				System.out.println("Input could not be read!");
//				e1.printStackTrace();
//			}
		}
	}

	private static void exercise2() {

		Representative[] trainingData = readData("data/01/pendigits-training.txt");
		NearestNeighborClassificator kNN = new NearestNeighborClassificator();
		kNN.setData(trainingData);
		Representative[] testData = readData("data/01/pendigits-testing.txt");
		String input = "";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Enter k for the kNN-classificator. If you enter -1, "
						+ "the classificator will iterate through k = {1,..,7}");
		try {
			input = reader.readLine();
		} catch (IOException e1) {
			System.out.println("Input could not be read!");
			e1.printStackTrace();
		}
		int k = Integer.parseInt(input);
		if (k > 0) {
			kNN.setK(k);
			e2classify(testData, kNN, 10);
		} else {
			String tableString = "";
			int[] kArray = new int[7];
			double[] recall = new double[7];
			for (int i = 1; i <= kArray.length; i++) {
				kNN.setK(i);
				recall[i - 1] = e2classify(testData, kNN, 10);
				kArray[i - 1] = i;
				tableString += i + "\t" + recall[i - 1] + "\n";
			}
			tableString = tableString.trim();
			Plot.setGnuplotExecutable("gnuplot");
			File tmpFile = new File(System.getProperty("user.dir")
					+ System.getProperty("file.separator") + ".tmp");
			Plot.setPlotDirectory(tmpFile.getParent());
			Plot nPlot = new Plot();
			try {
				FileWriter nWriter = new FileWriter(tmpFile);
				nWriter.write(tableString);
				nWriter.close();
				Graph nGraph = new Graph(tmpFile.getAbsolutePath(), "1:2",
						Axes.NOT_SPECIFIED, Style.LINES,
						LineType.POSTSCRIPT_SOLID,
						PointType.POSTSCRIPT_CIRCLE_DOT);
				nPlot.pushGraph(nGraph);
				nPlot.setTitle("Erkennungsrate über k");
				nPlot.plot();
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static double e2classify(Representative[] testData,
			NearestNeighborClassificator kNN, int numberOfClasses) {
		int correctClassified = 0;
		int falseClassifieds = 0;
		for (int i = 0; i < testData.length; i++) {
			if (testData[i].getDataClass() == kNN.classify(testData[i],numberOfClasses))
				correctClassified++;
			else
				falseClassifieds++;
		}
		System.out.println("k = " + kNN.getK() + "\nCorrect Classifications: "
				+ correctClassified + " \nWrong Classifications: "
				+ falseClassifieds);
		return (double) correctClassified
				/ (double) (correctClassified + falseClassifieds);
	}

	private static void exercise1() {
		Representative[] data = readData("data/01/pendigits-training.txt");
		String input = "";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter which data line you want to visualize!");
		try {
			input = reader.readLine();
		} catch (IOException e1) {
			System.out.println("Input could not be read!");
			e1.printStackTrace();
		}
		int dataLine = Integer.parseInt(input);
		try {
			if (dataLine < data.length)
				data[dataLine].plot();
			else
				System.out.println("Entered number is no valid line number!");
		} catch (IOException e) {
			System.out.println("Couldn't plot!");
			e.printStackTrace();
		}
	}

	private static Representative[] readData(String fName) {
		DataReader nReader = new DataReader(fName);
		nReader.read();
		Representative[] data = nReader.getData().toArray(new Representative[1]);
		return data;
	}
}
