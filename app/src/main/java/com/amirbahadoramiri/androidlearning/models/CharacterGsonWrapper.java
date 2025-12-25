package com.amirbahadoramiri.androidlearning.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterGsonWrapper {

    @SerializedName("results")
    private List<Character> results;

    public CharacterGsonWrapper() {
    }

    public List<Character> getResults() {
        return results;
    }

}
