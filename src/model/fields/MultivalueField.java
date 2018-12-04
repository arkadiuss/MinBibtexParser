package model.fields;

import common.StringUtils;
import model.Field;
import model.FieldName;

import java.util.Arrays;

/**
 * Field which value isn't single.
 * It can contains many values.
 */
public class MultivalueField extends Field {
    protected String[] values;

    public MultivalueField(FieldName name, boolean isRequired) {
        super(name, isRequired);
    }

    public MultivalueField(FieldName name, FieldName alternativeName, boolean isRequired) {
        super(name, alternativeName, isRequired);
    }

    public MultivalueField(FieldName name, String value, boolean isRequired) {
        super(name, isRequired);
        setValue(value);
    }

    /**
     * Multivalue fields are splitted
     * by 'and'. This method gets value
     * and split it by this keyword
     *
     * @param value value to split
     */
    @Override
    public void setValue(String value) {
        values = value.split("and");
    }

    /**
     * Method that connects values
     * with 'and' and return them as String
     * @return Connected values
     */
    @Override
    public String getValue() {
        return Arrays.stream(values)
                .reduce((x,y) -> x + " and " + y)
                .orElse("");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('*')
                .append(StringUtils.getFixedLengthString(getName().name))
                .append("*");
        boolean isFirst = true;
        for(String v : values){
            if(isFirst) {
                isFirst = false;
            }else{
                builder.append('*');
                builder.append(StringUtils.getFixedLengthString(""));
                builder.append('*');
            }
            builder.append(StringUtils.getFixedLengthString(v.trim()))
                    .append("*\n");
        }
        builder.append(StringUtils.getFixedLineOfAsterics());
        return builder.toString();
    }

    /**
     * Check if any of values contains pattern
     * as a substring
     *
     * @param pattern String to check
     * @return true if any of values contains pattern as a substring or false otherwise
     */
    @Override
    public boolean contains(String pattern) {
        return Arrays.stream(values)
                .anyMatch(value -> value.contains(pattern));
    }
}
