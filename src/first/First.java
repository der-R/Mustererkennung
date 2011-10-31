package first;

import java.io.IOException;
import java.util.List;

import data.Number;

import input.NumberReader;

public class First {

	public static void main(String[] args) {

		NumberReader nReader = new NumberReader(args[0]);
		nReader.read();
		List<Number> data = nReader.getData();
		try {
			data.get(1).plot();
		} catch (IOException e) {
			System.out.println("Couldn't plot!");
			e.printStackTrace();
		}
	}
}
