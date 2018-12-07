import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void before(){
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testWithSimpleFile(){
        App.main("test.bib".split(" "));
        String expectedOutput =
                "*******************************************\n" +
                "*ARTICLE (article-minimal)                *\n" +
                "*******************************************\n" +
                "*author              *Leslie Ported       *\n" +
                "*                    *Good Author         *\n" +
                "*******************************************\n" +
                "*title               *The Gnats and Gnus Document Preparation System*\n" +
                "*******************************************\n" +
                "*journal             *G-Animal's Journal  *\n" +
                "*******************************************\n" +
                "*year                *1986                *\n" +
                "*******************************************\n\n\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
