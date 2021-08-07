package com.epam.rd.java.basic.practice7;

import java.util.List;

public class Song {
    private String title;
    private List<String> musician;
    private String country;
    private int year;
    private char rating;

    public Song(String title, List<String> musician, String country, int year, char rating) {
        this.title = title;
        this.musician = musician;
        this.country = country;
        this.year = year;
        this.rating = rating;
    }

    public Song() {
    }

    public String getTitle() {
        return title;
    }

    public List<String> getMusician() {
        return musician;
    }

    public String getStringMusician() {
        StringBuilder temp = new StringBuilder();
        for(String s : musician) {
            temp.append(s).append(" ");
        }
        return temp.toString().trim();
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public char getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMusician(List<String> musician) {
        this.musician = musician;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(char rating) {
        this.rating = rating;
    }
}
