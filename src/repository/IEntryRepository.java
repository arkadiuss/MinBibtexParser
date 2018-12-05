package repository;

import model.Entry;

import java.util.List;

/**
 * Container for parsed entries, which enables
 * to add entries and get them by specific criteria
 */
public interface IEntryRepository {
    /**
     * Method that allows to add many entries to repository,
     * which enable to keep and get them later
     * @param entries List of parsed entries
     */
    void addAll(List<Entry> entries);

    /**
     * Method that get all added entries
     * @return List of entries
     */
    List<Entry> getAll();

    /**
     * Method that get entries from repository
     * with requested type
     *
     * @param type Type to filter by
     * @return List of entries that match type criteria
     */
    List<Entry> getAllByType(String type);

    /**
     * Method that get entries from repository
     * which match any type from the given list
     *
     * @param types List of types to filter by
     * @return List of entries that match types criteria
     */
    List<Entry> getAllByTypes(List<String> types);

    /**
     * Method that get entries from repository
     * which author's contain given string as substring
     *
     * @param authorName String to filter by
     * @return List of entries that match author criteria
     */
    List<Entry> getAllByAuthorName(String authorName);

    /**
     * Method that get entries from repository
     * which author's contain any of given string
     * as substring
     *
     * @param authorNames List of strings to filter by
     * @return List of entries that match author criteria
     */
    List<Entry> getAllByAuthorNames(List<String> authorNames);

    /**
     * Method that get entries from repository
     * which type match any of given types and
     * which author contains any string from
     * authorNames list as substring
     *
     * @param types List of types to filter by
     * @param authorNames List of authors' names to filter by
     * @return List of entries that match both criteria
     */
    List<Entry> getAllByTypesAndAuthorNames(List<String> types, List<String> authorNames);

    List<Entry> geyByYear(String year);
}
