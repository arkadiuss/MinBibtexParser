package model.entries;

import model.*;
import model.fields.AuthorField;
import model.fields.MultivalueField;

public class ArticleEntry extends Entry {

    public ArticleEntry() {
        fields = new Field[]{
                new AuthorField(FieldName.AUTHOR, FieldName.EDITOR,true),
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
