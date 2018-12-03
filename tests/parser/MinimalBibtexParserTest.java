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
    private String basicAuthor = "Good author";
    private String basicTitle = "Good book";
    private String basicPublisher = "Good publisher";
    private String basicYear = "1900";

    @Test
    void shouldParseSimpleEntry() throws ParseException {
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuotes(basicAuthor) + "\n," +
                "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                "year="+ wrapWithQuotes(basicYear)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        assertArrayEquals(
                new String[]{basicAuthor, basicTitle, basicPublisher, basicYear},
                getEntriesList(e));
    }

    @Test
    void shouldParseEntryWithStringVariable() throws ParseException {
        String titleWithVariable = "\"start\" # variable # \"end\"";
        String entry =
                "@STRING{" +
                    "variable = \"value\""+
                "}\n\n" +
                "@BOOK{\n" +
                    "author = " + titleWithVariable + "\n," +
                    "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                    "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                    "year="+ wrapWithQuotes(basicYear)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        assertArrayEquals(
                new String[]{"startvalueend", basicTitle, basicPublisher, basicYear},
                getEntriesList(e));
    }

    @Test
    void shouldThrowWhenTypeUnknown() {
        String entry = "@UNKNOWN{\n" +
                "author = " + wrapWithQuotes(basicAuthor) + "\n," +
                "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                "year="+ wrapWithQuotes(basicYear)+"\n"+
                "}";
        assertThrows(ParseException.class,() -> parser.parse(entry));
    }

    @Test
    void shouldThrowParseExceptionWhenEntryDoesntContainRequiredField() {
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuotes(basicAuthor) + "\n," +
                "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                "}";
        assertThrows(ParseException.class,() -> parser.parse(entry));
    }

    @Test
    void shouldAcceptOptionalFields() throws ParseException {
        String volume = "good volume";
        String series = "good series";
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuotes(basicAuthor) + "\n," +
                "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                "year="+ wrapWithQuotes(basicYear)+"\n,"+
                "volume="+ wrapWithQuotes(volume)+"\n,"+
                "series="+ wrapWithQuotes(series)+"\n"+
                "}";
        List<Entry> entries = parser.parse(entry);
        assertEquals(1,entries.size());
        Entry e = entries.get(0);
        assertArrayEquals(
                new String[]{
                        basicAuthor, basicTitle,
                        basicPublisher, basicYear,
                        volume, series
                },
                getEntriesList(e));
    }

    @Test
    void shouldSkipUnknownFields() throws ParseException {
        String entry = "@BOOK{\n" +
                "author = " + wrapWithQuotes(basicAuthor) + "\n," +
                "title = "+ wrapWithQuotes(basicTitle)+"\n,"+
                "publisher = "+ wrapWithQuotes(basicPublisher)+"\n,"+
                "year="+ wrapWithQuotes(basicYear)+"\n,"+
                "unknown=\"unknown\"\n"+
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

    private String wrapWithQuotes(String s){
        return "\""+s+"\"";
    }
}