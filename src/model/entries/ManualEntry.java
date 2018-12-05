package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class ManualEntry extends Entry {

    public ManualEntry() {
        fields = new Field[]{
                new Field(FieldName.TITLE, true),
                new AuthorField(FieldName.AUTHOR,false),
                new Field(FieldName.ORGANIZATION,false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.EDITION,false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.YEAR,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.BOOK;
    }
}
