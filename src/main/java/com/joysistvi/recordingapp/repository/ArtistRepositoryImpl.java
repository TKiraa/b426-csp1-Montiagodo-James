package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository{

    // Constructor Injection
    // Composition -> has a relationship
    // Using Composition Artist Repository is loosely coupled to DbConnection
    private final DbConnection dbConnection;

    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Artist> findAllActive() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                artists.add(new Artist(id, name));


            }

        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + e.getMessage());
        }

        return artists;
    }

    @Override
    public List<Artist> findAllArchived() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 1";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ){

            while (resultSet.next()) {
                artists.add(new Artist(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + e.getMessage());
        }
        return artists;
    }

    @Override
    public boolean save(Artist artists) {

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artists.getName().trim());

            return preparedStatement.executeUpdate() > 0;

            //System.out.println("\nArtist: " + artists.getName().trim() + " created successfully!");

        } catch (SQLException e) {
            System.out.println("\nRepository Error (save): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Artist artist) {
        String query = "UPDATE artists SET name=? WHERE id=?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getName());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM artists WHERE id=?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Repository Error (deleteById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean archiveById(int id) {
        String query = "UPDATE artists SET is_archived=1 WHERE id=?";

        try (Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Repository Error (archiveById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean restoreById(int id) {
        String query = "UPDATE artists SET is_archived=0 WHERE id=?";

        try (Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (restoreById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Artist> searchByName(String keyword) {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 0 AND name LIKE ? ORDER BY ID ASC";

        try (Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    artists.add(new Artist(resultSet.getInt("id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByName): " + e.getMessage());
        }
        return artists;
    }
}
