package repository;

import model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepository implements IEntryRepository {

    private List<Entry> entries = new ArrayList<>();

    @Override
    public void addAll(List<Entry> entries) {
        this.entries.addAll(entries);
    }

    @Override
    public List<Entry> getAll() {
        return this.entries;
    }
}
