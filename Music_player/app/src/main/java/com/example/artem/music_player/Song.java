package com.example.artem.music_player;

/**
 * Created by Artem on 2018-05-11.
 */

public class Song {
    private String title;
    private String author;
    private int song;

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public Song() {}

    public Song(String title, String author, int song) {
        this.title = title;
        this.author = author;
        this.song = song;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
