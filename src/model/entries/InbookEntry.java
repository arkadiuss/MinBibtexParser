package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class InbookEntry extends Entry {

    public InbookEntry(String quoteKey) {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR, FieldName.EDITOR, true),
                new Field(FieldName.TITLE, true),
                new Field(FieldName.CHAPTER, FieldName.PAGES,true),
                new Field(FieldName.PUBLISHER,true),
                new Field(FieldName.YEAR,true),
                new Field(FieldName.VOLUME, FieldName.NUMBER, false),
                new Field(FieldName.TYPE, false),
                new Field(FieldName.SERIES, false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.EDITION, false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.INBOOK;
        this.quoteKey = quoteKey;
    }
}
