package src.nasaimage;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class NasaImageViewer {
    private final Map<String, ArrayList<String>> mapDatesToImageFiles;

    public NasaImageViewer(Map<String, ArrayList<String>> mapDatesToImageFiles) {
	this.mapDatesToImageFiles = mapDatesToImageFiles;
    }

    public void open() throws IOException {
	StringBuffer html = new StringBuffer();
	html.append(this.getStyleHTML());
	html.append("<body>");

	for (Map.Entry<String, ArrayList<String>> dateToFiles : this.mapDatesToImageFiles.entrySet()) {
	    String date = dateToFiles.getKey();
	    ArrayList<String> files = dateToFiles.getValue();
	    html.append(this.getImageHTML(date, files));
	}

	html.append("</body>");

	File f = new File("dates.html");
	BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
	bwr.write(html.toString());
	bwr.flush();

	Desktop desktop = Desktop.getDesktop();
	desktop.open(f);
    }

    private String getStyleHTML() {
	return "<style> .date { display: flex; flex-direction: row; flex-wrap: wrap; justify-content: flex-start; align-items: center; } img { padding: 5px 5px 5px 5px; height: 150px; width: 150px; } </style>";
    }

    private String getImageHTML(String date, ArrayList<String> files) {
	StringBuffer imageHTML = new StringBuffer();
	imageHTML.append(String.format("<h2>%s</h2>", date));
	imageHTML.append("<div class=\"date\">");
	for (String file : files) {
	    imageHTML.append(String.format("<img src=\"%s\" />", file));
	}
	imageHTML.append("</div>");
	return imageHTML.toString();
    }
}
