package src.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongDate implements Formatter {
    private final Pattern pattern;
    private final Map<String, String> months;

    public LongDate() {
	this.pattern = Pattern.compile("^([a-zA-z]{3,9})\\s*([0-9]{1,2})\\s*,\\s*([0-9]{4})$");
	this.months = new HashMap<String, String>() {
	    {
		put("january", "1");
		put("february", "2");
		put("march", "3");
		put("april", "4");
		put("may", "5");
		put("june", "6");
		put("july", "7");
		put("august", "8");
		put("september", "9");
		put("october", "10");
		put("november", "11");
		put("december", "12");
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
