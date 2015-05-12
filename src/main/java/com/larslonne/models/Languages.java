package com.larslonne.models;

import java.util.List;

/**
 * Created by lars on 12/05/15.
 */
public final class Languages {
    private List<Language> languages;

    public Languages() {
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public class Language {
        private String code;
        private String name_display;

        public Language() {
        }

        public String getCode() {
            return code;
        }

        public String getName_display() {
            return name_display;
        }
    }
}
