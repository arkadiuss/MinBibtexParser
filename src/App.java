import exception.ParseException;
import filereader.BibtexFileReader;
import parser.MinimalBibtexParser;

import java.io.IOException;

public class App {

    public static void main(String[] args){
        BibtexFileReader fileReader = new BibtexFileReader();
        try {
            String bibtexString = fileReader.read(args[0]);
            MinimalBibtexParser mbp = new MinimalBibtexParser();
            mbp.parse(bibtexString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
