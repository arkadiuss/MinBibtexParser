package model;

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
    KEY("key");

    private String name;
    FieldName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
