package com.larslonne;

import org.junit.Before;
import org.junit.Test;

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
}