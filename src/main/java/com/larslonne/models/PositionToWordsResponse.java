package com.larslonne.models;

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
