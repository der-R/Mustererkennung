package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import data.Number;

public class NumberReader {

	private File input;

	private List<Number> data;

	public NumberReader() {

	}

	public NumberReader(String fName) {
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
		this.data = new ArrayList<Number>(1000);
		try {
			String lString = "";
			while ((lString = fReader.readLine()) != null) {
				String[] tokens = lString.trim().split("\\s+");
				int[] points = new int[tokens.length - 1];
				for (int i = 0; i < tokens.length - 1; i++) {
					points[i] = Integer.parseInt(tokens[i]);
				}
				Number parsedNumber = new Number(
						Integer.parseInt(tokens[tokens.length - 1]));
				parsedNumber.setPoints(points);
				data.add(parsedNumber);
			}
		} catch (IOException e) {
			System.out.println("File could not be read!");
			e.printStackTrace();
		}
	}

	public List<Number> getData() {
		return this.data;
	}
}
