package src.formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// MM/DD/YYYY || MM/DD/YY
// ShortDate assumes 21st century if year is provided as "YY"
public class ShortDate implements Formatter {
    private final Pattern pattern;

    public ShortDate() {
	this.pattern = Pattern.compile("([0-9]{1,2})\\s*[/-]\\s*([0-9]{1,2})[/-]\\s*([0-9]{2,4})$");
    }

    public String format(String date) {
	Matcher m = this.pattern.matcher(date);
	if (m.find()) {
	    String month = m.group(1);
	    String day = m.group(2);
	    String year = m.group(3);

	    // if year is 2 digits, we're going to assume 21st century for now
	    if (year.length() != 4) {
		year = "20" + year;
	    }

	    return year+"-"+month+"-"+day;
	}
	return date;
    }

    public boolean match(String date) {
	return this.pattern.matcher(date).matches();
    }
}
