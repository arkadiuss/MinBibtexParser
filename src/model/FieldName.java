package model;

import java.util.Arrays;

public enum FieldName {
    AUTHOR("author"),
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
