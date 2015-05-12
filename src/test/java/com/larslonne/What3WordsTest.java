package com.larslonne;

import com.larslonne.models.Languages;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class What3WordsTest {

    private What3Words what3Words;
    private String[] position;
    private String words;
    private static final String API_KEY = "SKGZYUQ3";

    @Before
    public void setUp() throws Exception {
        what3Words = new What3Words(API_KEY);
        position = new String[]{"59.909996", "10.750027"};
        words = "dates.surgical.grabs";
    }

    @Test
    public void testWordsToPosition() throws Exception {
        assertArrayEquals(position, what3Words.wordsToPosition(words));
    }

    @Test
    public void testPositionToWords() throws Exception {
        assertEquals(words, what3Words.positionToWords(position));
    }

    @Test
    public void testValidateWords() throws Exception {
        assertTrue(what3Words.validateWords(words));
        assertFalse(what3Words.validateWords("dates surgical grabs"));
        assertFalse(what3Words.validateWords("dates.surgical"));
    }

    @Test
    public void testValidatePosition() throws Exception {
        assertTrue(what3Words.validatePosition(position));
        assertFalse(what3Words.validatePosition(new String[] {"51", "28"}));
    }

    @Test
    public void testGetLanguages() throws Exception {
        List<Languages.Language> languages = what3Words.getLanguages();
        Optional<Languages.Language> maybeEnglish = languages.stream().filter(lang -> lang.getCode().equals("en")).findFirst();
        assertTrue("English should be one of the languages", maybeEnglish.isPresent());
        assertTrue(maybeEnglish.get().getName_display().equals("English"));
    }
}