package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Representative;

public class DataReader {

	private File input;

	private ArrayList<Representative> data;
	
	private int pointsDimension = 2;

	public DataReader() {
	}

	public DataReader(String fName) {
		this.input = new File(fName);
	}

	public void setInput(String fName) {
		this.input = new File(fName);
	}

	public void read() {

		BufferedReader fReader = null;
		try {
			fReader = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
			e.printStackTrace();
		}
		this.data = new ArrayList<Representative>(1000);
		try {
			String lString = "";
			while ((lString = fReader.readLine()) != null) {
				String[] tokens = lString.trim().split("\\s+");
				double[] points = new double[tokens.length - 1];
				for (int i = 0; i < tokens.length - 1; i++) {
					points[i] = Double.parseDouble(tokens[i]);
				}
				Representative parsedNumber = new Representative(Integer.parseInt(tokens[tokens.length - 1]));
				parsedNumber.setPoints(points, pointsDimension);
				data.add(parsedNumber);
			}
		} catch (IOException e) {
			System.out.println("File could not be read!");
			e.printStackTrace();
		}
	}

	public List<Representative> getData() {
		return this.data;
	}
}
