package com.bousquet.noe.appjazz;

public class Playlist {
    Integer cover;
    String name;
    String artist;
    String description;
    String nbSongs;
    String duration;
    String link;


    public Playlist(Integer cover, String name, String artist, String description, String nbSongs, String duration, String link) {
        this.cover = cover;
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.nbSongs = nbSongs;
        this.duration = duration;
        this.link = link;
    }

}
