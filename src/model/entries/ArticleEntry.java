package model.entries;

import model.Entry;
import model.EntryType;
import model.Field;
import model.FieldName;

public class ArticleEntry extends Entry {

    public ArticleEntry() {
        fields = new Field[]{
                new Field(FieldName.AUTHOR,true),
                new Field(FieldName.TITLE,true),
                new Field(FieldName.JOURNAL,true),
                new Field(FieldName.YEAR,true),
                new Field(FieldName.VOLUME,false),
                new Field(FieldName.PAGES,false),
                new Field(FieldName.NUMBER,false),
                new Field(FieldName.MONTH,false),
                new Field(FieldName.NOTE,false),
                new Field(FieldName.KEY,false)
        };
        type = EntryType.ARTICLE;
    }
}
