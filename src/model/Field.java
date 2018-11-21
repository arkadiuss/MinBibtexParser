package model;

public class Field {
    public final FieldName name;
    public final String value;
    public final boolean isRequired;

    public Field(FieldName name, String value, boolean isRequired) {
        this.name = name;
        this.value = value;
        this.isRequired = isRequired;
    }
}
