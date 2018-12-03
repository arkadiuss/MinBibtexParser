package model.fields;

import model.FieldName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorFieldTest {

    @Test
    void shouldAcceptSimpleName(){
        AuthorField field = new AuthorField(
                FieldName.AUTHOR,"Name Surname",true);
        assertEquals( "Name Surname", field.getValue());
    }

    @Test
    void shouldChangeOrder(){
        AuthorField field = new AuthorField(
                FieldName.AUTHOR,"Surname| Name",true);
        assertEquals( "Name Surname", field.getValue());
    }

    @Test
    void shouldChangeOrderIfWithPreposition(){
        AuthorField field = new AuthorField(
                FieldName.AUTHOR,"Surname| Jr.| Name",true);
        assertEquals( "Jr. Name Surname", field.getValue());
    }

}