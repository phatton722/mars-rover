package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import src.formatter.DateFormatter;
import src.formatter.ShortDate;
import src.formatter.MediumDate;
import src.formatter.LongDate;
import src.nasaimage.NasaImageFinder;
import src.nasaimage.NasaImageViewer;
import src.reader.Reader;

public class App {
    public static void main(String[] args) {
	String datesFile = args[0];
	String imageCache = args[1];

	// read the dates text file
	Reader reader = new Reader(datesFile);
	ArrayList<String> dates = null;
	try {
	  dates = reader.Read();
	} catch (FileNotFoundException e) {
	  e.printStackTrace();
	  return;
	}

	// format the dates
	DateFormatter df = new DateFormatter(new ShortDate(), new MediumDate(), new LongDate());
	ArrayList<String> formattedDates = df.formatDates(dates);

	// get images by earth date
	NasaImageFinder nif = new NasaImageFinder();
	Map<String, ArrayList<String>> mapDateToImageFiles = null;
	try {
	  mapDateToImageFiles = nif.downloadAndStoreImagesByEarthDate(formattedDates, imageCache);
	  if (mapDateToImageFiles == null || mapDateToImageFiles.size() == 0) {
	    System.out.println("no images returned for provided dates, exiting");
	    return;
	  }
	} catch (Exception e) {
	  e.printStackTrace();
	  return;
	}

	NasaImageViewer niv = new NasaImageViewer(mapDateToImageFiles);
	try {
	    niv.open();
	} catch (IOException e) {
	    e.printStackTrace();
	    return;
	}
    }
}
