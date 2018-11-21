package filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BibtexFileReader {

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
