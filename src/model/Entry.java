package model;

import java.util.Arrays;

/**
 * Object representation for bibtex entry
 */
public class Entry {
    protected Field[] fields;
    protected EntryType type;

    public Field[] getFields() {
        return fields;
    }

    public EntryType getType() {
        return type;
    }

    /**
     * This method converts all non-empty fields
     * into string representation of this entry.
     *
     * @return String representation of entry
     */
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

    /**
     * Method that returns true if
     * value of given field contains
     * pattern as a substring
     *
     * @param fieldName Field to check
     * @param pattern String which value has to contain as substring
     * @return true is value contains pattern as substring or else false
     */
    public boolean fieldContains(FieldName fieldName, String pattern){
        Field f = Arrays.stream(fields)
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return f.contains(pattern);
    }
}
