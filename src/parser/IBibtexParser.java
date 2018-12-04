package parser;

import exception.ParseException;
import model.Entry;

import java.util.List;

/**
 * Bibtex parser which enables to
 * validate and parse given bibtex
 * to object representation
 */
public interface IBibtexParser {
    /**
     * Method that parse given string, which
     * contains bibtex, to Entry, which is
     * an object representation of given
     * records. It also validate if given
     * string match bibtex format.
     *
     * @param bibtex String that contains content of bibtex file
     * @return List of entries from bibtex string
     * @throws ParseException when format of file is incorrect
     */
    List<Entry> parse(String bibtex) throws ParseException;
}
