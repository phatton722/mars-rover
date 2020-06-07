package src.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MediumDate implements Formatter {
    private final Pattern pattern;
    private final Map<String, String> months;

    public MediumDate() {
	this.pattern = Pattern.compile("([a-zA-Z]{3})-([0-9]{1,2})-([0-9]{4})");
	this.months = new HashMap<String, String>() {
	    {
		put("jan", "1");
		put("feb", "2");
		put("mar", "3");
		put("apr", "4");
		put("may", "5");
		put("jun", "6");
		put("jul", "7");
		put("aug", "8");
		put("sep", "9");
		put("oct", "10");
		put("nov", "11");
		put("dec", "12");
	    }
	};
    }

    public String format(String date) {
	Matcher m = this.pattern.matcher(date);
	if (m.find()) {
	    String month = this.months.get(m.group(1).toLowerCase());
	    String day = m.group(2);
	    String year = m.group(3);
	    if (month != null) {
		return year+"-"+month+"-"+day;
	    }
	}
	return date;
    }

    public boolean match(String date) {
	return this.pattern.matcher(date).matches();
    }
}
