package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class MiscEntry extends Entry {

    public MiscEntry() {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR,false),
                new Field(FieldName.TITLE,false),
                new Field(FieldName.HOWPUBLISHED, false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.YEAR,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.MISC;
    }
}
