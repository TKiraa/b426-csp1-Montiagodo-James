package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepository;
import java.util.ArrayList;
import java.util.List;


// Application Tier: Business Rule / Validation
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllActiveArtists() {
        return artistRepository.findAllActive();
    }

    @Override
    public List<Artist> getAllArchivedArtists() {
        return artistRepository.findAllArchived();
    }

    @Override
    public boolean createArtist(String name){
        // Guard Clause
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return false;
        }
        return artistRepository.save(new Artist(name.trim()));
    }

    @Override
    public boolean updateArtist(int id, String name) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist id is required.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Artist name is required.");
            return false;
        }
        Artist updated = new Artist(id, name.trim());
        return artistRepository.save(updated);
    }

    @Override
    public boolean deleteArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist id is required.");
            return false;
        }
        return artistRepository.deleteById(id);
    }

    @Override
    public boolean archiveArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist id is required.");
            return false;
        }
        return artistRepository.archiveById(id);
    }

    @Override
    public boolean restoreArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist id is required.");
            return false;
        }
        return artistRepository.restoreById(id);
    }

    @Override
    public List<Artist> searchArtists(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Artist name is required.");
            return null;
        }
        return artistRepository.searchByName(keyword.trim());
    }

}
