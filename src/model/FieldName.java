package model;


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
    EDITION("edition");


    public String name;
    FieldName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
