package model;

import java.util.Arrays;
import java.util.Objects;

public class Entry {
    protected Field[] fields;
    protected EntryType type;

    public Field[] getFields() {
        return fields;
    }

    public EntryType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder entryString = new StringBuilder();
        entryString.append(type.name.toUpperCase()).append("\n");
        Arrays.stream(fields)
                .filter(field -> field.getValue() != null)
                .map(Field::toString)
                .forEach(entryString::append);
        entryString.append("\n\n");
        return entryString.toString();
    }

    public boolean fieldContains(FieldName fieldName, String pattern){
        Field f = Arrays.stream(fields)
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return f.contains(pattern);
    }
}
