package parser;

import exception.ParseException;
import model.Entry;
import model.EntryBuilder;
import model.EntryType;

import java.util.*;

public class MinimalBibtexParser implements IBibtexParser {

    @Override
    public List<Entry> parse(String bibtex) throws ParseException,
            ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        String[] entries = bibtex.split("@");
        List<Entry> parsedEntries = new ArrayList<>();
        Map<String, String> variables = new HashMap<>();
        for(int i=1;i<entries.length;i++){
            Entry e = processObject(entries[i], variables);
            //temporary workaround
            if(e!=null)
                parsedEntries.add(e);
        }
        return parsedEntries;
    }

    private Entry processObject(String entry, Map<String, String> variables) throws ParseException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int typeLen = entry.indexOf('{');
        if(typeLen == -1)
            throw new ParseException(entry, "Opening bracket not found");
        int closingBracket = entry.lastIndexOf('}');
        if(closingBracket == -1)
            throw new ParseException(entry, "Closing bracket not found");
        String type = entry.substring(0, typeLen);
        String fields = entry.substring(typeLen+1, closingBracket);
        if(type.equals("STRING")){
            processVariable(fields, variables);
            return null;
        }else{
            return processEntry(fields, type, variables);
        }
    }

    private Entry processEntry(String fields, String type, Map<String, String> variables) throws
            ClassNotFoundException, NoSuchMethodException, InstantiationException, ParseException, IllegalAccessException {
        EntryBuilder builder = new EntryBuilder();
        //temporary workaround
        if(!EntryType.contains(type)) return null;
        builder.setType(type);
        String[] fieldsWithValues = fields.split(",");
        for (String fwv:fieldsWithValues) {
            String[] fieldAndValue = fwv.split("=");
            if(fieldAndValue.length == 2) {
                builder.setField(
                        fieldAndValue[0].trim(),
                        getValueOfField(fieldAndValue[1].trim(), variables));
            }
        }
        return builder.build();
    }

    private String getValueOfField(String fieldValue, Map<String, String> variables){
        String[] parts = fieldValue.split("#");
        for(int i=0;i<parts.length;i++){
            String fromVar = variables.get(parts[i].trim());
            if(fromVar != null){
                parts[i] = fromVar;
            }
        }
        return reduce(parts);
    }

    private String reduce(String[] parts){
        return Arrays.stream(parts).reduce(String::concat).orElse("");
    }

    private void processVariable(String fields,  Map<String, String> variables){
        String[] nameAndValue = fields.split("=");
        variables.put(
                nameAndValue[0].trim(),
                nameAndValue[1].trim().substring(1,nameAndValue[1].length()-2));
    }


}
