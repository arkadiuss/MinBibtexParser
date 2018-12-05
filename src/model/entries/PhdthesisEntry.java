package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;
import model.fields.AuthorField;

public class PhdthesisEntry extends Entry {

    public PhdthesisEntry() {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR,true),
                new Field(FieldName.TITLE, true),
                new Field(FieldName.SCHOOL,true),
                new Field(FieldName.YEAR,true),
                new Field(FieldName.TYPE,false),
                new Field(FieldName.ADDRESS,false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.NOTE, false),
                new Field(FieldName.KEY, false)
        };
        type = EntryType.PHDTHESIS;
    }
}
