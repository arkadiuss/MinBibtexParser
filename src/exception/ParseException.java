package exception;

/**
 * Exception which represents problem with parsing
 * bibtex file.
 */
public class ParseException extends Exception {

    public ParseException(String message){
        super("Exception during parsing: " + message);
    }

    public ParseException(String entry, String message){
        super("Exception during parsing entry: " + entry +
                "\n nested exception is: "+ message);
    }
}
