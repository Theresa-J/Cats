package com.example.cats.model;

public class Cats {

    private String id;
    private String description;
    private String name;
    private String wikipedia_url;
    private String weight_imperial;
    private String temperament;
    private String origin;
    private String life_span;
    private int dog_friendly;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getWeight_imperial() {
        return weight_imperial;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }
}
