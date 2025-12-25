package com.amirbahadoramiri.androidlearning.models;

import com.squareup.moshi.Json;

import java.util.List;

public class CharacterMoshiWrapper {

    @Json(name = "results")
    private List<Character> results;

    public CharacterMoshiWrapper() {
    }

    public List<Character> getResults() {
        return results;
    }

}
