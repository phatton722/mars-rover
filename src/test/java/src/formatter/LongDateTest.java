package src.formatter;

import org.junit.Assert;
import org.junit.Test;

public class LongDateTest {
    @Test
    public void testLongDateFormat() {
	LongDate ld = new LongDate();
	Assert.assertEquals("2000-1-1", ld.format("January 1, 2000"));
	Assert.assertEquals("2000-2-2", ld.format("February 2, 2000"));
	Assert.assertEquals("2000-3-3", ld.format("March 3, 2000"));
	Assert.assertEquals("2000-4-4", ld.format("April 4, 2000"));
	Assert.assertEquals("2000-5-5", ld.format("May 5, 2000"));
	Assert.assertEquals("2000-6-6", ld.format("June 6, 2000"));
	Assert.assertEquals("2000-7-7", ld.format("July 7, 2000"));
	Assert.assertEquals("2000-8-8", ld.format("August 8, 2000"));
	Assert.assertEquals("2000-9-9", ld.format("September 9, 2000"));
	Assert.assertEquals("2000-10-10", ld.format("October 10, 2000"));
	Assert.assertEquals("2000-11-11", ld.format("November 11, 2000"));
	Assert.assertEquals("2000-12-12", ld.format("December 12, 2000"));
	Assert.assertEquals("DOES NOT MATCH", ld.format("DOES NOT MATCH"));
    }

    @Test
    public void testLongDateMatch() {
	LongDate ld = new LongDate();
	Assert.assertEquals(true, ld.match("January 1, 2000"));
	Assert.assertEquals(true, ld.match("December 12, 2000"));
	Assert.assertEquals(false, ld.match("DOES NOT MATCH"));
    }
}
