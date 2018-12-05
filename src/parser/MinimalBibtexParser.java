package parser;

import exception.ParseException;
import model.Entry;
import model.EntryBuilder;

import java.util.*;

/**
 * Implementation of bibtex parser, which
 * allows user to parse simplified bibtex
 * Simplified bibtex format is described here
 */
public class MinimalBibtexParser implements IBibtexParser {

    /**
     * {@inheritDoc}
     *
     * This implementation firstly split
     * all the objects by '@' sign what
     * enables to keep all entries seperately
     * and then iterate over all records to process them.
     * It also declared a map to keep bibtex string
     * variables.
     */
    @Override
    public List<Entry> parse(String bibtex) throws ParseException {
        String[] entries = bibtex.split("@");
        List<Entry> parsedEntries = new ArrayList<>();
        Map<String, String> variables = new HashMap<>();
        for(int i=1;i<entries.length;i++){
            Entry e = processObject(entries[i], variables);
            if(e!=null)
                parsedEntries.add(e);
        }
        return parsedEntries;
    }

    /**
     * Method that looks for first '{' sign and last '}'
     * to determine range of the entry and then
     * it takes type from the entry and proccess
     * it as entry or a variable
     *
     * @param entry One of the entries without @ sign
     * @param variables Maps that keep bibtex variables
     * @return Processed entry or null if record is a string variable or not an entry
     * @throws ParseException when brackets are not found
     */
    private Entry processObject(String entry, Map<String, String> variables)
            throws ParseException {
        int typeLen = entry.indexOf('{');
        if (typeLen == -1)
            return null;
        int closingBracket = entry.lastIndexOf('}');
        if (closingBracket == -1)
            throw new ParseException(entry, "Closing bracket not found");
        String type = entry.substring(0, typeLen);
        String fields = entry.substring(typeLen + 1, closingBracket);
        if (type.equals("PREAMBLE") || type.equals("COMMENT")){
            return null;
        }else if(type.equals("STRING")){
            processVariable(fields, variables);
            return null;
        }else{
            return processEntry(fields, type, variables, entry);
        }
    }

    /**
     * Method that process entry without surrounding
     * brackets. It firstly split record by , sign
     * and then divide every field to name and value
     * by = sign, which are inserted into EntryBuilder
     *
     * @param fields Not splitted entry without surrounding brackets
     * @param type Type of the entry
     * @param variables Map of string variables
     * @return Builded entry with values
     * @throws ParseException
     */
    private Entry processEntry(String fields, String type,
                               Map<String, String> variables, String source)
            throws ParseException {
        EntryBuilder builder = new EntryBuilder();
        builder.setType(type);
        String[] fieldsWithValues = fields.split(",");
        builder.setQuoteKey(fieldsWithValues[0]);
        for (String fwv:fieldsWithValues) {
            String[] fieldAndValue = fwv.split("=");
            if(fieldAndValue.length == 2) {
                builder.setField(
                        fieldAndValue[0].trim(),
                        getValueOfField(fieldAndValue[1].trim(), variables));
            }
        }
        try {
            return builder.build();
        }catch (ParseException e){
            throw new ParseException(source, e.getMessage());
        }
    }

    /**
     * One value of field can contain strings and
     * variables. This method change every variable
     * to its value and then connect everything into
     * one value
     *
     * @param fieldValue Value of the field with variables
     * @param variables Map of variables
     * @return Value with inserted values of variables
     * @throws ParseException when variable is not found
     */
    private String getValueOfField(String fieldValue, Map<String, String> variables) throws ParseException {
        String[] parts =
                Arrays.stream(fieldValue.split("#"))
                .map(String::trim)
                .toArray(String[]::new);
        for(int i=0;i<parts.length;i++){
            if(parts[i].charAt(0) == '\"'){
                parts[i] = parts[i].substring(1,parts[i].length()-1);
            }else{
                String fromVar = variables.get(parts[i].trim());
                if(fromVar == null)
                    throw new ParseException("Variable " + parts[i].trim() + " not found");
                parts[i] = fromVar;
            }
        }
        return Arrays.stream(parts).reduce(String::concat).orElse("");
    }

    /**
     * Method that add a variable to map of variables
     * which enables to use them later
     *
     * @param fields Content of record without surrounding brackets
     * @param variables Map of variables to add new variable
     */
    private void processVariable(String fields,  Map<String, String> variables){
        String[] nameAndValue = fields.split("=");
        variables.put(
                nameAndValue[0].trim(),
                nameAndValue[1].trim().substring(1,nameAndValue[1].length()-2));
    }


}
