package filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading bibtex files.
 */
public class BibtexFileReader {

    /**
     * Method that reads file with given
     * name and returns its content as a string
     * @param fileName Name of the file
     * @return Content of the file as String
     * @throws IOException when there are problems with files
     */
    public String read(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            result.append(line).append("\n");
        }
        return result.toString();
    }
}
