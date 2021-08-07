package com.epam.rd.java.basic.practice7;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    private Sorter() {}

    public static final void sortSongsByTitle(Catalog catalog) {
        Collections.sort(catalog.getSongs(), Comparator.comparing(Song::getTitle));
    }

    public static final void sortSongsByYear(Catalog catalog) {
        Collections.sort(catalog.getSongs(), Comparator.comparingInt(Song::getYear));
    }

    public static final void sortSongsByRating(Catalog catalog) {
        Collections.sort(catalog.getSongs(), Comparator.comparingInt(Song::getRating));
    }
}
