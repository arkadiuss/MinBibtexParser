import filereader.BibtexFileReader;

import java.io.IOException;

public class App {

    public static void main(String[] args){
        BibtexFileReader fileReader = new BibtexFileReader();
        try {
            String bibtexString = fileReader.read(args[0]);
            System.out.println(bibtexString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
