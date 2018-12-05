import exception.ParseException;
import filereader.BibtexFileReader;
import model.Entry;
import org.apache.commons.cli.*;
import parser.MinimalBibtexParser;
import repository.EntryRepository;
import repository.IEntryRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Simple app that enables user to parse bibtex file
 * and show results. App also has an option to filter
 * results by author or type (category)
 *
 * @author Arkadiusz Kraus
 */
public class App {
    private CommandLineParser commandLineParser = new DefaultParser();
    private HelpFormatter helpFormatter = new HelpFormatter();
    private final Option help = Option.builder("h")
            .longOpt("help")
            .hasArg(false)
            .desc("Show this help")
            .build();
    private final Option categories = Option.builder("c")
            .longOpt("cat")
            .hasArgs()
            .desc("Filter selected records from file by category")
            .build();
    private final Option names = Option.builder("a")
            .longOpt("anames")
            .hasArgs()
            .argName("authors' names")
            .desc("Filter selected records from file by authors' surnames")
            .build();
    private Options options = createCommandLineOptions();

    public static void main(String[] args){
        App app = new App();
        CommandLine cmd = app.parseCommandLine(args);
        if(cmd.hasOption(app.help.getOpt())){
            app.printHelp();
        }else{
            IEntryRepository entryRepository = new EntryRepository();
            if(cmd.getArgs().length != 1){
                app.printHelp();
                System.exit(1);
            }
            String bibtexString = app.readFile(cmd.getArgs()[0]);
            List<Entry> parsed = app.parseBibtex(bibtexString);
            entryRepository.addAll(parsed);

            List<String> names = app.getValuesForNames(cmd);
            List<String> cats = app.getValuesForCategories(cmd);

            List<Entry> entriesFromRepo;
            if(cmd.hasOption(app.categories.getOpt()) &&
                    cmd.hasOption(app.names.getOpt())) {
                entriesFromRepo = entryRepository.getAllByTypesAndAuthorNames(cats, names);
            }else if(cmd.hasOption(app.categories.getOpt())){
                entriesFromRepo = entryRepository.getAllByTypes(cats);
            }else if(cmd.hasOption(app.names.getOpt())){
                entriesFromRepo = entryRepository.getAllByAuthorNames(names);
            }else{
                entriesFromRepo = entryRepository.getAll();
            }
            entriesFromRepo.forEach(System.out::println);
        }
    }

    private Options createCommandLineOptions(){
        Options options = new Options();
        options.addOption(categories);
        options.addOption(names);
        options.addOption(help);
        return options;
    }

    private void printHelp(){
        helpFormatter.printHelp("parser <FILE> [OPTIONS]", options);
    }

    private CommandLine parseCommandLine(String[] args) {
        try {
            return commandLineParser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            printHelp();
            System.exit(1);
        }
        return null;
    }

    private String readFile(String file){
        BibtexFileReader fileReader = new BibtexFileReader();
        try {
            return fileReader.read(file);
        } catch (IOException e) {
            System.err.println("Cannot read a file, nested exception is "+
                    e.getMessage());
            System.exit(2);
        }
        return null;
    }

    private List<Entry> parseBibtex(String bibtexString){
        MinimalBibtexParser mbp = new MinimalBibtexParser();
        try{
            return mbp.parse(bibtexString);
        } catch (ParseException e) {
            System.err.println("Problem during parsing "+e.getMessage());
            System.exit(3);
        }
        return null;
    }

    private List<String> getValuesForNames(CommandLine cmd){
        try {
            return Arrays.asList(cmd.getOptionValues(names.getOpt()));
        }catch (NullPointerException e){
            return Collections.emptyList();
        }
    }

    private List<String> getValuesForCategories(CommandLine cmd){
        try {
            return Arrays.asList(cmd.getOptionValues(categories.getOpt()));
        }catch (NullPointerException e){
            return Collections.emptyList();
        }
    }
}
