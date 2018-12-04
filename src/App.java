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
        HelpFormatter helpFormatter = new HelpFormatter();
        Options options = createCommandLineOptions();
        helpFormatter.printHelp("parser <FILE> [OPTIONS]", options);
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            e.printStackTrace();
            return;
        }
        if(commandLine.hasOption("help")){
            helpFormatter.printHelp("parser <FILE> [OPTIONS]", options);
        }else{
            try {
                String bibtexString = fileReader.read(commandLine.getArgs()[0]);
                MinimalBibtexParser mbp = new MinimalBibtexParser();
                List<Entry> parsed = mbp.parse(bibtexString);
                entryRepository.addAll(parsed);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            List<Entry> entriesFromRepo;
            if(commandLine.hasOption("categories")){
                entriesFromRepo = entryRepository.getAll();
            }else if(commandLine.hasOption("anames")){
                entriesFromRepo = entryRepository.getAll();
            }else{
                entriesFromRepo = entryRepository.getAll();
            }
            entriesFromRepo.forEach(System.out::println);
        }
    }

    private static Options createCommandLineOptions(){
        Option help = Option.builder()
                .argName("help")
                .longOpt("help")
                .desc("Show this help")
                .build();
        Option types = Option.builder()
                .argName("categories")
                .longOpt("cat")
                .desc("Filter selected records from file by category")
                .build();
        Option names = Option.builder()
                .argName("authors' names")
                .longOpt("anames")
                .desc("Filter selected records from file by authors' surnames")
                .build();
        Options options = new Options();
        options.addOption(types);
        options.addOption(names);
        options.addOption(help);
        return options;
    }
}
