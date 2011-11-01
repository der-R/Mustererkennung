package exercises;

import input.NumberReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import classification.NearestNeighborClassificator;

import data.Number;

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
		// TODO Auto-generated method stub
		
	}

	private static void exercise2() {
		
		List<Number> trainingData = readData("data/01/pendigits-training.txt");
		NearestNeighborClassificator kNN = new NearestNeighborClassificator();
		kNN.setData(trainingData);
		List<Number> testData = readData("data/01/pendigits-testing.txt");
		String input = "";
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter k for the kNN-Classificator");
		try {
			input = reader.readLine();
		} catch (IOException e1) {
			System.out.println("Input could not be read!");
			e1.printStackTrace();
		}
		int k = Integer.parseInt(input);
		kNN.setK(k);
		
		int correctClassified = 0;
		int falseClassifieds = 0;
		for (int i = 0; i < testData.size(); i++) {
			if (testData.get(i).getNumber() == kNN.classify(testData.get(i)))
				correctClassified++;
			else
				falseClassifieds++;
		}
		System.out.println("Correct Classifications: " + correctClassified + " \nWrong Classifications: " + falseClassifieds);
	}

	private static void exercise1(){
		List<Number> data = readData("data/01/pendigits-training.txt");
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
			if (dataLine < data.size())
				data.get(dataLine).plot();
			else
				System.out.println("Entered number is no valid line number!");
		} catch (IOException e) {
			System.out.println("Couldn't plot!");
			e.printStackTrace();
		}
	}

	private static List<Number> readData(String fName) {
		NumberReader nReader = new NumberReader(fName);
		nReader.read();
		List<Number> data = nReader.getData();
		return data;
	}
}
