package parser;

import exception.ParseException;
import model.Entry;
import model.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MinimalBibtexParserTest {

    @Test
    void shouldParseSimpleEntry() throws ParseException {
        IBibtexParser parser = new MinimalBibtexParser();
        String author = "Good author";
        String title = "Good book";
        String publisher = "Good publisher";
        String year = "1900";
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuote(author) + "\n," +
                "title = "+wrapWithQuote(title)+"\n,"+
                "publisher = "+wrapWithQuote(publisher)+"\n,"+
                "year="+wrapWithQuote(year)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        String[] entriesFields =
                Arrays.stream(e.getFields())
                .map(Field::getValue)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
        assertArrayEquals(
                new String[]{author, title, publisher,year},
                entriesFields);
    }

    private String wrapWithQuote(String s){
        return "\""+s+"\"";
    }
}