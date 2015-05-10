package com.larslonne;

/**
 * Created by lars on 10/05/15.
 */
public final class PositionToWordsResponse {
    private String[] words;
    private String[] position;
    private String language;

    public PositionToWordsResponse() {
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
