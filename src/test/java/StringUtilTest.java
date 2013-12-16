import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void shouldSplitBySpace() {
        String str = "blah hello";
        List<String> result = StringUtil.quoteSensitiveSplit(str);
        assertEquals(2, result.size());
        assertEquals("blah", result.get(0));
        assertEquals("hello", result.get(1));
    }

    @Test
    public void shouldIgnoreMultipleSpaces() {
        String str = "blah hello     world";
        List<String> result = StringUtil.quoteSensitiveSplit(str);
        assertEquals(3, result.size());
        assertEquals("blah", result.get(0));
        assertEquals("hello", result.get(1));
        assertEquals("world", result.get(2));
    }

    @Test
    public void shouldNotSplitTextWithinDoubleQuote() {
        String str = "blah \"hello    world\" fancy \"gandalf.frodo.com\"";
        List<String> result = StringUtil.quoteSensitiveSplit(str);
        System.out.println(result);
        assertEquals(4, result.size());
        assertEquals("blah", result.get(0));
        assertEquals("\"hello    world\"", result.get(1));
        assertEquals("fancy", result.get(2));
        assertEquals("\"gandalf.frodo.com\"", result.get(3));
    }

    @Test
    public void shouldHandleSpacesAtTheBeginning() {
        String str = "   blah hello     world";
        List<String> result = StringUtil.quoteSensitiveSplit(str);
        assertEquals(3, result.size());
        assertEquals("blah", result.get(0));
        assertEquals("hello", result.get(1));
        assertEquals("world", result.get(2));
    }
}
