package src.formatter;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DateFormatter {
    private final Formatter[] formatters;

    public DateFormatter(Formatter... formatters) {
	this.formatters = formatters;
    }

    public ArrayList<String> formatDates(ArrayList<String> dates) {
	ArrayList<String> formattedDates = new ArrayList<String>();
	for (String date : dates) {
	    String formattedDate = date;
	    for (Formatter f : this.formatters) {
		if (f.match(date)) {
		    formattedDate = f.format(date);
		}
	    }
	    formattedDates.add(formattedDate);
	}
	return formattedDates;
    }
}
