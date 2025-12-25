package com.amirbahadoramiri.androidlearning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterJacksonWrapper {

    @JsonProperty("results")
    private List<Character> results;

    public CharacterJacksonWrapper() {}

    public List<Character> getResults() {
        return results;
    }

}
