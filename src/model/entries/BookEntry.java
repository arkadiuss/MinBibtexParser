package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;

public class BookEntry extends Entry {

    public BookEntry() {
        fields = new Field[]{
                new Field(FieldName.AUTHOR, null, true),
                new Field(FieldName.TITLE, null, true)
        };
        type = EntryType.ARTICLE;
    }
}
