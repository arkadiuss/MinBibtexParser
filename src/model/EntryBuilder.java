package model;

import exception.ParseException;

import java.lang.reflect.Constructor;
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

    public Entry build() throws ParseException {
        Entry entry = getByType(type);
        validateAndAssignValues(entry);
        return entry;
    }

    private Entry getByType(String type) throws ParseException {
        try {
            String capType = capitalize(type);
            Class cl = Class.forName("model.entries."+capType + "Entry");
            return (Entry) cl.newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                ClassNotFoundException e) {
            throw new ParseException("Cannot create class by type");
        }
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

    private String capitalize(String text){
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }
}