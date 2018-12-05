package model;

import exception.ParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * Builder for Entry element.
 * It enables to add all the fields and
 * validate and create an Entry
 */
public class EntryBuilder {
    private String type;
    private Map<String, String> fields = new HashMap<>();
    private String quoteKey;

    /**
     * Method to set type of the Entry
     * @param type Type of entry
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Method that allows to set value
     * of certain field to given value
     *
     * @param field Field's name
     * @param value New value of the field
     */
    public void setField(String field, String value){
        this.fields.put(field, value);
    }

    public void setQuoteKey(String quoteKey){
        this.quoteKey = quoteKey;
    }

    /**
     * Method that validate added fields
     * and then convert them into entry
     * @return Entry from added fields
     * @throws ParseException
     */
    public Entry build() throws ParseException {
        Entry entry = getByType(type, quoteKey);
        validateAndAssignValues(entry);
        return entry;
    }

    /**
     * Method that uses reflection mechanism
     * to create object of matching type
     *
     * @param type Type of the record which is the name of the class
     * @return Object of matched class
     * @throws ParseException
     */
    private Entry getByType(String type, String quoteKey) throws ParseException {
        String capType = capitalize(type);
        try {
            Class cl = Class.forName("model.entries."+capType + "Entry");
            Constructor<?> cons = cl.getConstructor(String.class);
            return (Entry) cons.newInstance(quoteKey);
        } catch (InstantiationException | IllegalAccessException |
                ClassNotFoundException | NoSuchMethodException |
                InvocationTargetException e) {
            throw new ParseException("Cannot create class by type "+capType);
        }
    }

    /**
     * This method goes through the fields
     * and validate if it contains all the
     * required fields and then is assign a value
     *
     * @param e Entry of specific type
     * @throws ParseException when there is no required field
     */
    private void validateAndAssignValues(Entry e) throws ParseException {
        for(int i=0;i<e.fields.length;i++){
            String value = getFieldValueByEntry(e, i);
            if(value == null && e.fields[i].isRequired())
                throw new ParseException("Entry doesn't contain a required field "+e.fields[i].getName());
            e.fields[i].setValue(value);
        }
    }

    /**
     * Sometimes field can be also identified by
     * alternative name, when the name is null.
     * This method checks if there is value for name
     * or for alternative name.
     *
     * @param e Entry of specific type
     * @param position Which field of given entry
     * @return Value for given field
     */
    private String getFieldValueByEntry(Entry e, int position){
        Field field = e.fields[position];
        String value = fields.get(field.getName().name);
        if(value == null && field.getAlternativeName() != null)
            value = fields.get(field.getAlternativeName().name);
        return value;
    }

    /**
     * Method that capitalize string
     * @param text String to capitalize
     * @return Capitalized string
     */
    private String capitalize(String text){
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }
}