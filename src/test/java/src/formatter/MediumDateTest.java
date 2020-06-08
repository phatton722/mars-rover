package src.formatter;

import org.junit.Test;
import org.junit.Assert;

public class MediumDateTest {
    @Test
    public void testMediumDateFormat() {
	MediumDate md = new MediumDate();
	Assert.assertEquals("2000-1-1", md.format("Jan-1-2000"));
	Assert.assertEquals("2000-2-2", md.format("Feb-2-2000"));
	Assert.assertEquals("2000-3-3", md.format("Mar-3-2000"));
	Assert.assertEquals("2000-4-4", md.format("Apr-4-2000"));
	Assert.assertEquals("2000-5-5", md.format("May-5-2000"));
	Assert.assertEquals("2000-6-6", md.format("Jun-6-2000"));
	Assert.assertEquals("2000-7-7", md.format("Jul-7-2000"));
	Assert.assertEquals("2000-8-8", md.format("Aug-8-2000"));
	Assert.assertEquals("2000-9-9", md.format("Sep-9-2000"));
	Assert.assertEquals("2000-10-10", md.format("Oct-10-2000"));
	Assert.assertEquals("2000-11-11", md.format("Nov-11-2000"));
	Assert.assertEquals("2000-12-12", md.format("Dec-12-2000"));
	Assert.assertEquals("DOES NOT MATCH", md.format("DOES NOT MATCH"));
    }

    @Test
    public void testMediumDateMatch() {
	MediumDate md = new MediumDate();
	Assert.assertEquals(true, md.match("Jan-1-2000"));
	Assert.assertEquals(true, md.match("Feb-10-2000"));
	Assert.assertEquals(false, md.match("February-10-2000"));
	Assert.assertEquals(false, md.match("obviously doesn't match"));
    }
}
