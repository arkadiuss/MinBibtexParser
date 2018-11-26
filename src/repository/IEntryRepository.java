package repository;

import model.Entry;

import java.util.List;

public interface IEntryRepository {
    void addAll(List<Entry> entries);
    List<Entry> getAll();
}
