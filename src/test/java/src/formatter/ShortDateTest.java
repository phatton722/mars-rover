package src.formatter;

import org.junit.Test;
import org.junit.Assert;

public class ShortDateTest {
    @Test
    public void testShortDateFormat() {
	ShortDate sd = new ShortDate();
	Assert.assertEquals("2000-01-01", sd.format("01/01/2000"));
	Assert.assertEquals("2000-02-02", sd.format("02/02/2000"));
	Assert.assertEquals("2000-03-03", sd.format("03/03/2000"));
	Assert.assertEquals("2000-04-04", sd.format("04/04/2000"));
	Assert.assertEquals("2000-05-05", sd.format("05/05/2000"));
	Assert.assertEquals("2000-06-06", sd.format("06/06/2000"));
	Assert.assertEquals("2000-07-07", sd.format("07/07/2000"));
	Assert.assertEquals("2000-08-08", sd.format("08/08/2000"));
	Assert.assertEquals("2000-09-09", sd.format("09/09/2000"));
	Assert.assertEquals("2000-10-10", sd.format("10/10/2000"));
	Assert.assertEquals("2000-11-11", sd.format("11/11/2000"));
	Assert.assertEquals("2000-12-12", sd.format("12/12/2000"));
	Assert.assertEquals("2000-1-1", sd.format("1-1-2000"));
	Assert.assertEquals("2000-2-2", sd.format("2-2-2000"));
	Assert.assertEquals("2000-3-3", sd.format("3-3-2000"));
	Assert.assertEquals("2000-4-4", sd.format("4-4-2000"));
	Assert.assertEquals("2000-5-5", sd.format("5-5-2000"));
	Assert.assertEquals("2000-6-6", sd.format("6-6-2000"));
	Assert.assertEquals("2000-7-7", sd.format("7-7-2000"));
	Assert.assertEquals("2000-8-8", sd.format("8-8-2000"));
	Assert.assertEquals("2000-9-9", sd.format("9-9-2000"));
	Assert.assertEquals("2000-10-10", sd.format("10-10-2000"));
	Assert.assertEquals("2000-11-11", sd.format("11-11-2000"));
	Assert.assertEquals("2000-12-12", sd.format("12-12-2000"));
	Assert.assertEquals("DOES NOT MATCH", sd.format("DOES NOT MATCH"));
    }

    @Test
    public void testShortDateMatch() {
	ShortDate sd = new ShortDate();
	Assert.assertEquals(true, sd.match("01-01-2000"));
	Assert.assertEquals(true, sd.match("1/1/2000"));
	Assert.assertEquals(false, sd.match("does not match"));
    }
}
