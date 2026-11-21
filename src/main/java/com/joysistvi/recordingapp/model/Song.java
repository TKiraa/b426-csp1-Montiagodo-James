package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
    private int albumId;
    private int duration;
    private boolean archived;

    public Song() {
    }

    public Song(int id, String title, int albumId, int duration) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.duration = duration;
    }

    public Song(String title, int albumId, int duration) {
        this.title = title;
        this.albumId = albumId;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

}
