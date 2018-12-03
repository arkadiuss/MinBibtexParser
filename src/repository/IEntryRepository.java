package repository;

import model.Entry;

import java.util.List;

public interface IEntryRepository {
    void addAll(List<Entry> entries);
    List<Entry> getAll();
    List<Entry> getAllByType(String type);
    List<Entry> getAllByTypes(List<String> types);
    List<Entry> getAllByAuthorName(String authorName);
    List<Entry> getAllByAuthorNames(List<String> authorName);
}
