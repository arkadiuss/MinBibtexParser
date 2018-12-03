package model;

import java.util.Arrays;

public class MultivalueField extends Field {
    private String[] values;

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
        return Arrays.stream(values).reduce(String::concat).get();
    }
}
