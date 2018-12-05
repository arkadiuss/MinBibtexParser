package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class UnpublishedEntry extends Entry {

    public UnpublishedEntry() {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR,true),
                new Field(FieldName.TITLE,true),
                new Field(FieldName.NOTE, true),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.YEAR,false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.UNPUBLISHED;
    }
}
