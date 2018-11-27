package parser;

import exception.ParseException;
import model.Entry;
import model.EntryBuilder;
import model.EntryType;

import java.util.ArrayList;
import java.util.List;

public class MinimalBibtexParser implements IBibtexParser {

    @Override
    public List<Entry> parse(String bibtex) throws ParseException,
            ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        String[] entries = bibtex.split("@");
        List<Entry> parsedEntries = new ArrayList<>();
        for(int i=1;i<entries.length;i++){
            Entry e = processEntry(entries[i]);
            //temporary workaround
            if(e!=null)
                parsedEntries.add(e);
        }
        return parsedEntries;
    }

    private Entry processEntry(String entry) throws ParseException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int typeLen = entry.indexOf('{');
        if(typeLen == -1)
            throw new ParseException(entry, "Opening bracket not found");
        int closingBracket = entry.lastIndexOf('}');
        if(closingBracket == -1)
            throw new ParseException(entry, "Closing bracket not found");
        EntryBuilder builder = new EntryBuilder();
        String type = entry.substring(0, typeLen);
        //temporary workaround
        if(!EntryType.contains(type)) return null;
        builder.setType(type);
        String fields = entry.substring(typeLen+1, closingBracket);
        String[] fieldsWithValues = fields.split(",");
        for (String fwv:fieldsWithValues) {
            String[] fieldAndValue = fwv.split("=");
            if(fieldAndValue.length == 2) {
                builder.setField(fieldAndValue[0].trim(), fieldAndValue[1].trim());
            }
        }
        return builder.build();
    }


}
