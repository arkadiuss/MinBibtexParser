import exception.ParseException;
import filereader.BibtexFileReader;
import model.Entry;
import org.apache.commons.cli.*;
import parser.MinimalBibtexParser;
import repository.EntryRepository;
import repository.IEntryRepository;

import java.io.IOException;
import java.util.List;

public class App {


    public static void main(String[] args){
        BibtexFileReader fileReader = new BibtexFileReader();
        IEntryRepository entryRepository = new EntryRepository();


        CommandLineParser parser = new DefaultParser();
        Options options = createCommandLineOptions();
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("syntax", options);
        try {
            CommandLine commandLine = parser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            e.printStackTrace();
        }
        try {
            String bibtexString = fileReader.read(args[0]);
            MinimalBibtexParser mbp = new MinimalBibtexParser();
            List<Entry> parsed = mbp.parse(bibtexString);
            entryRepository.addAll(parsed);
            entryRepository.getAllByAuthorName("Les")
                    .forEach(System.out::println);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static Options createCommandLineOptions(){
        Option filename;
        Option types = Option.builder()
                .argName("categories")
                .longOpt("cat")
                .desc("Categories")
                .build();
        Option names = Option.builder()
                .argName("anames")
                .longOpt("anam")
                .desc("Author names")
                .build();
        Options options = new Options();
        options.addOption(types);
        options.addOption(names);
        return options;
    }
}
