import exception.ParseException;
import filereader.BibtexFileReader;
import model.Entry;
import parser.MinimalBibtexParser;
import repository.EntryRepository;
import repository.IEntryRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {


    public static void main(String[] args){
        BibtexFileReader fileReader = new BibtexFileReader();
        IEntryRepository entryRepository = new EntryRepository();
        try {
            String bibtexString = fileReader.read(args[0]);
            MinimalBibtexParser mbp = new MinimalBibtexParser();
            List<Entry> parsed = mbp.parse(bibtexString);
            entryRepository.addAll(parsed);
            entryRepository.getAll().forEach(entry -> {
                System.out.println("TYPE: "+entry.getType());
                Arrays.stream(entry.getFields()).forEach(field -> {
                    if(field.value != null)
                        System.out.println(field.name + ": "+ field.value);
                });
                System.out.println("\n\n");
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
