package repository;

import model.Entry;
import model.FieldName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Entry> getAllByType(String type) {
        return this.entries.stream()
                .filter(e ->
                        e.getType().name().equals(type.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Entry> getAllByTypes(List<String> types) {
        List<String> castedTypes = types.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return this.entries.stream()
                .filter(e ->{
                    String name = e.getType().name();
                    return castedTypes.contains(name);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Entry> getAllByAuthorName(String authorName) {
        return this.entries.stream()
                .filter(entry -> entry.fieldContains(FieldName.AUTHOR, authorName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Entry> getAllByAuthorNames(List<String> authorNames) {
        return this.entries.stream()
                .filter(entry ->
                        authorNames.stream()
                                .anyMatch(name ->
                                        entry.fieldContains(FieldName.AUTHOR, name)))
                .collect(Collectors.toList());
    }

}
