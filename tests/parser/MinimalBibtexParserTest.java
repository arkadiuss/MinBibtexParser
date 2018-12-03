package parser;

import exception.ParseException;
import model.Entry;
import model.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MinimalBibtexParserTest {
    private IBibtexParser parser = new MinimalBibtexParser();
    private String basicAuthor = "Good basicAuthor";
    private String basicTitle = "Good book";
    private String basicPublisher = "Good publisher";
    private String basicYear = "1900";

    @Test
    void shouldParseSimpleEntry() throws ParseException {
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuote(basicAuthor) + "\n," +
                "title = "+wrapWithQuote(basicTitle)+"\n,"+
                "publisher = "+wrapWithQuote(basicPublisher)+"\n,"+
                "year="+wrapWithQuote(basicYear)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        assertArrayEquals(
                new String[]{basicAuthor, basicTitle, basicPublisher, basicYear},
                getEntriesList(e));
    }

    @Test
    void shouldParseEntryWithStringVariable(){
        String titleWithVariable = "\"Title\" # variable # \"end\""
        String entry = "@BOOK{\n" +
                "author = " + titleWithVariable + "\n," +
                "title = "+wrapWithQuote(basicTitle)+"\n,"+
                "publisher = "+wrapWithQuote(basicPublisher)+"\n,"+
                "year="+wrapWithQuote(basicYear)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        assertArrayEquals(
                new String[]{basicAuthor, basicTitle, basicPublisher, basicYear},
                getEntriesList(e));
    }

    private String[] getEntriesList(Entry e){
        return Arrays.stream(e.getFields())
                        .map(Field::getValue)
                        .filter(Objects::nonNull)
                        .toArray(String[]::new);
    }

    private String wrapWithQuote(String s){
        return "\""+s+"\"";
    }
}