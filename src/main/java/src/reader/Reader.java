package src.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private final String fileName;

    public Reader(String fileName) {
	this.fileName = fileName;
    }

    public ArrayList<String> Read() throws FileNotFoundException {
	Scanner scanner = new Scanner(new File(this.fileName));

	ArrayList<String> lines = new ArrayList<String>();
	while (scanner.hasNextLine()) {
	    lines.add(scanner.nextLine());
	}
	scanner.close();

	return lines;
    }
}
