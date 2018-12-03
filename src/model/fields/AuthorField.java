package model.fields;

import model.FieldName;

import java.util.Arrays;

public class AuthorField extends MultivalueField {
    public AuthorField(FieldName name, boolean isRequired) {
        super(name, isRequired);
    }

    public AuthorField(FieldName name, FieldName alternativeName, boolean isRequired) {
        super(name, alternativeName, isRequired);
    }

    public AuthorField(FieldName name, String value, boolean isRequired) {
        super(name, value, isRequired);
        setValue(value);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        values = Arrays.stream(values)
                .map(this::formatAuthor)
                .toArray(String[]::new);
    }

    private String formatAuthor(String author) {
        String[] splittedAuthor = author.split("\\|");
        if (splittedAuthor.length == 1){
            return splittedAuthor[0];
        }else if(splittedAuthor.length == 2){
            return splittedAuthor[1].trim() + " " + splittedAuthor[0];
        }else if(splittedAuthor.length == 3){
            return splittedAuthor[1].trim() + " "
                    + splittedAuthor[2].trim() + " "
                    + splittedAuthor[0];
        }
        throw new IllegalArgumentException("Author: "+author+" is not properly formatted");
    }

}
