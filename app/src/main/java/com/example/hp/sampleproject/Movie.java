package com.example.hp.sampleproject;

/**
 * Created by HP on 11/22/2017.
 */

/**
 * Created by HP on 11/19/2017.
 */

public class Movie{

    private int id;
    private String movie_name;
    private String time;
    private String image;
    private String description;

    public Movie(int id, String movie_name, String time, String image, String description) {
        this.id = id;
        this.movie_name = movie_name;
        this.time = time;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }


    public String getTime() {
        return time;
    }


    public String getImage() {
        return image;
    }


    public String getDescription() {
        return description;
    }

}

