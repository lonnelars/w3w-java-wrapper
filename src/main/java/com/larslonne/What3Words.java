package com.larslonne;

import com.google.gson.Gson;
import com.larslonne.models.Languages;
import com.larslonne.models.PositionToWordsResponse;
import com.larslonne.models.WordsToPositionResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class What3Words {
    private final String API_KEY;
    private static final String API_URL = "http://api.what3words.com/";

    public What3Words(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String[] wordsToPosition(String words) {
        if (validateWords(words)) {
            Response response = callAPI("w3w",
                    Collections.singletonMap("string", words));
            return getPosition(response.readEntity(String.class));
        } else {
            return new String[0];
        }
    }

    public String positionToWords(String[] position) {
        if (validatePosition(position)) {
            Response response = callAPI("position",
                    Collections.singletonMap("position", positionToString(position)));
            return getWords(response.readEntity(String.class));
        } else {
            return "";
        }
    }

    public boolean validatePosition(String[] position) {
        if (position.length == 2) {
            Pattern positionPattern = Pattern.compile("^[-]?\\d+\\.\\d{6}$");
            Matcher latMatcher = positionPattern.matcher(position[0]);
            Matcher longMatcher = positionPattern.matcher(position[1]);
            return latMatcher.matches() && longMatcher.matches();
        } else {
            return false;
        }
    }

    public boolean validateWords(String words) {
        Pattern wordsPattern = Pattern.compile("^\\p{L}+\\.\\p{L}+\\.\\p{L}+$", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = wordsPattern.matcher(words);
        return matcher.matches();
    }

    public List<Languages.Language> getLanguages() {
        String json = callAPI("get-languages", Collections.emptyMap()).readEntity(String.class);
        Gson gson = new Gson();
        Languages languages = gson.fromJson(json, Languages.class);
        return languages.getLanguages();
    }

    private Response callAPI(String path, Map<String, String> params) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(API_URL)
                .path(path)
                .queryParam("key", API_KEY);
        for (String key : params.keySet()) {
            webTarget = webTarget.queryParam(key, params.get(key));
        }
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();
    }

    private String getWords(String json) {
        Gson gson = new Gson();
        PositionToWordsResponse response = gson.fromJson(json, PositionToWordsResponse.class);
        return Arrays.stream(response.getWords()).collect(Collectors.joining("."));
    }

    private String positionToString(String[] position) {
        return Arrays.stream(position).collect(Collectors.joining(","));
    }

    private String[] getPosition(String json) {
        Gson gson = new Gson();
        WordsToPositionResponse response = gson.fromJson(json, WordsToPositionResponse.class);
        return response.getPosition();
    }
}
