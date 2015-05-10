package com.larslonne;

/**
 * Created by lars on 10/05/15.
 */
public class WordsToPositionResponse {
    private String type;
    private String[] words;
    private String[] position;
    private String language;

    public WordsToPositionResponse() {
    }

    public String getType() {
        return type;
    }

    public String[] getWords() {
        return words;
    }

    public String[] getPosition() {
        return position;
    }

    public String getLanguage() {
        return language;
    }
}
