package parser;

import exception.ParseException;
import model.Entry;

import java.util.List;

public interface IBibtexParser {
    List<Entry> parse(String bibtex) throws ParseException;
}
