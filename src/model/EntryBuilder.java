package model;

import exception.ParseException;

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
        String capType = capitalize(type);
        try {
            Class cl = Class.forName("model.entries."+capType + "Entry");
            return (Entry) cl.newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                ClassNotFoundException e) {
            throw new ParseException("Cannot create class by type "+capType);
        }
    }

    private void validateAndAssignValues(Entry e) throws ParseException {
        for(int i=0;i<e.fields.length;i++){
            String value = getFieldValueByEntry(e, i);
            if(value == null && e.fields[i].isRequired())
                throw new ParseException("Entry doesn't contain a required field "+e.fields[i].getName());
            e.fields[i].setValue(value);
        }
    }

    private String getFieldValueByEntry(Entry e, int position){
        Field field = e.fields[position];
        String value = fields.get(field.getName().name);
        if(value == null && field.getAlternativeName() != null)
            value = fields.get(field.getAlternativeName().name);
        return value;
    }


    private String capitalize(String text){
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }
}