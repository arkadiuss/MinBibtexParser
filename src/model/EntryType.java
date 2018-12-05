package model;

/**
 * Enum that represents allowed entries' types
 */
public enum EntryType {
    BOOK("BOOK"),
    ARTICLE("ARTICLE"),
    INPROCEEDINGS("INPROCEEDINGS"),
    BOOKLET("BOOKLET"),
    INBOOK("INBOOK"),
    INCOLLECTION("INCOLLECTION"),
    MANUAL("MANUAL"),
    MASTERTHESIS("MASTERTHESIS"),
    PHDTHESIS("PHDTHESIS"),
    TECHREPORT("TECHREPORT"),
    MISC("MISC"),
    UNPUBLISHED("UNPUBLISHED");

    /**
     * Name that occurs in bibtex file
     */
    String name;
    EntryType(String name){
        this.name = name;
    }
}
