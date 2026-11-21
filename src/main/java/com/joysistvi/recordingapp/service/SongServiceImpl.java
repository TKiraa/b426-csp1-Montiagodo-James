package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;

public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllActiveSongs() {
        return songRepository.findAllActive();
    }

    @Override
    public List<Song> getAllArchivedSongs() {
        return songRepository.findAllArchived();
    }

    @Override
    public boolean createSong(String title, int albumId, int duration) {
        if (title == null || title.isBlank()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (albumId <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        if (duration <= 0) {
            System.out.println("Duration must be greater than 0.");
            return false;
        }

        Song song = new Song(title.trim(), albumId, duration);
        return songRepository.save(song);
    }

    @Override
    public boolean updateSong(int id, String title, int albumId, int duration) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        if (title == null || title.isBlank()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (albumId <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        if (duration <= 0) {
            System.out.println("Duration must be greater than 0.");
            return false;
        }

        Song song = new Song(id, title.trim(), albumId, duration);
        return songRepository.update(song);
    }

    @Override
    public boolean deleteSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.deleteById(id);
    }

    @Override
    public boolean archiveSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.archiveById(id);
    }

    @Override
    public boolean restoreSong(int id) {
        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.restoreById(id);
    }

    @Override
    public List<Song> searchSongs(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            System.out.println("Search keyword cannot be empty.");
            return new ArrayList<>();
        }

        return songRepository.searchByTitle(keyword.trim());
    }
}
