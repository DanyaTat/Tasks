package com.epam.rd.java.basic.practice7;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final ArrayList<Song> songs;

    public void add (Song s) {
        songs.add(s);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Catalog() {
        this.songs = new ArrayList<>();
    }
}
