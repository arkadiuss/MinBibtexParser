package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class BookletEntry extends Entry {

    public BookletEntry(String quoteKey) {
        fields = new Field[]{
                new Field(FieldName.TITLE, true),
                new AuthorField(FieldName.AUTHOR,false),
                new Field(FieldName.HOWPUBLISHED,false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.YEAR,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.BOOKLET;
        this.quoteKey = quoteKey;
    }
}
