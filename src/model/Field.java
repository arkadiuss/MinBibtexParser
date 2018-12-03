package model;

public class Field {
    private FieldName name;
    private FieldName alternativeName;
    private String value;
    private boolean required;

    public Field(FieldName name, boolean isRequired) {
        this.name = name;
        this.required = isRequired;
    }

    public Field(FieldName name, FieldName alternativeName, boolean isRequired) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.required = isRequired;
    }

    public Field(FieldName name, String value, boolean isRequired) {
        this.name = name;
        this.value = value;
        this.required = isRequired;
    }

    public FieldName getName() {
        return name;
    }

    public void setName(FieldName name) {
        this.name = name;
    }

    public FieldName getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(FieldName alternativeName) {
        this.alternativeName = alternativeName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
