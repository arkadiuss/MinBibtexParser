package model;

import exception.ParseException;
import model.entries.ArticleEntry;
import model.entries.BookEntry;

import java.util.HashMap;
import java.util.Map;


public class EntryBuilder {
    private String type;
    private Map<String, String> fields = new HashMap<>();

    public void setType(String type){
        this.type = type;
    }

    public void setField(String field, String value){
        this.fields.put(field, value);
    }

    public Entry build() throws ParseException{
        EntryType type = EntryType.valueOf(this.type);
        Entry entry = getByType(type);
        validateAndAssignValues(entry);
        return entry;
    }

    private Entry getByType(EntryType type) throws ParseException {
        switch (type) {
            case BOOK:
                return new BookEntry();
            case ARTICLE:
                return new ArticleEntry();
        }
        throw new ParseException("Unknown type exception");
    }

    private void validateAndAssignValues(Entry e) throws ParseException {
        for(int i=0;i<e.fields.length;i++){
            String value = fields.get(e.fields[i].name.name);
            if(value == null && e.fields[i].isRequired)
                throw new ParseException("Entry doesn't contain a required field "+e.fields[i].name);
            e.fields[i] = new Field(
                    e.fields[i].name, value, e.fields[i].isRequired);
        }
    }
}