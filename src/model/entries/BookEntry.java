package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;

public class BookEntry extends Entry {

    public BookEntry() {
        fields = new Field[]{
                new Field(FieldName.AUTHOR,true),
                new Field(FieldName.TITLE, true),
                new Field(FieldName.PUBLISHER,true),
                new Field(FieldName.YEAR,true),
                new Field(FieldName.VOLUME,false),
                new Field(FieldName.SERIES, false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.EDITION, false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.ARTICLE;
    }
}
