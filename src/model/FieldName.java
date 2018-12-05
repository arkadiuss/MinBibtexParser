package model;


/**
 * Enum for all possible field names
 */
public enum FieldName {
    AUTHOR("author"),
    EDITOR("editor"),
    TITLE("title"),
    JOURNAL("journal"),
    YEAR("year"),
    VOLUME("volume"),
    NUMBER("number"),
    PAGES("pages"),
    MONTH("month"),
    NOTE("note"),
    KEY("key"),
    PUBLISHER("publisher"),
    ADDRESS("address"),
    SERIES("series"),
    EDITION("edition"),
    BOOKTITLE("booktitle"),
    ORGANIZATION("organization"),
    HOWPUBLISHED("howpublished"),
    CHAPTER("chapter"),
    TYPE("type"),
    SCHOOL("SCHOOL"),
    INSTITUTION("INSTITUTION");


    public String name;
    FieldName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
