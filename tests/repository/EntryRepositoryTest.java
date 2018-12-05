package repository;

import exception.ParseException;
import model.Entry;
import model.EntryBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryTest {

    @Test
    void canAddTest() throws ParseException {
        EntryBuilder builder = new EntryBuilder();
        IEntryRepository entryRepository = new EntryRepository();
        builder.setType("BOOK");
        builder.setField("author","Good author");
        builder.setField("title","Good publisher");
        builder.setField("publisher","Good publisher");
        builder.setField("year","1990");
        Entry entry1 = builder.build();
        builder = new EntryBuilder();
        builder.setType("ARTICLE");
        builder.setField("author","Good author");
        builder.setField("title","Good publisher");
        builder.setField("journal","Good publisher");
        builder.setField("year","1990");
        Entry entry2 = builder.build();
        entryRepository.addAll(Arrays.asList(entry1, entry2));
        List<Entry> entries = entryRepository.getAll();
        assertEquals(2, entries.size());
    }

    @Test
    void canFilterByTypeTest() throws ParseException {
        EntryBuilder builder = new EntryBuilder();
        IEntryRepository entryRepository = new EntryRepository();
        builder.setType("BOOK");
        builder.setField("author","Good author");
        builder.setField("title","Good publisher");
        builder.setField("publisher","Good publisher");
        builder.setField("year","1990");
        Entry entry1 = builder.build();
        builder = new EntryBuilder();
        builder.setType("ARTICLE");
        builder.setField("author","Good author");
        builder.setField("title","Good publisher");
        builder.setField("journal","Good publisher");
        builder.setField("year","1990");
        Entry entry2 = builder.build();
        entryRepository.addAll(Arrays.asList(entry1, entry2));
        List<Entry> entries = entryRepository.getAllByType("BOOK");
        assertEquals(1, entries.size());
    }

}