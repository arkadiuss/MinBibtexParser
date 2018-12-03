package model.fields;

import model.Field;
import model.FieldName;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @Override
    public void setValue(String value) {
        values = value.split("and");
    }

    @Override
    public String getValue() {
        return Arrays.stream(values)
                .reduce((x,y) -> x + " and " + y)
                .orElse("");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName().name).append(": ");
        Arrays.stream(values)
                .forEach(v -> {
                    if(builder.length()!=getName().name.length()+2)
                        builder.append("\t\t");
                    builder.append(v.trim()).append("\n");
                });
        return builder.toString();
    }

    @Override
    public boolean contains(String pattern) {
        return Arrays.stream(values)
                .anyMatch(value -> value.contains(pattern));
    }
}
