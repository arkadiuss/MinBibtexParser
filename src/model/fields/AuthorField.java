package model.fields;

import model.FieldName;

import java.util.Arrays;

/**
 * Author field is special because
 * it can be given by 3 different ways.
 * This class allows to unify them.
 */
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

    /**
     * Method which split if it is multivalue
     * and format author names.
     *
     * @param value Value to split and format
     */
    @Override
    public void setValue(String value) {
        super.setValue(value);
        values = Arrays.stream(values)
                .map(this::formatAuthor)
                .toArray(String[]::new);
    }

    /**
     * Function that determine unified author name
     * based on given value.
     * @param author Author in one of three formats
     * @return Author in unified format
     */
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
