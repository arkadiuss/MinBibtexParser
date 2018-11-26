package model;

import java.util.Arrays;

public enum EntryType {
    BOOK("BOOK"), ARTICLE("ARTICLE");

    String name;
    EntryType(String name){
        this.name = name;
    }

    public static boolean contains(String name){
        return Arrays.stream(values()).anyMatch(fieldName -> fieldName.name.equals(name));
    }

}
