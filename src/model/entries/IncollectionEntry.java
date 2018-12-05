package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class IncollectionEntry extends Entry {

    public IncollectionEntry() {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR,true),
                new Field(FieldName.TITLE, true),
                new Field(FieldName.BOOKTITLE, true),
                new Field(FieldName.PUBLISHER,true),
                new Field(FieldName.YEAR,true),
                new Field(FieldName.EDITOR, false),
                new Field(FieldName.VOLUME, FieldName.NUMBER,false),
                new Field(FieldName.SERIES, false),
                new Field(FieldName.TYPE, false),
                new Field(FieldName.CHAPTER, false),
                new Field(FieldName.PAGES, false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.EDITION, false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.INCOLLECTION;
    }
}
