package model;

/**
 * Enum that represents allowed entries' types
 */
public enum EntryType {
    BOOK("BOOK"), ARTICLE("ARTICLE");

    /**
     * Name that occurs in bibtex file
     */
    String name;
    EntryType(String name){
        this.name = name;
    }
}
